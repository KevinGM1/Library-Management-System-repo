package com.kevinguevara.library_management.controller;

import com.kevinguevara.library_management.dto.book.BookRequestDTO;
import com.kevinguevara.library_management.dto.book.BookResponseDTO;
import com.kevinguevara.library_management.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    /* Add a book */
    @PostMapping
    public ResponseEntity<BookResponseDTO> addBook(@RequestBody BookRequestDTO request){
        BookResponseDTO response = bookService.addBook(request);
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/{bookId}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long bookId){
        bookService.deleteBook(bookId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search/title")
    public ResponseEntity<List<BookResponseDTO>> searchByTitle(@RequestParam String title){
        return ResponseEntity.ok(bookService.searchBookByTitle(title));
    }

    @GetMapping("/search/author")
    public ResponseEntity<List<BookResponseDTO>> searchByAuthor(@RequestParam String author){
        return ResponseEntity.ok(bookService.searchBookByAuthor(author));
    }

    @GetMapping("/search/isbn")
    public ResponseEntity<BookResponseDTO> searchByIsbn(@RequestParam String isbn){
        BookResponseDTO response = bookService.searchBookByIsbn(isbn);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<BookResponseDTO>> getAllBooks(){
        return ResponseEntity.ok(bookService.getAllBooks());
    }
    
}
