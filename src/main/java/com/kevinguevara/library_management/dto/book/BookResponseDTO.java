package com.kevinguevara.library_management.dto.book;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class BookResponseDTO {
    Long bookId;
    String title;
    String author;
    String isbn;
    int totalCopies;
    int availableCopies;
}
