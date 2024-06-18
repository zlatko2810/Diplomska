package com.example.bookshop.service.author.impl;

import com.example.bookshop.entity.dto.AuthorDto;
import com.example.bookshop.entity.product.AuthorEntity;
import com.example.bookshop.repository.AuthorRepository;
import com.example.bookshop.service.author.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public List<AuthorDto> findAll() {
        List<AuthorEntity> authors = authorRepository.findAll();
        List<AuthorDto> authorsDto = new ArrayList<>();
        for (AuthorEntity a : authors) {
            authorsDto.add(new AuthorDto(a.getId(), a.getFirstName(), a.getLastName()));
        }
        return authorsDto;
    }

    public AuthorDto findById(Long id) throws Exception {
        AuthorEntity author = authorRepository.findById(id).orElseThrow(Exception::new);
        AuthorDto authorDto = new AuthorDto();
        authorDto.setId(author.getId());
        authorDto.setFirstName(author.getFirstName());
        authorDto.setLastName(author.getLastName());
        return authorDto;
    }

    public void addAuthor(String firstName, String lastName) {
        authorRepository.save(new AuthorEntity(firstName, lastName));
    }

    public void editAuthor(Long id, String firstName, String lastName) throws Exception {
        AuthorEntity author = authorRepository.findById(id).orElseThrow(Exception::new);
        author.setFirstName(firstName);
        author.setLastName(lastName);
        authorRepository.save(author);
    }

    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }
}
