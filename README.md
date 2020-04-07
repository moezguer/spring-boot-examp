# Spring-Boot with Mysql with Docker-Compose

> A sample Spring-Boot project with unit tests 


## Installation

- Create a project from version control tab.

## Clone

- Clone this repo to your local machine using `https://github.com/moezguer/spring-boot-examp.git`

## Setup

From the terminal of the IDE, in order to have the docker containers to be installed, 
first run the command
`cd spring-boot-examp`
then
`docker-compose up`

Now the app is running. Open your browser and call the adress below:
`http://localhost:8080/books`
This will show the initialized book samples.

---

**Note:** All the functions can be used via Postman collection in the project folder

---

## Closing the docker images

From the terminal of the IDE, in order to have the docker containers to be installed, run the command
`docker-compose down`

---

## Features
It is a sample rest api implementation with the features below;
	-Mysql
	-H2 DB used for unit tests
	-Unit Tests implemented for Controller
	-Docker is used for easy setup
	-CRUD operations
	-OneToMany Relations
	-SpringBoot
	-JPA
	-Maven checkstyle Plugin
	-Postman

---

## Testing
The controller method can be studied via Postman. Please refer to the postman collection and env. variable json in the project

---

## Documentation
API documentation has been done via Postman collection. Please refer to the collection given in the project folder.
