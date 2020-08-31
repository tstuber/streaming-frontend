package ch.ipt.handson.kafka;

import io.smallrye.reactive.messaging.annotations.Broadcast;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import javax.enterprise.context.ApplicationScoped;

/**
 * A bean consuming data from the "message" Kafka topic.
 * The result is pushed to the "my-message-stream" stream which is an in-memory stream.
 */
@ApplicationScoped
public class MessageConverter {

    @Incoming("messages")                                 
    @Outgoing("my-message-stream")                         
    @Broadcast                                          
    public String process(String message) {
        return message;
    }

}