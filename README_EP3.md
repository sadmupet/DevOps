# 🚀 Proyecto Microservicio Juego 2D - Ingeniería DevOps (EP3)

## 📌 # EP3: Añadiéndole complejidad a nuestro pipeline

En esta tercera evaluación parcial (**EP3**), el proyecto da un salto cualitativo al transicionar de un empaquetamiento básico en contenedores aislados hacia una arquitectura avanzada basada en **DevSecOps con Observabilidad Cloud** y **Gobernanza TI automatizada**. 

El pipeline de CI/CD ya no solo actúa como un integrador de código, sino como un **gatekeeper (salvavidas automatizado)** que restringe, valida, orquesta en Kubernetes y monitorea proactivamente la salud de la infraestructura ante cualquier despliegue simulado en producción.

---

## 🛠️ Arquitectura de la Solución

El ecosistema de esta entrega está diseñado bajo tres pilares fundamentales exigidos por la rúbrica de Duoc UC:

* **Orquestación y Alta Disponibilidad (IE2):** Migración completa desde entornos locales hacia manifiestos nativos de **Kubernetes (K8s)** para despliegues dinámicos.
* **Monitoreo, Telemetría y Logging (IE1 / IE3):** Captura proactiva de datos mediante un agente de **Prometheus** y visualización mediante un Dashboard centralizado en **Grafana**.
* **Políticas de Cumplimiento y Fail-Fast (IE5 / IE6):** Mecanismos automatizados de auditoría estática de código que interrumpen el flujo en seco si se detectan anomalías de seguridad o calidad.

---

## 🏗️ Componentes Técnicos del Repositorio

El proyecto incluye los archivos y configuraciones clave solicitados por la pauta específica de evaluación:

### 1. Pipeline CI/CD con Bloqueo Crítico (`.github/workflows/ci-cd.yml`)
* **Control de Calidad (IE5):** Incorpora una fase de auditoría que escanea el código en busca de malas prácticas, deuda técnica o vulnerabilidades.
* **Mecanismo Fail-Fast (IE6):** Si el motor de validación detecta la etiqueta crítica `FIXME` en los componentes de código fuente (`./src`), el pipeline **se detiene de manera inmediata**, retornando un código de error `exit 1` y congelando las etapas subsecuentes para blindar el entorno productivo.

### 2. Manifiestos de Orquestación Cloud (`/k8s`)
* **`deployment.yaml` (IE2):** Define el estado deseado de la aplicación levantando **2 réplicas (Pods)** simultáneas para asegurar tolerancia a fallos. Incorpora políticas estrictas de restricción de hardware:
    * `limits`: CPU máxima de `500m` (0.5 núcleos) y Memoria RAM máxima de `512Mi`.
    * `requests`: Reserva inicial de `250m` de CPU y `256Mi` de RAM.
    * **Anotaciones de Monitoreo:** Inyecta metadatos nativos para el auto-descubrimiento de Prometheus (`prometheus.io/scrape: "true"`).
* **`service.yaml`:** Abstrae el acceso a los pods mediante una IP interna estable (`ClusterIP`) balanceando el tráfico en el puerto `8080`.

### 3. Configuración de Observabilidad y Dashboards (`/monitoring`)
* **`prometheus.yml` (IE1):** Archivo de gobernanza del servidor de monitoreo configurado para realizar el raspado (*scraping*) automatizado de métricas sobre el clúster de Kubernetes en intervalos estrictos de 15 segundos.
* **`dashboard-config.json` (IE3):** Configuración declarativa (Infrastructure as Code) del panel de **Grafana**. Mapea gráficamente las tres métricas clave esenciales para la toma de decisiones:
    1.  *Consumo de CPU por Pod* frente al límite de 0.5.
    2.  *Uso de Memoria RAM activa* en comparación con el umbral de 512MB.
    3.  *Tasa de Disponibilidad y Errores HTTP (Códigos 5xx)* para detección temprana de anomalías.

---

## 📊 Impacto en la Toma de Decisiones Técnicas (IE4)

La implementación de este ecosistema de observabilidad permite al equipo de ingeniería mitigar riesgos operativos mediante métricas empíricas:
1.  **Escalabilidad Horizontal Basada en Datos:** Si el panel de Grafana indica que el uso de memoria RAM por Pod supera reiteradamente el 85% del límite asignado (`512Mi`), se toma la decisión técnica informada de realizar un escalado horizontal modificando el parámetro `replicas` a 3 o 4 instancias.
2.  **Gobernanza Operacional de Errores:** El monitoreo en tiempo real de la tasa de fallas HTTP (5xx) gatilla alarmas que permiten al equipo de operaciones aplicar rollbacks inmediatos o parches calientes antes de comprometer la experiencia global del usuario.

---

## 🤖 Declaración de Uso Ético de Inteligencia Artificial

Dando estricto cumplimiento a la normativa de honestidad académica estipulada en la página 3 de la pauta de Duoc UC, el equipo declara formalmente el uso de herramientas de asistencia técnica bajo el siguiente desglose:

| Herramienta de IA | Forma de Aplicación en el Proyecto | Validación e Inspección del Equipo |
| :--- | :--- | :--- |
| **Modelos de Lenguaje Avanzados** | Soporte técnico en el diseño de sintaxis de los manifiestos YAML de Kubernetes y estructuración de los arreglos JSON de Grafana. | El equipo auditó manualmente cada línea de código, garantizando que los puertos (`8080`), namespaces y límites de hardware (`512Mi` / `500m`) respondan fielmente a los requerimientos de la rúbrica. |