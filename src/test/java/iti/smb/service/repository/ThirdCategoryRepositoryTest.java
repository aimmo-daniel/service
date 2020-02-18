package iti.smb.service.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import iti.smb.service.model.entity.SubCategory;
import iti.smb.service.model.entity.ThirdCategory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ThirdCategoryRepositoryTest {

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Autowired
    private ThirdCategoryRepository thirdCategoryRepository;

    @Test
    public void list() throws JsonProcessingException {
        List<ThirdCategory> categoryList = thirdCategoryRepository.findAll();
        String result = JsonEncode(categoryList);
        System.out.println(result);
    }

    @Transactional
    @Test
    public void create() {
        SubCategory subCategory = subCategoryRepository.findById(1L).orElse(null);

        ThirdCategory thirdCategory = new ThirdCategory();
        thirdCategory.setName("소분류1");
        //thirdCategory.setSubCategory(subCategory);

        ThirdCategory newThirdCategory = thirdCategoryRepository.save(thirdCategory);

        assertThat(newThirdCategory).isNotNull();

        assertThat(newThirdCategory.getName()).isEqualTo("소분류1");
        //assertThat(newThirdCategory.getSubCategory().getName()).isEqualTo("중분류1");
        //assertThat(newThirdCategory.getSubCategory().getMainCategory().getName()).isEqualTo("대분류1");
    }

    @Transactional
    @Test
    public void modify() {
        ThirdCategory thirdCategory = thirdCategoryRepository.findById(1L).orElse(null);

        thirdCategory.setName("소분류100");

        thirdCategoryRepository.save(thirdCategory);

        assertThat(thirdCategory.getName()).isEqualTo("소분류100");
    }

    @Transactional
    @Test
    public void delete() {
        Optional<ThirdCategory> thirdCategory = thirdCategoryRepository.findById(1L);

        assertThat(thirdCategory.isPresent()).isTrue();

        thirdCategory.ifPresent(t -> {
            thirdCategoryRepository.delete(t);
        });

        Optional<ThirdCategory> deleteThirdCategory = thirdCategoryRepository.findById(1L);

        assertThat(deleteThirdCategory.isPresent()).isFalse();
    }


    public String JsonEncode(Object object) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(object);
    }

}