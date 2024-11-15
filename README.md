# Bankaya challenge - pokemon soap api

This project contains a **Pokemon Service** running on **Spring Boot** connected to a **PostgreSQL** database 
and integrated with **SonarQube** for code quality analysis. The project uses Docker and Docker Compose 
for easy setup and execution.

## Prerequisites

- Docker and Docker Compose installed on your machine.
    - [Install Docker](https://docs.docker.com/get-docker/)
    - [Install Docker Compose](https://docs.docker.com/compose/install/)
    - [Install SoapUi](https://www.soapui.org/downloads/soapui/)

## Getting Started

### 1. Clone the repository

Clone this project to your local machine:

```bash
git clone git@github.com:JAlayon/pokemon-challenge.git
cd bankaya-challenge
```

### 2. Set up Docker services with Docker Compose

This project uses Docker Compose to set up the following services:

* PostgreSQL - A PostgreSQL database to store Pokemon-related data.
* SonarQube - For analyzing the project's code quality.
* Pokemon Service - The Spring Boot-based service that interacts with the PostgreSQL database.


### 3. Build and Run the services

#### a. Build and run the Docker containers:

Run the following command to build and start all services:

```bash
docker-compose up --build
```


#### b. Verify services are running:

Once the containers are up and running, you can access:

* SonarQube: Open a browser and go to http://localhost:9000. The default credentials are:

  - Username: admin
  
  - Password: admin

  Note: Once done here, it will ask you to change the password.


* Pokemon Service: The service will be available at http://localhost:8080.
* Check if is healthy at: http://localhost:8080/actuator/health


####  c. Test service operations

To test our service operations, we can use the soap-ui project under `soapui` folder.
Just import the xml file in soapUI application to start testing.


### 3. Accessing Database


To connect to the PostgreSQL database running in the Docker container, you can use the following connection details:

* Host: localhost or postgres (for services running within the Docker network)
* Port: 5432
* Database: pokemon_db
* Username: pokemon_user
* Password: password123

Note: All these values are in the docker-compose file.



### 4. Analyzing Code with SonarQube

Once the services are running, you can trigger an analysis by running the Maven.
Before to do this, it's necessary to generate your auth-token in sonar. 
You can follow up this link: [SonarQube Managing Tokens](https://docs.sonarsource.com/sonarqube/latest/user-guide/managing-tokens/)

To perform sonar analysis, we run this command:

```bash
./mvnw clean verify sonar:sonar -Dsonar.login=<your-token>
```