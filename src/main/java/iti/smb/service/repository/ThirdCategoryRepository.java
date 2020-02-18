package iti.smb.service.repository;

import iti.smb.service.model.entity.ThirdCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ThirdCategoryRepository extends JpaRepository<ThirdCategory, Long> {

    List<ThirdCategory> findBySubCategoryId(Long subCategoryId);

}
