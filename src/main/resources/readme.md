
<h3>Session management -- JSESSIONID </h3>
1. http://localhost:8080/login
2. http://localhost:8080/logout

<h3>H2 database console</h3>

http://localhost:8080/h2

<h3>User credentials</h3>
 
1. testuser/testpassword
2. testadmin/adminpassword 


APIs

1. http://localhost:8080/api/account-report/2
2. http://localhost:8080/api/account/statement-report/1?fromDate=2020-08-01&toDate=2020-08-02
3. http://localhost:8080/api/account/statement-report/2?fromAmount=1000&toAmount=9000




MAVEN build project

<start-class>com.assignment.Application</start-class>

Note: application.properties contains both ms-access and h2 database properties 