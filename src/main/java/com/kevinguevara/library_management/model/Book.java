package com.kevinguevara.library_management.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(
    name = "books"
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book{
    @Column (name = "bookId")
    @Id@GeneratedValue
    private Long bookId;

    @Column(name = "title", nullable = false)
    private String title;
    
    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "isbn", nullable = false, unique = true)
    private String isbn;

    @Column(name = "totalCopies", nullable = false)
    private int totalCopies;

    @Column(name = "availableCopies", nullable = false)
    private int availableCopies;

    @PrePersist
    protected void onCreate(){
        this.availableCopies = this.totalCopies;
    }

    public void decreaseAvailableCopies(){
        if(availableCopies <= 0){
            throw new IllegalStateException("No copies available");
        }
        availableCopies--;
    }
    public void increaseAvailableCopies(){
        if(availableCopies < totalCopies)
            availableCopies++;
    }

}