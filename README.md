
# Book Rental Management System

![Backend Build](https://img.shields.io/badge/backend-SpringBoot-blue)
![Frontend Build](https://img.shields.io/badge/frontend-React-green)
![Java Version](https://img.shields.io/badge/java-17-orange)
![Node Version](https://img.shields.io/badge/node-18-brightgreen)

A full-stack application for managing books and rentals. Built with **Spring Boot** (backend) and **React.js** (frontend).

---

## Table of Contents

- [Features](#features)
- [Setup Instructions](#setup-instructions)
- [API Endpoints](#api-endpoints)
- [Assumptions & Additional Features](#assumptions--additional-features)
- [Project Structure](#project-structure)

---

## Features

- CRUD operations for books.
- Create and manage rentals.
- Auto-update book availability based on rental return dates.
- Display rental history with status (Returned / Need to return).
- Frontend with interactive book cards and modals.
- Unit tests for backend services and controllers.

---

## Setup Instructions

### Backend (Spring Boot)

1. **Clone the repository:**
   
   git clone <repo-url>
   cd Book_Rental_Management_System/backend
``

2. **Set up MySQL database:**

   
   CREATE DATABASE book_rental_db;
   ```

3. **Configure `application.properties`:**

   
   spring.datasource.url=jdbc:mysql://localhost:3306/book_rental_db
   spring.datasource.username=<your-username>
   spring.datasource.password=<your-password>
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   ```

4. **Build and run the backend:**

   
   mvn clean install
   mvn spring-boot:run
   ```

5. Backend runs on `http://localhost:8080`.

---

### Frontend (React.js)

1. Navigate to the frontend directory:

   
   cd ../frontend
   ```

2. Install dependencies:

  
   npm install
   ```

3. Start the development server:

   
   npm start
   ```

4. Frontend runs on `http://localhost:3000`.

---

## API Endpoints

### Book Endpoints

| Method | URL             | Description          | Request Body                          |
| ------ | --------------- | -------------------- | ------------------------------------- |
| GET    | /api/books      | Get all books        | -                                     |
| GET    | /api/books/{id} | Get book by ID       | -                                     |
| POST   | /api/books      | Add new book         | `{ title, author, genre, available }` |
| PUT    | /api/books/{id} | Update existing book | `{ title, author, genre, available }` |
| DELETE | /api/books/{id} | Delete book by ID    | -                                     |

### Rental Endpoints

| Method | URL               | Description            | Request Body                                                             |
| ------ | ----------------- | ---------------------- | ------------------------------------------------------------------------ |
| GET    | /api/rentals      | Get all rentals        | -                                                                        |
| GET    | /api/rentals/{id} | Get rental by ID       | -                                                                        |
| POST   | /api/rentals      | Create new rental      | `{ book: {id}, userName, userEmail, userPhone, rentalDate, returnDate }` |
| PUT    | /api/rentals/{id} | Update existing rental | `{ book: {id}, userName, userEmail, userPhone, rentalDate, returnDate }` |

---

## Assumptions & Additional Features

### Assumptions

* Each book can be rented by only one user at a time.
* Rentals have a mandatory return date.
* User information (name, email, phone) is collected during rental.
* Dates are stored as ISO strings in JSON for frontend communication.

### Additional Features

* Automatic book availability update after return date.
* Frontend rental modal for collecting user details.
* Rental history UI with color-coded status (Returned / Need to return).
* Validation & error handling (backend and frontend).
* Unit testing for services and controllers.
* Frontend refresh functionality for books and rentals.

---

## Project Structure


Book_Rental_Management_System/
│
├── backend/                # Spring Boot backend
│   ├── src/main/java/
│   │   └── com/example/Book_Rental_Management_System/
│   │       ├── Controller/
│   │       ├── Model/
│   │       ├── Repository/
│   │       └── Service/
│   ├── pom.xml
│   └── application.properties
│
├── frontend/               # React frontend
│   ├── src/
│   │   ├── components/
│   │   ├── App.js
│   │   └── index.js
│   ├── package.json
│   └── public/
``

## Links

* **Backend:** [http://localhost:8080](http://localhost:8080)
* **Frontend:** [http://localhost:3000](http://localhost:3000)


 
