package com.smarthome.platform.upc.shared.infrastructure.persistence.jpa.repositories;

import com.smarthome.platform.upc.shared.domain.model.entities.DeviceType;
import com.smarthome.platform.upc.shared.domain.model.valueobjects.DeviceTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceTypeRepository extends JpaRepository<DeviceType, Long> {
    boolean existsByName(DeviceTypes name);
}
