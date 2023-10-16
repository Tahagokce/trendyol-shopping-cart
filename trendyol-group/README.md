# Shopping-cart case-study

Shopping-cart is a microservice project that I developed as a solution for the Trendyol case study. While developing the project, design patterns such as clean code, SOLID principles, DDD, TDD, and design patterns (Factory, Command, Strategy) were applied for testability and sustainability, and the current technologies were used.

## Requirements

- [JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Maven 3.8.1](https://maven.apache.org)
- [Redis 7.2](https://redis.io/download/)
- [Postgresql 13.7](https://www.postgresql.org/ftp/source/v13.7/)

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `de.codecentric.springbootsample.Application` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```
You can find the necessary `Dockerfile` in the `docker` folder to use Postgres and `Redis` via `Docker`. You can also access the DDL file required to create the database from there.  

## Getting started
- First of all, you need to make Redis and Postgresql working. Then, you need to add the Postgresql connection information to the relevant fields in the `command-runner-service.yml` and `shopping-cart.yml` files under config-server. [See](https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html#appendix.application-properties.data)

- Since it is a microservice project, `Spring Cloud Config` was used to manage the config files of the services more easily. Microservices need config files to run, so you need to run `config-server` first. [See](https://docs.spring.io/spring-cloud-config/docs/current/reference/html/)

- `Service Discovery` was used to enable microservices to communicate with each other more effectively, so `Eureka Server`, where microservices will register, is our second service that needs to be run. [See](https://spring.io/guides/gs/service-registration-and-discovery/)

- Our last step is to run `shopping-cart-service` and `command-runner-service`.

`Note: Files are read from a folder path and run at regular intervals to produce results, so do not forget to configure this in the command-runner-service.yml file.`
## Tech Stack
**Spring:** Spring framework, Spring Cloud, Spring Data

**Unit Test:** JUnit 5, Mockito, EasyRandom

**Cache:** Redis OM

**Database:** Postgresql


## Documentation

[Postman](https://api.postman.com/collections/16680884-42f68199-0e81-4970-b6d4-0385ccb5d7ee?access_key=PMAT-01HCW7JP8WVM76FTW012Z911JH)


## Authors
Your feedback is very important to me, thank you for reviewing.

Nice have a work day.

- [@Tahagokce](https://github.com/Tahagokce)
