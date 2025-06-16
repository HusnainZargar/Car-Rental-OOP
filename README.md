# Car Rental System

A console-based Java application for managing a car rental service, allowing customers to browse, book, and return cars, and administrators to manage inventory and bookings.

---

## Features

### Admin Features
- **Login/Authentication**: Secure login with default credentials (`admin/admin`).
- **Car Management**: Add, edit, temporarily remove, or permanently delete cars.
- **Booking Management**: Approve/reject booking requests and set delivery times.
- **User Management**: View customer details, booked cars, and total income.
- **Credential Management**: Update admin username and password.

### Customer Features
- **Registration/Login**: Sign up with unique username/password or log in.
- **Car Search**: Filter cars by budget, make, color, engine capacity, horsepower, or fuel mileage.
- **Booking**: Book a car for 1-7 days with bill generation.
- **View Bookings**: Check booking status (pending/approved) and delivery details.
- **Return Cars**: Return booked cars to make them available.

### General Features
- **Error Handling**: Validates numeric inputs with clear error messages.
- **Console Interface**: Menu-driven UI with visual separators.
- **OOP Design**: Uses inheritance, encapsulation, and composition.

---

## Technologies Used
- **Java**: JDK 8 or higher.
- **OOP Principles**: Inheritance (User abstract class), encapsulation, composition.

---

## Usage

### Admin Dashboard

- **Login**: Use default credentials (`admin/admin`) or sign up as a customer.

#### Options:
- **Car Management**: Add, edit, or remove (temporary/permanent) cars.
- **Manage Booking Requests**: Approve/reject bookings, set delivery times.
- **View All Users**: See customer details and booking income.
- **Change Credentials**: Update username/password.
- **Logout**: Return to login screen.

---

### Customer Dashboard

- **Sign Up/Login**: Register or log in with unique credentials.

#### Options:
- **List All Cars**: View available cars with details.
- **Search for a Car**: Filter by budget, make, color, etc.
- **Book a Car**: Select car, specify days (1–7), confirm with bill.
- **View Requests**: Check booking status and delivery details.
- **Return a Car**: Return booked car.
- **Logout**: Return to login screen.

---

> **Note:** Data (cars, users, bookings) is stored in memory and resets on program termination.

