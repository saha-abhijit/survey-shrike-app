#Survey Shrike Application

#Frameworks:

1. Spring Boot
2. AngularJS

#Database:

MySQL


#Architecture
The application is built in a micro-service structure consisting of 3 modules, namely:

1. Authorization Module (survey-shrike-auth)
2. Service Module (survey-shrike-service)
3. Client Module (survey-shrike-client)

Each of the 3 modules are packaged as jars and can be deployed individually.

The modules interact within themselves via REST endpoints.

#Authentication and Authorization:

The authentication is achieved using calls to the Authorization Service for a user.
Authorization is achieved by the usage of roles (BUSINESS_ADMIN and CUSTOMER) and the same is leveraged using Spring Security using a custom UserDetailsService

Access to endpoints is authorized by the WebSecurityConfigurerAdapter of Spring

#Swagger Support:

The Authorization Module and Service module are supported by swagger test interfaces

URLs for the same would be 

Auth module: https://<domain>:8084/swagger-ui.html
Service module: https://<domain>:8090/swagger-ui.html