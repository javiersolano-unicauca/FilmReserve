# API
En este apartado se encuentra detalladamente la documentacion para realizar las peticiones a los 'endpoint' del servicio web.

## USUARIOS
En esta seccion se establecen los 'endpoint' para la gestion de usuarios.

- INICIAR SESION

    - Inicio de sesion de cualquier usuario
        - Enpoint: `/api/v1/login`
        - Method: `POST`
        - Body:
        ```
            {
                "identification": Long,
                "password": String
            }
        ```

    - Actualizar contraseña
        - Endpoint: `/api/v1/login`
        - Method: `PUT`
        - Body:
        ```
            {
                "identification": Long,
                "newPassword": String
            }
        ```

- ADMINISTRADOR

    - Obtener por identificacion
        - Endpoint: `/api/v1/administrator/{identification}`
        - Method: `GET`

    - Registro
      - Endpoint: `/api/v1/administrator`
      - Method: `POST`
      - Body:
      ```
        {
            "identification": Long,
            "firstName": String,
            "secondName": String [Optional],
            "firstSurname": String,
            "secondSurname": String [Optional],
            "password": String
        }
      ```
    
    - Eliminar por identificacion
        - Endpoint: `/api/v1/administrator/{identification}`
        - Method: `DELETE`

- CLIENTE

    - Obtener por identificacion
        - Endpoint: `/api/v1/customer/{identification}`
        - Method: `GET`

    - Registro
      - Endpoint: `/api/v1/customer`
      - Method: `POST`
      - Body:
      ```
        {
            "identification": Long,
            "firstName": String,
            "secondName": String [Optional],
            "firstSurname": String,
            "secondSurname": String [Optional],
            "password": String,
            "day": Integer,
            "month": Integer,
            "year": Integer,
            "phone": Long
        }
      ```
    
    - Eliminar por identificacion
        - Endpoint: `/api/v1/customer/{identification}`
        - Method: `DELETE`

- TAQUILLERO    

    - Obtener por identificacion
        - Endpoint: `/api/v1/ticket-seller/{identification}`
        - Method: `GET`

    - Registro
      - Endpoint: `/api/v1/ticket-seller`
      - Method: `POST`
      - Body:
      ```
        {
            "identification": Long,
            "firstName": String,
            "secondName": String [Optional],
            "firstSurname": String,
            "secondSurname": String [Optional],
            "password": String,
            "turn": String
        }
      ```
    
    - Eliminar por identificacion
        - Endpoint: `/api/v1/ticket-seller/{identification}`
        - Method: `DELETE`

    - Actualizar turno
        - Endpoint: `/api/v1/ticket-seller`
        - Method: `PUT`
        - Body:
        ```
            {
                "identification": Long,
                "turn": String
            }
        ```