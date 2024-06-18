package com.example.bookshop.controller;


import com.example.bookshop.entity.dto.ProductDto;
import com.example.bookshop.service.product.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


/**
 * When getting all the products, you should get all the
 * categories and authors, so you can filter by them
 */

@RestController
@AllArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;


    /**
     * Method to get all products
     *
     * @return list of products
     */

    @GetMapping("/")
    public ResponseEntity<List<ProductDto>> findAll() throws Exception {
        return ResponseEntity.ok(productService.findAll());
    }

    /**
     * Method to get product specified by ID
     * This method is used to get the product we want to edit
     * We also need to get all the authors and all the categories
     *
     * @return product
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> findById(@PathVariable(name = "id") Long id) throws Exception {
        return ResponseEntity.ok(productService.findById(id));
    }

    /**
     * Method to add product
     *
     * @return string
     */

    @PostMapping("/add")
    public ResponseEntity<String> addProduct(@RequestParam(name = "name") String name,
                                             @RequestParam(name = "description") String description,
                                             @RequestParam(name = "price") int price,
                                             @RequestParam(name = "image") MultipartFile multipartFile,
                                             @RequestParam(name = "category") Long categoryId,
                                             @RequestParam(name = "author") Long authorId) throws Exception {
        productService.addProduct(name, description, price, multipartFile.getBytes(), categoryId, authorId);
        return ResponseEntity.status(HttpStatus.CREATED).body("Product successfully updated");
    }

    /**
     * Method to edit product
     *
     * @return string
     */


    @PutMapping("/{id}")
    public ResponseEntity<String> editProduct(@PathVariable(name = "id") Long id,
                                              @RequestParam(name = "name") String name,
                                              @RequestParam(name = "description") String description,
                                              @RequestParam(name = "price") int price,
                                              @RequestParam(name = "image", required = false) MultipartFile multipartFile,
                                              @RequestParam(name = "category") Long categoryId,
                                              @RequestParam(name = "author") Long authorId) throws Exception {
        productService.editProduct(id, name, description, price, multipartFile.getBytes(), categoryId, authorId);
        return ResponseEntity.status(HttpStatus.CREATED).body("Product successfully updated");
    }

    /**
     * Method to delete product
     *
     * @return string
     */


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable(name = "id") Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.status(HttpStatus.CREATED).body("Product successfully deleted");
    }

}