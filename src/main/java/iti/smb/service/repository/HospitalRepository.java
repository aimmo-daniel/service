package iti.smb.service.repository;

import iti.smb.service.model.entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HospitalRepository extends JpaRepository<Hospital, Long> {

    // 병원 코드로 병원 상세정보 조회
    Optional<Hospital> findByCode(String code);

    // 서비스중인 병원 목록만 조회
    List<Hospital> findByEndServiceFalse();

    // 서비스 종료된 병원 목록만 조회
    List<Hospital> findByEndServiceTrue();

}
