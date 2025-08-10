# Spring-Boot-Task-Management-System
This Spring Boot application manages Task and Task Lists, offering endpoints for creating, reading, updating, and deleting (CRUD) their records. It integrates PostgreSQL as the primary database.

 ---
 ## 📂 Features Overview

### Task List
- Add, update , and delete Task List
- Retrieve a single Task List by it's ID or list all Task Lists exist

### Task
- Add, update, and delete a Task of specific Task List providing task list ID
- Retrieve a single Task by task List ID and Task ID or list all Tasks of specific Task Lists by providing Task List ID

---

## ⚙️ Setup & Installation

### 1️⃣ Clone the Repository
```bash
git clone https://github.com/MohammadJarrar1201726/Spring-Boot-Task-Management-System
```

### 2️⃣ Configure Database
Create a PostgreSQL database named `postgres` and update credentials in `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### 3️⃣ Build & Run
```bash
mvn clean install
mvn spring-boot:run
```
Application runs at: **`http://localhost:8081`**

---


## 🔗 API Reference

### 📖 Task List Endpoints
| Method | Endpoint                | Description                |
|--------|-------------------------|----------------------------|
| POST    | `/task_list`         | Create task list     |
| GET    | `/task_list`                | Get all task lists              |
| GET    | `/task_list/{task_list_id}`         | Get task list by task list ID           |
| PUT  | `/task_list/{task_list_id}`         |  update task list        |
| DELETE | `/task_list/{task_list_id}`         | Delete task list                |

### ✍️ Task Endpoints
| Method | Endpoint                | Description                |
|--------|-------------------------|----------------------------|
| POST   | `/task_list/{task_list_id}/tasks`              | Create Task              |
| GET    | `/task_list/{task_list_id}/tasks`              | Get all Task of specific Task List            |
| GET    | `/task_list/{task_list_id}/{task_id}`         | Get Task by Task List ID and Task ID           |
| PUT    | `/task_list/{task_list_id}/{task_id}`         | Full update of Task         |
| DELETE | `/task_list/{task_list_id}/{task_id}`         | Delete Task              |
