# GISTA TEMPLATES  
![](https://github.com/thomas-archive/java-image-to-svg/workflows/Javadoc/badge.svg) ![](https://img.shields.io/badge/java-11.0-red) ![](https://img.shields.io/badge/maven-3.6.3-blue)

Gista templates is used as a reference when creating new application services related of GISTA, the following specifications are used in this templates:

<br/>

## Prerequisites
| `Standard`  | `Recommended`           |
|-------------|-------------------------|
| Java        | Using Java 11           |
| Maven       | Using Maven 3.6.3       |
| Spring Boot | 2.6.14 (Stable Version) |

<br/>

## Dependencies
`** (Required)  * (Optional)`

| `Standard`              | `Dependency`                                | `Utility`                                                                                                                                                                                                                                                                                                                                                  |
|-------------------------|---------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Security                | Keycloak **                                 | Management of targeted user access to the destination applications or services                                                                                                                                                                                                                                                                             |
| Distributed             | Spring Cloud **                             | Declarative model to send and receive messages using Apache Kafka                                                                                                                                                                                                                                                                                          |
| Database Migration      | Flyway **                                   | Version control system use to maintain database migrations across all application instance                                                                                                                                                                                                                                                                 |
| Database Repository     | JPA Repository **                           | Java Persistence API that can make it easier for us as developers to create CRUD on String Data                                                                                                                                                                                                                                                            |
| Validation              | Spring Validator **                         | Validate objects by using an errors object and can report validation failures to the errors object                                                                                                                                                                                                                                                         |
| Data Transform          | Mapstruct **                                | Mapping between objects without having to bother with setting attributes per object                                                                                                                                                                                                                                                                        |
| Data Audit              | Envers **                                   | To enable easy auditing of persistent classes that want to audit, and will hold the history of changes made to entity                                                                                                                                                                                                                                      |
| Health                  | Actuator **                                 | Understanding traffic and interact with the services that have been created                                                                                                                                                                                                                                                                                |
| Distributed Tracing     | Zipkin ** <br/>Sentry ** <br/> Prometheus * | Distributed tracing system that helps collect the data needed to solve latent problems in service architectures. Features include data collection and retrieval                                                                                                                                                                                            |
| Annotation Preprocessor | Lombok **                                   | Provides a set of useful annotations for eliminating a tremendous amount of boilerplate code from your Java classes                                                                                                                                                                                                                                        |
| Documentation           | Swagger *                                   | API documentation intended for users to be easy to read and understand                                                                                                                                                                                                                                                                                     |
| Export File             | Apache POI *                                | Well-known in the Java field as a library for reading and writing Microsoft Office file formats, especially Excel which is used to create export files                                                                                                                                                                                                     |
| HTTP Client             | Open Feign *                                | Declarative REST Client: Feign creates a dynamic implementation of an interface decorated with  annotations                                                                                                                                                                                                                                                |
| Google Core Libraries   | Google Guava *                              | Guava is a set of core Java libraries from Google that includes new collection types (such as multimap and multiset), immutable collections, a graph library, and utilities for concurrency, I/O, hashing, caching, primitives, strings, and more! It is widely used on most Java projects within Google, and widely used by many other companies as well. |

<br/>

## Structure Packages

> **Base** : erp.logisticassistant.gista.base
```
+ erp.logisticassistant.gista.base
+----| configuration
+--------| datasource
+--------| documentation
+--------| exeption
+--------| kafka
+--------| security
+----| projectname {project}
+-----------| example {module}
+---------------| application
+-------------------| external
+-------------------| internal
+-----------------------| command
+-----------------------| query
+---------------| domain
+-------------------| aggregates
+-------------------| entities
+-------------------| commands
+-------------------| services
+---------------| infrastructure
+---------------| interfaces 
+-------------------| rest
+-----------------------| controller
+-----------------------| dto
+-----------------------| transform
+----| shareddomain
+--------| cache
+--------| commons
+--------| constant
+--------| kafka
+--------| util
+-----------| base
+-----------| client
+-----------| dto
+-----------| enums
+-----------| transform
```
> `projectname` : _You can be replace according name of the projects_ <br/>
> `example (module)` : _You can be replace according the working on modules_

<br/>

## Package Explanations

* `configuration`, _Packages containing configuration class for support service requirements_
  * `datasource`, _Packages containing configuration class for both migration and data audits_
  * `documentation`, _Packages containing configuration class for documentation services_
  * `exception`, _Packages containing configuration class for handle anything related to processing errors_
  * `kafka`, _Packages containing configuration class for kafka factory_
  * `security`, _Packages containing configuration class for users authentication with keycloak management system_
* `projectname (module package)`, _Packages containing classes related to service module requirements_
  * `example (module)` _Packages containing the module class to be created_
    * `application`, _Packages containing implementation classes according to business modules_ 
      * `external`, _Packages containing class that calls functions outside of its own module_ 
      * `internal`, _Packages containing classes that call functions on their own modules_
        * `command`, _Package containing classes related to input datasource_
        * `query`, _Package containing classes related to reading datasource_ 
    * `domain`, _Package containing entity classes and aggregate classes with specifications related to object data filters_
      * `aggregates`, _Packages containing aggregate classes according to the module created_
      * `entities`, _Packages containing entity classes according to the module created_
      * `commands`, _Packages containing commands classes according to the module created_
      * `services`, _Packages containing data specification classes according to the desired entities or aggregates_ 
    * `infrastructure`, _Packages containing  entity-related and aggregate-related repository class_
    * `interfaces`, _Package containing  connector  between the controller classes and the process classes along with the objects to be received or displayed_
      * `rest`, _Packages related to controller, request, and response_ 
        * `controller`, _Packages containing controller classes that will call the desired process class_ 
        * `dto`, _Packages containing the request object classes and the response object to be processed by the controller_ 
        * `transform`, _Packages containing transformation data from request data object to entity classes or from entity classes to response data object_
* `shareddomain`, _Packages containing support class that can be used for all require modules_
  * `cache` _Packages containing the consume classes from identity services related to cache entity class user management and role management according to the application that is triggered_
  * `commons`,  _Packages containing implementation properties that can be used module class that requires value properties_
  * `constant`, _Packages containing classes to hold constant variables that can be needed in various module classes_
  * `kafka`, _Packages containing implementation of kafka-produce class and logs of kafka when data consumption error_
  * `util`,  _Packages containing the supporting classes of all the modules_
    * `base`, _Packages containing base entities auditing and base specification filter_
    * `client`, _Packages containing interface classes related to rest client out of service_
    * `dto`, _Packages containing object classes that can be instantiated all of modules_
    * `enums`, _Packages containing enum classes_
    * `transform`, _Packages that contain transformation data that can be used for all requires of modules_

<br/>

## Installation and Getting Started
1) Change the value properties Project Name in **pom.xml** according to the name of the project to be created
    ```xml
    <project...>
       <name>Gista Base</name>
       ....
    </project>
    ```
2) Change the value properties `GISTA-BASE` in **application.properties** according `ApplicationId` that will be created according to service requirements
    ```properties
    spring.application.id = GISTA-BASE
    ```

3) Change the value of the `final-name` property in **pom.xml** according to the name of the project .jar file that will be generated
    ```xml
    <properties>
       <final.name>gista-base</final.name>
       ....
    </properties>
    ```
4) Rename of package `projectname` according the name of project to be created
    ```
    erp.logisticassistant.gista.base.{projectname}
    ```
5) Rename of package `example` according the modules to be worked on or create new modules according the structure templates
    ```
    erp.logisticassistant.gista.base.{projectname}.{example}
    ```
6) Running tests
    ```cmd
    mvn clean test
    ```

<br/>

## Authors
[Harry Setiawan]() PT. Akarinti Teknologi