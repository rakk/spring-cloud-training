# Spring Cloud Training

This project is used for training of Spring-Cloud developer tools.

Configuration for project is located here: [rakk/spring-cloud-training-configuration](https://github.com/rakk/spring-cloud-training-configuration) repository.

Table of Contents
=================

* [Build and run on Windows](#windows)
* [Build and run Linux/MacOS](#linux-or-mac-os)
* [OAuth configuration](#oauth)

## Installation process

Install Java JDK 8/9 and Node 8.9.4

Project is build by maven wrapper so maven installation can be skipped.

## Build and run project

### Windows

#### Download source code
````
cd %Homepath%\Desktop
git clone https://github.com/rakk/spring-cloud-training
git clone https://github.com/rakk/spring-cloud-training-configuration
````
This might take a while...

#### Build source code

```
cd %Homepath%\Desktop\spring-cloud-training && mvnw clean install
```
This might take a while...

#### Run java projects

##### 1. Run **config-server**

```cd %Homepath%\Desktop\spring-cloud-training\config-service && mvnw spring-boot:run```

open in browser port: **9020** => [http://localhost:9020/lending-service/develop](http://localhost:9020/lending-service/develop)

##### 2. Run **discovery-service**

```cd %Homepath%\Desktop\spring-cloud-training\discovery-service && mvnw spring-boot:run```

open in browser port: **9021** => [http://localhost:9021](http://localhost:9021)

##### 3. Run **tracing-service**

```cd %Homepath%\Desktop\spring-cloud-training\tracing-service && mvnw spring-boot:run```

open in browser port: **9022** => [http://localhost:9022](http://localhost:9022)
 

##### 4. Run **admin-service**

```cd %Homepath%\Desktop\spring-cloud-training\admin-service && mvnw spring-boot:run```

open in browser port: **9024** => [http://localhost:9024](http://localhost:9024)

##### 5. Make sure that config-server and discovery-service are running

Rest of core projects requires both config-server and discovery-service. It is important to be sure that they are up and running.

##### 6. Run **oath2-security-server**

**! master branch only !**

```cd %Homepath%\Desktop\spring-cloud-training\oath2-security-server && mvnw spring-boot:run``` and

open in browser port: **9030** [http://localhost:9030/uaa/login](http://localhost:9030/uaa/login)

##### 7. Run **securities-service**

```cd %Homepath%\Desktop\spring-cloud-training\securities-service && mvnw spring-boot:run```

and open in browser port: **9001** [http://localhost:9001/info](http://localhost:9030/info) 

##### 8. Run **lending-service**

```cd %Homepath%\Desktop\spring-cloud-training\lending-service && mvnw spring-boot:run``` and

open in browser port: **9000** [http://localhost:9000](http://localhost:9000)

##### 9. Run **lending-ui**

```cd %Homepath%\Desktop\spring-cloud-training\lending-ui && mvnw spring-boot:run``` and

open in browser port: **9002** [http://localhost:9002](http://localhost:9002)

##### 10. Run **routing-service**

```cd %Homepath%\Desktop\spring-cloud-training\routing-service && mvnw spring-boot:run``` and

open in browser port: **9090** [http://localhost:9090](http://localhost:9090)

##### 11. Install http-server

```
npm install http-server -g
```

##### 12. Run Web NODE 1

```cd %Homepath%\Desktop\spring-cloud-training\ui && http-server``` and

open in browser port: **8080** [http://localhost:8080](http://localhost:8080)

##### 13. Run Web NODE 2

```cd %Homepath%\Desktop\spring-cloud-training\ui && http-server -p 8090``` and

open in browser port: **8090** [http://localhost:8090](http://localhost:8090)

### Linux or Mac OS

#### Download source code
````
cd ~/Desktop
git clone https://github.com/rakk/spring-cloud-training
git clone https://github.com/rakk/spring-cloud-training-configuration
````
This might take a while...

#### Build source code

```
cd ~/Desktop/spring-cloud-training && ./mvnw clean install
```
This might take a while...

#### Run java projects

##### 1. Run **config-server**

```cd ~/Desktop/spring-cloud-training/spring-cloud-training/config-service && ./mvnw spring-boot:run```

open in browser port: **9020** => [http://localhost:9020/lending-service/develop](http://localhost:9020/lending-service/develop)

##### 2. Run **discovery-service**

```cd ~/Desktop/spring-cloud-training\discovery-service && ./mvnw spring-boot:run```

open in browser port: **9021** => [http://localhost:9021](http://localhost:9021)

##### 3. Run **tracing-service**

```cd ~/Desktop/spring-cloud-training/tracing-service && ./mvnw spring-boot:run```

open in browser port: **9022** => [http://localhost:9022](http://localhost:9022)
 
##### 4. Run **admin-service**

```cd ~/Deskop/spring-cloud-training/admin-service && ./mvnw spring-boot:run```

open in browser port: **9024** => [http://localhost:9024](http://localhost:9024)

##### 5. Make sure that config-server and discovery-service are running

Rest of core projects requires both config-server and discovery-service. It is important to be sure that they are working fine.

##### 6. Run **oath2-security-server**

**! master branch only !**

```cd ~/Deskop/spring-cloud-training/oath2-security-server && ./mvnw spring-boot:run``` and

open in browser port: **9030** [http://localhost:9030/uaa/login](http://localhost:9030/uaa/login)

##### 7. Run **securities-service**

```cd ~/Deskop/spring-cloud-training/securities-service && ./mvnw spring-boot:run``` and

open in browser port: **9001** [http://localhost:9001/info](http://localhost:9030/info) 

##### 8. Run **lending-service**

```cd ~/Deskop/spring-cloud-training/lending-service && ./mvnw spring-boot:run``` and

open in browser port: **9000** [http://localhost:9000](http://localhost:9000)

##### 9. Run **lending-ui**

```cd ~/Deskop/spring-cloud-training/lending-ui && ./mvnw spring-boot:run``` and

open in browser port: **9002** [http://localhost:9002](http://localhost:9002)

##### 10. Run **routing-service**

```cd ~/Deskop/spring-cloud-training/routing-service && ./mvnw spring-boot:run``` and

open in browser port: **9090** [http://localhost:9090](http://localhost:9090)

##### 11. Install http-server

```
npm install http-server -g
```

##### 12. Run Web NODE 1

```cd ~/Deskop/spring-cloud-training/ui && http-server``` and

open in browser port: **8080** [http://localhost:8080](http://localhost:8080)

##### 13. Run Web NODE 2

```cd ~/Deskop/spring-cloud-training/ui && http-server -p 8090``` and

open in browser port: **8090** [http://localhost:8090](http://localhost:8090)


## OAuth

This version of the app contains spring oauth2 authorization server.

Predefined OAuth test users created for this app

* ```admin```:```admin``` - has role ADMIN

* ```user```:```password``` - has role USER
