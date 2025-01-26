package com.smarthome.platform.upc.analytics.application.internal.commandservices;

import com.smarthome.platform.upc.analytics.domain.exceptions.DuplicatePerformanceIndicatorException;
import com.smarthome.platform.upc.analytics.domain.exceptions.InvalidPerformanceIndicatorRangeException;
import com.smarthome.platform.upc.analytics.domain.model.aggregates.PerformanceIndicator;
import com.smarthome.platform.upc.analytics.domain.model.commands.CreatePerformanceIndicatorCommand;
import com.smarthome.platform.upc.analytics.domain.services.PerformanceIndicatorCommandService;
import com.smarthome.platform.upc.analytics.infrastructure.persistence.jpa.repositories.PerformanceIndicatorRepository;
import com.smarthome.platform.upc.shared.domain.model.valueobjects.DeviceTypes;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PerformanceIndicatorCommandServiceImpl implements PerformanceIndicatorCommandService {
    private final PerformanceIndicatorRepository performanceIndicatorRepository;

    public PerformanceIndicatorCommandServiceImpl(PerformanceIndicatorRepository performanceIndicatorRepository) {
        this.performanceIndicatorRepository = performanceIndicatorRepository;
    }

    @Override
    public Optional<PerformanceIndicator> handle(CreatePerformanceIndicatorCommand command) {
        if (performanceIndicatorRepository.existsByDeviceType(DeviceTypes.valueOf(command.deviceType()))) {
            throw new DuplicatePerformanceIndicatorException(command.deviceType());
        }

        if (command.minValue() > command.maxValue()) {
            throw new InvalidPerformanceIndicatorRangeException(command.minValue(), command.maxValue());
        }

        var performanceIndicator = new PerformanceIndicator(command);
        performanceIndicatorRepository.save(performanceIndicator);

        return Optional.of(performanceIndicator);
    }
}
