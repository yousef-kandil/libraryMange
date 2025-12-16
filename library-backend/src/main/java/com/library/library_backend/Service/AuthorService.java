package com.library.library_backend.Service;

import com.library.library_backend.Repository.BookRepository;
import com.library.library_backend.dto.Request.AuthorRequestDTO;
import com.library.library_backend.dto.Responce.AuthorResponseDTO;
import com.library.library_backend.Entity.Author;
import com.library.library_backend.Repository.AuthorRepository;
import com.library.library_backend.Utils.ObjectMapperUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public AuthorResponseDTO createAuthor(AuthorRequestDTO dto) {
        if (authorRepository.existsByName(dto.getName())) {
            throw new RuntimeException("Author Already Found!");
        }

        Author author = ObjectMapperUtils.map(dto, Author.class);
        Author saved = authorRepository.save(author);

        return ObjectMapperUtils.map(saved, AuthorResponseDTO.class);


//-------------------------------------------------------------------
//        Author author = new Author();
//        author.setName(dto.getName());
//        author.setBio(dto.getBio());
//        Author saved = authorRepository.save(author);
//
//        AuthorResponseDTO response = new AuthorResponseDTO();
//        response.setId(saved.getId());
//        response.setName(saved.getName());
//        response.setBio(saved.getBio());
//
//        return response;
    }


    public AuthorResponseDTO updateAuthorById(Long id, AuthorRequestDTO dto) {
        Author existingAuthor = authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author Not Found!"));

        if (!existingAuthor.getName().equals(dto.getName())) {
            if (authorRepository.existsByName(dto.getName())) {
                throw new RuntimeException("Author Already Exists");
            }
        }

        ObjectMapperUtils.map(dto, existingAuthor);
        Author saved = authorRepository.save(existingAuthor);
        return ObjectMapperUtils.map(saved, AuthorResponseDTO.class);

//--------------------------------------------------------------------------
//        existingAuthor.setName(dto.getName());
//        existingAuthor.setBio(dto.getBio());
//        Author saved = authorRepository.save(existingAuthor);
//
//        AuthorResponseDTO response = new AuthorResponseDTO();
//        response.setId(saved.getId());
//        response.setName(saved.getName());
//        response.setBio(saved.getBio());
//
//        return response;
    }


    public AuthorResponseDTO getAuthorById(Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author Not Found!"));

        return ObjectMapperUtils.map(author, AuthorResponseDTO.class);

//---------------------------------------------------------------------
//        AuthorResponseDTO response = new AuthorResponseDTO();
//        response.setId(author.getId());
//        response.setName(author.getName());
//        response.setBio(author.getBio());
//
//        return response;
    }


    public List<AuthorResponseDTO> getAllAuthors() {

        return authorRepository.findAll().stream().map(author -> {
            AuthorResponseDTO dto = ObjectMapperUtils.map(author, AuthorResponseDTO.class);
            return dto;
        }).toList();

//---------------------------------------------------------------------------------------------
//        return authorRepository.findAll().stream().map(author -> new AuthorResponseDTO(
//                author.getId(),
//                author.getName(),
//                author.getBio()
//        )).toList();
    }


    public void deleteAuthorById(Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author Not Found!"));

        boolean  hasBooks = bookRepository.existsByAuthor(author);
        if (hasBooks){
            throw new RuntimeException("This author cannot be deleted. It contains books");
        }

        authorRepository.deleteById(id);
    }

}
