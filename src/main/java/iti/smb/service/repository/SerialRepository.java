package iti.smb.service.repository;

import iti.smb.service.domain.Serial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SerialRepository extends JpaRepository<Serial, Long> {
}
