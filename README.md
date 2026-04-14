# DevOps
# EP1: Implementación de Pipeline de Integración Continua

## 1. Descripción del Proyecto
Este repositorio contiene un **Videojuego 2D desarrollado en Java** El proyecto ha sido estructurado siguiendo estándares de cultura DevOps, preparando el entorno para la integración continua (CI) y asegurando la colaboración eficiente mediante un modelo de ramificación controlado.
**Tecnologías:** Java (Swing/AWT), Git, GitHub Actions.


## 2. Estrategia de Ramificación: GitFlow
Se ha implementado el modelo de trabajo **GitFlow** para gestionar el ciclo de vida del desarrollo del videojuego
<img width="938" height="768" alt="image" src="https://github.com/user-attachments/assets/08490ae3-7f67-49cc-b17f-53f9271cbf66" />

En esta captura hicimos la creación de las ramas siguiendo el modelo GitFlow y el uso de Conventional Commits (feat, fix) para mantener la trazabilidad del desarrollo del juego 2D.

### Justificación Técnica:
Se eligió GitFlow porque el proyecto requiere una separación clara entre el código en desarrollo y el código estable. Al trabajar en parejas, esta estrategia permite aislar funcionalidades en ramas `feature/` y manejar correcciones de errores en ramas `hotfix/`.

## 3. Guía de Buenas Prácticas

### Mensajes de Commit (Conventional Commits)
Seguimos el estándar para mantener un historial de cambios legible:
- `feat:` para nuevas funcionalidades (ej: agregar movimiento).
- `fix:` para corrección de errores (ej: corregir colisiones).
- `docs:` cambios en la documentación.

### Estructura del Proyecto
- `src/main/`: Contiene el código fuente (`Main.java`, `Panel.java`, `KeyHand.java`).
- `bin/`: Archivos compilados `.class`.
- `.github/workflows/`: Configuración de la automatización.

## 3. Guía de Buenas Prácticas

### Mensajes de Commit (Conventional Commits)
Seguimos el estándar para mantener un historial de cambios legible:
- `feat:` para nuevas funcionalidades (ej: agregar movimiento).
- `fix:` para corrección de errores (ej: corregir colisiones).
- `docs:` cambios en la documentación.

### Estructura del Proyecto
- `src/main/`: Contiene el código fuente (`Main.java`, `Panel.java`, `KeyHand.java`).
- `bin/`: Archivos compilados `.class`.
- `.github/workflows/`: Configuración de la automatización.

---

## 4. Automatización con GitHub Actions (CI)
Se implementó un pipeline de Integración Continua (`main.yml`) que automatiza la compilación del código Java.

**Disparadores:**
- **Push en `develop`:** Valida la estabilidad de la rama de integración.
- **Pull Request hacia `main`:** Filtro de calidad antes de pasar a producción.

**Pasos del Pipeline:**
1. Checkout del código.
2. Configuración de JDK 17.
3. Compilación de archivos `.java` mediante `javac`.

---

## 5. Declaración de Uso de IA
Se utilizó **IA (Gemini/Claude)** para:
- Estructurar la documentación técnica del README.
- Validar la sintaxis del archivo YAML para el pipeline.
- Generar ejemplos de comandos Git para el flujo de trabajo.

---

Automatización con GitHub Actions (CI)
"Para garantizar la calidad del software, se configuró un pipeline de Integración Continua que se dispara automáticamente ante cada push en la rama develop. El pipeline realiza las siguientes tareas: checkout del código, configuración del entorno Java JDK 17 y la compilación de los archivos fuente."

<img width="887" height="727" alt="Captura de pantalla 2026-04-14 181327" src="https://github.com/user-attachments/assets/9bfaa4d3-3d2b-46fa-b3a0-3d57200ed8f7" />

La imagen superior muestra el flujo de trabajo denominado Pipeline_EP1_DevOps ejecutado con éxito. El check verde confirma que el código presente en el repositorio es estable y compila correctamente bajo los estándares definidos en el archivo main.yml

## 6. Reflexión Individual

### [NATHAN GUTIERREZ]:
"Durante esta actividad, comprendí la importancia de GitFlow para mantener el orden. El uso de ramas permite fallar y corregir sin afectar al usuario final en la rama main. La automatización con GitHub Actions es clave para asegurar que el código siempre compile."

### [CRISTOBAL REYES]:
"Mi participación se centró en la lógica del videojuego y la organización del repositorio bajo el modelo GitFlow y Lograr que el pipeline de GitHub Actions compilara correctamente nuestro código en Java fue el mayor desafío y aprendizaje de esta etapa.."
