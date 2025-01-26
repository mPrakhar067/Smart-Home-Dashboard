package com.smarthome.platform.upc.inventory.domain.exceptions;

public class DuplicateDeviceException extends RuntimeException{
    public DuplicateDeviceException(String serialNumber) {
        super("Device with serial number %s already exists".formatted(serialNumber));
    }
}
