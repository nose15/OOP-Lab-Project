package org.pwr.app.eventhandling;

import org.pwr.InputHandler;

public class BaseEventListener {
    protected final InputHandler inputHandler;

    public BaseEventListener(InputHandler inputHandler) {
        this.inputHandler = inputHandler;
    }
}
