package com.example.bookshop.service.category;

import com.example.bookshop.entity.dto.CategoryDto;

import java.util.List;

public interface CategoryService {

    List<CategoryDto> findAll();

    CategoryDto findById(Long id) throws Exception;

    void addCategory(String name, Long parentId) throws Exception;

    void editCategory(Long id, String name, Long parentId) throws Exception;

    void deleteCategory(Long id);
}
