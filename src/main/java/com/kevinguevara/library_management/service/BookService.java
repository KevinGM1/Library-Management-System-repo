package com.kevinguevara.library_management.service;

import com.kevinguevara.library_management.model.Book;

import java.util.List;

public interface BookService {

    public Book addBook(Book book);

    public void deleteBook(Long bookId);

    public List<Book> searchBookByTitle(String title);

    public List<Book> searchBookByAuthor(String author);

    public Book searchBookByIsbn(String isbn);

    public List<Book> getAllBooks();
}