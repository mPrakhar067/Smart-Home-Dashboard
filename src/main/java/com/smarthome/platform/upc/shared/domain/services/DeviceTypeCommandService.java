package com.smarthome.platform.upc.shared.domain.services;

import com.smarthome.platform.upc.shared.domain.model.commands.SeedDeviceTypesCommand;

public interface DeviceTypeCommandService {
    void handle(SeedDeviceTypesCommand command);
}
