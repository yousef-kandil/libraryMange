package com.library.library_backend.Service;


import com.library.library_backend.Entity.Borrow;
import com.library.library_backend.Repository.BorrowRepository;
import com.library.library_backend.Utils.ObjectMapperUtils;
import com.library.library_backend.dto.Request.BookRequestDTO;
import com.library.library_backend.dto.Responce.BookResponseDTO;
import com.library.library_backend.Entity.Author;
import com.library.library_backend.Entity.Book;
import com.library.library_backend.Entity.Category;
import com.library.library_backend.Repository.AuthorRepository;
import com.library.library_backend.Repository.BookRepository;
import com.library.library_backend.Repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;
    private final AuthorRepository authorRepository;
    private final BorrowRepository borrowRepository;


    public BookResponseDTO createBook(BookRequestDTO dto) {
        Author author = authorRepository.findById(dto.getAuthorId())
                .orElseThrow(() -> new RuntimeException("Author Not Found!"));

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category Not Found!"));


        Book book = ObjectMapperUtils.map(dto, Book.class);
        book.setCategory(category);
        book.setAuthor(author);

        Book saved = bookRepository.save(book);

        BookResponseDTO response = ObjectMapperUtils.map(saved, BookResponseDTO.class);
        response.setCategoryTitle(saved.getCategory().getTitle());
        response.setAuthorName(saved.getAuthor().getName());

        return response;
//----------------------------------------------------------------------------------------
//        Book book = new Book();
//        book.setTitle(dto.getTitle());
//        book.setDescription(dto.getDescription());
//        book.setCategory(category);
//        book.setAuthor(author);
//        book.setStock(dto.getStock());
//        book.setTotalCopiesForBook(dto.getTotalCopiesForBook());
//        book.setCopiesAvailableForBorrow(dto.getCopiesAvailableForBorrow());
//        book.setPrice(dto.getPrice());
//
//        Book saved = bookRepository.save(book);
//
//        BookResponseDTO response = new BookResponseDTO();
//        response.setId(saved.getId());
//        response.setTitle(saved.getTitle());
//        response.setDescription(saved.getDescription());
//        response.setCategoryTitle(saved.getCategory().getTitle());
//        response.setAuthorName(saved.getAuthor().getName());
//        response.setStock(saved.getStock());
//        response.setTotalCopiesForBook(saved.getTotalCopiesForBook());
//        response.setCopiesAvailableForBorrow(saved.getCopiesAvailableForBorrow());
//        response.setPrice(saved.getPrice());
//
//        return response;
    }


    public BookResponseDTO updateBookById(Long id, BookRequestDTO dto) {

        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book Not Found!"));

        ObjectMapperUtils.map(dto, book);

        if (!book.getAuthor().getId().equals(dto.getAuthorId())) {
            Author author = authorRepository.findById(dto.getAuthorId())
                    .orElseThrow(() -> new RuntimeException("Author Not Found!"));
            book.setAuthor(author);
        }

        if (!book.getCategory().getId().equals(dto.getCategoryId())) {
            Category category = categoryRepository.findById(dto.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category Not Found!"));
            book.setCategory(category);
        }

        Book saved = bookRepository.save(book);

        BookResponseDTO response = ObjectMapperUtils.map(saved, BookResponseDTO.class);
        response.setAuthorName(saved.getAuthor().getName());
        response.setCategoryTitle(saved.getCategory().getTitle());

        return response;
    }


    public BookResponseDTO GetBookById(Long id) {

        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book Not Found!"));

        BookResponseDTO response = ObjectMapperUtils.map(book, BookResponseDTO.class);
        response.setAuthorName(book.getAuthor().getName());
        response.setCategoryTitle(book.getCategory().getTitle());

        return response;
    }


    public List<BookResponseDTO> GetAllBooks() {
        return bookRepository.findAll().stream().map(book -> {
            BookResponseDTO dto = ObjectMapperUtils.map(book, BookResponseDTO.class);
            dto.setAuthorName(book.getAuthor().getName());
            dto.setCategoryTitle(book.getCategory().getTitle());

            return dto;
        }).toList();
    }


    public void deleteBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book Not Found!"));

        boolean hasBorrow = borrowRepository.existsByBookAndStatus(book , Borrow.borrowStatus.BORROWED);
        if(hasBorrow){
            throw new RuntimeException("The book cannot be deleted because it is borrowed");
        }

        bookRepository.deleteById(id);
    }
}
