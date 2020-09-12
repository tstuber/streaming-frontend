package ch.ipt.handson.model;

import java.util.Date;
import java.util.Objects;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection // Lets Quarkus register this class for reflection during the native build
public class Message {

    private int id;
    private String timestamp;
    private String username;
    private String message;

    public Message(int id, String timestamp, String username, String message) {
        this.id = id;
        this.timestamp = timestamp;
        this.username = username;
        this.message = message;
    }

    public Message(int id, String username, String message) {
        this.id = id;
        this.timestamp = new Date().toString();
        this.username = username;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Message)) {
            return false;
        }

        Message other = (Message) obj;

        return Objects.equals(other.username, this.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.username);
    }
}