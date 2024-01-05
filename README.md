# Speer-Notes-App

Step 1. Please refer 'SQL_execute' script file to create DB and table in MYSQL DB

step 2. Provide your MySQL DB Username and Password in application.properties file

step 3. Clone project and run "mvn clean install" to install dependacies from maven 

step 4. Hit Signup URL (/api/auth/signup) with payload. Sample payload as follows 
{
    "name": "user2",
    "email":"user2@gmail",
    "roles":"admin,user",
    "password": "password"
}

step 5. hit login URL (/api/auth/login) with payload to recive JWT token. Sample payload as follows

{
    "username":"user2",
    "password":"password"
}
