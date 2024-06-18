package com.example.bookshop.controller;

import com.example.bookshop.entity.dto.CategoryDto;
import com.example.bookshop.entity.product.CategoryEntity;
import com.example.bookshop.service.category.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;


    /**
     * Method to get all categories
     * @return list of categories
     */
    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> findAll() {
        return ResponseEntity.ok(categoryService.findAll());
    }

    /**
     * Method to get category specified by ID
     * This method is used to edit category
     * You need to get the other categories too (call the first method)
     * @return category
     */
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> findById(@PathVariable(name = "id") Long id) throws Exception {
        return ResponseEntity.ok(categoryService.findById(id));
    }


    /**
     * Method to add category
     * @return string
     */

    @PostMapping("/add")
    public ResponseEntity<String> addCategory(@RequestParam(name = "name") String name, @RequestParam(name = "parent", required = false) Long parent) throws Exception {
        categoryService.addCategory(name, parent);
        return ResponseEntity.status(HttpStatus.CREATED).body("Category successfully created");
    }

    /**
     * Method to edit category
     * @return string
     */

    @PutMapping("/{id}")
    public ResponseEntity<String> editCategory(@PathVariable(name = "id") Long id, @RequestParam(name = "name") String name, @RequestParam(name = "parent", required = false) Long parent) throws Exception {
        categoryService.editCategory(id, name, parent);
        return ResponseEntity.status(HttpStatus.CREATED).body("Category successfully updated");
    }

    /**
     * Method to delete category
     * @return string
     */

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable(name = "id") Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.status(HttpStatus.CREATED).body("Category successfully deleted");
    }
}