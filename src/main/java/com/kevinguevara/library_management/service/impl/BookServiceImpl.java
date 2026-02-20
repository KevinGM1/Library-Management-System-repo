package com.kevinguevara.library_management.service.impl;

import com.kevinguevara.library_management.model.Book;
import com.kevinguevara.library_management.repository.BookRepository;
import com.kevinguevara.library_management.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.kevinguevara.library_management.dto.book.*;
import com.kevinguevara.library_management.mapper.BookMapper;

//import java.util.Optional;
import java.util.List;


@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService{
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    
    @Override
    public BookResponseDTO addBook(BookRequestDTO request){
       /*  Optional<Book> optionalBook = bookRepository.findByIsbn(book.getIsbn());
        if(optionalBook.isPresent()){
            throw new IllegalArgumentException("ISBN already exists");
        }
        return bookRepository.save(book);
        */
       if(bookRepository.findByIsbn(request.getIsbn()).isPresent()){
            throw new IllegalArgumentException("ISBN already exists");
       }
       Book book = bookMapper.toEntity(request);
       Book saved = bookRepository.save(book);
       return bookMapper.toResponseDTO(saved);
    }
    @Override
    public void deleteBook(Long bookId){
        if(!bookRepository.existsById(bookId)){
            throw new IllegalArgumentException("Book not found with id: " + bookId);
        }
        bookRepository.deleteById(bookId);
    }
    @Override
    public List<BookResponseDTO> searchBookByTitle(String title){
        //return bookRepository.findByTitleContainingIgnoreCase(title);
        List<Book> booksByTitle = bookRepository.findByTitleContainingIgnoreCase(title);
        return booksByTitle.stream().map(bookMapper::toResponseDTO).toList();
        
    }
    @Override
    public List<BookResponseDTO> searchBookByAuthor(String author){
        //return bookRepository.findByAuthorContainingIgnoreCase(author);
        List<Book> booksByauthor = bookRepository.findByAuthorContainingIgnoreCase(author);
        return booksByauthor.stream().map(bookMapper::toResponseDTO).toList();
    }
    @Override
    public BookResponseDTO searchBookByIsbn(String isbn){
        /*Optional<Book> optionalBook = bookRepository.findByIsbn(isbn);
        if(optionalBook.isPresent())
            return optionalBook.get();
        else
            return null;
        */
        Book book = bookRepository.findByIsbn(isbn).orElseThrow(() -> new IllegalArgumentException("ISBN not found"));
        return bookMapper.toResponseDTO(book);
    }
    @Override
    public List<BookResponseDTO> getAllBooks(){
        //return bookRepository.findAll();
        List<Book> allBooks = bookRepository.findAll();
        return allBooks.stream().map(bookMapper::toResponseDTO).toList();
    }
}