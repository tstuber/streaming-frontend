package ch.ipt.handson.kafka.produce;

import javax.enterprise.context.ApplicationScoped;

import org.apache.camel.builder.endpoint.EndpointRouteBuilder;

@ApplicationScoped
public class MessageProvider extends EndpointRouteBuilder {

    MessageProcessor processor = new MessageProcessor();

    @Override
    public void configure() throws Exception {
        from(platformHttp("/camel/message?httpMethodRestrict=GET"))
                .setBody().simple("Camel runs on ${hostname}");

         from(platformHttp("/camel/message?httpMethodRestrict=POST"))
                //.to(log("Debug").showExchangePattern(true).showBodyType(true))
                //.log("${in.body}")
                //.log("${in.headers}")
                .process(processor)
                .marshal().json()
                .to("kafka:messages?brokers=my-cluster-kafka-bootstrap:9092");
    }
}