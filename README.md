# Spring Boot Bank Webservice

## Project Overview

The "Bank" project is a comprehensive Spring Boot application designed to provide a robust banking service. With a focus on three main domains—Customer, Account, and Currency Rate—this project encapsulates essential functionalities crucial for efficient banking operations.

### Key Features

#### Customer Domain:
- Add, search, get, update, and delete customer records.

#### Account Domain:
- Open accounts, search by account or customer number, get account details.
- Deposit and withdraw funds, and perform seamless fund transfers between accounts.

#### Currency Rate Domain:
- Retrieve currency rates based on specified dates using [third-party API](https://github.com/fawazahmed0/currency-api).

### Technologies and Dependencies

- Spring Boot for streamlined development and robust application structure.
- Spring Data JPA for simplified data access.
- Liquibase for database schema version control.
- Lombok for concise and readable code.
- MapStruct for efficient object mapping.
- Microsoft SQL Server as the underlying relational database.

### Pom.xml Highlights

```xml
<!-- Dependencies -->
<dependencies>
    <!-- Spring Boot Starter dependencies -->
    <!-- Liquibase Core -->
    <!-- Lombok -->
    <!-- MapStruct -->
    <!-- Microsoft SQL Server JDBC -->
</dependencies>
```

### Project Tree
```
├── docker-compose.yml               # Docker Compose configuration file
├── HELP.md                          # Spring Boot generated help file
├── log
│   ├── bank-app.log                 # Application log file
│   └── bank-app.log.2023-12-03.0.gz # Archived log file
├── misc
│   ├── Bank Service.postman_collection.json  # Postman collection for testing
│   └── spring-init.txt                        # Initialization URL
├── mvnw                               # Maven wrapper script for Unix-based systems
├── mvnw.cmd                           # Maven wrapper script for Windows
├── pom.xml                            # Maven Project Object Model (POM) file
├── README.md                          # Project readme file
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── anasdidi
│   │   │           └── bank
│   │   │               ├── BankApplication.java       # Main application class
│   │   │               ├── common
│   │   │               │   ├── BaseDTO.java          # Base DTO class
│   │   │               │   ├── BaseEntity.java       # Base Entity class
│   │   │               │   ├── ErrorDTO.java         # Error DTO class
│   │   │               │   └── PaginationDTO.java    # Pagination DTO class
│   │   │               ├── config
│   │   │               │   ├── DatabaseConfig.java    # Database configuration
│   │   │               │   └── RestControllerAdvice.java  # Global exception handler
│   │   │               ├── domain
│   │   │               │   ├── account
│   │   │               │   │   ├── ...              # Account-related classes
│   │   │               │   ├── currencyrate
│   │   │               │   │   ├── ...              # Currency Rate-related classes
│   │   │               │   └── customer
│   │   │               │       ├── ...              # Customer-related classes
│   │   │               ├── exception
│   │   │               │   ├── ...                  # Custom exception classes
│   │   │               └── filter
│   │   │                   └── RequestResponseLogFilter.java  # Request/response log filter
│   │   └── resources
│   │       ├── application.yml           # Application configuration
│   │       ├── db
│   │       │   └── changelog
│   │       │       ├── data
│   │       │       │   ├── t_acct.csv   # CSV data for account
│   │       │       │   └── t_cust.csv   # CSV data for customer
│   │       │       ├── db.changelog-0.1.0.yml   # Liquibase changelog for version 0.1.0
│   │       │       ├── db.changelog-0.2.0.yml   # Liquibase changelog for version 0.2.0
│   │       │       └── db.changelog-master.yml  # Liquibase master changelog
│   │       ├── static
│   │       └── templates
    └── test
        └── java
            └── com
                └── anasdidi
                    └── bank
                        └── BankApplicationTests.java  # Test class
```

## Environment Variable
| Variable Name | Data Type | Description | Default Value |
| --- | --- | --- | --- |
| APP_PORT | Number | Application port | 8080 |
| DB_URL | String | Database URL | jdbc:sqlserver://localhost:1433;database=TESTDB;encrypt=false |
| DB_USERNAME | String | Database username | sa |
| DB_PASSWORD | String | Database password | p@ssw0rD! |
| LOG_LOCATION | String | Log location with path and file name | bank-app.log |
| LOG_LEVEL | String | Log level | INFO |

## Development Setup
Visual Studio Code (VSCode) provides a feature called "Dev Containers" that allows you to define a development environment using Docker containers. This helps in maintaining consistent development environments across different machines.

### Prerequisites:
1. Install Docker: [Docker Install Guide](https://docs.docker.com/get-docker/)
2. Install Visual Studio Code: [VSCode Install Guide](https://code.visualstudio.com/download)
3. Install the "Remote - Containers" extension for VSCode: [VSCode Remote - Containers Extension](https://marketplace.visualstudio.com/items?itemName=ms-vscode-remote.remote-containers)

### Open in Container:
1. Open your project in VSCode.
2. Click on the green icon in the bottom-left corner (><), or use the "Remote-Containers: Reopen in Container" command from the Command Palette (Ctrl+Shift+P).
3. VSCode will now rebuild the Docker container, and your project will be opened inside the container.

## Run and Build Project

### Run the Spring Boot Application Locally
To run the Spring Boot application locally, use the following Maven command:
```
mvn spring-boot:run
```

### Create an Executable JAR
To create an executable JAR file, run the following Maven command:
```
mvn clean package
```

This command creates a JAR file in the **target** directory. You can run the JAR file using the following command:
```
java -jar target/bank-0.0.1-SNAPSHOT.jar
```

## References
- [Common Application Properties](https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html)
- [Microsoft SQL Server - Ubuntu based images](https://hub.docker.com/_/microsoft-mssql-server)
- [Microsoft JDBC Driver for SQL Server](https://learn.microsoft.com/en-us/sql/connect/jdbc/microsoft-jdbc-driver-for-sql-server?view=sql-server-ver16)
- [currency-api](https://github.com/fawazahmed0/currency-api)

## Contact
Created by [Anas Juwaidi](mailto:anas.didi95@gmail.com)
