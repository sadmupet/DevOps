# DevOps
# EP2: Añadiéndole complejidad a nuestro pipeline

## 1. Descripción del Proyecto
Este repositorio contiene un **Videojuego 2D desarrollado en Java**, extendido con un pipeline CI/CD completo que automatiza la integración, pruebas, análisis de seguridad y despliegue del microservicio mediante contenedores.

**Tecnologías:** Java (Swing/AWT), Maven, Docker, Docker Compose, GitHub Actions, Snyk, JaCoCo.

---

## 2. Estrategia de Ramificación: GitFlow
Se mantiene el modelo de trabajo **GitFlow** para gestionar el ciclo de vida del desarrollo.

### Justificación Técnica:
Se eligió GitFlow porque permite separar claramente el código en desarrollo del código estable. Las ramas `feature/` aíslan funcionalidades y las ramas `hotfix/` permiten correcciones urgentes sin afectar la rama principal.

---

## 3. Estructura del Proyecto
- `src/main/java/main/`: Código fuente (`Main.java`, `Panel.java`, `KeyHand.java`).
- `test/main/`: Pruebas unitarias (`KeyHandTest.java`).
- `Dockerfile`: Imagen Docker multi-stage del microservicio.
- `docker-compose.yml`: Orquestación de contenedores con límites de recursos.
- `.github/workflows/ci-cd.yml`: Pipeline CI/CD unificado.
- `.github/dependabot.yml`: Escaneo automático semanal de dependencias.

---

## 4. Pipeline CI/CD Unificado

Se implementó un pipeline unificado (`ci-cd.yml`) en GitHub Actions que automatiza completamente el ciclo de vida del microservicio.

**Disparadores:**
- **Push en `master` o `main`:** Ejecuta el pipeline completo.
- **Pull Request hacia `master` o `main`:** Filtro de calidad antes de pasar a producción.

**Pasos del Pipeline:**
1. Checkout del código.
2. Configuración de Docker Buildx.
3. Configuración de JDK 17.
4. Cache de dependencias Maven.
5. **Construcción de imagen Docker (IE1):** `docker compose build`.
6. **Pruebas unitarias con JaCoCo (IE2):** `mvn clean verify`, genera reporte de cobertura.
7. **Análisis de vulnerabilidades con Snyk (IE3):** Escaneo de dependencias Maven.
8. **Análisis de calidad con SonarCloud (IE3):** Análisis estático del código.
9. **Despliegue simulado con Docker Compose (IE4 + IE5):** Levanta, verifica y apaga el entorno.

---

## 5. Trazabilidad y Calidad

| Etapa | Herramienta | Garantía |
|---|---|---|
| Build | Docker + Maven | Imagen construida desde código fuente |
| Tests | JUnit 5 + JaCoCo | Reporte de cobertura por ejecución |
| Seguridad | Snyk + Dependabot | Detección de vulnerabilidades en dependencias |
| Calidad | SonarCloud | Análisis estático del código |
| Deploy | Docker Compose | Entorno orquestado con healthcheck |

Cada ejecución del pipeline queda registrada en GitHub Actions con su SHA de commit, permitiendo rastrear exactamente qué código fue desplegado y en qué estado pasó cada etapa.

---

## 6. Orquestación de Contenedores (IE5)

El archivo `docker-compose.yml` define la orquestación:
- **`db`**: MySQL 8.0 con healthcheck activo.
- **`juegazo-app`**: Imagen construida desde Dockerfile multi-stage, con límites de recursos (`0.5 CPU`, `512MB RAM`).
- La dependencia `depends_on` con `condition: service_healthy` garantiza el orden correcto de arranque.

---

## 7. Declaración de Uso de IA
Se utilizó **IA (Claude)** para:
- Estructurar la documentación técnica del README.
- Validar la sintaxis del archivo YAML del pipeline.
- Revisar el Dockerfile y docker-compose.yml.

Todas las decisiones técnicas fueron revisadas y validadas por el equipo.

EVIDENCIAS DEL PIPELINE
<img width="1421" height="425" alt="Captura de pantalla 2026-05-26 181318" src="https://github.com/user-attachments/assets/d93f4dc1-fa6b-4f42-bf09-dee55b3f23bf" />


---

## 8. Reflexión Individual

### [NATHAN GUTIERREZ]:
*Lo más complejo y de valor por asi decirlo de esta EP2 fue aprender a resolver problemas bajo presión. Al principio el pipeline en GitHub Actions fallaba por errores del compilador en la nube y por rutas de carpetas mal mapeadas, pero pasar todo a Docker pude entender mas de DevOps: asegurar que el código funcione igual en mi PC que en un servidor. Configurar Snyk para seguridad y Docker Compose para limitar los recursos me dio una visión real de cómo se despliega software hoy en día en la industria.*

### [CRISTOBAL REYES]:
*Este proyecto me enseñó que la Integración Continua (CI/CD) es un ayuda y no un error. Integrar JaCoCo para pruebas y Snyk para buscar vulnerabilidades deja en claro que la seguridad se automatiza desde el primer momento que pruebas todo. Aunque nos costó adaptar el juego a los contenedores por compatibilidad, lograr la orquestación con Docker Compose limitando la CPU y memoria según la rúbrica fue clave para entender la gobernanza de TI real.*
