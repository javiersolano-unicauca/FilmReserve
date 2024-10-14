# API
En este apartado se encuentra detalladamente la documentacion para realizar las peticiones a los 'endpoint' del servicio web.

## USUARIOS
En esta seccion se establecen los 'endpoint' para la gestion de usuarios.

- INICIAR SESION

    - Inicio de sesion de cualquier usuario
        - Enpoint: `/api/v2/login`
        - Method: `POST`
        - Body:
        ```
            {
                "identification": Long,
                "password": String
            }
        ```

    - Actualizar contraseña
        - Endpoint: `/api/v2/login`
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
        - Endpoint: `/api/v2/administrator/{identification}`
        - Method: `GET`

    - Registro
      - Endpoint: `/api/v2/administrator/save`
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
        - Endpoint: `/api/v2/administrator/{identification}`
        - Method: `DELETE`

- CLIENTE

    - Obtener por identificacion
        - Endpoint: `/api/v2/customer/{identification}`
        - Method: `GET`

    - Registro
      - Endpoint: `/api/v2/customer/save`
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
        - Endpoint: `/api/v2/customer/{identification}`
        - Method: `DELETE`

- TAQUILLERO    

    - Obtener por identificacion
        - Endpoint: `/api/v2/ticket-seller/{identification}`
        - Method: `GET`

    - Registro
      - Endpoint: `/api/v2/ticket-seller/save`
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
        - Endpoint: `/api/v2/ticket-seller/{identification}`
        - Method: `DELETE`

    - Actualizar turno
        - Endpoint: `/api/v2/ticket-seller`
        - Method: `PUT`
        - Body:
        ```
            {
                "identification": Long,
                "turn": String
            }
        ```

## CARTELERA
En esta seccion se establecen los 'endpoint' para la gestion de cartelera.

- PELICULAS

    - Obtener por identificacion
        - Enpoint: `/api/v2/movie/{idMovie}`
        - Method: `GET`

    - Obtener todo
        - Enpoint: `/api/v2/movie/all`
        - Method: `GET`

    - Registro
        - Endpoint: `/api/v2/movie/save`
        - Method: `POST`
        - Body:
        ```
            {
                "idMovie": Long,
                "name": String,
                "sypnosis": String,
                "poster": File,
                "listGenders": String -> [gender1, gender2, gender3, ..., gender10]
            }
        ```
    - Eliminar por identificacion
        - Endpoint: `/api/v2/movie/{idMovie}`
        - Method: `DELETE`

- SALAS DE CINE

    - Registro
        - Endpoint: `/api/v2/cinema-room/save`
        - Method: `POST`
        - Body:
        ```
            {
                "idCinemaRoom": Long
            }
        ```

- FUNCIONES DE CINE

    - Obtener por identificacion
        - Enpoint: `/api/v2/movie-function/{idMovie}/{idCinemaRoom}/{startDate}`
        - Method: `GET`

    - Obtener todo
        - Enpoint: `/api/v2/movie-function/all`
        - Method: `GET`

    - Registro
        - Endpoint: `/api/v2/movie-function/save`
        - Method: `POST`
        - Body:
        ```
            {
                "idMovie": Long,
                "idCinemaRoom": Long,
                "startDate": Date [yyyy-mm-dd],
                "endDate": Date [yyyy-mm-dd],
                "startTime": Time [hh:mm],
                "endTime": Time [hh:mm]
            }
        ```

    - Eliminar por identificacion
        - Endpoint: `/api/v2/movie-function/{idMovie}/{idCinemaRoom}/{startDate}`
        - Method: `DELETE`