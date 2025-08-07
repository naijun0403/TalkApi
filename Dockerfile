# Stage 1: Cache Gradle dependencies
FROM gradle:latest AS cache
RUN mkdir -p /home/gradle/cache_home
ENV GRADLE_USER_HOME=/home/gradle/cache_home
COPY build.gradle.* gradle.properties /home/gradle/app/
COPY gradle /home/gradle/app/gradle
WORKDIR /home/gradle/app
RUN gradle --no-daemon dependencies

# Stage 2: Final image with application
FROM gradle:latest
EXPOSE 8080
COPY --from=cache /home/gradle/cache_home /home/gradle/.gradle
COPY --chown=gradle:gradle . /app
WORKDIR /app
# Make sure Gradle wrapper is executable
RUN chmod +x ./gradlew
# Run the application using Gradle wrapper
CMD ["./gradlew", "run", "--no-daemon"]