#  Banking System Simulator

**Spring Boot Monolith with MongoDB** – Backend Mini Project 


## Overview
This project is a Spring Boot–based backend system that simulates real-world banking operations.  
It allows account management, deposits, withdrawals, transfers, and transaction tracking.  
MongoDB is used for persistence, and APIs can be tested using Postman.


## Features
- Create, update, delete bank accounts
- Deposit money
- Withdraw money (with balance validation)
- Transfer money between accounts
- View transaction history for each account
- Global exception handling
- Logging using SLF4J
- Unit tests with JUnit 5 & Mockito
- Clean layered architecture: Controller → Service → Repository → Model



## Project Structure
com.bankingsystem
├── controller # REST APIs
├── service # Business logic
├── repository # MongoDB interfaces
├── exception # Custom exceptions & global handler
├── model  # Entities & DTOs




## Setup Instructions
1. Install required tools: Java 17+, Maven, MongoDB, Postman
2. Start MongoDB:
mongod


## Configure application.properties:
properties
spring.data.mongodb.uri=mongodb://localhost:27017/banking_system
spring.data.mongodb.database=banking_system
server.port=8080

## Run the application:
mvn clean install
mvn spring-boot:run



## Test APIs using Postman or browser (GET endpoints)

API Endpoints
Operation	HTTP Method	Endpoint
Create Account	POST	/api/accounts
Get Account	GET	/api/accounts/{accountNumber}
Deposit	PUT	/api/accounts/{accountNumber}/deposit
Withdraw	PUT	/api/accounts/{accountNumber}/withdraw
Transfer	POST	/api/accounts/transfer
Transactions	GET	/api/acc

## API testing via Postman

## Unit test coverage report (70%+ recommended).
Unit tests for service and repository layers using JUnit 5 + Mockito.
mvn test
