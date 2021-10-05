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

## List of services

*Path*: /mutant

<br>
*REST Type:* POST<br>
*Description:* Method for evaluating mutant Dna chain and check if contains mutant genome.
<br>
*Body Request*: { “dna”:["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"] }
<br>
*Expected Response*: if is mutant then HTTP 200, otherwise HTTP 403


*Path*: /stats

<br>
*REST Type:* GET<br>
*Description:* Method for evaluating ratio of dna results
<br>
*Expected Response*: <code>JSON-TYPE {“count_mutant_dna”:Integer number of mutant chains
, “count_human_dna”:Integer number of non mutant chain: “ratio”:0.4}</code>


## Algoritmo de Detección de Mutantes

El problema presentado tiene un alto grado de complejidad computacional debido a que es una matriz de dos dimensiones. 
Teniendo en cuenta este escenario, se diseño un algoritmo que evalua la matriz en 3 aspectos:
* Validaciones horizontales
* Validaciones verticales o por columnas
* Validaciones diagonales e inversas 

Por otra parte cada una de las subcadenas obtenidas puede contener cero o varias secuencias de elementos iguales agregando 
mas complejidad de análisis.

### Validaciones Horizontales

Se toman cada uno de los elementos del arreglo de strings y se evaluan si tienen secuencias. Se evalua de esta forma 
para evitar recorrer en dos dimensiones reduciendo complejidad

Si se encuentra más de una coincidencia de gen mutante entonces se termina la validación

### Validaciones Verticales

El proceso de validación en verticales o columnas se realiza en dos pasos:
1. Conversión a matriz transpuesta, como es una matriz cuadrada de orden NxN se hace el cambio entre columnas - filas en su correspondiente posición
2. Analizar cada uno de las subcadenas obtenidas.

Si se encuentra más de una coincidencia de gen mutante entonces se termina la validación.

### Validaciones en Diagonales

El proceso de validación por diagonales es el mas complejo debido a que su validación únicamente es posible en dos dimensiones
al ser el mas costoso (en términos de recursos y tiempo de ejecución) se realiza en los siguientes pasos:
1. Validación por diagonales principales: un algoritmo de orden (n) en el caso mas largo, donde se validan si las diagonales principales tienen alguna secuencia mutante
2. Validación por diagonales de columnas: iniciando las subcadenas desde el primer elemento de la primera fila de la matriz
3. Validación por diagonales de filas: iniciando subcadenas desde la primera y ultima columna de matriz 



