package iti.smb.service.repository;

import iti.smb.service.model.entity.History;
import iti.smb.service.model.enumclass.ServiceStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoryRepository extends JpaRepository<History, Long> {


    // 삭제되지 않은 목록만 조회
    List<History> findByDeletedFalse();

    // 삭제된 목록만 조회
    List<History> findByDeletedTrue();

    // 현재 진행상태에 따라 조회
    List<History> findByStatus(ServiceStatus status);

}
