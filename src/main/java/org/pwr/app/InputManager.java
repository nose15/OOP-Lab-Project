package org.pwr.app;

import org.pwr.dtos.ConfigDTO;

import java.util.concurrent.BlockingQueue;

public class InputManager {
    private final BlockingQueue<ConfigDTO> queue;
    
    public InputManager(BlockingQueue<ConfigDTO> queue) {
        this.queue = queue;
        try {
            queue.put(new ConfigDTO("setAllZero", String.valueOf(0)));
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }
    
    public void processInput(String name, int val) {
        try {
            queue.put(new ConfigDTO(name, String.valueOf(val)));
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }

    public void processInput(String name, boolean val) {
        try {
            queue.put(new ConfigDTO(name, String.valueOf(val)));
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }
}
