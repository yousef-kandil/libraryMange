package com.library.library_backend.dto.Responce;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AuthorResponseDTO {
    private Long id;

    private String name;

    private String bio;
}
