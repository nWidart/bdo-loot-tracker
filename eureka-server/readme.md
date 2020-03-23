# User Service

## Docker

### Build

```
./mvnw install dockerfile:build -DskipTests
```

### Run

You need to have a running mysql container running with a user `root`, password `root` and a `bdo-loot-tracker` database.

Fill in the environment variables


```
docker run -d --name eureka-server \
    -p 8761:8761 \
    nwidart/bdoloottracker/eureka-service \
    "eureka-server-0.0.1-SNAPSHOT.jar"
```
