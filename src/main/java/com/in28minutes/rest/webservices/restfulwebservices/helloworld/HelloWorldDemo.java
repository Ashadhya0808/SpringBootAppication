package com.in28minutes.rest.webservices.restfulwebservices.helloworld;

public class HelloWorldDemo {
    private String message;

    public HelloWorldDemo(String message) {
        this.message = message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "HelloWorldDemo[" +
                "message='" + message + '\'' +
                ']';
    }
}
