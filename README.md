# streaming-frontend project

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Precondition for running the app

* Running OpenShift Cluster
* RH SSO & Keycloak (OIDC)
* Kafka Cluster
* Provided Kafka Secrets within OpenShift namespace

App is based on Quarkus Kafka Quickstart example: https://quarkus.io/guides/kafka.

## Inserting Data to Kafka

Data can be entered into Kafka via a camel route. It can be invoked via UI (http://localhost:8080) or via `curl`.

```
curl --header "Content-Type: application/json" -X POST --data '{"timestamp":"1599336927881", "message":"foo bar"}' http://localhost:8080/direct/message
```

## Retrieving Data from Kafka

Messages from Kafka are consumed via Camel and Smallrye reactive messaging
* https://smallrye.io/smallrye-reactive-messaging/smallrye-reactive-messaging/2/camel/camel.html
* https://camel.apache.org/camel-quarkus/latest/reference/extensions/smallrye-reactive-messaging.html

The frontend recieves messages via Server-sent events (SSE).

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```
./mvnw quarkus:dev
```

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

## Deploying to Openshift 

Deployment to OpenShift is achived via:
* Quarkus OpenShift Extension (for DEV)
* S2I (via this repository)

Further infos can be found here: 
https://access.redhat.com/documentation/en-us/red_hat_build_of_quarkus/1.3/html/deploying_quarkus_applications_on_red_hat_openshift_container_platform/index
