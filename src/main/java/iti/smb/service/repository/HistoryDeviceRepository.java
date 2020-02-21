package iti.smb.service.repository;

import iti.smb.service.model.entity.HistoryDevice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoryDeviceRepository extends JpaRepository<HistoryDevice, Long> {

    List<HistoryDevice> findByDeviceId(Long deviceId);

    List<HistoryDevice> findByHistoryId(Long historyId);
}
