# CI-5644

## Frontend
Para correr por primera vez:
```
npm install
```

Correr `dev script` con:
```
npm run dev
```

En caso de querer empaquetar, ejecutar:
```
npm run package
```

Posteriormente puede ejecutarse el paquete generado mediante:
```
npm run start
```

Versión utilizada de node: >= 20.X

**Nota:** Debe hacerse en el directorio `./frontend`

## Backend

La aplicación de backend puede ejecutarse haciendo uso del plugin de Spring Boot para el administrador de proyectos Maven:
```
mvn spring-boot:run
```

**Nota:** Debe hacerse desde el directorio `./backend`

## Docker

Ambas aplicaciones pueden ejecutarse en simultáneo en contenedores de Docker utilizando el script `docker-compose.yml` ubicado en este directorio. 
La base de datos de Postgres no corre dentro de un contenedor y debe estar funcionando como un servicio dentro del sistema huésped.

Versión utilizada de JDK: 17
Versión utilizada de Kotlin: 1.9.21
