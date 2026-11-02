FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY src/ ./src/
RUN find src -name "*.java" > sources.txt && \
    javac -encoding UTF-8 -d out @sources.txt
CMD ["java", "-cp", "out", "br/com/desafionttdata/Main"]