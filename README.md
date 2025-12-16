-- Simple Library Management System --

* Description
A Java Spring Boot project for managing books in a library. 
The system provides CRUD operations for books, users, and borrow records, allowing easy tracking of library resources.

* Technologies
- Java 17
- Spring Boot
- Hibernate / JPA
- Maven
- Lombok
- SQL (or any relational database)
- ModelMapper (for DTO mapping)

* Features
- Add, update, delete, and view books
- Manage users (add, update, delete, list)
- Track borrow and return records
- Data validation and error handling
- Easily extensible for future features

  * Project Structure
- src/main/java/com/library/library_backend/ → Main Java code (controllers, services, repositories, DTOs, entities)
- src/main/resources/ → Configuration files (application.properties)
- utils/ → Utility classes like ObjectMapperUtils for DTO mapping

* Future Improvements
- Add authentication & authorization (Spring Security)
- Implement frontend interface


* How to Run
1. Open the backend folder in your IDE (IntelliJ, Eclipse, etc.)
2. Configure your database in src/main/resources/application.properties.
3. Build and run the project:
   mvn clean install
   mvn spring-boot: run
4. Use Postman or any REST client to test the endpoints
