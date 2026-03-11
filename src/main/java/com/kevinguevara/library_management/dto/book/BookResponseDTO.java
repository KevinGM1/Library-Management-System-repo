package com.kevinguevara.library_management.dto.book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookResponseDTO {
    private Long bookId;
    private String title;
    private String author;
    private String isbn;
    private int totalCopies;
    private int availableCopies;
}
