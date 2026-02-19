package com.kevinguevara.library_management.service.impl;

import com.kevinguevara.library_management.model.Book;
import com.kevinguevara.library_management.repository.BookRepository;
import com.kevinguevara.library_management.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

//import java.util.Optional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService{
    private final BookRepository bookRepository;
    @Override
    public Book addBook(Book book){
       /*  Optional<Book> optionalBook = bookRepository.findByIsbn(book.getIsbn());
        if(optionalBook.isPresent()){
            throw new IllegalArgumentException("ISBN already exists");
        }
        return bookRepository.save(book);
        */
       if(bookRepository.findByIsbn(book.getIsbn()).isPresent()){
            throw new IllegalArgumentException("ISBN already exists");
       }
       return bookRepository.save(book);
    }
    @Override
    public void deleteBook(Long bookId){
        if(bookRepository.existsById(bookId)){
            throw new IllegalArgumentException("Book not found with id: " + bookId);
        }
        bookRepository.deleteById(bookId);
    }
    @Override
    public List<Book> searchBookByTitle(String title){
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }
    @Override
    public List<Book> searchBookByAuthor(String author){
        return bookRepository.findByAuthorContainingIgnoreCase(author);
    }
    @Override
    public Book searchBookByIsbn(String isbn){
        /*Optional<Book> optionalBook = bookRepository.findByIsbn(isbn);
        if(optionalBook.isPresent())
            return optionalBook.get();
        else
            return null;
        */
       return bookRepository.findByIsbn(isbn).orElseThrow(() -> new IllegalArgumentException("ISBN not found"));
    }
    @Override
    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }
}