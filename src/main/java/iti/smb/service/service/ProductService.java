package iti.smb.service.service;

import iti.smb.service.exception.ProductNotFoundException;
import iti.smb.service.interfaces.CrudInterface;
import iti.smb.service.model.entity.Product;
import iti.smb.service.model.network.Header;
import iti.smb.service.model.network.request.ProductReq;
import iti.smb.service.model.network.response.ProductRes;
import iti.smb.service.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService implements CrudInterface<ProductReq, ProductRes, Long> {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Header<ProductRes> create(ProductReq request) {
        Product product = Product.builder()
                .name(request.getName()).build();

        Product newProduct = productRepository.save(product);

        return Header.OK(response(newProduct));
    }

    @Override
    public Header<List<ProductRes>> list() {
        List<Product> productList = productRepository.findAll();
        List<ProductRes> productResList = new ArrayList<>();

        for(Product product : productList) {
            productResList.add(response(product));
        }

        return Header.OK(productResList);
    }

    @Override
    public Header<ProductRes> read(Long id) {
        return productRepository.findById(id)
                .map(product -> Header.OK(response(product)))
                .orElseThrow(ProductNotFoundException::new);
    }

    @Override
    public Header<ProductRes> update(ProductReq request) {
        return productRepository.findById(request.getId())
                .map(product -> {
                    product.setName(request.getName());

                    productRepository.save(product);
                    return Header.OK(response(product));
                })
                .orElseThrow(ProductNotFoundException::new);
    }

    @Override
    public Header delete(Long id) {
        return productRepository.findById(id)
                .map(product -> {
                    productRepository.delete(product);
                    return Header.DELETE();
                })
                .orElseThrow(ProductNotFoundException::new);
    }

    public ProductRes response(Product product) {
        ProductRes response = ProductRes.builder()
                .id(product.getId())
                .name(product.getName())
                .build();

        return response;
    }

}
