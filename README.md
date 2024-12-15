# 📚 LibrarySphere: A Smart Library Management System

Welcome to **LibrarySphere**, a robust and user-friendly Library Management System designed to simplify and streamline the process of managing books, users, and transactions for libraries of all sizes. Built with Java, Spring Boot, and PostgreSQL, LibrarySphere ensures seamless operations and an intuitive interface.

---

## 🚀 Features

- **User Management**: 
  - Add, update, delete, and list library members and admins.
  - Role-based access (Admin, Member).

- **Book Management**:
  - Add, update, delete, and list books.
  - Track availability of books.

- **Transaction Management**:
  - Issue and return books.
  - Automatic fine calculation for overdue returns.

- **Database Integration**:
  - PostgreSQL for reliable and scalable data storage.
  - Preloaded random sample data for quick testing.

---

## 🛠️ Technologies Used

- **Backend**: Java with Spring Boot
- **Database**: PostgreSQL
- **CLI**: Spring Shell for command-line interaction
- **Build Tool**: Maven
- **ORM**: Hibernate (via Spring Data JPA)

---

## 🗂️ Project Structure

```plaintext
src/
├── main/
│   ├── java/com/library/
│   │   ├── entities/          # Entity classes for Users, Books, Transactions
│   │   ├── repositories/      # JPA Repositories for database interaction
│   │   ├── services/          # Business logic for Users, Books, and Transactions
│   │   ├── controllers/       # RESTful API Controllers
│   │   ├── LibraryCLI.java    # Command-line interface (Spring Shell)
│   │   └── LibraryManagementSystemApplication.java  # Main Application Class
│   ├── resources/
│       ├── application.properties  # Database configurations
│  
| PopulateTable.sql     # SQL script to create tables and insert sample data
```

# 📥 Getting Started

## Prerequisites

Ensure the following are installed on your system:

- **Java 17 or higher**
- **Maven**
- **PostgreSQL**
- Optional: **Postman** (for testing REST APIs)

---

## Setup Instructions

### Clone the Repository
```bash
[git clone https://github.com/your-username/library-sphere.git](https://github.com/jamaluddin57/library-management-system.git)
cd library-management-system
```
### ***For Linux/Ubuntu***:

### Set Up PostgreSQL

1. Log in as the `postgres` user:
   ```bash
   sudo -u postgres psql
   ```


2. Create the database and user:
   ```sql
   CREATE DATABASE library_db;
   CREATE USER library_user WITH PASSWORD 'postgres';
   GRANT ALL PRIVILEGES ON DATABASE library_db TO library_user;
   \c library_db
   ```
   ```sql
   GRANT USAGE ON SCHEMA public TO library_user;
   GRANT CREATE ON SCHEMA public TO library_user;
   ALTER SCHEMA public OWNER TO library_user;
   ```


3. Exit the PostgreSQL shell:
   ```sql
   \q
   ```
---


### Run the SQL Script
For Linux:
```bash
sudo -u postgres psql
```
Then:
```bash
\c library_db
 \i src/main/resources/library_db.sql
 \q
```
---
### ***For Windows***


#### **1. Open pgAdmin**
1. Open **pgAdmin** from the Start Menu or search bar.
2. Log in to the pgAdmin interface using your PostgreSQL credentials (default username: `postgres`).


#### **2. Create the Database and User**
1. In the left-hand navigation tree, right-click on the "Databases" node and select **Create > Database**:
   - Name: `library_db`
   - Owner: `postgres'.
   - Click **Save**.


2. Create a new user (role):
   - Navigate to **"Login/Group Roles"** under the "Roles" node.
   - Right-click and select **Create > Login/Group Role**.
     - Name: `library_user`
     - Go to the **Definition** tab, set the password to `postgres`.
     - Go to the **Privileges** tab and grant appropriate privileges:
       - Can Login: Yes
       - Superuser: Yes
     - Click **Save**.


3. Grant the user access to the database:
   - Open the **Query Tool** (right-click on the database `library_db` and select "Query Tool").
   - Run the following commands:
     ```sql
     GRANT ALL PRIVILEGES ON DATABASE library_db TO library_user;
     ```
---


### **Run the SQL Script**

1. Navigate to the database `library_db` in the left-hand tree.
2. Right-click on the database and select **Query Tool**.
3. Load the SQL script:
   - Click the **folder icon** in the query tool or press **Ctrl+O** to browse to `src/main/resources/library_db.sql`.
4. Run the script:
   - After loading the file, click **Execute/Play** or press **F5**.

---

### Build the Project

```bash
mvn clean install
```
---

### Run the Application


```bash
mvn spring-boot:run
```
---

### Access the CLI

Once the application starts, the CLI will be available for interaction:
```plaintext
shell:>
```

---


# 📋 CLI Commands


## User Management


### Add a User
```plaintext
add-user "Alice" "alice@example.com" "MEMBER"
```


### List All Users
```plaintext
list-users
```


---


## Book Management


### Add a Book
```plaintext
add-book "Spring Boot in Action" "Craig Walls" "Programming" 2016 true
```


### List All Books
```plaintext
list-books
```

---


## Transaction Management


### Issue a Book
```plaintext
issue-book 1 1 "2024-11-28" "2024-12-05"
```

### List All Transactions
```plaintext
list-transactions
```
---


# 🛠️ Testing the REST APIs


Use **Postman** or **curl** to test the RESTful API endpoints. Examples:


### Add a User
```bash
curl -X POST http://localhost:8080/users \
-H "Content-Type: application/json" \
-d '{"name":"John Doe", "email":"john.doe@example.com", "role":"MEMBER"}'
```


### Get All Books
```bash
curl -X GET http://localhost:8080/books
```
---

