package org.pwr.app.eventhandling;

import org.pwr.app.InputHandler;

public class BaseEventListener {
    protected final InputHandler inputHandler;

    public BaseEventListener(InputHandler inputHandler) {
        this.inputHandler = inputHandler;
    }
}
