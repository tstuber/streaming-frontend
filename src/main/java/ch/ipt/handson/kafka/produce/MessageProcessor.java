package ch.ipt.handson.kafka.produce;

import java.util.Date;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import ch.ipt.handson.model.CDCMessage;
import ch.ipt.handson.model.Message;

public class MessageProcessor implements Processor {

    private int counter;

    public void process(Exchange exchange) throws Exception {

        // Retrieve POST parameters
        String ts = exchange.getIn().getHeader("timestamp").toString();
        String msg = exchange.getIn().getHeader("message").toString();

        Date timestamp = new Date(Long.parseLong(ts));

        // Create simulated CDC event message
        Message message = new Message(counter++, timestamp, "Dummy Username", msg);
        CDCMessage cdcmessage = new CDCMessage(message);

        exchange.getIn().setBody(cdcmessage);
    }
}