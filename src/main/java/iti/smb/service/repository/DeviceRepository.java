package iti.smb.service.repository;

import iti.smb.service.model.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DeviceRepository extends JpaRepository<Device, Long> {

    Optional<Device> findBySerialNumber(String serialNumber);

}
