# Proyecto Microservicio Juego 2D - Ingeniería DevOps

Evaluación Final Transversal - DOY0101

Automatización del ciclo de vida completo de una aplicación Java (juego 2D), aplicando prácticas DevOps: control de versiones, contenerización, pipeline CI/CD, análisis de calidad/seguridad, despliegue en Kubernetes, malla de servicios con Istio y monitoreo con AWS CloudWatch.

## Estrategia de ramificación

- `master`: rama principal, protegida, siempre desplegable.
- `feature/*`: nuevas funcionalidades, se integran vía Pull Request.
- `hotfix/*`: correcciones urgentes.

## Contenerización

`Dockerfile` multi-stage: compila con Maven (`eclipse-temurin-17`) y ejecuta en una imagen liviana JRE.

```bash
docker build -t juego2d:local .
```

## Pipeline CI/CD (`.github/workflows/ci-cd.yml`)

Se dispara en cada push/PR a `main`. Tres jobs secuenciales:

1. **calidad-y-seguridad**: bloquea el pipeline si detecta `FIXME` en el código, corre análisis estático con SonarQube.
2. **prueba-aceptacion**: compila y ejecuta pruebas unitarias (`mvn clean verify`) como gate obligatorio antes del despliegue.
3. **despliegue-orquestado**: construye la imagen Docker y valida los manifiestos de Kubernetes offline con `kubeconform`. El despliegue real hacia AWS EKS está documentado en el pipeline (build → push ECR → kubectl apply); se validó de forma funcional contra un clúster Kubernetes local (Kind/Docker Desktop).

## Seguridad

- SonarQube: análisis estático de código en cada ejecución.
- Dependabot: escaneo de dependencias vulnerables.
- Gate de calidad: el pipeline se detiene ante código con pendientes críticos sin resolver.

## Kubernetes

- `k8s/namespace.yaml`: namespace `devops-ep3` con `istio-injection: enabled`.
- `k8s/deployment.yaml`: 2 réplicas, límites de 500m CPU / 512Mi memoria.
- `k8s/service.yaml`: expone el pod vía `ClusterIP` en el puerto 8080.

```bash
kubectl apply -f k8s/namespace.yaml
kubectl apply -f k8s/deployment.yaml
kubectl apply -f k8s/service.yaml
kubectl get pods -n devops-ep3
```

## Istio

```bash
istioctl install --set profile=demo -y
kubectl label namespace devops-ep3 istio-injection=enabled --overwrite
kubectl apply -f k8s/istio-gateway.yaml
```

`k8s/istio-gateway.yaml` define el Gateway y VirtualService que exponen el servicio a través del Istio Ingress Gateway.

## Monitoreo (AWS CloudWatch)

- `monitoring/cloudwatch-agent-config.json`: recolecta `cpu_usage_active` y `mem_used_percent` cada 60 segundos.
- `monitoring/cloudwatch-alerts.json`: alarma cuando el uso de CPU supera 80% en 5 minutos, notifica vía SNS.

## Despliegue seguro y continuo

- Pruebas de aceptación como gate previo al despliegue.
- Aprobación manual mediante GitHub Environment `production` con Required reviewers.
- Trazabilidad: cada despliegue queda vinculado al SHA del commit.

## Uso de Inteligencia Artificial

Se utilizó Claude (Anthropic) como apoyo en la sintaxis de manifiestos Kubernetes, configuración de Istio y CloudWatch, estructura del pipeline de GitHub Actions, y resolución de errores de configuración durante la integración. Todo contenido generado fue validado y ejecutado por el equipo contra un clúster Kubernetes local antes de su incorporación al proyecto.

## Equipo

Cristobal Reyes, Nathan Gutierrez

## Ejecución local

```bash
docker build -t juego2d:local .
kind create cluster --name devops-ep3
kind load docker-image juego2d:local --name devops-ep3
kubectl apply -f k8s/namespace.yaml
kubectl apply -f k8s/deployment.yaml
kubectl apply -f k8s/service.yaml
kubectl get pods -n devops-ep3
```
