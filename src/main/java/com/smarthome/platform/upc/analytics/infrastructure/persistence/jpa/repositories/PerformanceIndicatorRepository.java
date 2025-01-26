package com.smarthome.platform.upc.analytics.infrastructure.persistence.jpa.repositories;

import com.smarthome.platform.upc.analytics.domain.model.aggregates.PerformanceIndicator;
import com.smarthome.platform.upc.shared.domain.model.valueobjects.DeviceTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerformanceIndicatorRepository extends JpaRepository<PerformanceIndicator, Long> {
    boolean existsByDeviceType(DeviceTypes deviceType);
}
