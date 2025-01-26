package com.smarthome.platform.upc.shared.application.internal.commandservices;

import com.smarthome.platform.upc.shared.domain.model.commands.SeedDeviceTypesCommand;
import com.smarthome.platform.upc.shared.domain.model.entities.DeviceType;
import com.smarthome.platform.upc.shared.domain.model.valueobjects.DeviceTypes;
import com.smarthome.platform.upc.shared.domain.services.DeviceTypeCommandService;
import com.smarthome.platform.upc.shared.infrastructure.persistence.jpa.repositories.DeviceTypeRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DeviceTypeCommandServiceImpl implements DeviceTypeCommandService {
    private final DeviceTypeRepository deviceTypeRepository;

    public DeviceTypeCommandServiceImpl(DeviceTypeRepository deviceTypeRepository) {
        this.deviceTypeRepository = deviceTypeRepository;
    }

    @Override
    public void handle(SeedDeviceTypesCommand command) {
        Arrays.stream(DeviceTypes.values()).forEach(deviceTypes -> {
            if (!deviceTypeRepository.existsByName(deviceTypes)) {
                deviceTypeRepository.save(new DeviceType(DeviceTypes.valueOf(deviceTypes.name())));
            }
        });
    }
}
