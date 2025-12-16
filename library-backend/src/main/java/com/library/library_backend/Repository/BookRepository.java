package com.library.library_backend.Repository;


import com.library.library_backend.Entity.Author;
import com.library.library_backend.Entity.Book;
import com.library.library_backend.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    boolean existsByAuthor(Author author);

    boolean existsByCategory(Category category);
}