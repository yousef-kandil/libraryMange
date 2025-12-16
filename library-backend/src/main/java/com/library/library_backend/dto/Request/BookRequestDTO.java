package com.library.library_backend.dto.Request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookRequestDTO {

    private String title;

    private String description;

    private Double price;

    private Integer stock;

    private Integer copiesAvailableForBorrow;

    private Long categoryId;

    private Long authorId;

    private LocalDate publishDate;
}
