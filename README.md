# Mutant Detector
Prueba Tecnica Mercadolibre

## Installation
Application can be executed from the jar as a standalone application. No server installation is necessary.
### Dependencies
* Docker
* Java version 11 
* postgres SQL 
* Maven
### DB Installation

Postgres can be installed from docker image please use the follow commands for install a fresh instance of postgres
<br><code>docker pull postgres</code>
<br><code>$ docker run --name <instanceName-postgres> -e POSTGRES_USER=<user-name> -e POSTGRES_PASSWORD=<password> -p 5432:5432  -d postgres
</code>

### Starting the Application

For this part we need to have installed maven and the source code, first add your DB credentials into the application.properties file located in src/resources
<br><code>
spring.datasource.driverClassName: org.postgresql.Driver
spring.datasource.url: jdbc:postgresql://localhost:5432/mutant
spring.datasource.username= admin
spring.datasource.password: admin123
</code>

Second, perform a cd navigation to the source code and then execute the next command:
<br><code>mvn clean install</code><br>
After the end of this command proceed to the target folder in the source code and execute de jar 
<br><code>java -jar mutantdna-0.0.1-SNAPSHOT.jar</code>

Enjoy Testing :) 


