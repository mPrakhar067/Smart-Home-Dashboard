package com.smarthome.platform.upc.inventory.domain.model.commands;

import java.util.Date;

public record UpdateDeviceCommand(
        Long id,
        String model,
        String deviceType,
        Date installationDate,
        String status
) {
}
