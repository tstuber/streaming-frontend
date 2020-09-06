# streaming-frontend project

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Precondition for running the app

* Local kafka cluster runnning
* Unprotected broker port 9092 open

App is based on Quarkus Kafka Quickstart example: https://quarkus.io/guides/kafka.

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```
./mvnw quarkus:dev
```

## Inserting Data to Kafka

Data can be entered into Kafka via a camel route. It can be invoked via UI (http://localhost:8080/message.html) or via `curl`.

```
curl --header "Content-Type: application/json" -X POST --data '{"timestamp":1599336927881, "message":"foo bar"}' http://localhost:8080/camel/message
```

## Retrieving Data from Kafka

The UI recieves Server-side events in case new messages arrive in Kafka. 

## Packaging and running the application

The application can be packaged using `./mvnw package`.
It produces the `streaming-frontend-1.0-SNAPSHOT-runner.jar` file in the `/target` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/lib` directory.

The application is now runnable using `java -jar target/streaming-frontend-1.0-SNAPSHOT-runner.jar`.

## Creating a native executable

You can create a native executable using: `./mvnw package -Pnative`.

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: `./mvnw package -Pnative -Dquarkus.native.container-build=true`.

You can then execute your native executable with: `./target/streaming-frontend-1.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/building-native-image.