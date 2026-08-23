# Production Ready Spring Boot Features

A Spring Boot + JPA based Patient Management System that provides APIs to manage patient records. The application demonstrates CRUD operations, database persistence using JPA/Hibernate, and integration with PostgreSQL.

## Features

* Create new patient records
* Retrieve patient details
* Update existing patient information
* Delete patient records
* Store patient blood group information using Java Enums
* Automatic creation timestamp management
* Database interaction using Spring Data JPA
* PostgreSQL database integration

## Technology Stack

* **Java**: 17+
* **Spring Boot**
* **Spring Data JPA**
* **Hibernate ORM**
* **PostgreSQL**
* **Lombok**
* **Maven**

## Project Structure

```
src/main/java
└── com.example.patientmanagement
    ├── controller
    │   └── PatientController.java
    ├── service
    │   └── PatientService.java
    ├── repository
    │   └── PatientRepository.java
    ├── entity
    │   ├── Patient.java
    │   └── BloodGroupType.java
    └── PatientManagementApplication.java
```

## Patient Entity

The application manages patient details with the following attributes:

| Field          | Description                    |
| -------------- | ------------------------------ |
| id             | Unique patient identifier      |
| name           | Patient name                   |
| bloodGroupType | Patient blood group            |
| birthDate      | Patient date and time of birth |
| email          | Patient email address          |
| gender         | Patient gender                 |
| createDate     | Record creation timestamp      |

Supported blood groups:

* A_POSITIVE
* A_NEGATIVE
* B_POSITIVE
* B_NEGATIVE
* AB_POSITIVE
* AB_NEGATIVE
* O_POSITIVE
* O_NEGATIVE

## Database Configuration

Update `application.properties` with your PostgreSQL configuration:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/patient_db
spring.datasource.username=your_username
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

## Database Table

The application uses a `patient` table:

```sql
CREATE TABLE patient (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255),
    blood_group_type VARCHAR(50),
    birth_date TIMESTAMP,
    email VARCHAR(255),
    gender VARCHAR(255),
    create_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

## Running the Application

### Clone the repository

```bash
git clone <repository-url>
cd patient-management-system
```

### Build the application

Using Maven:

```bash
mvn clean install
```

### Run the application

```bash
mvn spring-boot:run
```

The application will start on:

```
http://localhost:8080
```

## API Endpoints

### Create Patient

```
POST /patients
```

Example request:

```json
{
  "name": "Aarav Sharma",
  "bloodGroupType": "A_POSITIVE",
  "birthDate": "1995-04-12T10:30:00",
  "email": "aarav.sharma@gmail.com",
  "gender": "Male"
}
```

---

### Get All Patients

```
GET /patients
```

---

### Get Patient By ID

```
GET /patients/{id}
```

Example:

```
GET /patients/1
```

---

### Update Patient

```
PUT /patients/{id}
```

---

### Delete Patient

```
DELETE /patients/{id}
```

---

## Sample Database Records

Example patient data:

| Name         | Blood Group | Gender |
| ------------ | ----------- | ------ |
| Aarav Sharma | A_POSITIVE  | Male   |
| Priya Nair   | O_NEGATIVE  | Female |
| Rahul Verma  | B_POSITIVE  | Male   |
| Sneha Iyer   | AB_POSITIVE | Female |

## Future Enhancements

* Add authentication and authorization using Spring Security
* Add pagination and sorting support
* Add patient search functionality
* Add validation using Jakarta Bean Validation
* Add API documentation using Swagger/OpenAPI
* Add unit and integration tests

## License

This project is created for learning and demonstration purposes.
