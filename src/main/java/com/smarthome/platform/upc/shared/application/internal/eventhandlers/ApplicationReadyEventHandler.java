package com.smarthome.platform.upc.shared.application.internal.eventhandlers;

import com.smarthome.platform.upc.shared.domain.model.commands.SeedDeviceTypesCommand;
import com.smarthome.platform.upc.shared.domain.services.DeviceTypeCommandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class ApplicationReadyEventHandler {
    private final DeviceTypeCommandService deviceTypeCommandService;
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationReadyEventHandler.class);

    public ApplicationReadyEventHandler(DeviceTypeCommandService deviceTypeCommandService) {
        this.deviceTypeCommandService = deviceTypeCommandService;
    }

    @EventListener
    public void on(ApplicationReadyEvent event) {
        var applicationName = event.getApplicationContext().getId();
        LOGGER.info("Application {} is ready at {}", applicationName, currentTimestamp());
        var seedDeviceTypesCommand = new SeedDeviceTypesCommand();
        deviceTypeCommandService.handle(seedDeviceTypesCommand);
        LOGGER.info("Seed device types command handled at {}", currentTimestamp());
    }

    private Timestamp currentTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }
}
