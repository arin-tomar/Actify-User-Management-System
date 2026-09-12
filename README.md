# Actify User Management System

A Spring Boot based User Management System with JWT authentication and Role-Based Access Control.

## Technologies

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- Spring Security
- JWT
- H2 Database
- Maven

## Features

- JWT based authentication
- Role-Based Access Control
- Admin user management
- Manager user and task management
- User profile and task access
- BCrypt password encryption
- Input validation
- H2 relational database
- Sample data initialization

## Roles

### Admin

- Create users
- View users
- Update users
- Delete users
- Assign roles

### Manager

- View all users
- View tasks
- Assign tasks

### User

- View own profile
- View assigned tasks

## Run the Application

1. Clone the repository.
2. Open the project in Eclipse or any Java IDE.
3. Make sure Java 17 and Maven are configured.
4. Run `ActifyApplication.java`.
5. Application will start on:

`http://localhost:8080`

## Login API

### Endpoint

`POST /api/auth/login`

### Request

```json
{
  "email": "admin@actify.com",
  "password": "Password@123"
}
