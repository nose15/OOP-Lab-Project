package org.pwr.app.eventhandling;


import org.pwr.app.InputManager;
import org.pwr.dtos.ConfigDTO;

import java.util.concurrent.BlockingQueue;

public class BaseEventListener  {
    protected final InputManager inputManager;

    public BaseEventListener(InputManager inputManager) {
        this.inputManager = inputManager;
    }
}
