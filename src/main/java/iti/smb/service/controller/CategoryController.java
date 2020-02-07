package iti.smb.service.controller;

import iti.smb.service.domain.SubCategory;
import iti.smb.service.domain.ThirdCategory;
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
    public List mainCategorylist() {
        return categoryService.mainCategoryList();
    }

    // 대분류 추가
    @PostMapping("/main")
    @ResponseStatus(HttpStatus.CREATED)
    public void addMain(@RequestParam String name) {
        categoryService.addMain(name);
    }

    // 대분류 이름 수정
    @PatchMapping("/main/{id}")
    public void modifyMainName(@RequestParam String name, @PathVariable("id") Long id) {
        categoryService.modifyMainName(name, id);
    }

    // 대분류 삭제
    @DeleteMapping("/main/{id}")
    public void delMain(@PathVariable("id") Long id) {
        categoryService.delMain(id);
    }

    // 중분류 목록
    @GetMapping("/sub/{mainId}")
    public List<SubCategory> subCategoryList(@PathVariable("mainId") Long id) {
        return categoryService.subCategoryList(id);
    }

    // 중분류 추가
    @PostMapping("/sub/{mainId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addSub(@RequestParam String name, @PathVariable("mainId") Long id) {
        categoryService.addSub(name, id);
    }

    // 중분류 이름 수정
    @PatchMapping("/sub/{id}")
    public void modifySubName(@RequestBody String name, @PathVariable("id") Long id) {
        categoryService.modifySubName(name, id);
    }

    // 중분류 삭제
    @DeleteMapping("/sub/{id}")
    public void delSub(@PathVariable("id") Long id) {
        categoryService.delSub(id);
    }

    // 소분류 목록
    @GetMapping("/third/{subId}")
    public List<ThirdCategory> thirdCategoryList(@PathVariable("subId") Long id) {
        return categoryService.thirdCategoryList(id);
    }

    // 소분류 추가
    @PostMapping("/third/{subId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addThird(@RequestParam String name, @PathVariable("subId") Long id) {
        categoryService.addThird(name, id);
    }

    // 소분류 이름 수정
    @PatchMapping("/third/{id}")
    public void modifyThirdName(@RequestParam String name, @PathVariable("id") Long id) {
        categoryService.modifyThirdName(name, id);
    }

    // 소분류 삭제
    @DeleteMapping("third/{id}")
    public void delThird(@PathVariable("id") Long id) {
        categoryService.delThird(id);
    }

}
