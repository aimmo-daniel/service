package iti.smb.service.controller;

import iti.smb.service.model.entity.MainCategory;
import iti.smb.service.model.entity.SubCategory;
import iti.smb.service.model.entity.ThirdCategory;
import iti.smb.service.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/category")
@RestController
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // 대분류 목록
    @GetMapping("/main")
    public List<MainCategory> mainCategoryList() {
        return categoryService.mainCategoryList();
    }

    // 대분류 추가
    @PostMapping("/main")
    @ResponseStatus(HttpStatus.CREATED)
    public void addMainCategory(String name) {
        categoryService.addMain(name);
    }

    // 대분류 이름 수정
    @PatchMapping("/main/{id}")
    public void modifyMainCategoryName(@PathVariable Long id, String name) {
        categoryService.modifyMainName(id, name);
    }

    // 대분류 삭제
    @DeleteMapping("/main/{id}")
    public void deleteMainCategory(@PathVariable Long id) {
        categoryService.delMain(id);
    }

    // 중분류 목록
    @GetMapping("/main/{main_id}/sub")
    public List<SubCategory> subCategoryList(@PathVariable("main_id") Long id) {
        return categoryService.subCategoryList(id);
    }

    // 중분류 추가
    @PostMapping("/main/{main_id}/sub")
    @ResponseStatus(HttpStatus.CREATED)
    public void addSubCategory(@PathVariable("main_id") Long id, String name) {
        categoryService.addSub(id, name);
    }

    // 중분류 이름 수정
    @PatchMapping("/main/{main_id}/sub/{sub_id}")
    public void modifySubCategoryName(@PathVariable("sub_id") Long id, String name) {
        categoryService.modifySubName(id, name);
    }

    // 중분류 삭제
    @DeleteMapping("/main/{main_id}/sub/{sub_id}")
    public void deleteSubCategory(@PathVariable("sub_id") Long id) {
        categoryService.delSub(id);
    }

    // 소분류 목록
    @GetMapping("/sub/{sub_id}/third")
    public List<ThirdCategory> thirdCategoryList(@PathVariable("sub_id") Long id) {
        return categoryService.thirdCategoryList(id);
    }

    // 소분류 추가
    @PostMapping("/sub/{sub_id}/third")
    @ResponseStatus(HttpStatus.CREATED)
    public void addThirdCategory(@PathVariable("sub_id") Long id, String name) {
        categoryService.addThird(id, name);
    }

    // 소분류 이름 수정
    @PatchMapping("/sub/{sub_id}/third/{third_id}")
    public void modifyThirdCategoryName(@PathVariable("third_id") Long id, String name) {
        categoryService.modifyThirdName(id, name);
    }

    // 소분류 삭제
    @DeleteMapping("/sub/{sub_id}/third/{third_id}")
    public void deleteThirdCategory(@PathVariable("third_id") Long id) {
        categoryService.delThird(id);
    }

}
