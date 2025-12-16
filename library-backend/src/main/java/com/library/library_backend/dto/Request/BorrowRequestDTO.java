package com.library.library_backend.dto.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class BorrowRequestDTO {

   private Long userId;

   private Long bookID;

   private Integer borrowDays;
}
