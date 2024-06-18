package com.example.bookshop.service.product.impl;

import com.example.bookshop.entity.dto.ProductDto;
import com.example.bookshop.entity.product.AuthorEntity;
import com.example.bookshop.entity.product.CategoryEntity;
import com.example.bookshop.entity.product.ProductEntity;
import com.example.bookshop.repository.AuthorRepository;
import com.example.bookshop.repository.CategoryRepository;
import com.example.bookshop.repository.ProductRepository;
import com.example.bookshop.service.author.AuthorService;
import com.example.bookshop.service.category.CategoryService;
import com.example.bookshop.service.product.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final AuthorRepository authorRepository;
    private final AuthorService authorService;
    private final CategoryService categoryService;

    private static final String BOOKS_DIR = "books";

    @Override
    public List<ProductDto> findAll() throws Exception {
        List<ProductEntity> products = productRepository.findAll();
        List<ProductDto> productDtos = new ArrayList<>();

        for (ProductEntity product : products) {
            ProductDto productDto = new ProductDto();
            productDto.setId(product.getId());
            productDto.setName(product.getName());
            productDto.setDescription(product.getDescription());
            productDto.setPrice(product.getPrice());
            productDto.setImage(product.getImage());
            productDto.setAuthor(authorService.findById(product.getAuthor().getId()));
            productDto.setCategory(categoryService.findById(product.getCategory().getId()));
            productDtos.add(productDto);
        }
        return productDtos;
    }

    @Override
    public ProductDto findById(Long id) throws Exception {
        ProductEntity product = productRepository.findById(id).orElseThrow(Exception::new);
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setImage(product.getImage());
        productDto.setAuthor(authorService.findById(product.getAuthor().getId()));
        productDto.setCategory(categoryService.findById(product.getCategory().getId()));
        return productDto;
    }

    @Override
    public void addProduct(String name, String description, int price, byte[] image, Long categoryId, Long authorId) throws Exception {
        CategoryEntity category = categoryRepository.findById(categoryId).orElseThrow(Exception::new);
        AuthorEntity author = authorRepository.findById(authorId).orElseThrow(Exception::new);
        productRepository.save(new ProductEntity(name, description, price, image, category, author));
    }

    @Override
    public void editProduct(Long id, String name, String description, int price, byte[] image, Long categoryId, Long authorId) throws Exception {
        ProductEntity product = productRepository.findById(id).orElseThrow(Exception::new);
        CategoryEntity category = categoryRepository.findById(categoryId).orElseThrow(Exception::new);
        AuthorEntity author = authorRepository.findById(authorId).orElseThrow(Exception::new);

        product.setName(name);
        product.setDescription(description);
        product.setCategory(category);
        product.setAuthor(author);
        product.setPrice(price);

        if (image.length != 0) {
            product.setImage(image);
        }
        productRepository.save(product);


    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public void addDocument(Long id, MultipartFile multipartFile) {
        // Check if the file is empty
        if (multipartFile.isEmpty()) {
            throw new RuntimeException("File is empty");
        }

        // Ensure the directory exists
        File booksDir = new File(BOOKS_DIR);
        if (!booksDir.exists()) {
            booksDir.mkdir();
        }

        // Create the path for the new file
        Path filePath = Paths.get(BOOKS_DIR, id + ".pdf");

        try {
            // Save the file to the specified path
            Files.write(filePath, multipartFile.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Failed to save file", e);
        }
    }
}
