package com.library.library_backend.dto.Responce;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoryResponseDTO {
    private Long id;

    private String title;

    private String description;

}
