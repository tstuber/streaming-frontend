package ch.ipt.handson.model;

import java.util.Date;

public class CDCMessage {

    private Object before;
    private Message after;
    private Object source;
    private String op;
    private Date ts_ms;
    private String transaction;

    public CDCMessage() {}

    public CDCMessage(Message message) {
        this.after = message;
    }

    public Object getBefore() {
        return before;
    }

    public String getTransaction() {
        return transaction;
    }

    public void setTransaction(String transaction) {
        this.transaction = transaction;
    }

    public Date getTs_ms() {
        return ts_ms;
    }

    public void setTs_ms(Date ts_ms) {
        this.ts_ms = ts_ms;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public Object getSource() {
        return source;
    }

    public void setSource(Object source) {
        this.source = source;
    }

    public Message getAfter() {
        return after;
    }

    public void setAfter(Message after) {
        this.after = after;
    }

    public void setBefore(Object before) {
        this.before = before;
    }
    
}