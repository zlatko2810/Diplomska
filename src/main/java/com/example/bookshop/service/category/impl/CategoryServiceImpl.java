package com.example.bookshop.service.category.impl;


import com.example.bookshop.entity.dto.CategoryDto;
import com.example.bookshop.entity.product.CategoryEntity;
import com.example.bookshop.repository.CategoryRepository;
import com.example.bookshop.service.category.CategoryService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;


    public List<CategoryDto> findAll() {
        List<CategoryEntity> categories = categoryRepository.findAll();
        List<CategoryDto> categoriesDto = new ArrayList<>();

        for (CategoryEntity c : categories) {
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setId(c.getId());
            categoryDto.setName(c.getName());
            if (c.getCategory() != null) {
                CategoryDto parent = new CategoryDto();
                parent.setName(c.getCategory().getName());
                parent.setParent(null);
                categoryDto.setParent(parent);
            }
            categoriesDto.add(categoryDto);
        }
        return categoriesDto;
    }

    public CategoryDto findById(Long id) throws Exception {
        CategoryEntity category = categoryRepository.findById(id).orElseThrow(Exception::new);
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        if (category.getCategory() != null) {
            CategoryDto parent = new CategoryDto();
            parent.setName(category.getCategory().getName());
            parent.setParent(null);
            categoryDto.setParent(parent);
        }
        return categoryDto;
    }


    public void addCategory(String name, Long parent) throws Exception {
        if (parent == null) {
            categoryRepository.save(new CategoryEntity(name));
        } else {
            categoryRepository.save(new CategoryEntity(name, categoryRepository.findById(parent).orElseThrow(Exception::new)));
        }
    }


    @Transactional
    public void editCategory(Long id, String name, Long parent) throws Exception {
        CategoryEntity category = categoryRepository.findById(id).orElseThrow(Exception::new);
        category.setName(name);
        if (parent == null) {
            category.setCategory(null);
        } else {
            category.setCategory(categoryRepository.findById(parent).orElseThrow(Exception::new));
        }
        categoryRepository.save(category);

    }


    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}