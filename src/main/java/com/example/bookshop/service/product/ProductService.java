package com.example.bookshop.service.product;

import com.example.bookshop.entity.dto.ProductDto;
import com.example.bookshop.entity.product.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {

    List<ProductDto> findAll() throws Exception;

    ProductDto findById(Long id) throws Exception;

    void addProduct(String name, String description, int price, byte[] image, Long categoryId, Long authorId) throws Exception;

    void editProduct(Long id, String name, String description, int price, byte[] image, Long categoryId, Long authorId) throws Exception;

    void deleteProduct(Long id);

    void addDocument(Long id, MultipartFile multipartFile);
}
