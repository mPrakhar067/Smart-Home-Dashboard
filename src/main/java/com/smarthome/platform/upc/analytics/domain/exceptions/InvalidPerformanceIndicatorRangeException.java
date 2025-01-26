package com.smarthome.platform.upc.analytics.domain.exceptions;

public class InvalidPerformanceIndicatorRangeException extends RuntimeException {
    public InvalidPerformanceIndicatorRangeException(Double minValue, Double maxValue) {
        super(String.format("Min value %s must be less than max value %s", minValue, maxValue));
    }
}
