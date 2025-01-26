package com.smarthome.platform.upc.inventory.application.internal.commandservices;

import com.smarthome.platform.upc.inventory.domain.exceptions.DuplicateDeviceException;
import com.smarthome.platform.upc.inventory.domain.model.aggregates.Device;
import com.smarthome.platform.upc.inventory.domain.model.commands.CreateDeviceCommand;
import com.smarthome.platform.upc.inventory.domain.model.commands.UpdateDeviceCommand;
import com.smarthome.platform.upc.inventory.domain.services.DeviceCommandService;
import com.smarthome.platform.upc.inventory.infrastructure.persistence.jpa.repositories.DeviceRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class DeviceCommandServiceImpl implements DeviceCommandService {
    private final DeviceRepository deviceRepository;

    public DeviceCommandServiceImpl(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    @Override
    public Optional<Device> handle(CreateDeviceCommand command) {
        if (deviceRepository.existsBySerialNumber(command.serialNumber())) {
            throw new DuplicateDeviceException(command.serialNumber());
        }

        if (command.installationDate().before(new Date())) {
            throw new IllegalArgumentException("Installation date cannot be in the past");
        }

        var device = new Device(command);
        deviceRepository.save(device);
        return Optional.of(device);
    }

    @Override
    public Optional<Device> handle(UpdateDeviceCommand command) {
        var device = deviceRepository.findById(command.id())
                .orElseThrow(() -> new IllegalArgumentException("Device not found"));

        if (command.installationDate().before(new Date())) {
            throw new IllegalArgumentException("Installation date cannot be in the past");
        }

        device.update(command);
        deviceRepository.save(device);
        return Optional.of(device);
    }
}
