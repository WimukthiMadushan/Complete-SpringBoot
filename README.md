# Complete Spring Boot Project

A fully featured Spring Boot application built with a clean architecture, modern Spring Boot practices, and production-ready configurations.

---

## 📌 Features

- Getting Started with Spring Boot
- Dependency Injection
- Database Accsess
- Database Migration with Flyway
- Lombok for cleaner code
- JPA Entity States
- Fetching Strategies: LAZY vs EAGER
- Spring MVC

---

## Getting Started with Spring Boot

The Spring Framework is a powerful and flexible Java framework with many modules, each designed for a specific purpose. Some important modules include
- Core – Handles dependency injection and object management.
- AOP (Aspect-Oriented Programming) – Adds cross-cutting features like logging and security.
- Web / WebMVC – Used to build web applications and REST APIs.
- Data / JDBC / ORM – Works with databases using JPA, Hibernate, JDBC, and more.
- Test – Tools for testing Spring components.

Spring Boot is built on top of the Spring Framework.
Its main goal is to simplify Spring development by:

✔ Removing manual configurations
✔ Managing dependencies automatically
✔ Providing production-ready defaults
✔ Including an embedded server (like Tomcat)

It allows us to quickly build production ready applications with minimal setup.
Spring Boot provides starter dependencies, which bundle commonly used libraries into a single easy-to-use dependency.

spring-boot-starter-web includes
- Tomcat → Embedded server
- WebMVC → REST controllers & MVC features
- Web → Web functionalities
- Jackson → JSON serialization and deserialization
- Logging → Log setup

---

## Dependency Injection

Dependency Injection (DI) is a core pattern in the Spring Framework that aims to make your code more flexible, maintainable, and easier to test. It's a key part of the Inversion of Control (IoC) principle.

### What is Dependency Injection?
A dependency is simply an object that another object needs to perform its job.
### What is Dependency?
If class A needs class B to do its work, then B is a dependency of A.

(Using Code)
### The Problem: 

The OrderService is tightly coupled to StripePaymentService. If you later decide to switch to PayPalPaymentService, you must manually edit and recompile the OrderService class.

## 🌟 The Solution: Dependency Injection (DI)

**Dependency Injection (DI)** is a design pattern where the responsibility for creating and providing dependencies is transferred to an external entity.  
In Spring, this responsibility is handled by the **Spring IoC Container**.

DI helps keep code clean, modular, and easy to test.

---

### 🔧 Constructor Injection (Recommended)

Constructor Injection is the **preferred and most reliable** method of DI.

- The Spring IoC Container provides the required dependency **through the class constructor**.
- Dependencies are set **at the time the bean is created**.
- Ensures immutability and makes unit testing easier.

**Example:**

```java
@Service
public class OrderService {

    private final PaymentService paymentService;

    public OrderService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
}
```
### 🔧 Setter Injection (Not Recommended)

```
@Service
public class UserService {

    private EmailService emailService;

    // Spring injects EmailService using this setter
    @Autowired
    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }

    public void sendWelcomeEmail() {
        emailService.sendEmail("Welcome to our platform!");
    }
}

```

## 🗄️ Data Access Methods in Java

### JDBC (Java Database Connectivity)
JDBC is the lowest level approach to accessing databases in Java.  
- You write SQL queries manually  
- You manage connections, statements, and result sets yourself  
- Lots of boilerplate code  
- Very flexible but requires more work  

**Best when:** you need full control over SQL.

---

### JPA (Java Persistence API)
JPA is a specification (not a library) for working with relational data in an object-oriented way.  
- Uses entities (Java classes mapped to database tables)  
- Removes most boilerplate code  
- You work with objects instead of raw SQL  
- Querying done with JPQL or Criteria API  

**JPA = a set of rules/standards. It does not implement anything itself.**

---

### Hibernate ORM (JPA Implementation)
Hibernate is the most popular **implementation of JPA**.  
- Provides the actual code behind JPA  
- Handles object-to-table mapping (ORM)  
- Generates SQL automatically  
- Includes advanced features like caching, lazy loading, dirty checking  

**Hibernate = JPA + extra powerful features.**

---

### Spring Data JPA
Spring Data JPA builds on top of JPA and Hibernate to make data access even easier.

- Eliminates almost all boilerplate  
- Generates repository methods automatically  
- Provides CRUD operations out of the box  
- Allows creating queries from method names  
- Integrates seamlessly with Spring Boot  

**Example:**

```java
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByName(String name);
}
```

---

## 🛠️ Database Migration with Flyway.

**Flyway** is a database migration tool that helps manage and version your database schema.  
It ensures that every change to your database is tracked, repeatable, and consistent across all environments.

### Why Flyway?
- Keeps database schema versions organized
- Automates applying changes (migrations)
- Ensures all team members and servers use the same database structure
- Works seamlessly with Spring Boot

---

### How Flyway Works

Flyway reads migration files placed inside:

Each migration file has a version-based name:

V1__create_users_table.sql

V2__add_orders_table.sql

V3__add_email_column.sql


When the application starts, Flyway:

1. Checks the current database version  
2. Finds new migration files  
3. Applies them in order (V1 → V2 → V3…)  
4. Records applied migrations in a special table `flyway_schema_history`

---

### 🧱 Example Migration File

**V1__create_users_table.sql**

```sql
CREATE TABLE users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    email VARCHAR(150)
);

```

---

## ✨ Lombok for Cleaner Code

Lombok is a Java library that helps reduce boilerplate code by automatically generating common methods like getters, setters, constructors, `toString()`, and more.  
It works using annotations and integrates seamlessly with Spring Boot.

Without Lombok, you would manually write repetitive code for every entity or DTO.  
With Lombok, your classes stay clean, readable, and easier to maintain.

---

### 🪄 Common Lombok Annotations

| Annotation | Purpose |
|-----------|----------|
| `@Getter` / `@Setter` | Generates getter and setter methods |
| `@NoArgsConstructor` | Generates a no-argument constructor |
| `@AllArgsConstructor` | Generates a constructor with all fields |
| `@RequiredArgsConstructor` | Generates constructor for final fields |
| `@ToString` | Generates a `toString()` method |
| `@EqualsAndHashCode` | Generates `equals()` and `hashCode()` |
| `@Data` | Combines Getter, Setter, ToString, EqualsAndHashCode, and RequiredArgsConstructor |

---

### 🧾 Example Without Lombok

```java
public class User {
    private Long id;
    private String name;

    public User() {}

    public User(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    @Override
    public String toString() {
        return "User{id=" + id + ", name='" + name + "'}";
    }
}

```
 ---

 ## JPA Entity States (Brief Explanation)

In JPA, an entity can exist in four different states during its lifecycle.  
These states determine how the entity interacts with the **Persistence Context** (managed by EntityManager or Hibernate Session).

---

### 1. Transient State
A new object created using `new` and **not associated** with the persistence context.

- Not stored in the database  
- No ID assigned (unless manually set)  
- JPA/Hibernate does not track it

**Example:**
```java
User user = new User(); // Transient
user.setName("John");
```

## 🔵 2. Persistent State

An entity enters the **Persistent** state when it is being managed by the **Persistence Context**.

### Characteristics:
- Changes to the entity are **automatically tracked**
- Updates are synchronized to the database **when the transaction commits**
- Entity is inside the Persistence Context

### How an Entity Becomes Persistent:
- Calling `entityManager.persist(entity)`
- Using `save()` in Spring Data JPA
- Retrieving entities via `findById`, `findAll`, `getReference`, etc.

### Example:
```java
User user = new User("John", "john@mail.com");
entityManager.persist(user);  // Now Persistent
```

## 🟡 3. Detached State

An entity becomes **Detached** when it was Persistent earlier but is **no longer managed** by the Persistence Context.

### When Does Detachment Happen?

- The persistence context is closed  
- The transaction ends  
- The entity is manually detached  
- The application clears the context

When an entity is Detached:

- It is **not tracked** by Hibernate  
- Changes to the entity are **NOT automatically saved** to the database  

---

### 🧾 Example: Entity Becomes Detached

```java
// Entity is loaded from the database → Persistent state
User user = entityManager.find(User.class, 1L);

// Detach the entity → Detached state
entityManager.detach(user);

// At this point, the entity is no longer managed.
// Changes made here will NOT be saved automatically.
user.setName("Updated Name"); 
```

## 🔴 4. Removed State

An entity enters the **Removed** state when it is marked for deletion by the Persistence Context.

### Characteristics:

- The entity **is still inside the Persistence Context**
- It is scheduled for deletion
- The actual deletion happens **when the transaction commits**
- After committing, the entity will no longer exist in the database

---

### 🧾 Example: Entity in Removed State

```java
// Load the entity → Persistent state
User user = entityManager.find(User.class, 1L);

// Mark the entity for deletion → Removed state
entityManager.remove(user);

// At this point:
// - The entity is flagged for removal
// - The row will be deleted from the database when the transaction commits
```

## 🔄 Hibernate Transaction (Brief Explanation)

A **transaction** in Hibernate represents a unit of work that must be executed completely or not at all.  
It ensures **data consistency**, **atomicity**, and **rollback safety** when interacting with the database.

When using Hibernate (or JPA), operations like `save`, `update`, `delete`, or running queries should happen **inside a transaction**.

---

### ⭐ Why Transactions Are Important

- Ensures all operations complete successfully  
- If something fails, changes are **rolled back**  
- Prevents corrupted or partial data  
- Maintains the ACID properties (Atomicity, Consistency, Isolation, Durability)

---

### 🎯 How Hibernate Transactions Work

1. Begin a transaction  
2. Perform operations (persist, update, delete)  
3. Commit the transaction  
4. If an error occurs → rollback  

Hibernate uses the **Persistence Context** + **Transaction Manager** to coordinate these operations.

---

## 🔍 Fetching Strategies: LAZY vs EAGER

Fetching strategy determines **when related entities are loaded** from the database.

Hibernate provides two fetch types:

### LAZY (Default & Recommended)
- Related entities are **loaded only when needed**
- Uses **proxy objects**
- Improves performance
- Avoids unnecessary queries

✨ Best choice for most relationships, especially collections.

---

###  EAGER (Load Immediately)
- Related entities are **loaded at the same time** as the parent entity
- Executes **additional JOIN queries**
- Can cause performance issues if overused
- Increases memory usage

⚠ Should be used only when data is always required.

---

## 🔗 How Fetch Types Work in Entity Relationships

###  **@OneToMany**
> One parent → Many children  
- Default Fetch Type: **LAZY**
- Reason: Collections can be large  
- Best practice: Always keep it LAZY

### **@ManyToOne**
> Many children → One parent
- Default Fetch Type: EAGER
- Reason: The parent is usually small and always needed
- BUT best practice: Change to LAZY to avoid unnecessary joins

### ***@OneToOne***
> One-to-one relationship
- Default Fetch Type: EAGER
- Often safe, but can cause extra joins
- Best practice: Use LAZY unless always required

### ***@ManyToMany***
> Many-to-many relationship via join table
- Default Fetch Type: LAZY
- Collections can be large and complex
- Best practice: Keep as LAZY

---

## Spring MVC (Model–View–Controller)

**Spring MVC** is a web framework within the Spring ecosystem used to build **web applications** and **RESTful APIs**.  
It follows the **Model–View–Controller (MVC)** architectural pattern, which separates an application into three layers:

- **Model** → Represents data and business logic  
- **View** → UI or response (HTML, JSON, XML)  
- **Controller** → Handles incoming requests, processes them, and sends responses  

Spring MVC makes handling HTTP requests simple, structured, and scalable.


##  How Spring MVC Works

1. A client sends an HTTP request  
2. The request reaches the **DispatcherServlet** (front controller)  
3. DispatcherServlet routes the request to the appropriate **Controller**  
4. Controller executes logic and interacts with Services / Model  
5. The response (View or JSON) is sent back to the client  

---
