Auto web authorized network login
=================================================

This piece of software automatically connects to a web authorization based wireless-network.  
The project was launched due to a request by ourselves to automatically login to the network of our school.

## Basic ideas 
 - Try to connect to school network through web authorized network automatically

## Used technologies and technical ideas
 - `JavaEE 6`
 - `Java 1.8`
 - apache maven
 - Use `JNI` to access to wireless network API of underlining operating system.
 - Windows support only (at the moment)
 - `Java FX 2+`
 - Client-only application architecture
 - Windows taskbar support
 - Run as a service at windows logon

## Maven module hierarchy
     - auto-web-auth                        // root (type: pom)
     | --- auto-web-auth-bom                // bill of materials, includes all modules in lastest version
     | --- auto-web-auth-client-fx          // javaFX client
     | --- auto-web-auth-core-api           // authorization mechanism api
     | --- auto-web-auth-core-impl-base     // implementation of api
     | --- auto-web-auth-commons            // some common classes, helper classes

## Further information
see doc/tech-info.md for more information
