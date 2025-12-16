package com.library.library_backend.Controller;


import com.library.library_backend.Service.BorrowService;
import com.library.library_backend.dto.Request.BorrowRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/borrows")
public class BorrowController {

    private final BorrowService borrowService;

    @Operation(summary = "create new borrow", description = "api to create a new borrow")
    @PostMapping("/create")
    public ResponseEntity<?> createBorrow(@RequestBody BorrowRequestDTO borrowRequestDTO){
        return new ResponseEntity<>(borrowService.createBorrow(borrowRequestDTO) , HttpStatus.CREATED);
    }


    @Operation(summary = "update borrow status by id", description = "api to update borrow status by id")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> borrowStatusUpdateToReturnedById(@PathVariable  Long id){
        return new ResponseEntity<>(borrowService.borrowStatusUpdateToReturnedById(id) , HttpStatus.OK);
    }


    @Operation(summary = "show borrow By id", description = "api to show borrow by id")
    @GetMapping("/show/{id}")
    public ResponseEntity<?> getBorrowById(@PathVariable Long id){
        return new ResponseEntity<>(borrowService.getBorrowById(id) , HttpStatus.OK);
    }


    @Operation(summary = "show all borrows", description = "api to show all borrows")
    @GetMapping("/list")
    public ResponseEntity<?> getAllBorrows(){
        return new ResponseEntity<>(borrowService.getAllBorrows() , HttpStatus.OK);
    }


    @Operation(summary = "delete borrow by id", description = "api to delete borrow by id")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteBorrowById(@PathVariable Long id){
        borrowService.deleteBorrowById(id);
        return ResponseEntity.noContent().build();
    }

}
