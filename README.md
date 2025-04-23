# Hibernate & JPA Demo Project

This project demonstrates the use of **Hibernate ORM** and **Hibernate with JPA** to perform basic CRUD operations using the **DAO pattern**. 
It integrates both `hibernate.cfg.xml` (for pure Hibernate) and `persistence.xml` (for JPA) configurations. The in-memory **H2 database** is used for easy testing and demonstration.

---
# Project Overview

This project includes two data access layers:

## 1. **Pure Hibernate**
- Uses `hibernate.cfg.xml` for configuration.
- Manages `SessionFactory` and `Session` manually.
- CRUD operations implemented using the DAO pattern.

## 2. **Hibernate with JPA**
- Uses `persistence.xml` for configuration via JPA standard.
- Manages `EntityManagerFactory` and `EntityManager`.
- CRUD operations also follow the DAO pattern, with `EntityManager`.

---
# Technologies Used

| Tech               | Version       |
|--------------------|---------------|
| Java               | 17+ (recommended) |
| Hibernate Core     | 6.4.4.Final   |
| H2 Database        | 2.2.224       |
| JPA (Jakarta Persistence) | Provided via Hibernate |
| JUnit Jupiter (JUnit 5) | For unit testing and assertions |
| Maven              | Dependency management |

# Features & Functionality

- Create, Read, Update, Delete (CRUD) operations
- Two-layered DAO structure: `GenericDAO` and entity-specific DAOs (e.g. `PersonDAO`)
- Demonstrates entity management and persistence contexts
- Transactions handled per operation
- In-memory H2 database for lightweight testing
- Uses JUnit Jupiter (JUnit 5) for writing and asserting test cases
