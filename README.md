# E-Commerce-V-1
EasyBuyStore is a full-stack e-commerce product management app built with Spring Boot, MySQL, and JavaScript. 
# 🛒 EasyBuyStore – Full Stack E-Commerce Product Management System

## 🚀 Overview

EasyBuyStore is a full-stack web application that enables users to browse, search, filter, and sort products, while administrators can manage products through create, update, and delete operations.

The project demonstrates real-world backend architecture using Spring Boot along with a dynamic and responsive frontend built using HTML, CSS, and JavaScript.

---

## 🧠 Features

### 🔹 Backend

* RESTful APIs using Spring Boot
* Product CRUD operations
* Pagination & Sorting
* Dynamic Filtering using Specification API
* DTO pattern (Request & Response separation)
* Clean layered architecture (Controller → Service → Repository)

### 🔹 Frontend

* Product listing with responsive grid layout
* Search & filter (category, price, rating, keyword)
* Sorting (price, rating, name)
* Pagination controls
* Add / Update / Delete products
* Modal-based update UI
* Toast notifications & loading spinner

---

## 🛠️ Tech Stack

### 🔹 Backend

* Java
* Spring Boot
* Spring Data JPA
* MySQL
* Lombok

### 🔹 Frontend

* HTML
* CSS (Responsive Design)
* JavaScript (Fetch API)

---

## 📂 Project Structure

```
ecommerce/
│
├── backend/
│   ├── controller/
│   ├── service/
│   ├── repository/
│   ├── entity/
│   ├── dto/
│   ├── spec/
│   └── config/
│
├── frontend/
│   ├── index.html
│   ├── style.css
│   └── script.js
```

---

## ⚙️ Setup Instructions

### 🔹 Backend Setup

1. Clone the repository
2. Open in IntelliJ
3. Configure MySQL in `application.properties`

```
spring.datasource.url=jdbc:mysql://localhost:3306/your_db
spring.datasource.username=root
spring.datasource.password=your_password
```

4. Run the Spring Boot application

---

### 🔹 Frontend Setup

1. Open the `frontend` folder in VS Code
2. Run using Live Server

```
http://127.0.0.1:5500
```

---

## 🔗 API Endpoints

| Method | Endpoint             | Description       |
| ------ | -------------------- | ----------------- |
| GET    | /api/products        | Get all products  |
| GET    | /api/products/{id}   | Get product by ID |
| POST   | /api/products/add    | Add product       |
| PUT    | /api/products/{id}   | Update product    |
| DELETE | /api/products/{id}   | Delete product    |
| GET    | /api/products/search | Filter + Search   |

---

## 🚀 Future Enhancements

* 🔐 JWT Authentication & Authorization (Admin/User roles)
* 🧑‍💼 Role-based access control
* 🖼️ Product image upload (Cloudinary / AWS S3)
* 🛒 Shopping cart & order management
* 💳 Payment integration (Stripe / Razorpay)
* 📊 Admin dashboard with analytics
* 🌐 Deployment using Docker & cloud platforms
* ⚡ Performance optimization & caching
* 📱 Mobile-first responsive UI

---

## 💯 Learning Outcomes

* Full-stack development workflow
* REST API design & architecture
* DTO & Specification pattern
* Frontend-backend integration
* Debugging real-world issues (CORS, HTTP errors)

---

## 🙌 Author

**Sakthi**

---

## ⭐ Support

If you like this project, give it a ⭐ on GitHub!
