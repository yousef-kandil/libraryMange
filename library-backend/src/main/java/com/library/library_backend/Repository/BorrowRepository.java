package com.library.library_backend.Repository;

import com.library.library_backend.Entity.Book;
import com.library.library_backend.Entity.Borrow;
import com.library.library_backend.Entity.SystemUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowRepository extends JpaRepository<Borrow, Long> {

    boolean existsByBookAndStatus(Book book , Borrow.borrowStatus status);

    boolean existsByUserAndStatus(SystemUser user , Borrow.borrowStatus status);

}
