package com.kevinguevara.library_management.mapper;

import com.kevinguevara.library_management.dto.book.BookRequestDTO;
import com.kevinguevara.library_management.dto.book.BookResponseDTO;
import com.kevinguevara.library_management.model.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {
    
    public Book toEntity(BookRequestDTO requestDTO){
        if(requestDTO == null)
            return null;
        return Book.builder()
            .title(requestDTO.getTitle())
            .author(requestDTO.getAuthor())
            .isbn(requestDTO.getIsbn())
            .totalCopies(requestDTO.getTotalCopies())
            .availableCopies(requestDTO.getTotalCopies())
            .build();
    }

    public BookResponseDTO toResponseDTO(Book book){
        if(book == null)
            return null;
        return BookResponseDTO.builder()
            .bookId(book.getBookId())
            .title(book.getTitle())
            .author(book.getAuthor())
            .isbn(book.getIsbn())
            .totalCopies(book.getTotalCopies())
            .availableCopies(book.getAvailableCopies())
            .build();
    }
}
