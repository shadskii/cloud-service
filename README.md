# Line Server Problem
In this exercise, you will build and document a system that is capable of serving lines out of a
file to network clients. You may do this in any language (although Java, Python are the most
used at Circle). You may use any reference and any open-source software you can find to help
you build this system, so long as you document your use. However, you should not actively
collaborate with others.

## Specification
Your system should act as a network server that serves individual lines of an immutable text file
over the network to clients using the following simple REST API:
* GET /lines/<line index>
  * Returns an HTTP status of 200 and the text of the requested line or an HTTP 413 status if the requested line is beyond the end of the file.
* Your server should support multiple simultaneous clients.
* The system should perform well for small and large files.
* The system should perform well as the number of GET requests per unit time increases.
* You may pre-process the text file in any way that you wish so long as the server behaves
correctly.

The text file will have the following properties:
* Each line is terminated with a newline ("\n").
* Any given line will fit into memory.
* The line is valid ASCII (e.g. not Unicode).

### Design considerations:
* What techniques would you use to achieve maximum uptime? Scenarios to consider:
code deployment, server failures, disk failures, etc…
* How extensible is your service / architecture? Can we add more endpoints for additional
features?
## What to submit
You should provide access to a public source code repository (or submit a zip file) that contains
shell scripts to build and run your system, documentation for your system, and the source code
for the system itself.
* build.sh​ - A script that can be invoked to build your system. A good usage would be to
run unit tests and fail the build if they don’t pass. You may invoke another tool such as
Maven, Ant, etc. with this script. You may download and install any libraries or other
programs you feel are necessary to help you build your system. (Please list out these
dependencies in the README).
* run.sh​ - A script that takes a single command-line parameter which is the name of the
file to serve. Ultimately, it should start the server you have built.
* README​ - A text file that answers the following questions:
  * How does your system work? (if not addressed in comments in source)
  * What do we need to build your system?
  * How will your system perform with a 1 GB file? a 10 GB file? a 100 GB file?
  * How will your system perform with 100 users? 10000 users? 1000000 users?
  * What documentation, websites, papers, etc did you consult in doing this assignment?
  * What third-party libraries or other tools does the system use? How did you
choose each library or framework you used?
  * How long did you spend on this exercise? If you had unlimited more time to spend on this, how would you spend it and how would you prioritize each item?
  * If you were to critique your code, what would you have to say about it?

The remainder of the files in your tree should be the source-code for your system.


### Questions 
##### How does your system work? (if not addressed in comments in source)
This system is comprised of four distinct components as described below.

###### Web Server
The web server is written in Java using Spring Boot and it handles the logic of the REST endpoint. It queries the persistence
layer for data.

###### Database Initializer 
The database initializer prepares the MongoDB instance by parsing a text file and inserting each line with an appropriate
key.

###### Database
The database is an instance of MongoDB running on port 27017 by default.

###### Load Balancer
The load balancer is an instance of HAProxy that distributes traffic over all instances of the web server to ensure
adequate distribution of load using a round robin load balancing algorithm.
  
##### What do we need to build your system?
To build this system you will need to install:
* HAProxy
* MongoDB 
* Gradle
* Java

##### How will your system perform with a 1 GB file? a 10 GB file? a 100 GB file?
This system should sufficiently handle any size file and is only limited by the disk space on the machine running it.
Due to hardware limitations, this system has not been tested with files larger than 4 GB.

##### How will your system perform with 100 users? 10000 users? 1000000 users?
This system can handle any number of simultaneous users without a substantial degradation in performance. This however
comes with the caveat that it is not able to dynamically scale. The system's ability to scale is based on the number of parallel
instances created at startup. 

##### What documentation, websites, papers, etc did you consult in doing this assignment?
   * [Designing Scalable Backend Infrastructures From Scratch](https://medium.com/@helloansh/designing-scalable-backend-infrastructures-from-scratch-af80f5767ccc)
   * [HaProxy vs Nginx](https://www.keycdn.com/support/haproxy-vs-nginx/)
   * [Accessing Data with MongoDB](https://spring.io/guides/gs/accessing-data-mongodb/)
   * [Microservices & API Gateways, Part 1: Why an API Gateway?](https://www.nginx.com/blog/microservices-api-gateways-part-1-why-an-api-gateway/)
   * [Scaling Django](https://djangobook.com/scaling-django/)
   
#####  What third-party libraries or other tools does the system use? How did you choose each library or framework you used?
###### Framework and Language Choice
The main framework used to build the web server is Spring. Spring was chosen for a few reasons
The first being performance; Spring Web is an established, high performance, testable library. Spring is used by many web developers
which means that there are many resources available to learn from. Another reason was the knowledge that
Java is one of the most frequently used languages at Circle. Creating an extensible web service was
a goal for this project. An important part of extensibility is having the developers to extend it.
###### Reverse Proxy/ Load Balancer 
Nginx and HAProxy were both considered as options for the reverse proxy and load balancer. Both offer similar performance under high 
traffic. However comparable their performance, many of Nginx's features
are proprietary. At the free level Nginx does not offer enough features to justify choosing it over
HAProxy. 
###### Persistence Layer
MongoDB was chosen for persistent storage. Mongo is one of the most noSQL databases available. It is noted for its ability to 
scale with large datasets. 



##### How long did you spend on this exercise? If you had unlimited more time to spend on this, how would you spend it and how would you prioritize each item?
I spent approximately two weeks on this assignment, of those two weeks I would estimate that I spent 15-20 hours working on this.
 If I had more time on this I would have explored using AWS. AWS provides a great number of services that could 
 improve scalability. These include dynamic scaling and their API gateway. Another area I would have spent more time would 
 have been scaling MongoDB. Out of the box Mongo is scalable but running parallel instances of it would yield a more scalable service.
 The web service itself should be sufficiently scalable so the highest priority in scaling is the load balancing and persistence layer. 

##### If you were to critique your code, what would you have to say about it?
Unit test coverage should be higher than it is. It was very difficult to achieve test coverage for LineRepository which 
is an interface used by Spring to define the queries that can be made to MongoDB. Starting and stopping the MongoDB instance 
for the test proved difficult. 
