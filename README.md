# app-gimnasio

Aplicación en Java para gestionar rutinas de gimnasio y calcular calorías, creada con el objetivo de aprender y poner en práctica lo visto en clase (programación en Java, ficheros y patrón DAO).

## Qué hace

- Crea un perfil de usuario (altura y peso) la primera vez que se ejecuta.
- Permite crear una rutina por día, con tantos ejercicios como se quiera (nombre, series, repeticiones, peso y descanso entre series).
- Consulta la rutina de cualquier día guardado.
- Calcula de forma aproximada las calorías quemadas en el día, sumando el entrenamiento (según la duración y las series de la rutina) y los pasos diarios.
- Guarda los datos automáticamente, así que siguen ahí al volver a abrir la aplicación.

## Tecnologías

- Java 21
- Eclipse
- Persistencia en ficheros de texto
- Git y GitHub

## Cómo ejecutarla

1. Clonar el repositorio o descargarlo.
2. Abrirlo como proyecto en Eclipse.
3. Comprobar que existe la carpeta `config/` con el archivo `application.properties` en la raíz del proyecto.
4. Ejecutar la clase `gymApp.Main`. La aplicación funciona con un menú por consola:

```
----- Menu Principal -----
1 - Crear Rutina y añadir ejercicios
2 - Calcular calorias quemadas hoy(pasos + entreno)
3 - Ver perfil de usuario
4 - Ver rutina de un dia
5 - Salir
```

Al ejecutarla por primera vez se crean los ficheros `usuario.txt` y `rutinas.txt` en la raíz del proyecto.

## Estructura del proyecto

El proyecto sigue el patrón DAO para separar la lógica de la aplicación del acceso a los datos:

```
src/gymApp/
├── Main.java            Menú por consola
├── model/               Clases del dominio
│   ├── Usuario.java
│   ├── Rutina.java
│   └── Ejercicio.java
├── dao/                 Interfaces de acceso a datos
│   ├── IUsuarioDAO.java
│   └── IRutinaDAO.java
├── dao/impl/            Implementaciones con ficheros de texto
│   ├── UsuarioDAO.java
│   └── RutinaDAO.java
└── config/
    └── Configuracion.java   Lectura de application.properties
config/
└── application.properties
```

`Main` solo conoce las interfaces `IUsuarioDAO` e `IRutinaDAO`. Así, cambiar la forma de guardar los datos (por ejemplo, a una base de datos) solo obliga a escribir una nueva implementación, sin tocar el resto de la aplicación.

## Configuración

Las rutas de los ficheros de datos y el separador se leen del archivo `config/application.properties`:

```
ruta.usuario=usuario.txt
ruta.rutinas=rutinas.txt
separador=;
```

Para cambiar el nombre de un fichero o el separador basta con editar ese archivo, sin modificar el código.

## Cambios a futuro

- Sustituir los ficheros por una base de datos, añadiendo una nueva implementación de los DAO.
- Añadir al menú las opciones de modificar y eliminar rutinas (el DAO ya permite eliminar).
- Mejorar el cálculo de calorías para que tenga en cuenta el peso del usuario.
- Controlar los datos que introduce el usuario, para que la aplicación no falle si se escribe texto donde se espera un número.
- Convertirla en una aplicación de escritorio.
- Convertirla en una aplicación móvil.
- Mejorar la interfaz.
- Añadir más funciones para hacerla más práctica y útil.
