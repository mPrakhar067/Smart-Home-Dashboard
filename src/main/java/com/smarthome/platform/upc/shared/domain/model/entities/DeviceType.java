package com.smarthome.platform.upc.shared.domain.model.entities;

import com.smarthome.platform.upc.shared.domain.model.valueobjects.DeviceTypes;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class DeviceType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DeviceTypes getName() {
        return name;
    }

    public void setName(DeviceTypes name) {
        this.name = name;
    }

    @Enumerated(EnumType.STRING)
    @Column(unique = true, length = 20)
    private DeviceTypes name;

    public DeviceType() {}

    public DeviceType(DeviceTypes name) {
        this.name = name;
    }

    public String getStringName() { return name.toString(); }

    public static DeviceType getDefaultDeviceType() { return new DeviceType(DeviceTypes.LIGHTING); }

    public static DeviceType toDeviceTypeFromName(String name) {
        return new DeviceType(DeviceTypes.valueOf(name));
    }

    public static List<DeviceType> validateDeviceTypeSet(List<DeviceType> deviceTypes) {
        if (deviceTypes == null || deviceTypes.isEmpty()) return List.of(getDefaultDeviceType());
        return deviceTypes;
    }
}
