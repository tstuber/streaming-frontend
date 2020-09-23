package ch.ipt.handson.kafka.consumer;

import org.apache.camel.builder.RouteBuilder;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import org.reactivestreams.Publisher;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.apache.camel.component.reactive.streams.api.CamelReactiveStreamsService;

/**
 * A bean consuming data from the "message" Kafka topic. The result is pushed to
 * the "my-message-stream" stream which is an in-memory stream.
 */
@ApplicationScoped
public class MessageConsumerRoute extends RouteBuilder {

    @Inject
    CamelReactiveStreamsService reactiveStreamsService;

    @Outgoing("message-stream")
    public Publisher<String> getDataFromCamelRoute() {
        return reactiveStreamsService.fromStream("kafka-stream", String.class);
    }

    @Override
    public void configure() throws Exception {
        from("kafka:{{kafka.topic}}" + "?brokers={{kafka.bootstrap.servers}}"
                + "&securityProtocol={{kafka.security.protocol}}"
                + "&sslTruststoreLocation={{kafka.ssl.truststore.location}}"
                + "&sslTruststorePassword={{kafka.ssl.truststore.password}}"
                + "&sslTruststoreType={{kafka.ssl.truststore.type}}")
                .log("body: ${in.body}")
                .to("reactive-streams:kafka-stream").routeId("route-kafka-reactive-stream");
    }
}