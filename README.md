# Reporting app with Spring Batch


An online shopping website wants to automate reporting on Excel tables for daily sales. For this purpose, a system needs to be developed that automatically generates reports for customers' invoices at the time of daily billing. A system has been developed to perform this task automatically.

##How do you run it?

Step 1: Go to the docker directory and run the code: docker-compose up -d
Step 2: Go back to the main directory and run this command: docker exec -i batch-db mysql -u root -pbatch batch < /path/to/batch-starter.sql
