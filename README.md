Auto web authenticated network login
=================================================

This piece of software allows users to automatically connect to a web authenticated wireless network. 
The project was launched due to a request my myself to automatically login to my school network.

## Motivation
 - Send the necessary HTTP request, the browser would send if the user was logged in through the login page.
 - Automate the login process so the user has to login only once.

## Core concepts and ideas
 - Inversion of control driven application design
 - Model-View-Presenter approach, Views are in a passive state
 - Business logic encapsulated in services

 ## Used technologies and technical ideas
 - Client-only application architecture
 - JSR 299 for java-se (JBoss Weld)
 - JPA 2.0
 - H2 embedded database
 - Java FX 2+
 - Java 1.8
 - Apache Maven
 - Java Native Interface (get notifications about network activities)

### Maven module hierarchy

     - auto-web-auth                        // root (type: pom)
     | --- auto-web-auth-bom                // bill of materials, includes all modules in latest version
     | --- auto-web-auth-client-fx          // javaFX client
     | --- auto-web-auth-core-api           // authentication api
     | --- auto-web-auth-core-impl-base     // implementation of api
     | --- auto-web-auth-commons            // some common classes, helper classes
