FROM openjdk:11-jdk
EXPOSE 8090
WORKDIR /app

# Copy maven executable to the image
COPY mvnw .
COPY .mvn .mvn


# Copy the pom.xml file
COPY ./pom.xml ./pom.xml

RUN chmod 755 /app/mvnw
RUN ./mvnw dependency:go-offline -B -Dmaven.artifact.threads=35


# Copy the project source
COPY ./src ./src


RUN ./mvnw  package
#RUN ls -al
ENTRYPOINT ["java","-jar","target/orderValidation-0.0.1-SNAPSHOT.jar"]