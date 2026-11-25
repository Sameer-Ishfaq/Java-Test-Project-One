#Creating a new container based on a linux image that already has jdk 26 installec
FROM maven

#Creating a folder called swagger api testing and to move into it. Same as cd /App
WORKDIR /swaggerApiTesting

#Copies the conents of src into the swaggerApiTesting directory
#Copies dependencies
COPY . .

RUN mvn clean test

CMD ["mvn", "clean", "test"]
