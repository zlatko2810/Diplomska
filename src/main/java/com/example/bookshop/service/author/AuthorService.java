package com.example.bookshop.service.author;

import com.example.bookshop.entity.dto.AuthorDto;
import com.example.bookshop.entity.product.AuthorEntity;

import java.util.List;

public interface AuthorService {

    List<AuthorDto> findAll();

    AuthorDto findById(Long id) throws Exception;

    void addAuthor(String firstName, String lastName);

    void editAuthor(Long id, String firstName, String lastName) throws Exception;

    void deleteAuthor(Long id);
}
