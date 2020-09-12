package ch.ipt.handson.model;

import java.util.Date;
import java.util.Objects;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection // Lets Quarkus register this class for reflection during the native build
public class Message {

    private int id;
    private String timestamp;
    private String name;
    private String message;

    public Message(int id, String timestamp, String name, String message) {
        this.id = id;
        this.timestamp = timestamp;
        this.name = name;
        this.message = message;
    }

    public Message(int id, String name, String message) {
        this.id = id;
        this.timestamp = new Date().toString();
        this.name = name;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return timestamp;
    }

    public void setDate(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Message)) {
            return false;
        }

        Message other = (Message) obj;

        return Objects.equals(other.name, this.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name);
    }
}