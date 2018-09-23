package edu.spring.domain.provider;

public class ExternalizedConstructorHelloWorldMessageProvider implements MessageProvider {

    private String message;

    public ExternalizedConstructorHelloWorldMessageProvider(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
