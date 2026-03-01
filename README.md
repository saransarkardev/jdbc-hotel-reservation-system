# 🏨 JDBC Hotel Reservation System

## 📌 Project Overview

The **JDBC Hotel Reservation System** is a console-based Java application that allows users to manage hotel room reservations using a MySQL database.
It demonstrates core backend concepts such as database connectivity, CRUD operations, and transaction handling using **JDBC**.

---

## 🚀 Features

* Reserve a room
* View all reservations
* Get room number by reservation ID
* Update reservation details
* Delete reservation
* Exit system with loading animation

---

## 🛠 Tech Stack

* **Language:** Java
* **Database:** MySQL
* **API:** JDBC
* **Concepts Used:** OOP, SQL, Exception Handling, Loops, Conditionals

---

## 🗄 Database Schema

### Table: `reservations`

| Column           | Type                              |
| ---------------- | --------------------------------- |
| reservation_id   | INT (Primary Key, Auto Increment) |
| guest_name       | VARCHAR                           |
| room_number      | INT                               |
| contact_number   | VARCHAR                           |
| reservation_date | TIMESTAMP                         |

---

## ▶ How to Run

### 1️⃣ Clone Repository

```bash
git clone https://github.com/yourusername/jdbc-hotel-reservation-system.git
```

---

### 2️⃣ Create Database

```sql
CREATE DATABASE hotel_db;
USE hotel_db;
```

---

### 3️⃣ Create Table

```sql
CREATE TABLE reservations (
    reservation_id INT AUTO_INCREMENT PRIMARY KEY,
    guest_name VARCHAR(100),
    room_number INT,
    contact_number VARCHAR(15),
    reservation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

---

### 4️⃣ Update Database Credentials

Edit inside Java file:

```java
private static final String url = "jdbc:mysql://localhost:3306/hotel_db";
private static final String username = "root";
private static final String password = "your_password";
```

---

### 5️⃣ Run Program

Compile and run:

```bash
javac HotelReservationSystem.java
java HotelReservationSystem
```

---

## 📸 Sample Menu

```
HOTEL MANAGEMENT SYSTEM
1. Reserve a Room
2. View Reservations
3. Get Room Number
4. Update Reservations
5. Delete Reservations
0. Exit
```

---

## 🔐 Security Notes

* Uses JDBC connection
* SQL execution via statements
* Input validation applied
* Exception handling implemented

---

## 📚 Learning Outcomes

This project helps understand:

* JDBC workflow
* SQL CRUD operations
* Database connection handling
* ResultSet processing
* Console-based application design

---

## 📈 Future Improvements

* Replace Statement with PreparedStatement (SQL Injection protection)
* Add login system for admin
* Implement room availability validation
* Add transaction management
* Build GUI (JavaFX / Swing)
* Convert to Spring Boot REST API

---

## 👨‍💻 Author

**Saran Sarkar**

---
