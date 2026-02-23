# Lab-Git-JSON-C36352-C37674

### Componentes Implementados

## Capa Model

La capa `model` define las entidades y enumeraciones fundamentales de la aplicación. En este caso:

- `Pet`: Clase que representa a una mascota. Contiene atributos como:
    - `name` (String)
    - `species` (enum o String)
    - `age` (int)
    - `ownerPhone` (String)
- `Species` (opcional): Enum que representa las posibles especies disponibles en la aplicación (por ejemplo, PERRO, GATO, OTRO). Su uso permite validación robusta y minimiza errores de entrada.

**Característica principal:**  
Todas las reglas de negocio relacionadas con los datos de las mascotas (validaciones, restricciones, etc.) deben colocarse preferentemente en el controller o en la vista, procurando que las entidades sean simples (POJO).

## Capa Repository

La capa `repository` se encarga de la persistencia y recuperación de datos de las mascotas utilizando archivos JSON.

- **Interfaz `PetRepository`:**
    - Define los métodos para persistir, obtener y consultar mascotas (ejemplo: `save(Pet pet)`, `findAll()`).
    - Permite desacoplar la lógica de la aplicación del mecanismo de almacenamiento.

- **Implementación principal:**  
  `JsonPetRepository`
    - Crea, si no existe, la carpeta `data/` y el archivo `data/pets.json`.
    - Se encarga de guardar y cargar los datos en formato JSON utilizando la biblioteca Jackson (configurada por Maven).
    - Mantiene la consistencia entre la memoria y el archivo.
    - Retorna listas inmutables para evitar problemas de concurrencia y asegurar el principio de inmutabilidad en la consulta de datos.

**Principios clave:**
- El Controller interactúa solo con `PetRepository`, nunca directamente con archivos ni con clases concretas de almacenamiento.
- La implementación concreta utilizada (en este caso JSON) puede intercambiarse sin afectar el resto del sistema.

## Capa Controller

La capa `controller` gestiona la lógica de la aplicación y actúa como intermediario entre la interfaz gráfica (View) y la capa de persistencia (Repository).

- **Clase principal:**  
  `PetController`
    - Maneja la lógica de validación de los datos antes de llamar al repository para guardar una mascota.
    - Expone métodos para registrar y listar mascotas.
    - Recibe, por constructor, la interfaz del Repository; de esta forma, se asegura la inyección de dependencias y el desacoplamiento.

**Responsabilidad esencial:**
- La vista nunca debe acceder directamente al Repository; toda la lógica y validación pasan por el Controller.
- El Controller expone únicamente lo necesario para que la vista muestre información o responda a eventos del usuario.

_Estas tres capas, junto con la vista, conforman la arquitectura en capas exigida: cada componente tiene responsabilidades claras y delimitadas, facilitando el mantenimiento y la escalabilidad del proyecto._

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
