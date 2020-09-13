package ch.ipt.handson.kafka.produce;

import javax.enterprise.context.ApplicationScoped;

import org.apache.camel.builder.endpoint.EndpointRouteBuilder;

@ApplicationScoped
public class MessageProvider extends EndpointRouteBuilder {

    MessageProcessor processor = new MessageProcessor();

    @Override
    public void configure() throws Exception {
        from(platformHttp("/direct/message?httpMethodRestrict=GET"))
                .setBody().simple("Camel runs on ${hostname}");

         from(platformHttp("/direct/message?httpMethodRestrict=POST"))
                //.to(log("Debug").showExchangePattern(true).showBodyType(true))
                //.log("${in.body}")
                //.log("${in.headers}")
                .process(processor)
                .marshal().json()
                .to("kafka:{{mp.messaging.incoming.messages.topic}}"
                    + "?brokers={{mp.messaging.incoming.messages.bootstrap.servers}}"
                    + "&securityProtocol=SSL"
                    + "&sslTruststoreLocation={{mp.messaging.incoming.messages.ssl.truststore.location}}"
                    + "&sslTruststorePassword={{mp.messaging.incoming.messages.ssl.truststore.password}}"
                    + "&sslTruststoreType={{mp.messaging.incoming.messages.ssl.truststore.type}}");
    }
}