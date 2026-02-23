# Lab-Git-JSON-C36352-C37674

### Componentes Implementados

///////////////////////////////////Eduardo///////////////////////////

## Vistas Swing (Capa View)

Se implementaron dos vistas utilizando Swing:

- `PetFormView`
- `PetListView`

### PetFormView

Esta clase representa la interfaz principal de la aplicación. Contiene:

- Cuatro campos de entrada:
    - `name`
    - `species` (JComboBox)
    - `age`
    - `ownerPhone`
- Un botón **Save** para registrar una mascota.
- Un botón **List Pets** para visualizar las mascotas registradas.
- Retroalimentación al usuario mediante `JOptionPane`.

La vista cumple estrictamente con las reglas de arquitectura MVC:

- Recibe una instancia de `PetController` por constructor (inyección de dependencias).
- No accede directamente al Repository.
- Delegó toda la lógica de negocio al Controller.

### PetListView

Esta clase representa una ventana secundaria que muestra las mascotas registradas en una tabla (`JTable`).

- Recibe una lista de objetos `Pet` desde el Controller.
- Se encarga únicamente de la visualización de datos.
- No contiene lógica de negocio ni acceso a persistencia.

## Implementación del Repository JSON

Se implementó la clase:

- `JsonPetRepository`

Esta clase implementa la interfaz `PetRepository` y es responsable de:

- Crear la carpeta `data/` si no existe.
- Crear el archivo `data/pets.json` si no existe.
- Guardar registros en formato JSON.
- Cargar registros desde el archivo JSON al iniciar la aplicación.
- Mantener consistencia entre memoria y archivo.
- Retornar listas inmutables al consultar los datos almacenados.

Para la serialización y deserialización se utilizó la librería **Jackson**, configurada mediante Maven.

## Integración UI → Controller

La integración entre capas se realizó en la clase `Main`:

1. Se crea una instancia de `JsonPetRepository`.
2. Se inyecta el Repository en el `PetController`.
3. Se inyecta el Controller en la `PetFormView`.

Esto garantiza:

- Inyección de dependencias correcta.
- Separación de responsabilidades.
- Cumplimiento de la arquitectura por capas requerida.
- Que la View no dependa directamente del Repository.

## Cumplimiento de la Arquitectura

La solución respeta la arquitectura obligatoria del laboratorio:

- `view` → Código exclusivo de interfaz gráfica (Swing).
- `controller` → Lógica de aplicación.
- `repository` → Persistencia en JSON (interfaz + implementación).
- `model` → Entidades y enums.

Reglas respetadas:

- La View recibe el Controller por constructor.
- El Controller recibe la interfaz del Repository por constructor.
- La View nunca accede directamente al Repository.
- Se utiliza implementación concreta del Repository mediante inyección.

## Pruebas Manuales Realizadas

Se realizaron las siguientes pruebas:

1. Registrar múltiples mascotas desde la interfaz.
2. Verificar la creación automática del archivo `data/pets.json`.
3. Cerrar la aplicación.
4. Volver a abrir la aplicación.
5. Confirmar que los registros previamente guardados se cargan correctamente desde el archivo JSON.
