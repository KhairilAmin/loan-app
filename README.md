# Loan Application

Welcome to the Loan Application project! This project is designed to manage loan applications and user information efficiently.

## Introduction

The Loan Application project is a Spring Boot application that provides RESTful APIs to manage users and their loan applications. It includes functionalities to retrieve user information based on their ID.


## Technologies Used

- Java
- JWT
- Spring Boot
- Lombok
- Maven

## Getting Started

To get a local copy up and running, follow these simple steps.

### Prerequisites

- Java 11 or higher
- Maven

### Installation

1. Clone the repository
   ```sh
   git clone https://github.com/KhairilAmin/loan-application.git
   ```
2. Navigate to the project directory
   ```sh
   cd loan-application
   ```
3. Install the dependencies
   ```sh
   mvn clean install
   ```
4. Run the application
   ```sh
   mvn spring-boot:run
   ```

## API Endpoints
## API Documentation

### Auth

#### Register Staff and Admin

Request :

- Endpoint : ```/api/v1/auth/signup```
- Method : POST
- Header :
    - Content-Type: application/json
    - Accept: application/json
- Body :

```json
{
  "email": "string",
  "password": "string"
}
```

Response :

```json
{
  "message": "string",
  "data": {
    "email": "string",
    "role": [
      "admin",
      "staff"
    ]
  }
}
```
#### Register Customer

Request :

- Endpoint : ```/api/v1/auth/signup/customer```
- Method : POST
- Header :
    - Content-Type: application/json
    - Accept: application/json
- Body :

```json
{
    "email" : "string",
    "password" : "",
    "data" : {
        "firstName" : "String",
        "lastName" : "String",
        "dateOfBirth": "Date",
        "phone" : "String",
        "status" : ""        
    }
}
```

Response :

```json
{
  "message": "string",
  "data": {
    "email": "string",
    "role": [
      "admin",
      "staff"
    ]
  }
}
```
#### Login

Request :

- Endpoint : ```/api/v1/auth/signin```
- Method : POST
- Header :
    - Content-Type: application/json
    - Accept: application/json
- Body :

```json
{
  "email": "string",
  "password": "string"
}
```

Response :

```json
{
  "message": "string",
  "data": {
    "email": "string",
    "role": [
      "admin",
      "staff"
    ],
    "token": "string"
  }
}
```

### User
#### Get User By Id 
Request

- Endpoint : ```/api/v1/users/{id}```
- Method : GET
    - Accept: application/json
- Response :

```json
{
  "message": "string",
  "data": {
    "email": "string",
    "role": [
      "admin",
      "staff"
    ]
  }
}
```

### Customer

#### Get Customer 

Request

- Endpoint : ```/api/v1/customers/{id}```
- Method : GET
    - Accept: application/json
- Response :

```json
{
  "message": "string",
  "data": {
    "id": "250b8bb1-7d55-458e-b30f-76c7307399bc",
    "firstName": null,
    "lastName": null,
    "dateOfBirth": null,
    "phone": null,
    "status": null
  }
}
```

#### Get All Customer
Request

- Endpoint : ```/api/v1/customers```
- Method : GET
    - Accept: application/json
- Response :

```json
{
  "message": "Successfully fetch user",
  "data": [
    {
      "id": "250b8bb1-7d55-458e-b30f-76c7307399bc",
      "firstName": null,
      "lastName": null,
      "dateOfBirth": null,
      "phone": null,
      "status": null
    },
    {
      "id": "250b8bb1-7d55-458e-b30f-76c7307399bc",
      "firstName": null,
      "lastName": null,
      "dateOfBirth": null,
      "phone": null,
      "status": null
    }
  ]
}
```

#### Update Customer 

Request :

- Endpoint : ```/api/v1/customers```
- Method : PUT
- Header :
    - Content-Type: application/json
    - Accept: application/json
- Body :

```json
{
  "id": "xxx",
  "firstName": "rifqi",
  "lastName": "ramadhan",
  "dateOfBirth": "0000-01-01",
  "phone": "087123",
  "status": 1
}
```

Response :

```json
{
  "message": "string",
  "data": {
    "id": "250b8bb1-7d55-458e-b30f-76c7307399bc",
    "firstName": null,
    "lastName": null,
    "dateOfBirth": null,
    "phone": null,
    "status": null
  }
}
```

#### Delete Customer ```*Soft Delete```

Request :

- Endpoint : ```/api/v1/customers/{id}```
- Method : DELETE
- Header :
    - Accept: application/json

Response :

```json
{
  "message": "string",
  "data": null
}
```

#### Upload Avatar

Request :

- Endpoint : ```/api/v1/customers/{customerId}/upload/avatar```
- Method : POST
- Header :
    - Content-type: multipart/form-data
- Form-Data:

```
  avatar : image
```

Response :

```json
{
  "message": "string",
  "data": {
    "name": "string",
    "url": "/api/v1/customers/xxx/avatar"
  }
}
```


### Instalment Type

#### Create Instalment Type 

Request :

- Authorize : `ADMIN & STAFF ONLY`
- Endpoint : ```/api/v1/instalment-types```
- Method : POST
- Header :
    - Content-Type: application/json
    - Accept: application/json
- Body :

```json
{
  "instalmentType": "ONE_MONTH"
}
```

Response :

```json
{
  "message": "string",
  "data": {
    "id": "58aa290c-a84b-48ab-9f18-3fbbeef6bb7d",
    "instalmentType": "ONE_MONTH"
  }
}
```

#### Get Instalment By Id

Request :

- Endpoint : ```/api/v1/instalment-types/{id}```
- Method : GET
    - Accept: application/json
- Response :

```json
{
  "message": "string",
  "data": {
    "id": "58aa290c-a84b-48ab-9f18-3fbbeef6bb7d",
    "instalmentType": "ONE_MONTH"
  }
}
```

#### Get All Instalment-type 

Request :

- Endpoint : ```/api/v1/instalment-types```
- Method : GET
    - Accept: application/json
- Response :

```json
{
  "message": "string",
  "data": [
    {
      "id": "58aa290c-a84b-48ab-9f18-3fbbeef6bb7d",
      "instalmentType": "ONE_MONTH"
    },
    {
      "id": "58aa290c-a84b-48ab-9f18-3fbbeef6bb2a",
      "instalmentType": "THREE_MONTHS"
    }
  ]
}
```

#### Update Instalment-type

Request :

- Authorize : `ADMIN & STAFF ONLY`
- Endpoint : ```/api/v1/instalment-types```
- Method : PUT
- Header :
    - Content-Type: application/json
    - Accept: application/json
- Body :

```json
{
  "id": "58aa290c-a84b-48ab-9f18-3fbbeef6bb7d",
  "instalmentType": "TWELVE_MONTHS"
}
```

Response :

```json
{
  "message": "string",
  "data": {
    "id": "58aa290c-a84b-48ab-9f18-3fbbeef6bb7d",
    "instalmentType": "ONE_MONTH"
  }
}
```

#### Delete Instalment type

Request :

- Endpoint : ```/api/v1/instalment-types/{id}```
- Method : DELETE
- Header :
    - Accept: application/json

Response :

```json
{
  "message": "string",
  "data": null
}
```

### Loan Type

#### Create Loan Type 

Request :

- Authorize : `ADMIN & STAFF ONLY`
- Endpoint : ```/api/v1/loan-types```
- Method : POST
- Header :
    - Content-Type: application/json
    - Accept: application/json
- Body :

```json
{
  "type": "Pinjaman Kredit Elektronik",
  "maxLoan": 10000000
}
```

Response :

```json
{
  "message": "string",
  "data": {
    "id": "58aa290c-a84b-48ab-9f18-3fbbeef6bb7d",
    "type": "Pinjaman Kredit Elektronik",
    "maxLoan": 10000000
  }
}
```

#### Get Loan By Id 

Request :

- Endpoint : ```/api/v1/loan-types/{id}```
- Method : GET
    - Accept: application/json
- Response :

```json
{
  "message": "string",
  "data": {
    "id": "58aa290c-a84b-48ab-9f18-3fbbeef6bb7d",
    "type": "Pinjaman Kredit Elektronik",
    "maxLoan": 10000000
  }
}
```

#### Get All Loan-type 

Request :

- Endpoint : ```/api/v1/loan-types```
- Method : GET
    - Accept: application/json
- Response :

```json
{
  "message": "string",
  "data": [
    {
      "id": "58aa290c-a84b-48ab-9f18-3fbbeef6bb7d",
      "type": "Pinjaman Kredit Elektronik",
      "maxLoan": 10000000
    },
    {
      "id": "58aa290c-a84b-48ab-9f18-3fbbeef6bb7d",
      "type": "Pinjaman Kredit Kendaraan",
      "maxLoan": 100000000
    }
  ]
}
```

#### Update Loan-type 

Request :

- Authorize : `ADMIN & STAFF ONLY`
- Endpoint : ```/api/v1/loan-types```
- Method : PUT
- Header :
    - Content-Type: application/json
    - Accept: application/json
- Body :

```json
{
  "id": "58aa290c-a84b-48ab-9f18-3fbbeef6bb7d",
  "type": "Pinjaman Kredit Kendaraan",
  "maxLoan": 100000000
}
```

Response :

```json
{
  "message": "string",
  "data": {
    "id": "58aa290c-a84b-48ab-9f18-3fbbeef6bb7d",
    "type": "Pinjaman Kredit Kendaraan",
    "maxLoan": 100000000
  }
}
```

#### Delete Loan type 

Request :

- Endpoint : ```/api/v1/loan-types/{id}```
- Method : DELETE
- Header :
    - Accept: application/json

Response :

```json
{
  "message": "string",
  "data": null
}
```

### Transaction

#### Request Loan

Request :

- Authorize : `CUSTOMER ONLY`
- Endpoint : ```/api/v1/transactions```
- Method : POST
- Header :
    - Content-Type: application/json
    - Accept: application/json
- Body :

```json
{
  "loanType": {
    "id": "d781644b-a73f-4d54-ae94-a7b59bff05fc"
  },
  "instalmentType": {
    "id": "3e7023c8-77a8-4311-a195-3958a14a20a1"
  },
  "customer": {
    "id": "250b8bb1-7d55-458e-b30f-76c7307399bc"
  },
  "nominal": 10000000
}
```

Response : ```*CREATE DTO IF YOU WANT DIFFERENT RESPONSE*```

```json
{
  "message": "string",
  "data": {
    "id": "bec5ca14-a867-4f34-a919-62dd6f941dec",
    "loanTypeId": "d781644b-a73f-4d54-ae94-a7b59bff05fc",
    "instalmentTypeId": "3e7023c8-77a8-4311-a195-3958a14a20a1",
    "customerId": "250b8bb1-7d55-458e-b30f-76c7307399bc",
    "nominal": 1.0E7,
    "approvedAt": null,
    "approvedBy": null,
    "approvalStatus": null,
    "transactionDetailResponses": null,
    "createdAt": 1661106557370,
    "updatedAt": null
  }
}
```

#### Get Transaction By id

Request :

- Endpoint : ```/api/v1/transactions/{id}```
- Method : GET
    - Accept: application/json
- Response :

```json
{
  "message": "string",
  "data": {
    "id": "bec5ca14-a867-4f34-a919-62dd6f941dec",
    "loanTypeId": "d781644b-a73f-4d54-ae94-a7b59bff05fc",
    "instalmentTypeId": "3e7023c8-77a8-4311-a195-3958a14a20a1",
    "customerId": "250b8bb1-7d55-458e-b30f-76c7307399bc",
    "nominal": 1.0E7,
    "approvedAt": 1661091574279,
    "approvedBy": "rifqyomp@gmail.com",
    "approvalStatus": "APPROVED",
    "transactionDetailResponses": [
      {
        "id": "fd7ff39e-7a4d-421d-92f3-987b9c411d33",
        "transactionDate": 1661091574279,
        "nominal": 1.03E7,
        "loanStatus": "PAID",
        "createdAt": 1661002579786,
        "updatedAt": 1661091574307
      }
    ],
    "createdAt": 1661106557370,
    "updatedAt": null
  }
}
```

#### Approve Transaction Request By Admin Id

Request :

- Authorize : `ADMIN/STAFF ONLY`
- Endpoint : ```/api/v1/transactions/{adminId}/approve```
- Method : PUT
- Header :
    - Content-Type: application/json
    - Accept: application/json
- Body : ```CREATE DTO FOR DIFFERENT REQUEST```

```json
{
  "loanTransactionId": "9abfd33f-de4a-4d44-834d-26ba2860e7fd",
  "interestRates": 3
}
```

Response : ```CREATE DTO IF YOU WANT DIFFERENT RESPONSE```

```json
{
  "message": "string",
  "data": {
    "id": "bec5ca14-a867-4f34-a919-62dd6f941dec",
    "loanTypeId": "d781644b-a73f-4d54-ae94-a7b59bff05fc",
    "instalmentTypeId": "3e7023c8-77a8-4311-a195-3958a14a20a1",
    "customerId": "250b8bb1-7d55-458e-b30f-76c7307399bc",
    "nominal": 1.0E7,
    "approvedAt": 1661106557370,
    "approvedBy": "rifqyomp@gmail.com",
    "approvalStatus": "APPROVED",
    "transactionDetails": [
      {
        "id": "fd7ff39e-7a4d-421d-92f3-987b9c411d33",
        "transactionDate": 1661091574279,
        "nominal": 1.03E7,
        "loanStatus": "PAID",
        "createdAt": 1661002579786,
        "updatedAt": 1661091574307
      }
    ],
    "createdAt": 1661106557370,
    "updatedAt": 1661108557312
  }
}
```

#### Pay Instalment

Request :

- Endpoint : ```/api/v1/transactions/{trxId}/pay```
- Method : PUT
- Header :
    - Content-type: "multipart/form-data"
      Request :

```

Response :

```json
{
  "message": "string",
  "data": null
}
```
## License

Distributed under the MIT License. See `LICENSE` for more information.

---

Thank you for visiting! If you have any questions or suggestions, feel free to open an issue or contact the project maintainers.
```
