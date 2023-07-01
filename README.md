
# Employee Management Application

This is an Employee Management application that consists of a backend developed using Spring Boot and a frontend developed using Angular.

## Features

- Add new employees with their details.
- View a list of all employees with their information.
- Delete employees from the system.

  Incoiming:
- Update employee details including their name, email, and position.
- Search for employees by name or position.

## Technologies Used

- Backend:
  - Spring Boot: A Java-based framework for building robust and scalable web applications.
  - Spring Data JPA: Provides support for database operations using the Java Persistence API (JPA).
  - H2DB: A popular open-source relational database management system.

- Frontend:
  - Angular: A TypeScript-based open-source framework for building user interfaces.
  - Bootstrap: A CSS framework for building responsive and mobile-first websites.

## Getting Started

To get started with the Employee Management application, follow these steps:

1. Clone the repository to your local machine.
   ```
   git clone <repository-url>
   ```

2. Backend setup:
   - Install Java Development Kit (JDK) if not already installed.
   - Update the database configuration in the `backend/src/main/resources/application.properties` file with your H2DB credentials.

3. Frontend setup:
   - Install Node.js if not already installed.
   - Navigate to the `frontend` directory and run the following command to install dependencies:
     ```
     npm install
     ```

4. Start the backend server:
   - Navigate to the `backend` directory and run the following command:
     ```
     ./mvnw spring-boot:run
     ```

5. Start the frontend development server:
   - Navigate to the `frontend` directory and run the following command:
     ```
     ng serve
     ```

6. Open your web browser and access the application at `http://localhost:4200`.

