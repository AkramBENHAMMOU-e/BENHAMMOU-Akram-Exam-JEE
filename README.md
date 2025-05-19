# Credit Management System

A comprehensive web application for managing bank credits, built with Spring Boot and Angular.

## Features

- **Client Management**: Add, view, edit, and delete clients
- **Credit Management**: Manage different types of credits (Personal, Real Estate, Professional)
- **Credit Status Tracking**: Track credit status (In Progress, Accepted, Rejected)
- **Repayment Management**: Track and manage credit repayments

## Project Structure

The project is divided into two main parts:

### Backend (Spring Boot)

- RESTful API built with Spring Boot
- JPA/Hibernate for database operations
- H2 in-memory database for data storage
- Layered architecture (Controller, Service, Repository)

### Frontend (Angular)

- Modern UI built with Angular 19
- Bootstrap for responsive design
- Component-based architecture
- Reactive forms for data input
- Service layer for API communication

## Running the Application

### Backend

1. Navigate to the backend directory:
   ```
   cd backend
   ```

2. Run the Spring Boot application:
   ```
   ./mvnw spring-boot:run
   ```
   
   The backend will start on http://localhost:8080

### Frontend

1. Navigate to the frontend directory:
   ```
   cd frontend
   ```

2. Install dependencies:
   ```
   npm install
   ```

3. Run the Angular application:
   ```
   npm start
   ```
   
   The frontend will start on http://localhost:4200

## API Documentation

The backend API documentation is available at http://localhost:8080/swagger-ui.html when the application is running.

## Credit Types

### Personal Credit
- For personal needs like car purchases, education, or home improvements
- Requires reason for the credit

### Real Estate Credit
- For purchasing property (apartments, houses, commercial properties)
- Tracks property type

### Professional Credit
- For business purposes
- Tracks company name and reason for the credit

## Future Enhancements

- User authentication and authorization
- Credit approval workflow
- Advanced reporting and analytics
- Email notifications
- Mobile application