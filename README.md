# Spring Boot integration with Prime Faces
- Localization
- Logging (external file(debug, error) & console)
- JPA Repositories
- MySQL Database
- Email notifications (smtp gmail)
- Spring Security

## Requirements
- JDK 25
- Maven 3.9+

## Things to do:
1. Clone repository:
    ```
    git clone https://github.com/hendisantika/spring-boot-prime-faces.git
    ```
2. Go the folder:
    ```
    cd spring-boot-prime-faces
    ```
3. Run the app:
    ```
    mvn clean spring-boot:run
    ```

When you will launch the application for the first time, run it with `--spring.sql.init.mode=always --spring.jpa.defer-datasource-initialization=true` to seed the sample employee data (omit these flags on later runs if you don't want the seed data re-applied)

To DO Things :
1. Go to http://localhost:8080/registration.faces and register your user

![Registration Page](images/registration.png "Registration Page")



2. Go to http://localhost:8080/login.faces and login with your credentials

![Login Page](images/login.png "Login Page")


3. Go to http://localhost:8080/admin/home.faces to see employee list

![Employees Page](images/employees.png "Employees Page")
