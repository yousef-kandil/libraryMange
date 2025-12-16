package com.library.library_backend.Controller;


import com.library.library_backend.dto.Request.CategoryRequestDTO;
import com.library.library_backend.Service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
@AllArgsConstructor

public class CategoryController {
    private final CategoryService categoryService;

    @Operation(summary = "create new category", description = "api to create a new category")
    @PostMapping("/create")
    public ResponseEntity<?> createCategory(@RequestBody CategoryRequestDTO dto) {
        return new ResponseEntity<>(categoryService.createCategory(dto) , HttpStatus.CREATED);
    }


    @Operation(summary = "update category", description = "api to update category")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCategoryById(@PathVariable Long id , @RequestBody CategoryRequestDTO dto){
        return new ResponseEntity<>(categoryService.updateCategoryById(id , dto) , HttpStatus.OK);
    }


    @Operation(summary = "get category By id", description = "api to get category by id")
    @GetMapping("/show/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable Long id){
        return new ResponseEntity<>(categoryService.getCategoryById(id) , HttpStatus.OK);
    }


    @Operation(summary = "show all categories", description = "api to show all categories")
    @GetMapping("/list")
    public ResponseEntity<?> getAllCategories(){
        return new ResponseEntity<>(categoryService.getAllCategories() , HttpStatus.OK);
    }


    @Operation(summary = "delete category by id", description = "api to delete category by id")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCategoryById(@PathVariable Long id){
        categoryService.deleteCategoryById(id);
        return ResponseEntity.noContent().build();
    }


}
