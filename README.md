# 🚀 Proyecto Microservicio Juego 2D - Ingeniería DevOps (EP3)

## 📌 # EP3: Añadiéndole complejidad a nuestro pipeline

En esta tercera evaluación parcial (**EP3**), el proyecto evoluciona desde un entorno de contenedores locales hacia una arquitectura cloud avanzada bajo un enfoque de **DevSecOps con Observabilidad Cloud** y **Gobernanza de Mallas de Servicios (Service Mesh)**, alineada con los estándares de Amazon Web Services (AWS) e Istio.

El pipeline de CI/CD actúa como un **gatekeeper automatizado** que restringe, valida, orquesta y prepara la infraestructura para un entorno altamente disponible y monitoreado proactivamente.

---

## 🛠️ Arquitectura de la Solución (Ecosistema Cloud)

El ecosistema de monitoreo y despliegue está diseñado bajo los pilares fundamentales oficiales de la unidad:

* **Observabilidad en la Nube (Amazon CloudWatch):** Captura de métricas de infraestructura (CPU y Memoria RAM) mediante el agente de CloudWatch configurado a través de AWS Systems Manager (SSM).
* **Alertas Proactivas (AWS SNS):** Umbrales automatizados en CloudWatch que gatillan notificaciones inmediatas mediante un tópico de AWS Simple Notification Service ante sobrecargas en el microservicio.
* **Malla de Servicios (Istio Service Mesh):** Implementación sobre un clúster de Amazon EKS para gestionar la comunicación interna, el balanceo de tráfico y la seguridad mediante proxies sidecar (Envoy).
* **Monitoreo Visual de Microservicios (Kiali):** Capa de observabilidad gráfica sobre Istio para analizar la topología de la red, flujos de tráfico y tasa de errores HTTP en tiempo real.

---

## 🏗️ Componentes Técnicos del Repositorio

### 1. Pipeline CI/CD con Bloqueo Crítico (`.github/workflows/ci-cd.yml`)
* **Mecanismo Fail-Fast:** Incorpora una fase de auditoría estática. Si el motor de validación detecta malas prácticas o la etiqueta crítica `FIXME` en el código fuente, el pipeline se detiene de inmediato (`exit 1`), deteniendo el empaquetamiento para proteger el entorno en la nube.

### 2. Configuración de Monitoreo Cloud (`/monitoring`)
* **`cloudwatch-agent-config.json`:** Archivo de configuración que define las dimensiones y métricas personalizadas a recolectar (como `cpu_usage_active` y `mem_used_percent`) con intervalos de recolección de 60 segundos mediante el agente unificado de CloudWatch.
* **`cloudwatch-alerts.json`:** Definición de una alarma automatizada que se activa cuando el uso medio de CPU supera el 80% en un período de 5 minutos, enviando la alerta al ARN de un tópico en **AWS SNS**.

### 3. Manifiestos de Orquestación e Inyección Istio (`/k8s`)
* **`deployment.yaml`:** Define el despliegue del microservicio en el namespace `devops-ep3` incorporando la etiqueta obligatoria `istio-injection: enabled`. Esto automatiza la inserción del proxy sidecar Envoy para que Istio y Kiali capturen el flujo. Estructura el software con **2 réplicas** y límites de hardware de `512Mi` de memoria RAM y `500m` de CPU.
* **`service.yaml`:** Expone el juego internamente a la malla mediante un `ClusterIP` en el puerto `8080`.

---
<img width="1279" height="299" alt="image" src="https://github.com/user-attachments/assets/519370f3-0324-4c24-89b9-80683543b984" />


## 📊 Impacto en la Toma de Decisiones Técnicas

La adopción de telemetría e infraestructura cloud permite al equipo de operaciones DevOps tomar decisiones basadas en datos empíricos:
1. **Políticas de Auto-Scaling (CloudWatch + SNS):** Al gatillarse alertas de CPU excedido por notificaciones SMS o de correo de AWS SNS, el equipo puede programar políticas automáticas de escalado en Amazon EKS para levantar más réplicas y estabilizar el sistema.
2. **Optimización de Tráfico (Istio + Kiali):** A través del mapa topológico de Kiali, el equipo puede visualizar cuellos de botella en la comunicación entre pods o incrementos en las respuestas HTTP de error (códigos 5xx), aislando fallas en versiones específicas de la app mediante reglas de enrutamiento.

---

## 🤖 Declaración de Uso Ético de Inteligencia Artificial

Cumpliendo con las normativas académicas de Duoc UC, se declara el uso asistido de herramientas de IA:

| Herramienta de IA | Forma de Aplicación en el Proyecto | Validación e Inspección del Equipo |
| :--- | :--- | :--- |
| **Modelos de Lenguaje** | Apoyo en la sintaxis de las directrices JSON del agente unificado de CloudWatch y validación de las etiquetas de inyección de Istio en Kubernetes. | El equipo auditó manualmente que los parámetros, recursos máximos de hardware (512MB RAM) y tópicos ARN se adaptaran fielmente a las especificaciones de la rúbrica. |

