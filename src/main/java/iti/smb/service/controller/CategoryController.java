package iti.smb.service.controller;

import iti.smb.service.interfaces.CrudInterface;
import iti.smb.service.model.network.Header;
import iti.smb.service.model.network.request.CategoryReq;
import iti.smb.service.model.network.response.CategoryRes;
import iti.smb.service.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/category")
@RestController
public class CategoryController implements CrudInterface<CategoryReq, CategoryRes, Long> {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // 카테고리 추가
    @Override
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Header<CategoryRes> create(@RequestBody CategoryReq request) {
        return categoryService.create(request);
    }

    // 카테고리 목록 조회
    @Override
    @GetMapping
    public Header<List<CategoryRes>> list() {
        return categoryService.list();
    }

    // 카테고리 상세 조회
    @Override
    @GetMapping("/{id}")
    public Header<CategoryRes> read(@PathVariable("id") Long id) {
        return categoryService.read(id);
    }

    // 카테고리 이름 수정
    @Override
    @PatchMapping
    public Header<CategoryRes> update(@RequestBody CategoryReq request) {
        return categoryService.update(request);
    }

    // 카테고리 삭제
    @Override
    @DeleteMapping("/{id}")
    public Header delete(@PathVariable("id") Long id) {
        return categoryService.delete(id);
    }
}
