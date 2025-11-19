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
Account Endpoint

| Operation        | HTTP Method | Endpoint                        | Request Body Example                                                      | Response Example                                                                    |
| ---------------- | ----------- | ------------------------------- | ------------------------------------------------------------------------- | ----------------------------------------------------------------------------------- |
| Create Account   | POST        | `/api/accounts`                 | `json { "name": "John Doe", "balance": 5000, "accountType": "SAVINGS" } ` | `json { "accountNumber": "ACC123", "name": "John Doe", "balance": 5000 } `          |
| Get Account      | GET         | `/api/accounts/{accountNumber}` | –                                                                         | `json { "accountNumber": "ACC123", "name": "John Doe", "balance": 5000 } `          |
| Update Account   | PUT         | `/api/accounts/{accountNumber}` | `json { "name": "Jane Doe", "accountType": "CURRENT" } `                  | `json { "accountNumber": "ACC123", "name": "Jane Doe", "balance": 5000 } `          |
| Delete Account   | DELETE      | `/api/accounts/{accountNumber}` | –                                                                         | `json { "message": "Account deleted successfully." } `                              |
| Get All Accounts | GET         | `/api/accounts`                 | –                                                                         | `json [ { "accountNumber": "ACC123", "name": "John Doe", "balance": 5000 }, ... ] ` |


Transaction Endpoint

| Operation           | HTTP Method | Endpoint                                     | Request Body Example                                                       | Response Example                                                                                                     |
| ------------------- | ----------- | -------------------------------------------- | -------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------------------------------- |
| Deposit             | PUT         | `/api/accounts/{accountNumber}/deposit`      | `json { "amount": 1000 } `                                                 | `json { "transactionId": "TXN001", "accountNumber": "ACC123", "amount": 1000, "type": "DEPOSIT", "balance": 6000 } ` |
| Withdraw            | PUT         | `/api/accounts/{accountNumber}/withdraw`     | `json { "amount": 500 } `                                                  | `json { "transactionId": "TXN002", "accountNumber": "ACC123", "amount": 500, "type": "WITHDRAW", "balance": 5500 } ` |
| Transfer            | POST        | `/api/accounts/transfer`                     | `json { "fromAccount": "ACC123", "toAccount": "ACC456", "amount": 2000 } ` | `json { "transactionId": "TXN003", "fromAccount": "ACC123", "toAccount": "ACC456", "amount": 2000 } `                |
| Transaction History | GET         | `/api/accounts/{accountNumber}/transactions` | –                                                                          | `json [ { "transactionId": "TXN001", "type": "DEPOSIT", "amount": 1000, "date": "2025-11-19" }, ... ] `              |

## API testing via Postman


## Unit Test Coverage
mvn test
The project has been tested using JUnit 5 and Mockito. Below is the JaCoCo coverage report:

- Overall coverage: 87%
- Service layer: 79%
- Bean layer: 85%
- DTO layer: 96%
- Exception and Controller layers: 100%

