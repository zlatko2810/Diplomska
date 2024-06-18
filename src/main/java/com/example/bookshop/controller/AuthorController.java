package com.example.bookshop.controller;

import com.example.bookshop.entity.dto.AuthorDto;
import com.example.bookshop.entity.product.AuthorEntity;
import com.example.bookshop.service.author.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/api/authors")
public class AuthorController {

    private final AuthorService authorService;

    /**
     * Method to get all authors
     * @return list of authors
     */
    @GetMapping("/")
    public ResponseEntity<List<AuthorDto>> findAll() {
        return ResponseEntity.ok(authorService.findAll());
    }

    /**
     * Method to get author specified by ID
     * @return author
     */
    @GetMapping("/{id}")
    public ResponseEntity<AuthorDto> findById(@PathVariable(name = "id") Long id) throws Exception {
        return ResponseEntity.ok(authorService.findById(id));
    }

    /**
     * Method to add author
     * @return string
     */
    @PostMapping("/add")
    public ResponseEntity<String> addAuthor(@RequestParam(name = "firstName") String firstName, @RequestParam(name = "lastName") String lastName) throws Exception {
        authorService.addAuthor(firstName, lastName);
        return ResponseEntity.status(HttpStatus.CREATED).body("Author successfully created");
    }

    /**
     * Method to edit author
     * @return string
     */

    @PutMapping("/{id}")
    public ResponseEntity<String> editAuthor(@PathVariable(name = "id") Long id, @RequestParam(name = "firstName") String firstName, @RequestParam(name = "lastName") String lastName) throws Exception {
        authorService.editAuthor(id, firstName, lastName);
        return ResponseEntity.status(HttpStatus.CREATED).body("Author successfully updated");
    }

    /**
     * Method to delete author
     * @return string
     */

    @PostMapping("/{id}")
    public ResponseEntity<String> deleteAuthor(@PathVariable(name = "id") Long id) {
        authorService.deleteAuthor(id);
        return ResponseEntity.status(HttpStatus.CREATED).body("Author successfully deleted");
    }
}
