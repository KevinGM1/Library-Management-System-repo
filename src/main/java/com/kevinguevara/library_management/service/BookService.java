package com.kevinguevara.library_management.service;

//import com.kevinguevara.library_management.model.Book;
import com.kevinguevara.library_management.dto.book.BookRequestDTO;
import com.kevinguevara.library_management.dto.book.BookResponseDTO;

import java.util.List;

public interface BookService {

    BookResponseDTO addBook(BookRequestDTO request);

    void deleteBook(Long bookId);

    List<BookResponseDTO> searchBookByTitle(String title);

    List<BookResponseDTO> searchBookByAuthor(String author);

    BookResponseDTO searchBookByIsbn(String isbn);

    List<BookResponseDTO> getAllBooks();
}