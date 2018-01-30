# Spring Cloud Training

This project is used for training of Spring-Cloud developer tools.

Configuration for project is located here: [rakk/spring-cloud-training-configuration](https://github.com/rakk/spring-cloud-training-configuration) repository.

Table of Contents
=================

* [Build and run on Windows](#windows)
* [Build and run Linux/MacOS](#linux-or-mac-os)
* [OAuth configuration](#oauth)

## Installation process

Install Java JDK 8 and Node 8.9.4

Project will build using maven wrapper so maven installation can be skipped.

## Build and run project

### Windows

#### Download source code
````
cd %Homepath%\Desktop
git clone https://github.com/rakk/spring-cloud-training-configuration
git clone https://github.com/rakk/spring-cloud-training-configuration-configuration
````
This might take a while...

#### Build source code

```
mvnw clean install
```
This might take a while...

#### Run java projects

##### 1. Run **config-server**

```cd %Homepath%\Desktop\config-service && mvnw spring-boot:run``` open in browser port: **9020** => [http://localhost:9020/lending-service/develop](http://localhost:9020/lending-service/develop)

#### 2. Run **discovery-service**

```cd %Homepath%\Desktop\discovery-service && mvnw spring-boot:run``` open in browser port: **9021** => [http://localhost:9021](http://localhost:9021)

#### 3. Run **tracking-service**

```cd %Homepath%\Desktop\tracking-service && mvnw spring-boot:run``` open in browser port: **9022** => [http://localhost:9022](http://localhost:9022)
 

#### 4. Run **admin-service**

```cd %Homepath%\Desktop\admin-service && mvnw spring-boot:run``` open in browser port: **9024** => [http://localhost:9024](http://localhost:9024)

#### 5. Make sure that config-server and discovery-service are running

Rest of core projects requires both config-server and discovery-service. It is important to be sure that they are working fine.

#### 6. Run **securities-service**

```cd %Homepath%\Desktop\securities-service && mvnw spring-boot:run``` and open in browser port: **9030** [http://localhost:9030/info](http://localhost:9030/info) 

#### 7. Run **lending-service**

```cd %Homepath%\Desktop\lending-service && mvnw spring-boot:run``` and open in browser port: **9000** [http://localhost:9000](http://localhost:9000)

#### 8. Run **lending-ui**

```cd %Homepath%\Desktop\lending-ui && mvnw spring-boot:run``` and open in browser port: **9002** [http://localhost:9002](http://localhost:9002)

#### 9. Run **oath2-security-server**

```cd %Homepath%\Desktop\oath2-security-server && mvnw spring-boot:run``` and open in browser port: *9030* [http://localhost:9030/uaa/login](http://localhost:9030/uaa/login)

#### 10. Run **routing-service**

```cd %Homepath%\Desktop\routing-service && mvnw spring-boot:run``` and open in browser port: *9030* [http://localhost:9090](http://localhost:9090)

#### 11. Install http-server

```
npm install http-server -g
```

#### 12. Run Web NODE 1

```cd %Homepath%\Desktop\ui && http-server``` and open in browser port: *8080* [http://localhost:8080](http://localhost:8080)

#### 13. Run Web NODE 2

```cd %Homepath%\Desktop\ui && http-server -p 8090``` and open in browser port: *8090* [http://localhost:8090](http://localhost:8090)

## OAuth

This version of the app contains spring oauth2 authorization server.

Predefined OAuth test users created for this app

* ```admin```:```admin``` - has role ADMIN

* ```user```:```password``` - has role USER
