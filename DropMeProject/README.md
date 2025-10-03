# DropMe — Online Transport App

**DropMe** is a Java-based online transport application designed to facilitate seamless ride-booking experiences for users and efficient fleet management for administrators.

---

## 🚀 Features

- **User Features:**
  - Register and log in
  - Book rides in real-time
  - View ride history and receipts
  - Rate drivers and provide feedback

- **Admin Features:**
  - Manage user accounts
  - Monitor active rides
  - Generate reports on ride statistics and earnings
  - Manage vehicle fleet and driver assignments

---

## 🧱 Tech Stack

- **Backend:** Java (Spring Boot)
- **Frontend:** HTML, CSS, JavaScript
- **Database:** MySQL
- **Authentication:** JWT (JSON Web Tokens)

---

## 📁 Project Structure

DropMe/
├── src/
│ ├── main/
│ │ ├── java/
│ │ └── resources/
├── sql/
│ └── schema.sql
├── assets/
│ └── images/
├── README.md
└── LICENSE

---

## ⚙️ Setup & Installation

1. **Clone the repository:**
   ```bash
   git clone https://github.com/Shakir-Hiflulla/DropMe.git
   cd DropMe
Set up the database:

Create a new MySQL database (e.g., dropme_db).

Import the schema from sql/schema.sql.

Configure application properties:

Update src/main/resources/application.properties with your database credentials.

Run the application:

Use your preferred IDE (e.g., IntelliJ IDEA, Eclipse) to run the Spring Boot application.

Access the application:

Open your browser and navigate to http://localhost:8080.
