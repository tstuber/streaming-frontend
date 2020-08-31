package ch.ipt.handson.kafka.produce;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import ch.ipt.handson.model.Message;

public class MessageProcessor implements Processor {

    private int counter;

    public void process(Exchange exchange) throws Exception {

        String name = exchange.getIn().getHeader("name").toString();
        String msg = exchange.getIn().getHeader("message").toString();

        Message message = new Message(counter++, name, msg);

        exchange.getIn().setBody(message);
    }
}