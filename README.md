SENG2050 Assignment 2
=====================

Author: Simon Hartcher
----------------------

Environment
-----------

Using CATALINA_BASE:   /usr/local/Cellar/tomcat/7.0.54/libexec
Using CATALINA_HOME:   /usr/local/Cellar/tomcat/7.0.54/libexec
Using CATALINA_TMPDIR: /usr/local/Cellar/tomcat/7.0.54/libexec/temp
Using JRE_HOME:        /Library/Java/JavaVirtualMachines/jdk1.7.0_17.jdk/Contents/Home
Using CLASSPATH:       /usr/local/Cellar/tomcat/7.0.54/libexec/bin/bootstrap.jar:/usr/local/Cellar/tomcat/7.0.54/libexec/bin/tomcat-juli.jar
Server version: Apache Tomcat/7.0.54
Server built:   May 19 2014 10:26:15
Server number:  7.0.54.0
OS Name:        Mac OS X
OS Version:     10.9.4
Architecture:   x86_64
JVM Version:    1.7.0_17-b02
JVM Vendor:     Oracle Corporation

Structure
---------

Website entry is handled in the GameController servlet class where the session data is managed. The bulk of the work is done within the DealOrNoDeal bean that contains all of the game state.

I haven't used any Javascript for this assignment, only JSP and JSTL.

Objects
-------

Briefcase:

The bean class for each briefcase in the game. It is a very simple class with only getters and setters.

DealOrNoDeal:

The game object bean that runs the game logic. All the game state is held within this class.

GameController:

The java servlet that runs the entire solution. It is responsible for monitoring the state of the game and outputting the correct jsp. 

Session Tracking
----------------

Session tracking is implemented within the GameController servlet by checking a "game" session attribute. This makes the game attribute available to the jsp where the current game state can be rendered to the user.

Student Type
------------

Undergraduate

URL
---

http://localhost:8080/c3185790_assignment2/ 
For some reason I couldn't get the game to work without the ending /

[![Build Status](https://travis-ci.org/deevus/seng2050-assign2.svg)](https://travis-ci.org/deevus/seng2050-assign2)