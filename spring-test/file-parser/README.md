## Upload and parse file Spring Boot sample ##

To start application run ParseFileApplication from IDEA or in terminal

    mvn spring-boot:run
    
 * Go to http://localhost:8088/ upload csv file with a structure represented by file 
 [example.csv](src/main/resources/data_s.csv) 
 
 * Go to http://localhost:8088/h2-console select jdbc:h2:mem:testdb to see uploaded data
 
 * It is interesting to run jConsole application in order to see memory consumption during large file uploading
  
 * Go to http://localhost:8088/swagger-ui.html there are all exposed REST API 
