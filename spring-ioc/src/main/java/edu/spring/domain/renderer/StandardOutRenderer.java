package edu.spring.domain.renderer;

import edu.spring.domain.provider.MessageProvider;

public class StandardOutRenderer implements MessageRenderer {

    private MessageProvider mp;

    @Override
    public void render() {
        System.out.println(mp.getMessage());
    }

    @Override
    public void setMessageProvider(MessageProvider mp) {
        this.mp = mp;
    }
}
