package com.kevinguevara.service;

import com.kevinguevara.library_management.dto.book.BookRequestDTO;
import com.kevinguevara.library_management.dto.book.BookResponseDTO;
import com.kevinguevara.library_management.mapper.BookMapper;
import com.kevinguevara.library_management.model.Book;
import com.kevinguevara.library_management.repository.BookRepository;
import com.kevinguevara.library_management.service.impl.BookServiceImpl;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;
    @Mock
    private BookMapper bookMapper;
    @InjectMocks
    private BookServiceImpl bookServiceImpl;
    private BookRequestDTO requestDTO;
    private Book book;
    private BookResponseDTO responseDTO;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
        
        requestDTO = new BookRequestDTO();
        requestDTO.setTitle("Harry Potter");
        requestDTO.setAuthor("J.K. Rowling");
        requestDTO.setIsbn("12345");
        requestDTO.setTotalCopies(5);
        
        book = new Book();
        book.setBookId(1L);
        book.setTitle("Harry Potter");
        book.setAuthor("J.K. Rowling");
        book.setIsbn("12345");
        book.setTotalCopies(5);
        book.setAvailableCopies(5);

        responseDTO = new BookResponseDTO();
        responseDTO.setBookId(1L);
        responseDTO.setTitle("Harry Potter");
        responseDTO.setAuthor("J.K. Rowling");
        responseDTO.setIsbn("12345");
        responseDTO.setTotalCopies(5);
        responseDTO.setAvailableCopies(5);
    }

    //testing addBook()
    @Test
    void testAddBook(){
        //no duplicate isbn
        when(bookRepository.findByIsbn(requestDTO.getIsbn())).thenReturn(Optional.empty());
        when(bookMapper.toEntity(requestDTO)).thenReturn(book); //dto to entity
        when(bookRepository.save(book)).thenReturn(book);   //database save
        when(bookMapper.toResponseDTO(book)).thenReturn(responseDTO); //entity to dto
        BookResponseDTO result = bookServiceImpl.addBook(requestDTO);

        assertNotNull(result);
        assertEquals("Harry Potter", result.getTitle());

        verify(bookRepository, times(1)).save(book);
    }

    //testing addBook() but isbn already exists
    @Test
    void testAddBook_DuplicateISBN(){
        //simulate an existing book
        when(bookRepository.findByIsbn(requestDTO.getIsbn())).thenReturn(Optional.of(book));
        //verify that service layers throws an exception
        assertThrows(IllegalArgumentException.class, () -> {bookServiceImpl.addBook(requestDTO);});
        //verify that database save never happens
        verify(bookRepository, never()).save(any());
    }

    @Test
    void testSearchByTitle(){
        List<Book> books = List.of(book);
        when(bookRepository.findByTitleContainingIgnoreCase("harry")).thenReturn(books);
        when(bookMapper.toResponseDTO(book)).thenReturn(responseDTO);
        List<BookResponseDTO> result = bookServiceImpl.searchBookByTitle("harry");

        assertEquals(1, result.size());
        assertEquals("Harry Potter", result.get(0).getTitle());

        verify(bookRepository).findByTitleContainingIgnoreCase("harry");
    }

    @Test
    void testSearchByAuthor(){
        List<Book> books = List.of(book);
        when(bookRepository.findByAuthorContainingIgnoreCase("rowling")).thenReturn(books);
        when(bookMapper.toResponseDTO(book)).thenReturn(responseDTO);
        List<BookResponseDTO> result = bookServiceImpl.searchBookByAuthor("rowling");

        assertEquals(1, result.size());
        assertEquals("J.K. Rowling", result.get(0).getAuthor());

        verify(bookRepository).findByAuthorContainingIgnoreCase("rowling");
    }

    @Test
    void testSearchByIsbn(){
        when(bookRepository.findByIsbn("12345")).thenReturn(Optional.of(book));
        when(bookMapper.toResponseDTO(book)).thenReturn(responseDTO);

        BookResponseDTO result = bookServiceImpl.searchBookByIsbn("12345");

        assertNotNull(result);
        assertEquals("Harry Potter", result.getTitle());

        verify(bookRepository).findByIsbn("12345");
    }
    
    @Test
    void testGetAllBooks(){
        List<Book> books = List.of(book);
        when(bookRepository.findAll()).thenReturn(books);
        when(bookMapper.toResponseDTO(book)).thenReturn(responseDTO);
        List<BookResponseDTO> result = bookServiceImpl.getAllBooks();
        
        assertEquals(1, result.size());
        assertEquals("Harry Potter", result.get(0).getTitle());

        verify(bookRepository).findAll();
    }
}
