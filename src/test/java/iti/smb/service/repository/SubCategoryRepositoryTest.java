package iti.smb.service.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import iti.smb.service.model.entity.MainCategory;
import iti.smb.service.model.entity.SubCategory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SubCategoryRepositoryTest {

    @Autowired
    private MainCategoryRepository mainCategoryRepository;

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Test
    public void list() throws JsonProcessingException {
        List<SubCategory> categoryList = subCategoryRepository.findAll();
        String result = JsonEncode(categoryList);
        System.out.println(result);
    }

    @Transactional
    @Test
    public void create() {
        MainCategory mainCategory = mainCategoryRepository.findById(1L).orElse(null);
        SubCategory subCategory = new SubCategory();
        subCategory.setName("중분류1");
        subCategory.setMainCategory(mainCategory);

        SubCategory newSubCategory = subCategoryRepository.save(subCategory);

        assertThat(newSubCategory).isNotNull();

        assertThat(newSubCategory.getName()).isEqualTo("중분류1");
        assertThat(newSubCategory.getMainCategory().getName()).isEqualTo("대분류1");
    }

    @Transactional
    @Test
    public void modify() {
        SubCategory subCategory = subCategoryRepository.findById(1L).orElse(null);
        assertThat(subCategory.getName()).isEqualTo("중분류1");
        assertThat(subCategory.getMainCategory().getId()).isEqualTo(1L);

        subCategory.setName("중분류2");

        MainCategory mainCategory = mainCategoryRepository.findById(1L).orElse(null);
        subCategory.setMainCategory(mainCategory);

        subCategoryRepository.save(subCategory);

        assertThat(subCategory.getName()).isEqualTo("중분류2");
        assertThat(subCategory.getMainCategory().getName()).isEqualTo("대분류1");
    }

    @Transactional
    @Test
    public void delete() {
        Optional<SubCategory> subCategory = subCategoryRepository.findById(1L);

        assertThat(subCategory.isPresent()).isTrue();

        subCategory.ifPresent(s -> {
            subCategoryRepository.delete(s);
        });

        Optional<SubCategory> deleteSubCategory = subCategoryRepository.findById(1L);

        assertThat(deleteSubCategory.isPresent()).isFalse();
    }


    public String JsonEncode(Object object) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(object);
    }
}