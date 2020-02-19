package iti.smb.service.controller;

import iti.smb.service.interfaces.CrudInterface;
import iti.smb.service.model.network.Header;
import iti.smb.service.model.network.request.ProductReq;
import iti.smb.service.model.network.response.ProductRes;
import iti.smb.service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/product")
@RestController
public class ProductController implements CrudInterface<ProductReq, ProductRes, Long> {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // 제품 추가
    @Override
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Header<ProductRes> create(@RequestBody ProductReq request) {
        return productService.create(request);
    }

    // 제품 목록 조회
    @Override
    @GetMapping
    public Header<List<ProductRes>> list() {
        return productService.list();
    }

    // 제품 상세 조회
    @Override
    @GetMapping("/{id}")
    public Header<ProductRes> read(@PathVariable("id") Long id) {
        return productService.read(id);
    }

    // 제품 정보 수정
    @Override
    @PatchMapping
    public Header<ProductRes> update(@RequestBody ProductReq request) {
        return productService.update(request);
    }

    // 제품 삭제
    @Override
    @DeleteMapping("/{id}")
    public Header delete(@PathVariable("id") Long id) {
        return productService.delete(id);
    }

}
