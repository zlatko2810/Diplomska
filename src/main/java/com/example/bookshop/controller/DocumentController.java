package com.example.bookshop.controller;

import com.example.bookshop.service.product.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;

@Controller
@RequestMapping("/api/document")
@AllArgsConstructor
public class DocumentController {

    private final ProductService productService;
    private static final String BOOKS_DIR = "books/";

    @GetMapping("/")
    public String returnPage() {
        return "form";
    }

    @PostMapping("/")
    public ResponseEntity<String> addPdf(@RequestParam(name = "productId") Long productId, @RequestParam(name = "document") MultipartFile multipartFile) {
        productService.addDocument(productId, multipartFile);
        return ResponseEntity.ok("Successfully added document");
    }


    @GetMapping("/download")
    public ResponseEntity<Resource> downloadDocument(@RequestParam("productId") Long productId) throws FileNotFoundException {
        File file = new File(BOOKS_DIR + productId + ".pdf");

        if (!file.exists()) {
            throw new FileNotFoundException("File not found with id " + productId);
        }

        FileSystemResource resource = new FileSystemResource(file);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
                .body(resource);
    }
}
