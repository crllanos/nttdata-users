# Prueba técnica NTT Data
#### Cristian Llanos | cristian.llanos@gmail.com

Se presenta una API REST como solución para el manejo de usuarios con sus datos básicos más su listado de teléfonos.

Además de añade gestión de administradores para permitir el perfilamiento del sistema, entregando un super administradr que permite generar nuevos administradores para ejecutar las labores de gestión sobre los datos.


## Documentación
_(se necesita arrancar el proyecto para poder acceder)_
* [Swagger del proyecto](http://localhost:8080/swagger-ui/index.html)
* [Api docs](http://localhost:8080/api-docs)



## Pruebas
### 1) Iniciar sesión
```
curl -L 'http://localhost:8080/auth/login' \
-H 'Content-Type: application/json' \
--data-raw '{
    "email": "super@admin.com",
    "password": "123456"
}'
```

### 2) Crea nuevo admin
```
curl -L 'http://localhost:8080/admins' \
-H 'Content-Type: application/json' \
-H 'Authorization: Bearer <TOKEN_JWT>' \
--data-raw '{
    "email": "admin@admin.com",
    "password": "AAbb123",
    "name": "Admin",
    "role": "ADMIN"
}'
```

### 3) Crea nuevo usuario
```
curl -L 'http://localhost:8080/user-registry/' \
-H 'Content-Type: application/json' \
-H 'Authorization: Bearer <TOKEN_JWT>' \
--data-raw '{
    "name": "Juan Rodriguez",
    "email": "juan@rodriguez.org",
    "password": "DocumentoS33",
    "phones": [
        {
            "number": "1234567",
            "citycode": "1",
            "contrycode": "57"
        }
    ]
}'
```

### 4) Lista de usuarios
```
curl -L 'http://localhost:8080/user-registry/' \
-H 'Authorization: Bearer <TOKEN_JWT>'
```

### 5) Ver registro de usuario
```
curl -L 'http://localhost:8080/user-registry/<UUID_USUARIO>' \
-H 'Authorization: Bearer <TOKEN_JWT>'
```

### 6) Actualizar datos de usuario
```
curl -L -X PUT 'http://localhost:8080/user-registry/<UUID_USUARIO>' \
-H 'Content-Type: application/json' \
-H 'Authorization: Bearer <TOKEN_JWT>' \
--data-raw '{
    "name": "Juan Rodriguez R.",
    "email": "juan@rodriguez.org",
    "password": "AAaaaaaaa33",
    "phones": [
        {
            "number": "1234567",
            "citycode": "1",
            "contrycode": "57"
        }
    ]
}'
```


### 7) Eliminar usuario
```
curl -L -X DELETE 'http://localhost:8080/user-registry/<UUID_USUARIO>' \
-H 'Authorization: Bearer <TOKEN_JWT>' 
```
