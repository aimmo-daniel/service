package iti.smb.service.service;

import iti.smb.service.exception.CategoryNotFoundException;
import iti.smb.service.interfaces.CrudInterface;
import iti.smb.service.model.entity.Category;
import iti.smb.service.model.network.Header;
import iti.smb.service.model.network.request.CategoryReq;
import iti.smb.service.model.network.response.CategoryRes;
import iti.smb.service.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService implements CrudInterface<CategoryReq, CategoryRes, Long> {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Header<CategoryRes> create(CategoryReq request) {
        Category category = Category.builder()
                .name(request.getName())
                .build();

        Category newCategory = categoryRepository.save(category);

        return Header.OK(response(newCategory));
    }

    @Override
    public Header<List<CategoryRes>> list() {
        List<Category> categoryList = categoryRepository.findAll();
        List<CategoryRes> categoryResList = new ArrayList<>();

        for(Category category : categoryList) {
            categoryResList.add(response(category));
        }

        return Header.OK(categoryResList);
    }

    @Override
    public Header<CategoryRes> read(Long id) {
        return categoryRepository.findById(id)
                .map(category -> Header.OK(response(category)))
                .orElseThrow(CategoryNotFoundException::new);
    }

    @Override
    public Header<CategoryRes> update(CategoryReq request) {
        return categoryRepository.findById(request.getId())
                .map(category -> {
                    category.setName(request.getName());

                    categoryRepository.save(category);
                    return Header.OK(response(category));
                })
                .orElseThrow(CategoryNotFoundException::new);
    }

    @Override
    public Header delete(Long id) {
        return categoryRepository.findById(id)
                .map(category -> {
                    categoryRepository.delete(category);
                    return Header.DELETE();
                })
                .orElseThrow(CategoryNotFoundException::new);
    }

    public CategoryRes response(Category category) {
        CategoryRes response = CategoryRes.builder()
                .id(category.getId())
                .name(category.getName())
                .build();

        return response;
    }

}
