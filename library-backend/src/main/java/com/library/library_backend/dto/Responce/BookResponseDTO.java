package com.library.library_backend.dto.Responce;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookResponseDTO {

    private Long id;

    private String title;

    private String description;

    private Double price;

    private Integer stock;

    private Integer totalCopiesForBook;

    private Integer copiesAvailableForBorrow;

    private String categoryTitle;

    private String authorName;

    private LocalDate publishDate;

}
