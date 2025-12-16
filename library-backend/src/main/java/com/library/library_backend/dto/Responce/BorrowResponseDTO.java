package com.library.library_backend.dto.Responce;

import com.library.library_backend.Entity.Borrow;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class BorrowResponseDTO {

    private Long id;

    private String userName;

    private String bookTitle;

    private LocalDate borrowDate;

    private LocalDate returnDate;

    private Borrow.borrowStatus status;
}
