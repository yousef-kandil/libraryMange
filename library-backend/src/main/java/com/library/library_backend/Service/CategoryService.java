package com.library.library_backend.Service;


import com.library.library_backend.Repository.BookRepository;
import com.library.library_backend.dto.Request.CategoryRequestDTO;
import com.library.library_backend.dto.Responce.CategoryResponseDTO;
import com.library.library_backend.Entity.Category;
import com.library.library_backend.Repository.CategoryRepository;
import com.library.library_backend.Utils.ObjectMapperUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final BookRepository bookRepository;

    public CategoryResponseDTO createCategory(CategoryRequestDTO dto) {
        if (categoryRepository.existsByTitle(dto.getTitle())) {
            throw new RuntimeException("Category title already exists!");
        }

        Category category = ObjectMapperUtils.map(dto, Category.class);

        Category saved = categoryRepository.save(category);

        return ObjectMapperUtils.map(saved, CategoryResponseDTO.class);
//-------------------------------------------------------------------------------------
//        Category category = new Category();
//        category.setTitle(dto.getTitle());
//        category.setDescription(dto.getDescription());
//        Category saved = categoryRepository.save(category);
//
//        CategoryResponseDTO response = new CategoryResponseDTO();
//        response.setId(saved.getId());
//        response.setTitle(saved.getTitle());
//        response.setDescription(saved.getDescription());
//        return response;
    }


    public CategoryResponseDTO updateCategoryById(Long id, CategoryRequestDTO dto) {
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category Not Found!"));
        if (!existingCategory.getTitle().equals(dto.getTitle())) {
            if (categoryRepository.existsByTitle(dto.getTitle())) {
                throw new RuntimeException("Category title already exists");
            }
        }


        ObjectMapperUtils.map(dto, existingCategory);
        Category saved = categoryRepository.save(existingCategory);
        return ObjectMapperUtils.map(saved, CategoryResponseDTO.class);
//---------------------------------------------------------------------------------
//        existingCategory.setTitle(dto.getTitle());
//        existingCategory.setDescription(dto.getDescription());
//
//        Category saved = categoryRepository.save(existingCategory);
//
//        CategoryResponseDTO response = new CategoryResponseDTO();
//        response.setId(saved.getId());
//        response.setTitle(saved.getTitle());
//        response.setDescription(saved.getDescription());
//        return response;
    }


    public CategoryResponseDTO getCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category Not Found!"));

        return ObjectMapperUtils.map(category, CategoryResponseDTO.class);


//--------------------------------------------------------------------------------------
//        CategoryResponseDTO response = new CategoryResponseDTO();
//        response.setId(category.getId());
//        response.setTitle(category.getTitle());
//        response.setDescription(category.getDescription());
//
//        return response;
    }


    public List<CategoryResponseDTO> getAllCategories() {

        return categoryRepository.findAll().stream().map(category -> {
            CategoryResponseDTO dto = ObjectMapperUtils.map(category, CategoryResponseDTO.class);
            return dto;
        }).toList();


//-----------------------------------------------------------------------------------------------------
//        return categoryRepository.findAll().stream().map(category -> new CategoryResponseDTO(
//
//                category.getId(),
//                category.getTitle(),
//                category.getDescription()
//        ))
//                    .toList();

    }


    public void deleteCategoryById(Long id) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category Not Found!"));

        boolean hasBooks = bookRepository.existsByCategory(category);
        if(hasBooks){
            throw new RuntimeException("This category cannot be deleted because it contains books");
        }

        categoryRepository.deleteById(id);
    }

}
