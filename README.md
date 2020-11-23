# Inventory System 
Inventory System is a web application based on REST API architecture which is developed to maintain & manage
articles, products.
Endpoints are:
* Load Inventory i.e list of articles.
* Load Products in to the inventory
* Get all products available in the current inventory.
* Remove requested product(provided id in JSON file) and update the inventory.

### Implementation Notes
* Java & Spring Boot is used.
* Postgres is used as a Database storage.
* Swagger UI integration as a testing tool.

  ### Used Technologies
* Java 11
* Spring Boot
* Maven 3.6.3
* PostgreSQL 13 

        
## Build and Run Project
* Download and install Postgres database server

* Update username & password of Postgres in application.properties

run one of following two commands under root folder:

        ./mvnw clean package spring-boot:run -Dserver.port=8095
        
        OR
        
        mvn clean package spring-boot:run -Dserver.port=8095

Then you can access to the endpoints from `http://localhost:8095`

## Swagger UI
In order to access Swagger UI, just open `http://localhost:8095/swagger-ui.html` from your browser.

