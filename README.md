# Mutant Detector
Prueba Tecnica Mercadolibre

## Installation
La aplicación puede ser ejecutada desde el jar (es una aplicación standAlone con su servidor incluido) no es necesario instalar un contendor de servlet aparte.
### Dependencias
* Docker
* Java version 11 
* postgres SQL 
* Maven
### Instalación base de datos 

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

NOTA: esto es para modo desarrollo
Happy Testing :) 

## Servicios Disponibles

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


## Algorítmo Detección de Mutantes

El problema presentado tiene un alto grado de complejidad computacional debido a que es una matriz de dos dimensiones. 
Teniendo en cuenta este escenario, se diseño un algoritmo donde se divide el escenario de ejecución en diferentes partes para reducir el tiempo de ejecución en cada solicitud que evalua la matriz en 3 aspectos:
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

![alt text](https://github.com/fepego/mutantdetector/blob/main/Screen%20Shot%202021-10-04%20at%208.53.42%20PM.png?raw=true)

La imagen muestra el proceso de validación realizado para las diagonales, amarillo paso 1, verde paso 2 y naranja paso 3. Es importante resaltar que este proceso se hace de izquierda a derecha hasta la mitad de la matriz y de derecha a izquerda
hasta la mitad.

Si se encuentran mas de un match en alguno de los pasos termina el procesamiento y retorna que es un DNA mutante.

### Detecciones Previas
Se tiene en cuenta que los request realizados al sistema pueden contener cadenas previamente identificadas por lo que antes de efectuar el 
analisis descrito en la sección anterior se busca la cadena a evaluar en la base de datos, reduciendo tiempos de respuesta.

## Proceso de Desarrollo

### Covertura en pruebas 
Se agrego la dependencia Jacoco al proyecto para tener visibilidad de la covertura de las pruebas en el codigo realizado. Las pruebas incluyen levantar el contexto para verificar que las dependencias no han sido modificadas o que no afecten el flujo a la fecha de la aplicación.

A continuación este es el resultado final.

### Integración Continua 
Se agrego un workflow en github para ejecutar las pruebas en cada commit y hacer pruebas de integración con la persistencia
puede ver el resultado en este link.
<code> https://github.com/fepego/mutantdetector/actions </code> 
