# API
En este apartado se encuentra detalladamente la documentacion para realizar las peticiones a los 'endpoint' del servicio web.

## USUARIOS
En esta seccion se establecen los 'endpoint' para la gestion de usuarios.

- INICIAR SESION

    - Inicio de sesion de cualquier usuario
        - Enpoint: `/api/v4/login`
        - Method: `POST`
        - Body:
        ```
            {
                "identification": Long,
                "password": String
            }
        ```

    - Actualizar contraseña
        - Endpoint: `/api/v4/login`
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
        - Endpoint: `/api/v4/administrator/{identification}`
        - Method: `GET`

    - Registro
      - Endpoint: `/api/v4/administrator/save`
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
            "avatarImage": File [Optional]
        }
      ```
    
    - Eliminar por identificacion
        - Endpoint: `/api/v4/administrator/{identification}`
        - Method: `DELETE`

- CLIENTE

    - Obtener por identificacion
        - Endpoint: `/api/v4/customer/{identification}`
        - Method: `GET`

    - Registro
      - Endpoint: `/api/v4/customer/save`
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
            "phone": Long,
            "avatarImage": File [Optional]
        }
      ```
    
    - Eliminar por identificacion
        - Endpoint: `/api/v4/customer/{identification}`
        - Method: `DELETE`

- MEMBRESIAS DE CLIENTE

    - Obtener por identificacion
        - Endpoint: `/api/v4/membership/{identification}/{startDate}`
        - Method: `GET`

    - Obtener membresias de un cliente
        - Endpoint: `/api/v4/membership/customer/{identification}`
        - Method: `GET`

    - Obtener la membresia activa de un cliente
        - Endpoint: `/api/v4/membership/active/{identification}`
        - Method: `GET`
    
    - Finalizar membresia
        - Endpoint: `/api/v4/membership/end`
        - Method: `PUT`
        - Body:
        ```
            {
                "identification": Long -> Identificacion del cliente,
                "startDate": Date [yyyy-mm-dd]
            }
        ```

    - Eliminar por identificacion
        - Endpoint: `/api/v4/membership/{identification}/{startDate}`
        - Method: `DELETE` 

- TAQUILLERO    

    - Obtener por identificacion
        - Endpoint: `/api/v4/ticket-seller/{identification}`
        - Method: `GET`

    - Registro
      - Endpoint: `/api/v4/ticket-seller/save`
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
            "turn": String,
            "avatarImage": File [Optional]
        }
      ```
    
    - Eliminar por identificacion
        - Endpoint: `/api/v4/ticket-seller/{identification}`
        - Method: `DELETE`

    - Actualizar turno
        - Endpoint: `/api/v4/ticket-seller`
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
        - Enpoint: `/api/v4/movie/{idMovie}`
        - Method: `GET`

    - Obtener todo
        - Enpoint: `/api/v4/movie/all`
        - Method: `GET`

    - Registro
        - Endpoint: `/api/v4/movie/save`
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
        - Endpoint: `/api/v4/movie/{idMovie}`
        - Method: `DELETE`

- ASIENTOS

    - Obtener por sala de cine
        - Enpoint: `/api/v4/seat/{cinemaRoom}`
        - Method: `GET`        

    - Registro
        - Endpoint: `/api/v4/seat/save`
        - Method: `POST`
        - Body:
        ```
            {
                "cinemaRoom": Integer,
                "row": Character,
                "numColumn": Integer
            }
        ```

    - Eliminar por identificacion
        - Endpoint: `/api/v4/seat/{cinemaRoom}/{row}/{numColumn}`
        - Method: `DELETE`

- FUNCIONES DE CINE

    - Obtener por identificacion
        - Enpoint: `/api/v4/function/{idMovie}/{startDate}/{startTime}`
        - Method: `GET`

    - Obtener todo
        - Enpoint: `/api/v4/function/all`
        - Method: `GET`

    - Registro
        - Endpoint: `/api/v4/function/save`
        - Method: `POST`
        - Body:
        ```
            {
                "idMovie": Long,
                "startDate": Date [yyyy-mm-dd],
                "startTime": Time [hh:mm],
                "endTime": Time [hh:mm]
            }
        ```

    - Eliminar por identificacion
        - Endpoint: `/api/v4/function/{idMovie}/{startDate}/{startTime}`
        - Method: `DELETE`

- RESERVAS DE CINE

    - Obtener una funcion reservada por sala
        - Enpoint: `/api/v4/function-reserve/{idMovie}/{startDate}/{startTime}/{cinemaRoom}`
        - Method: `GET`

    - Obtener asientos de la sala reservada en una funcion:
        - Enpoint: `/api/v4/function-reserve/seats-of-cinemaroom/{idMovie}/{startDate}/{startTime}/{cinemaRoom}`
        - Method: `GET`
    
    - Obtener las salas reservadas en una funcion con sus respectivos asientos:
        - Enpoint: `/api/v4/function-reserve/seats-of-function/{idMovie}/{startDate}/{startTime}`
        - Method: `GET`

    - Registro de la reserva de una sala en una funcion
        - Endpoint: `/api/v4/function-reserve/save`
        - Method: `POST`
        - Body:
        ```
            {
                "idMovie": Long,
                "startDate": Date [yyyy-mm-dd],
                "startTime": Time [hh:mm],
                "endTime": Time [hh:mm],
                "cinemaRoom": Integer
            }
        ```

    - Eliminar la reserva de una sala en una funcion
        - Endpoint: `/api/v4/function-reserve/{idMovie}/{startDate}/{startTime}/{cinemaRoom}`
        - Method: `DELETE`  

- COMPRAS DE TIQUETES

    - Obtener por identificacion
        - Enpoint: `/api/v4/purchase-ticket/{identification}/{idMovie}/{cinemaRoom}/{startDate}/{startTime}/{referenceId}`
        - Method: `GET`

    - Eliminar por identificacion
        - Endpoint: `/api/v4/purchase-ticket/{identification}/{idMovie}/{cinemaRoom}/{startDate}/{startTime}/{referenceId}`
        - Method: `DELETE`

- VENTAS DE TIQUETES

    - Obtener por identificacion
        - Enpoint: `/api/v4/sell-ticket/{identification}/{idMovie}/{cinemaRoom}/{startDate}/{startTime}`
        - Method: `GET`

    - Registro de una venta de tiquete
        - Endpoint: `/api/v4/sell-ticket/save`
        - Method: `POST`
        - Body:
        ```
            {
                "identification": Long -> Identificacion del taquillero,
                "idMovie": Long,
                "cinemaRoom": Integer,
                "startDate": Date [yyyy-mm-dd] -> Fecha de inicio de la funcion,
                "startTime": Time [hh:mm] -> Hora de inicio de la funcion,
                "listSelledSeats": String -> [A01, D13, F27, ..., Zn],
            }
        ```

    - Eliminar por identificacion
        - Endpoint: `/api/v4/sell-ticket/{identification}/{idMovie}/{cinemaRoom}/{startDate}/{startTime}`
        - Method: `DELETE`

- PAGOS WEB

    - Generar referencia de compra para tiquete
        - Endpoint: `/api/v4/payment/purchase-payment`
        - Method: `POST`
        - Body:
        ```
            {
                "identification": Long -> Identificacion del cliente,
                "idMovie": Long,
                "cinemaRoom": Integer,
                "startDate": Date [yyyy-mm-dd] -> Fecha de inicio de la funcion,
                "startTime": Time [hh:mm] -> Hora de inicio de la funcion,
                "listPurchasedSeats": String -> [A01, D13, F27, ..., Zn],
                "URLsuccess": String -> [http://... | https://...]
            }
        ``` 

   - Generar referencia de compra para membresia
        - Endpoint: `/api/v4/payment/membership-payment`
        - Method: `POST`
        - Body:
        ```
            {
                "identification": Long -> Identificacion del cliente,
                "startDate": Date [yyyy-mm-dd],
                "endDate": Date [yyyy-mm-dd],
                "URLsuccess": String -> [http://... | https://...]
            }
        ``` 