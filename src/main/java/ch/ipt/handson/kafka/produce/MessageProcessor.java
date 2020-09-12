package ch.ipt.handson.kafka.produce;

import java.util.Date;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import ch.ipt.handson.model.CDCMessage;
import ch.ipt.handson.model.Message;

public class MessageProcessor implements Processor {

    private int counter;

    public void process(Exchange exchange) throws Exception {

        // Retrieve POST parameters
        String jsonString = exchange.getIn().getBody(String.class);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonRequest = mapper.readTree(jsonString);
        String timestamp = jsonRequest.get("timestamp").textValue();
        String msg = jsonRequest.get("message").textValue();

        // Create simulated CDC event message
        Message message = new Message(counter++, timestamp, "Dummy Username", msg);
        CDCMessage cdcmessage = new CDCMessage(message);

        exchange.getIn().setBody(cdcmessage);
    }
}