package com.library.library_backend.Controller;


import com.library.library_backend.dto.Request.AuthorRequestDTO;
import com.library.library_backend.Service.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@AllArgsConstructor
@RequestMapping("/authors")

public class AuthorController {
    private final AuthorService authorService;

    @Operation(summary = "create new author", description = "api to create a new author")
    @PostMapping("/create")
    public ResponseEntity<?> createAuthor(@RequestBody AuthorRequestDTO dto) {
        return new ResponseEntity<>(authorService.createAuthor(dto), HttpStatus.CREATED);
    }


    @Operation(summary = "update author by id", description = "api to update author ")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateAuthorById(@PathVariable Long id, @RequestBody AuthorRequestDTO dto) {
        return new ResponseEntity<>(authorService.updateAuthorById(id, dto), HttpStatus.OK);
    }


    @Operation(summary = "get author by id", description = "api to get author by id ")
    @GetMapping("/show/{id}")
    public ResponseEntity<?> getAuthorById(@PathVariable Long id) {
        return new ResponseEntity<>(authorService.getAuthorById(id), HttpStatus.OK);
    }


    @Operation(summary = "show all authors", description = "api to show all authors ")
    @GetMapping("/list")
    public ResponseEntity<?> getAllAuthor() {
        return new ResponseEntity<>(authorService.getAllAuthors(), HttpStatus.OK);
    }


    @Operation(summary = "delete author by id", description = "api to delete author by id")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAuthorById(@PathVariable Long id) {
        authorService.deleteAuthorById(id);
        return ResponseEntity.noContent().build();
    }

}
