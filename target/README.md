# PROYECTO: DataJurídica AutomationTest
    Este proyecto contiene test automáticos basados en Java, RestAssured, Maven y Junit.
    Podemos encontrar además características como:
    Generación de logs con Log4J en forma de archivo de texto, y de informes formato Html gracias a ExtentReport.
### Requisitos:
    - Para ejecutar este proyecto desde un IDE, es necesario tener instalada la Máquina Virtual de Java o un intérprete de código Java.
    - En el caso de crear un JAR solo sería necesario un intérprete de código Java.
    - Si se utiliza Maven para la ejecución, se debe tener instalado Maven.
### Cómo configurar el proyecto:
El proyecto cuenta con un archivo de configuración **config.properties**. Desde aquí podemos modificar valores como el backend point o la url pricipal.
Este archivo de configuración, aunque para este proyecto no es necesario, puede ser de gran ayuda para proyectos grandes en los que involucramos muchas variables de configuración.

**Logger legel:**

Podemos elegir tres opciones de Logs en texto: *INFO*, *DEBUG* y *ERROR*

    LOG_LEVEL = INFO

## Ejecutar el proyecto

### Maven:

**Ejecutar el proyecto completo:** 

    mvn test

**Ejecutar seleccionando un solo TestCase (caseName = nombre del TestCase sin espacios)**

    mvn -Dtest=Test_caseName test

**Ejecutar seleccionando varios TestCases**
    
    mvn -Dtest=Test_caseName1,Test_caseName2 test

**Ejecutar una TestSuite (nameSuite = nombre de la TestSuite sin espacios)**
    
    mvn -Dtest=DataJuridica.nameSuite.Test* test

## Este proyecto tiene los siguientes TestSuites y TestCases disponibles
**TS01_BackEnd**
- TC01_ValidarRegistro_Encontrado
- TC02_ValidarRegistro_NoEncontrado
