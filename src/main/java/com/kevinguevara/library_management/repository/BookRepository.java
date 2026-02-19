package com.kevinguevara.library_management.repository;

import com.kevinguevara.library_management.model.Book;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long>{
    //@Query("FROM Book WHERE title = :title")
    //Book findByTitle(@Param("title") String title);
    List<Book> findByTitleContainingIgnoreCase(String title);

    //@Query("FROM Book WHERE author = :author")
    //Book findByAuthor(@Param("author") String author);
    List<Book> findByAuthorContainingIgnoreCase(String author);

    //@Query("FROM Book WHERE isbn = :isbn")
    //Optional<Book> findByIsbn(@Param("isbn") String isbn);
    Optional<Book> findByIsbn(String isbn);


    
}

    

