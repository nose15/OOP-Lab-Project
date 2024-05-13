package org.pwr.app;

public interface InputHandler {
    void handleInput(String name, int value);
    void handleInput(String name, float value);
    void handleInput(String name, boolean value);
}
