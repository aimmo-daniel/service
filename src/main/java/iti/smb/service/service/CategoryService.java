package iti.smb.service.service;

import iti.smb.service.domain.MainCategory;
import iti.smb.service.domain.SubCategory;
import iti.smb.service.domain.ThirdCategory;
import iti.smb.service.exception.CategoryNotFoundException;
import iti.smb.service.repository.MainCategoryRepository;
import iti.smb.service.repository.SubCategoryRepository;
import iti.smb.service.repository.ThirdCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public List<MainCategory> mainCategoryList() {
        return mainCategoryRepository.findAll();
    }

    @Transactional
    public void addMain(String name) {
        mainCategoryRepository.save(MainCategory.builder().name(name).build());
    }

    @Transactional
    public void modifyMainName(String name, Long id) {
        MainCategory mainCategory = mainCategoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new);

        mainCategory.setName(name);

        mainCategoryRepository.save(mainCategory);
    }

    @Transactional
    public void delMain(Long id) {
        mainCategoryRepository.deleteById(id);
    }

    public List<SubCategory> subCategoryList(Long id) {
        return subCategoryRepository.findByMainCategoryId(id);
    }

    @Transactional
    public void addSub(String name, Long id) {
        SubCategory subCategory = SubCategory.builder()
                .name(name)
                .mainCategoryId(id)
                .build();
        subCategoryRepository.save(subCategory);
    }

    @Transactional
    public void modifySubName(String name, Long id) {
        SubCategory subCategory = subCategoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new);
        subCategory.setName(name);
        subCategoryRepository.save(subCategory);
    }

    @Transactional
    public void delSub(Long id) {
        subCategoryRepository.deleteById(id);
    }

    public List<ThirdCategory> thirdCategoryList(Long id) {
        return thirdCategoryRepository.findBySubCategoryId(id);
    }

    @Transactional
    public void addThird(String name, Long id) {
        ThirdCategory thirdCategory = ThirdCategory.builder()
                .name(name)
                .subCategoryId(id)
                .build();
        thirdCategoryRepository.save(thirdCategory);
    }

    @Transactional
    public void modifyThirdName(String name, Long id) {
        ThirdCategory thirdCategory = thirdCategoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new);
        thirdCategory.setName(name);
        thirdCategoryRepository.save(thirdCategory);
    }

    @Transactional
    public void delThird(Long id) {
        thirdCategoryRepository.deleteById(id);
    }
}
