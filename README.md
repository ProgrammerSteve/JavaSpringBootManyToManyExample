## Setting up postgresql database

Make a volume then a PostgreSQL database on docker

```docker volume create VOLUME_NAME```
```
docker run --name CONTAINER_NAME -e POSTGRES_PASSWORD=YOUR_PASSWORD -d -p 5431:5432 -v VOLUME_NAME:/var/lib/postgresql/data postgres
```
Enter into the database
```
docker exec -it CONTAINER_NAME psql -U postgres
```
Create a database and connect to it
```
CREATE DATABASE yourdbname;
\c yourdbname;
```
Create the students table
```
CREATE TABLE students (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```
Create the courses table
```
CREATE TABLE courses (
    id SERIAL PRIMARY KEY,
    course_name VARCHAR(255) NOT NULL,
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```
Create the enrollments table
```
CREATE TABLE enrollments (
    student_id INTEGER NOT NULL,
    course_id INTEGER NOT NULL,
    enrollment_date DATE,
    PRIMARY KEY (student_id, course_id),
    FOREIGN KEY (student_id) REFERENCES students (id),
    FOREIGN KEY (course_id) REFERENCES courses (id),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```
run `\dt` to see if all tables were created

Set up .env file
- The last part of JDBC_URL will be the database name
- JDBC_USERNAME will be postgres

```
JDBC_URL=jdbc:postgresql://localhost:5431/XXXXXXX
JDBC_USERNAME=XXXXXXX
JDBC_PASSWORD=XXXXXXX
```
Set up `application.properties`:
```
spring.application.name=ManyToMany
spring.datasource.url=${JDBC_URL}
spring.datasource.username=${JDBC_USERNAME}
spring.datasource.password=${JDBC_PASSWORD}
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
```
