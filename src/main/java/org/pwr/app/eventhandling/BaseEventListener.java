package org.pwr.app.eventhandling;


import org.pwr.app.InputManager;

public class BaseEventListener  {
    protected final InputManager inputManager;

    public BaseEventListener(InputManager inputManager) {
        this.inputManager = inputManager;
    }
}
