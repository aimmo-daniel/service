package iti.smb.service.service;

import iti.smb.service.domain.MainCategory;
import iti.smb.service.domain.SubCategory;
import iti.smb.service.domain.ThirdCategory;
import iti.smb.service.repository.MainCategoryRepository;
import iti.smb.service.repository.SubCategoryRepository;
import iti.smb.service.repository.ThirdCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private MainCategoryRepository mainCategoryRepository;
    private SubCategoryRepository subCategoryRepository;
    private ThirdCategoryRepository thirdCategoryRepository;

    @Autowired
    public CategoryService(MainCategoryRepository maincategoryRepository,
                           SubCategoryRepository subCategoryRepository,
                           ThirdCategoryRepository thirdCategoryRepository) {
        this.mainCategoryRepository = maincategoryRepository;
        this.subCategoryRepository = subCategoryRepository;
        this.thirdCategoryRepository = thirdCategoryRepository;
    }


    public List categoryList(String type) {
        switch (type) {
            case "M":
                return mainCategoryRepository.findAll();
            case "S":
                return subCategoryRepository.findAll();
            case "T":
                return thirdCategoryRepository.findAll();
        }
        return null;
    }

}
