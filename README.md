# Spring Boot Bank Webservice

## Environment Variable
| Variable Name | Data Type | Description | Default Value |
| --- | --- | --- | --- |
| APP_PORT | Number | Application port | 8080 |
| DB_URL | String | Database URL | jdbc:sqlserver://localhost:1433;database=master;encrypt=false |
| DB_USERNAME | String | Database username | sa |
| DB_PASSWORD | String | Database password | p@ssw0rD! |
| LOG_LOCATION | String | Log location with path and file name | bank-app.log |
| LOG_LEVEL | String | Log level | INFO |

## References
- [Common Application Properties](https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html)
- [Microsoft SQL Server - Ubuntu based images](https://hub.docker.com/_/microsoft-mssql-server)
- [Microsoft JDBC Driver for SQL Server](https://learn.microsoft.com/en-us/sql/connect/jdbc/microsoft-jdbc-driver-for-sql-server?view=sql-server-ver16)
- [currency-api](https://github.com/fawazahmed0/currency-api)
