package edu.spring.domain.provider;

public class HelloWorldProvider implements MessageProvider {

    @Override
    public String getMessage() {
        return "Hallo Welt";
    }
}
