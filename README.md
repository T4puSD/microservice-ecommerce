# Microservice Ecommerce
A demo repo to demonstrate Microservice Architecture.

## How to run the demo
### Spring up the PostgreSQL databse
- First spin up a postgres docker container using the `docker-compose.yml` file
    
### Run the services in sequence
- Run `eureka` with `cd eureka && mvn spring-boot:run`
- Run `customer-service` with `cd customer-service && mvn spring-boot:run`
- Run `product-service` with `cd product-service && mvn spring-boot:run`
- Run `order-service` with `cd order-service && mvn spring-boot:run`
- Run `gateway` with `cd gateway && mvn spring-boot:run`
> Every service is configured with `liquibase` so initial data will be loaded for all services except `order-service` in `PostgreSQL`.
> The `gateway` is running at port `8080`. The `customer-service` is both a service for customer entity and a identity provider.

### Query the service with gateway

1. First, get a `jwt` token from the `customer-service` with the following REST call
```sh
curl -X POST http://localhost:8080/cs/api/v1/auth/login -H 'Content-Type: application/json' -d '{"email": "jack@dummy.com", "password": "dummy"}'
```

2. Then you can use the `jwt` token as Authorization Bearer token to query other serivces
```sh
curl http://localhost:8080/ps/api/v1/products/1 -H 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9..........'
```
