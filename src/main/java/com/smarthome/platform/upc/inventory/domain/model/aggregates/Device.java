package com.smarthome.platform.upc.inventory.domain.model.aggregates;

import com.smarthome.platform.upc.inventory.domain.model.commands.CreateDeviceCommand;
import com.smarthome.platform.upc.inventory.domain.model.commands.UpdateDeviceCommand;
import com.smarthome.platform.upc.inventory.domain.model.valueobjects.DeviceStatus;
import com.smarthome.platform.upc.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.smarthome.platform.upc.shared.domain.model.valueobjects.DeviceTypes;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Date;

@Entity
public class Device extends AuditableAbstractAggregateRoot<Device> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotBlank
    @Size(max = 30)
    @Column(nullable = false, unique = true)
    private String serialNumber;

    @NotBlank
    @Size(max = 50)
    @Column(nullable = false, unique = true)
    private String model;

    @NotNull
    private DeviceTypes deviceType;

    private Date installationDate;

    public Date getInstallationDate() {
        return installationDate;
    }

    public void setInstallationDate(Date installationDate) {
        this.installationDate = installationDate;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @NotNull
    private DeviceStatus status;

    protected Device() {}

    public Device(CreateDeviceCommand command) {
        this.serialNumber = command.serialNumber();
        this.model = command.model();
        this.deviceType = DeviceTypes.valueOf(command.deviceType());
        this.installationDate = command.installationDate();
        this.status = DeviceStatus.valueOf(command.status());
    }

    public void update(UpdateDeviceCommand command) {
        this.model = command.model();
        this.deviceType = DeviceTypes.valueOf(command.deviceType());
        this.installationDate = command.installationDate();
        this.status = DeviceStatus.valueOf(command.status());
    }

    public String getDeviceType() {
        return deviceType.name();
    }

    public String getStatus() {
        return status.name();
    }
}
