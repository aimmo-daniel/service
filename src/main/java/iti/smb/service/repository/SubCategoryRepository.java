package iti.smb.service.repository;

import iti.smb.service.domain.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {

    List<SubCategory> findByMainCategoryId(Long id);
}
