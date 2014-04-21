Auto web authenticated wireless network login
=================================================

This piece of software allows users to automatically connect to a web authenticated wireless network. 
The project was launched due to a request by my myself to automatically login to my school network.
  
  
  [![Build Status](https://travis-ci.org/abertschi/auto-web-auth.svg?branch=master)](https://travis-ci.org/abertschi/auto-web-auth)

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
 - JDK 1.7 (maybe 1.8 in a upcoming version?)
 - Apache Maven
 - Java Native Interface (get notifications about network activities of underlining os)
 
## Diagrams in lovely UML
UML diagrams are modeled in astah community version.  
Some others are made with MS Visio.

### Basic usecases
![Basic usecases](./doc/design/rendering/14-02-21_basic-usecases.png?raw=true)  

#### Further explanations
A supersudent acts as an administrator. He defines registrations for his school. A registration consists of the same necessary information, the login page would send, if the student was logged in through this page.

    <FORM method="post" ACTION="/login.html">  
    	<INPUT type="TEXT" name="username" SIZE="25" MAXLENGTH="80" VALUE="">  
    	<INPUT type="TEXT" name="password" SIZE="25" MAXLENGTH="80" VALUE="">  
    </FORM>

Based on these registrations, students are now able to create profiles for their accounts.  
After the profile is setup, the *automated web authenticated wireless network login* mechanism is able to login the user automatically. This mechanism is controlled by the Start - and Stop connection tracking usecases.

### Component diagram
This picuture is showing the basic components of the application. Keep in mind the application is in a client-only application architecture. Everything is basically the java-fx client.

![Basic usecases](./doc/design/rendering/14-02-21_component-diag-auto-web-auth.png?raw=true =250x)

## Maven module hierarchy

     - auto-web-auth                        // root (type: pom)
     | --- auto-web-auth-bom                // bill of materials, includes all modules in latest version
     | --- auto-web-auth-client-fx          // javaFX client
     | --- auto-web-auth-core-api           // authentication api
     | --- auto-web-auth-core-impl-base     // implementation of api, jni to underlining os
     | --- auto-web-auth-commons            // some common classes, helper classes



----------

Feel free to fork and improve.  
Enjoy!  

Andrin Bertschi  
https://github.com/abertschi/auto-web-auth
