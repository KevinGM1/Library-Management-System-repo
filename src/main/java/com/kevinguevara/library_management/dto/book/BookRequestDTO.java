package com.kevinguevara.library_management.dto.book;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BookRequestDTO {
    @NotBlank(message = "Title cannot be empty")
    private String title;
    @NotBlank(message = "Author cannot be empty")
    private String author;
    @NotBlank(message = "must have isbn")
    private String isbn;
    private int totalCopies;
}
