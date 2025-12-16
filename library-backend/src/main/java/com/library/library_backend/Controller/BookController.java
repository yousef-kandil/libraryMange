package com.library.library_backend.Controller;


import com.library.library_backend.Service.BookService;
import com.library.library_backend.dto.Request.BookRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;


    @Operation(summary = "create new book", description = "api to create a new book")
    @PostMapping("/create")
    public ResponseEntity<?> createBook(@RequestBody BookRequestDTO bookRequestDTO){
        return new ResponseEntity<>(bookService.createBook(bookRequestDTO) , HttpStatus.CREATED);
    }


    @Operation(summary = "update book by id", description = "api to update book ")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateBookById(@PathVariable Long id , @RequestBody BookRequestDTO bookRequestDTO){
        return new ResponseEntity<>(bookService.updateBookById(id , bookRequestDTO) , HttpStatus.OK);
    }



    @Operation(summary = "get book by id", description = "api to get book by id ")
    @GetMapping("/show/{id}")
    public ResponseEntity<?> GetBookById(@PathVariable Long id){
        return new ResponseEntity<>(bookService.GetBookById(id) , HttpStatus.OK);
    }


    @Operation(summary = "show all books", description = "api to show all books ")
    @GetMapping("/list")
    public ResponseEntity<?> GetAllBooks(){
        return new ResponseEntity<>(bookService.GetAllBooks() , HttpStatus.OK);
    }


    @Operation(summary = "delete book by id", description = "api to delete book by id")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteBookById(@PathVariable Long id){
        bookService.deleteBookById(id);
        return ResponseEntity.noContent().build();
    }
}
