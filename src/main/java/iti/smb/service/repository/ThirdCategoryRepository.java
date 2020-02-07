package iti.smb.service.repository;

import iti.smb.service.domain.ThirdCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ThirdCategoryRepository extends JpaRepository<ThirdCategory, Long> {

    List<ThirdCategory> findBySubCategoryId(Long id);

}
