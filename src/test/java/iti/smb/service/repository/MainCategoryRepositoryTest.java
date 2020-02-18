package iti.smb.service.repository;

import iti.smb.service.model.entity.MainCategory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
class MainCategoryRepositoryTest {

    @Autowired
    private MainCategoryRepository mainCategoryRepository;

    @Test
    public void list() {
        List<MainCategory> categoryList = mainCategoryRepository.findAll();
        assertThat(categoryList.size()).isEqualTo(2);
        log.info("mainCategoryList : ", categoryList);
    }

    @Test
    public void get() {
        MainCategory mainCategory = mainCategoryRepository.findById(2L).orElse(null);
        assertThat(mainCategory.getName()).isEqualTo("대분류2");
    }

    @Transactional
    @Test
    public void create() {
        MainCategory mainCategory = new MainCategory();
        mainCategory.setName("대분류2");

        MainCategory newMainCategory = mainCategoryRepository.save(mainCategory);

        assertThat(newMainCategory).isNotNull();
        assertThat(newMainCategory.getName()).isEqualTo("대분류2");
    }

    @Transactional
    @Test
    public void modify() {
        MainCategory mainCategory = mainCategoryRepository.findById(1L).orElse(null);
        assertThat(mainCategory.getName()).isEqualTo("대분류1");

        mainCategory.setName("대분류7");

        mainCategoryRepository.save(mainCategory);
        assertThat(mainCategory.getName()).isEqualTo("대분류7");
    }

    @Transactional
    @Test
    public void delete() {
        Optional<MainCategory> mainCategory = mainCategoryRepository.findById(2L);

        assertThat(mainCategory.isPresent()).isTrue();

        mainCategory.ifPresent(m -> {
            mainCategoryRepository.delete(m);
        });

        Optional<MainCategory> deleteMainCategory = mainCategoryRepository.findById(2L);

        assertThat(deleteMainCategory.isPresent()).isFalse();
    }

}