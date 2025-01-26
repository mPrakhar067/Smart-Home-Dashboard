package com.smarthome.platform.upc.inventory.interfaces.rest.transform;

import com.smarthome.platform.upc.inventory.domain.model.commands.UpdateDeviceCommand;
import com.smarthome.platform.upc.inventory.interfaces.rest.resources.UpdateDeviceResource;

public class UpdateDeviceCommandFromResourceAssembler {

    public static UpdateDeviceCommand toCommandFromResource(UpdateDeviceResource resource, Long id) {
        return new UpdateDeviceCommand(
                id,
                resource.model(),
                resource.deviceType(),
                resource.installationDate(),
                resource.status()
        );
    }
}
