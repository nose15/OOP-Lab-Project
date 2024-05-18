package org.pwr.dtos;

import javax.sound.sampled.EnumControl;

public class ConfigDTO {
    public String command;
    public String value;

    public ConfigDTO(String command, String value) {
        this.command = command;
        this.value = value;
    }
}
