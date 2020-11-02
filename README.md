# monitor-internet-connection

This application monitors internet connection and produces an excel at the end of the day with the instance when internet was not available.

# Architecture

This is simple spring boot application. The application leverages State Design Framework to perform action for different states of internet.
It caches the instances during the day when internet was not connected and at the end of the day writes this report to a excel file.
There are two scheduled jobs,
- First one triggers every second and logs the state when internet was not available with start and end time in cache
- Second one triggers at the end of the day to write the instances when internt was down from cache to an excel file which could be handed over to ISP.


# Technology Stack
* Java 8
* Spring Boot
* Lombok
* JUnit
* Mockito
* Tomcat 8


# How to build
mvn clean install


# How to run
java -jar target/monitor-internet-connection-0.0.1-SNAPSHOT.jar

Alternatively, if start git bash inside the folder monitor-internet-connection and execute below command
mvn spring-boot:run

# How to import in IDE
you can import in IDE as a maven project


# Improvements Possible

Considering time, I have not included below points, these are the possible enhancements


* To verify internet connection, instead of just connecting to Internet, we should get the upload and download speed of the internet, that gives us better idea how the internet is performing at the point of time.
* We should be able to provide ISP with upload and download speeds for the instances, when Teams/Skype drops off.
* Service to verify Internet connection could be build as a REST API, as it could also be used for verifying Internet connection other than this application.
* More unit test cases could be added
* Along with instances, we can also provide the aggregate results, with how many instances and total duration, in Excel




	
	