package com.smarthome.platform.upc.analytics.domain.exceptions;

public class DuplicatePerformanceIndicatorException extends RuntimeException{
    public DuplicatePerformanceIndicatorException(String deviceType) {
        super("Performance Indicator with device type %s already exists".formatted(deviceType));
    }
}
