package com.smarthome.platform.upc.inventory.interfaces.rest;

import com.smarthome.platform.upc.inventory.domain.services.DeviceCommandService;
import com.smarthome.platform.upc.inventory.interfaces.rest.resources.CreateDeviceResource;
import com.smarthome.platform.upc.inventory.interfaces.rest.resources.DeviceResource;
import com.smarthome.platform.upc.inventory.interfaces.rest.resources.UpdateDeviceResource;
import com.smarthome.platform.upc.inventory.interfaces.rest.transform.CreateDeviceCommandFromResourceAssembler;
import com.smarthome.platform.upc.inventory.interfaces.rest.transform.DeviceResourceFromEntityAssembler;
import com.smarthome.platform.upc.inventory.interfaces.rest.transform.UpdateDeviceCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/devices", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Devices", description = "Endpoints for managing devices")
public class DevicesController {
    private final DeviceCommandService deviceCommandService;

    public DevicesController(DeviceCommandService deviceCommandService) {
        this.deviceCommandService = deviceCommandService;
    }

    @PostMapping
    @Operation(summary = "Create a new device", description = "Create a new device")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Device created"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<DeviceResource> createDevice(@RequestBody CreateDeviceResource resource) {
        var command = CreateDeviceCommandFromResourceAssembler.toCommandFromResource(resource);
        var device = deviceCommandService.handle(command);
        if (device.isEmpty()) return ResponseEntity.badRequest().build();
        var createdDevice = device.get();
        var deviceResource = DeviceResourceFromEntityAssembler.toResourceFromEntity(createdDevice);
        return ResponseEntity.ok(deviceResource);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a device", description = "Update a device")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Device updated"),
            @ApiResponse(responseCode = "404", description = "Device not found")
    })
    public ResponseEntity<DeviceResource> updateDevice(@PathVariable Long id, @RequestBody UpdateDeviceResource resource) {
        var command = UpdateDeviceCommandFromResourceAssembler.toCommandFromResource(resource, id);
        var device = deviceCommandService.handle(command);
        if (device.isEmpty()) return ResponseEntity.badRequest().build();
        var updatedDevice = device.get();
        var deviceResource = DeviceResourceFromEntityAssembler.toResourceFromEntity(updatedDevice);
        return ResponseEntity.ok(deviceResource);
    }
}
