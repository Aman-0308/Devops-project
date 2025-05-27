# Stage 1: Build the application using Maven
# Taking Maven pre-build image.

FROM maven:3.9.4-eclipse-temurin-17 AS build
WORKDIR /app

# Copy Dependencies, iMaven configuration in pom.xml and wrapper (Maven wrapper scripts that allow you to run Maven commands without having to install Maven on your system.)
COPY pom.xml ./

#If we maven is not installed in system then you can run below comands # commands for Maven configuration in pom.xml and wrapper (Maven wrapper scripts that allow you to run Maven commands without having to install Maven

#COPY .mvn/ ./.mvn
#COPY mvnw mvnw.cmd ./

# RUN mvn dependency:go-offline to cache all dependencies mentined in the pom.xml file. This is a good practice to speed up the build process, especially when you are working with a large number of dependencies.
# It downloads all the dependencies specified in the pom.xml file and stores them in the local repository, so they don't need to be downloaded again during the build process.
RUN mvn dependency:go-offline

# Copy the rest of the application code
COPY logs ./
COPY src ./src

# Build the app. ./mvnw clean package- is a command that compiles the Java code (maven), clean (deletes any previous build output) and packages the application into a deployable format (/target/.jar). The -DskipTests flag is used to skip the test phase, which can speed up the build process if you are not interested in running tests at this stage.
RUN mvnw clean package -DskipTests

# Stage 2: Final image (distroless- Lighter version (reduce image size) to serve the app
FROM gcr.io/distroless/java17-debian11:latest  AS final

# The final stage takes the built static files from the build stage and copies them into the appropriate directory for Nginx to serve them.

# Copy the build artifacts from the build stage
COPY --from=BUILD /app/target/*.jar /app/app.jar
# COPY --from=build /app/target/*.jar /app/app.jar copies the built files from the build container (the build stage)

# Start the application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]

