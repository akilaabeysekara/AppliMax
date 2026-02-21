3SPRING BOOT POS SYSTEM – IJSE

Student Name: Akila Abeysekara
Batch: GDSE74
Institute: IJSE – Institute of Software Engineering

Project Description

This project is a Point of Sale (POS) System developed using Spring Boot for the backend and HTML, CSS, JavaScript, and AJAX for the frontend.

The system allows users to manage customers, items, orders, and payments through a web-based interface. The frontend communicates with the backend using REST APIs.

Technologies Used
Backend

Spring Boot

Spring Data JPA

Hibernate

REST API

MySQL

Maven

Frontend

HTML

CSS

JavaScript

jQuery

AJAX

System Features
User Login

Secure login system

Username and password validation

Customer Management

Add customer

Update customer

Delete customer

Search customer

Item Management

Add items

Update items

Delete items

Search items

Order Management

Create orders

Add items to cart

Auto calculate total

Payment Management

View payment history

Search payments

System Architecture

The system follows Layered Architecture.

Controller Layer
Service Layer
Repository Layer
Database
Controller Layer

Handles HTTP requests from the frontend.

Example:

POST /api/v1/customer
Service Layer

Contains business logic and validations.

Repository Layer

Handles database communication using JPA.

Database

Database used:

MySQL

Tables used in this project:

User

Customer

Item

Orders

Order Details

Payment

Spring Boot automatically creates tables using:

spring.jpa.hibernate.ddl-auto=update
Validation

Input validation is implemented using Jakarta Validation.

Example:

@NotBlank
@Pattern
@Size

This ensures:

Valid customer names

Valid phone numbers

Required fields are filled

Exception Handling

A global exception handler is implemented.

Handled exceptions include:

Validation errors

Business errors

Server errors

Example response:

{
  "status": 400,
  "message": "Customer ID already exists",
  "data": null
}
API Response Structure

All APIs return a standard response format using APIResponse.

Example:

{
  "status": 200,
  "message": "Customer saved successfully",
  "data": null
}

Benefits:

Consistent responses

Easier frontend handling

Clean API design

Frontend Communication

The frontend communicates with the backend using AJAX.

Example:

$.ajax({
   type: "POST",
   url: "http://localhost:8080/api/v1/customer",
   contentType: "application/json",
   data: JSON.stringify(customer)
});

Flow:

User Action
↓
JavaScript
↓
AJAX Request
↓
Spring Boot Controller
↓
Service Layer
↓
Database
↓
Response back to Frontend
Project Structure
POS-System
│
├── POS_Back_End
│   ├── controller
│   ├── service
│   ├── repository
│   ├── entity
│   ├── dto
│   └── exception
│
├── POS_Front_End
│   ├── pages
│   ├── css
│   └── js
Application Setup
1. Clone the Repository
git clone https://github.com/YOUR_USERNAME/YOUR_REPOSITORY.git
2. Configure Database
spring.datasource.url=jdbc:mysql://localhost:3306/pos_sys
spring.datasource.username=root
spring.datasource.password=yourpassword
3. Run Backend
mvn spring-boot:run
4. Run Frontend

Open:

index.html
System Demonstration Video

Add your YouTube video here.

https://youtube.com/YOUR_VIDEO_LINK

Or embed it:

<iframe width="800" height="450"
src="https://www.youtube.com/embed/YOUR_VIDEO_ID"
frameborder="0"
allowfullscreen>
</iframe>
Author

Akila Abeysekara
Graduate Diploma in Software Engineering
IJSE – Institute of Software Engineering

If you want, I can also give you a very impressive README (with badges, screenshots, architecture diagram, API docs) that can make your GitHub look like a professional project.

I want document with # s also with embedded YouTube link

Here is a clean GitHub-style README.md with proper # headings and an embedded YouTube video section. You can paste this directly into your repository.

SPRING BOOT POS SYSTEM – IJSE
Student Name

Akila Abeysekara

Batch

(Add Your Batch Number)

Institute

IJSE – Institute of Software Engineering

🎥 System Demonstration Video

Below is the demonstration video explaining the POS System.

YouTube Link

https://youtube.com/YOUR_VIDEO_LINK

Embedded Video
<iframe width="900" height="500"
src="https://www.youtube.com/embed/YOUR_VIDEO_ID"
title="Spring Boot POS System"
frameborder="0"
allowfullscreen>
</iframe>
📌 Project Overview

This project is a Point of Sale (POS) System developed using Spring Boot for the backend and HTML, CSS, JavaScript, AJAX for the frontend.

The system allows managing:

Customers

Items

Orders

Payments

User Login

The frontend communicates with the backend through REST APIs.

🧰 Technologies Used
Backend

Spring Boot

Spring Data JPA

Hibernate

REST API

MySQL

Maven

Frontend

HTML

CSS

JavaScript

jQuery

AJAX

🏗 System Architecture

The project follows Layered Architecture.

Controller Layer
      ↓
Service Layer
      ↓
Repository Layer
      ↓
Database (MySQL)
Controller Layer

Handles HTTP requests from the frontend.

Example:

POST /api/v1/customer
Service Layer

Contains business logic and validations.

Repository Layer

Handles database operations using JPA.

🗄 Database

Database used:

MySQL

Tables in the system:

User

Customer

Item

Orders

Order Detail

Payment

Spring Boot automatically creates tables using:

spring.jpa.hibernate.ddl-auto=update
✔ Validation

Input validation is implemented using Jakarta Validation.

Example:

@NotBlank
@Pattern
@Size

Example from CustomerDTO:

@NotBlank
@Pattern(regexp = "^\\p{L}+([ '\\-]\\p{L}+)*$")
private String name;

This ensures:

Required fields are not empty

Correct data formats

Clean data stored in database

⚠ Exception Handling

The system uses a Global Exception Handler.

Handled exceptions include:

Validation errors

Business errors

Server errors

Example response:

{
  "status": 400,
  "message": "Customer ID already exists",
  "data": null
}

This keeps the API clean and predictable.

📦 API Response Wrapper

All APIs return responses using the APIResponse class.

Example:

{
  "status": 200,
  "message": "Customer saved successfully",
  "data": null
}

Benefits:

Standard response format

Easier frontend handling

Cleaner API design

🔄 Frontend ↔ Backend Communication

Communication is done using AJAX requests.

Example:

$.ajax({
    type: "POST",
    url: "http://localhost:8080/api/v1/customer",
    contentType: "application/json",
    data: JSON.stringify(customer)
});

Flow:

User Action
↓
JavaScript
↓
AJAX Request
↓
Spring Boot Controller
↓
Service Layer
↓
Repository
↓
Database
↓
Response returned to frontend
⭐ System Features
Login System

Username and password authentication

Session stored in browser

Customer Management

Add customer

Update customer

Delete customer

Search customers

Item Management

Add item

Update item

Delete item

Search items

Order Management

Create orders

Add items to cart

Calculate totals automatically

Payment Management

View payment history

Search payments

📂 Project Structure
POS-System
│
├── POS_Back_End
│   ├── controller
│   ├── service
│   ├── repository
│   ├── entity
│   ├── dto
│   └── exception
│
└── POS_Front_End
    ├── pages
    ├── css
    └── js
⚙ How to Run the Project
1 Clone Repository
git clone https://github.com/YOUR_USERNAME/YOUR_REPOSITORY.git
2 Configure Database
spring.datasource.url=jdbc:mysql://localhost:3306/pos_sys
spring.datasource.username=root
spring.datasource.password=yourpassword
3 Run Backend
mvn spring-boot:run
4 Run Frontend

Open in browser:

index.html
👨‍💻 Author

Akila Abeysekara
Graduate Diploma in Software Engineering
