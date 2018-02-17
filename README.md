# Spring Cloud Training

This project is used for training of Spring-Cloud training.

Configuration for project is located in [rakk/spring-cloud-training-configuration](https://github.com/rakk/spring-cloud-training-configuration) repository.

Table of Contents
=================

* [Branches](#branches)
* [Build and run on Windows](#windows)
* [Build and run Linux/MacOS](#linux-or-mac-os)
* [Credentials](#credentials)

## Branches

During the training we will use branches:
* **live-coding-communication** (starting point for the live coding)
* **live-coding-routing**
* **live-coding-security**
* **live-coding-final** (code after live coding)


## Installation process

Install Java JDK 8/9 and Node 8.9.4

Project can be build by **maven wrapper** so maven installation can be skipped.

## Build and run project

### Windows

#### Download source code
````
cd %Homepath%\Desktop
git clone https://github.com/rakk/spring-cloud-training
git clone https://github.com/rakk/spring-cloud-training-configuration
````
This might take a while...

#### Checkout all live-coding branches

```bash
cd %Homepath%\Desktop\spring-cloud-config
git checkout -b live-coding-routing origin/live-coding-routing
git checkout -b live-coding-security origin/live-coding-security
git checkout -b live-coding-final origin/live-coding-final

git checkout -b live-coding-communication origin/live-coding-communication
```

#### Build source code

```cd %Homepath%\Desktop\spring-cloud-training && mvnw clean install```

This might take a while...

#### Run java projects

##### 1. Run **discovery-service**

```cd %Homepath%\Desktop\spring-cloud-training\discovery-service && mvnw spring-boot:run```

and open in browser port: **9021** => [http://localhost:9021](http://localhost:9021)

##### 2. Run **config-service**

```cd %Homepath%\Desktop\spring-cloud-training\config-service && mvnw spring-boot:run```

and open in browser port: **9020** => [http://localhost:9020/lending-service/develop](http://localhost:9020/lending-service/develop)

##### 3. Run **tracing-service**

```cd %Homepath%\Desktop\spring-cloud-training\tracing-service && mvnw spring-boot:run```

and open in browser port: **9022** => [http://localhost:9022](http://localhost:9022)


##### 4. Run **admin-service**

```cd %Homepath%\Desktop\spring-cloud-training\admin-service && mvnw spring-boot:run```

and open in browser port: **9024** => [http://localhost:9024](http://localhost:9024)

##### 5. Make sure that config-service and discovery-service are running

Rest of core projects requires both config-service and discovery-service. It is important to be sure that they are up and running.

##### 6. Run **oauth2-security-server**

:exclamation: **master branch only** :exclamation:

```cd %Homepath%\Desktop\spring-cloud-training\oauth2-security-server && mvnw spring-boot:run```

and open in browser port: **9030** => [http://localhost:9030/uaa/login](http://localhost:9030/uaa/login)

##### 7. Run **securities-service**

```cd %Homepath%\Desktop\spring-cloud-training\securities-service && mvnw spring-boot:run```

and open in browser port: **9001** => [http://localhost:9001/info](http://localhost:9001/info)

##### 8. Run **lending-service**

```cd %Homepath%\Desktop\spring-cloud-training\lending-service && mvnw spring-boot:run```

and open in browser port: **9000** => [http://localhost:9000](http://localhost:9000)

##### 9. Run **lending-ui**

```cd %Homepath%\Desktop\spring-cloud-training\lending-ui && mvnw spring-boot:run```

and open in browser port: **9002** => [http://localhost:9002](http://localhost:9002)

##### 10. Run **routing-service**

```cd %Homepath%\Desktop\spring-cloud-training\routing-service && mvnw spring-boot:run```

and open in browser port: **9090** => [http://localhost:9090](http://localhost:9090)

##### 11. Install http-server

```npm install http-server -g```

##### 12. Run Web NODE 1

```cd %Homepath%\Desktop\spring-cloud-training\ui && http-server```

and open in browser port: **8080** => [http://localhost:8080](http://localhost:8080)

##### 13. Run Web NODE 2

```cd %Homepath%\Desktop\spring-cloud-training\ui && http-server -p 8090```

and open in browser port: **8090** => [http://localhost:8090](http://localhost:8090)

### Linux or Mac OS

#### Download source code
````
cd ~/Desktop
git clone https://github.com/rakk/spring-cloud-training
git clone https://github.com/rakk/spring-cloud-training-configuration
````
This might take a while...

#### Build source code

```cd ~/Desktop/spring-cloud-training && ./mvnw clean install```

This might take a while...

#### Run java projects

##### 2. Run **discovery-service**

```cd ~/Desktop/spring-cloud-training/discovery-service && ./mvnw spring-boot:run```

and open in browser port: **9021** => [http://localhost:9021](http://localhost:9021)

##### 1. Run **config-service**

```cd ~/Desktop/spring-cloud-training/config-service && ./mvnw spring-boot:run```

and open in browser port: **9020** => [http://localhost:9020/lending-service/develop](http://localhost:9020/lending-service/develop)

##### 3. Run **tracing-service**

```cd ~/Desktop/spring-cloud-training/tracing-service && ./mvnw spring-boot:run```

and open in browser port: **9022** => [http://localhost:9022](http://localhost:9022)

##### 4. Run **admin-service**

```cd ~/Desktop/spring-cloud-training/admin-service && ./mvnw spring-boot:run```

and open in browser port: **9024** => [http://localhost:9024](http://localhost:9024)

##### 5. Make sure that config-service and discovery-service are running

Rest of core projects requires both config-service and discovery-service. It is important to be sure that they are working fine.

##### 6. Run **oauth2-security-server**

:exclamation: **master branch only** :exclamation:

```cd ~/Desktop/spring-cloud-training/oauth2-security-server && ./mvnw spring-boot:run```

and open in browser port: **9030** => [http://localhost:9030/uaa/login](http://localhost:9030/uaa/login)

##### 7. Run **securities-service**

```cd ~/Desktop/spring-cloud-training/securities-service && ./mvnw spring-boot:run```

and open in browser port: **9001** => [http://localhost:9001/info](http://localhost:9001/info)

##### 8. Run **lending-service**

```cd ~/Desktop/spring-cloud-training/lending-service && ./mvnw spring-boot:run```

and open in browser port: **9000** => [http://localhost:9000](http://localhost:9000)

##### 9. Run **lending-ui**

```cd ~/Desktop/spring-cloud-training/lending-ui && ./mvnw spring-boot:run```

and open in browser port: **9002** => [http://localhost:9002](http://localhost:9002)

##### 10. Run **routing-service**

```cd ~/Desktop/spring-cloud-training/routing-service && ./mvnw spring-boot:run```

and open in browser port: **9090** => [http://localhost:9090](http://localhost:9090)

##### 11. Install http-server

```npm install http-server -g```

##### 12. Run Web NODE 1

```cd ~/Desktop/spring-cloud-training/ui && http-server```

and open in browser port: **8080** => [http://localhost:8080](http://localhost:8080)

##### 13. Run Web NODE 2

```cd ~/Desktop/spring-cloud-training/ui && http-server -p 8090```

and open in browser port: **8090** => [http://localhost:8090](http://localhost:8090)


## Credentials

### Local OAuth Server (master branch)

:exclamation: Local OAuth Server is only on **master** branch :exclamation:

Predefined OAuth test users created for this app:

* admin - has role ADMIN
  * login: ```admin```
  * password: ```admin```
* user - has role USER
  * login: ```user```
  * password:```password```

### Facebook

:exclamation: Credentials needed on **live-coding-security** and **live-coding-final** branches :exclamation:

* ```hqichraxly_1516911670@tfbnw.net```

* ```imtykpbglb_1516911667@tfbnw.net```

* ```qgfuwwrlvt_1516911664@tfbnw.net```

* ```pyyxtvbbbr_1516911674@tfbnw.net```

* ```open_vbgkmxr_user@tfbnw.net```
