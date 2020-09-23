package ch.ipt.handson.kafka.producer;

import javax.enterprise.context.ApplicationScoped;

import org.apache.camel.builder.endpoint.EndpointRouteBuilder;

@ApplicationScoped
public class MessageProducerRoute extends EndpointRouteBuilder {

    MessageProcessor processor = new MessageProcessor();

    @Override
    public void configure() throws Exception {
        from(platformHttp("/direct/message").httpMethodRestrict("GET"))
                .setBody().simple("Camel runs on ${hostname}");

        from(platformHttp("/direct/message").httpMethodRestrict("POST"))
                // .to(log("Debug").showExchangePattern(true).showBodyType(true))
                // .log("${in.body}")
                // .log("${in.headers}")
                .process(processor)
                .marshal().json()
                .to("kafka:{{kafka.topic}}" + "?brokers={{kafka.bootstrap.servers}}"
                        + "&securityProtocol={{kafka.security.protocol}}"
                        + "&sslTruststoreLocation={{kafka.ssl.truststore.location}}"
                        + "&sslTruststorePassword={{kafka.ssl.truststore.password}}"
                        + "&sslTruststoreType={{kafka.ssl.truststore.type}}");
    }
}