package edu.spring.domain.provider;

public class HelloWorldProvider implements MessageProvider {

    protected String message = "Hallo Welt";

    @Override
    public String getMessage() {
        return message;
    }
}
