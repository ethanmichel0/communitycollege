# Community College

To run backend: 

cd backend


./mvnw spring-boot:run (this will run spring boot on port 8080)

To run frontend:

(You must install Node.js, which you can download here: https://nodejs.org/en/)

cd frontend

npm install

npm run serve

This application is using the h2 database, and in memory storage (won't persist if spring boot is refreshed). In a production or testing environment, this could be set to MySql or a different database based on an environment variable. In order to view the database, go to localhost:8080/h2-console. Feel free to add students, courses, or students-courses mappings (many to many). Also, note that the database is currently seeded with four students for front end testing. When asked for an email, enter "jon@gmail.com", "bill@gmail.com", "robert@gmail.com", or "mary@gmail.com"


Note: There are several API endpoints that I added but aren't currently used. I may go back later and add some more functionality on the front end that uses some of these endpoints such as adding courses, adding students, etc. 

Cheers! Let me know if you have any questions :)
