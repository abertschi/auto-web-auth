WIFI auto web-authenticator
===========================

This piece of software autoconnects a web authentification based wireless-network.  
The project was launched tue to a request by ourselves to autologin into the web authentificated network of our school.


## Basic ideas 
 - Try to auto-connect to school network through web-authentificated Network automatically


## Used technologies and technical ideas
 - `Java 1.8`
 - Use `JNI` to access to Wireless-Network API of underlining operating-system.
 - Windows Support only (at the moment)
 - `Java FX 2+`
 - Client-only application architecture
 - Windows taskbar support
 - Run as a service at windows-login
