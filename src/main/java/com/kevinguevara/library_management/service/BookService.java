package com.kevinguevara.library_management.service;

import com.kevinguevara.library_management.model.Book;

import java.util.List;

public interface BookService {

    public void createBook();

    public void deleteBook();

    public Book searchBookByTitle(String title);

    public Book searchBookByAuthor(String author);

    public Book searchBookByIsbn(String isbn);

    public List<Book> getAllBooks();
}