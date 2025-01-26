package com.smarthome.platform.upc.analytics.domain.model.aggregates;

import com.smarthome.platform.upc.analytics.domain.model.commands.CreatePerformanceIndicatorCommand;
import com.smarthome.platform.upc.shared.domain.model.valueobjects.DeviceTypes;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class PerformanceIndicator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 40)
    private String name;

    @NotBlank
    @Size(max = 200)
    private String description;

    @NotNull
    private Double minValue;

    @NotNull
    private Double maxValue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getMinValue() {
        return minValue;
    }

    public void setMinValue(Double minValue) {
        this.minValue = minValue;
    }

    public Double getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(Double maxValue) {
        this.maxValue = maxValue;
    }

    @NotNull
    private DeviceTypes deviceType;

    protected PerformanceIndicator() {}

    public PerformanceIndicator(CreatePerformanceIndicatorCommand command) {
        this.name = command.name();
        this.description = command.description();
        this.minValue = command.minValue();
        this.maxValue = command.maxValue();
        this.deviceType = DeviceTypes.valueOf(command.deviceType());
    }

    public String getDeviceType() {
        return deviceType.name();
    }
}
