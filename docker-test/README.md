##Create and run docker image ##

**Create docker image:**
 
    cd docker-test
    mvn clean install dockerfile:build

**Run docker image:**
 
    docker run -p 8080:9080 -t pro4nist/docker-test --name hello-docker-container 
 
Here the option -p 8080:9080 is important. It says that expose port 8080 for internal port 9080. Remember our application is running in port 9080 inside docker image and we will access that in port 8080 from outside Docker container.

Now access the application with URL http://192.168.99.100:8080/hello/DOCKER.  