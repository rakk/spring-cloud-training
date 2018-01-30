# Spring Cloud Training

This project is used for training of Spring-Cloud developer tools.

Configuration for project is located here: [rakk/spring-cloud-training-configuration](https://github.com/rakk/spring-cloud-training-configuration) repository.

## OAuth

This version of the app uses facebook as external authentication provider (oauth SSO).

Facebook OAuth test users created for this app

* ```hqichraxly_1516911670@tfbnw.net```

* ```imtykpbglb_1516911667@tfbnw.net```

* ```qgfuwwrlvt_1516911664@tfbnw.net```

* ```pyyxtvbbbr_1516911674@tfbnw.net```

* ```open_vbgkmxr_user@tfbnw.net```
## UI project
How To start http server with simple HTML page

1. Install Node.js 8.9.4
2. Install http-server: ```npm install http-server -g```
3. Run first node of UI server:

    a. Go to folder UI in the project and run 

    b. ```bash
    http-sever``` in first console window 
    
    c. Open web browser ```http://localhost:8080```
4. Run second node of UI server: 

    a. Go to folder UI in the proejct and

    b. ```bash
    http-server -p 8090``` in second console window
    
    c. Open web browser ```http://localhost:8090```

## How to run Java projects

### Build project

Run ```mvn clean install``` to build all projects.

### Config Server and Discovery Service

```cd config-service && ./mvnw spring-boot:run```

```cd discovery-service && ./mvnw spring-boot:run```

make sure that services are loaded:
* ```http://localhost:9020/lending-service/whatever```
* ```http://localhost:9021```

### Other tools

```cd admin-service && ./mvnw spring-boot:run```

```cd tracing-service && ./mvnw spring-boot:run```

### Core services

```cd lending-service && ./mvnw spring-boot:run```

```cd lending-ui && ./mvnw spring-boot:run```

```cd securities-service && ./mvnw spring-boot:run```

```cd routing-service && ./mvnw spring-boot:run```
