package com.library.library_backend.Service;


import com.library.library_backend.Entity.Book;
import com.library.library_backend.Entity.Borrow;
import com.library.library_backend.Entity.SystemUser;
import com.library.library_backend.Repository.BookRepository;
import com.library.library_backend.Repository.BorrowRepository;
import com.library.library_backend.Repository.UserRepository;
import com.library.library_backend.Utils.ObjectMapperUtils;
import com.library.library_backend.dto.Request.BorrowRequestDTO;
import com.library.library_backend.dto.Responce.BorrowResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class BorrowService {
    private final BorrowRepository borrowRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public BorrowResponseDTO createBorrow(BorrowRequestDTO borrowRequestDTO) {

        SystemUser systemUser = userRepository.findById(borrowRequestDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User Not Found!"));

        Book book = bookRepository.findById(borrowRequestDTO.getBookID())
                .orElseThrow(() -> new RuntimeException("Book Not Found!"));

        if (book.getCopiesAvailableForBorrow() <= 0) {
            throw new RuntimeException("Sorry Book is not available for borrowing");
        }

        Borrow borrow = ObjectMapperUtils.map(borrowRequestDTO, Borrow.class);
        borrow.setUser(systemUser);
        borrow.setBook(book);
        borrow.setBorrowDate(LocalDate.now());
        borrow.setReturnDate(LocalDate.now().plusDays(borrowRequestDTO.getBorrowDays()));
        borrow.setStatus(Borrow.borrowStatus.BORROWED);

        book.setCopiesAvailableForBorrow(book.getCopiesAvailableForBorrow() - 1);

        Borrow savedBorrow = borrowRepository.save(borrow);
        bookRepository.save(book);

        BorrowResponseDTO responseDTO = ObjectMapperUtils.map(savedBorrow, BorrowResponseDTO.class);
        responseDTO.setUserName(systemUser.getFirstName() + " " + systemUser.getLastName());
        responseDTO.setBookTitle(book.getTitle());

        return responseDTO;
    }


    public BorrowResponseDTO borrowStatusUpdateToReturnedById(Long id) {

        Borrow borrow = borrowRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Borrow Not Found!"));

        borrow.setStatus(Borrow.borrowStatus.RETURNED);
        borrow.setReturnDate(LocalDate.now());
        borrowRepository.save(borrow);

        Book book = borrow.getBook();
        book.setCopiesAvailableForBorrow(book.getCopiesAvailableForBorrow() + 1);
        bookRepository.save(book);

        BorrowResponseDTO responseDTO = ObjectMapperUtils.map(borrow, BorrowResponseDTO.class);
        responseDTO.setUserName(borrow.getUser().getFirstName() + " " + borrow.getUser().getLastName());
        responseDTO.setBookTitle(borrow.getBook().getTitle());

        return responseDTO;
    }


    public BorrowResponseDTO getBorrowById(Long id) {

        Borrow borrow = borrowRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Borrow Not Found!"));

        BorrowResponseDTO responseDTO = ObjectMapperUtils.map(borrow, BorrowResponseDTO.class);
        responseDTO.setUserName(borrow.getUser().getFirstName() + " " + borrow.getUser().getLastName());
        responseDTO.setBookTitle(borrow.getBook().getTitle());

        return responseDTO;
    }


    public List<BorrowResponseDTO> getAllBorrows() {

        return borrowRepository.findAll().stream().map(borrow -> {

                    BorrowResponseDTO responseDTO = ObjectMapperUtils.map(borrow, BorrowResponseDTO.class);
                    responseDTO.setUserName(borrow.getUser().getFirstName() + " " + borrow.getUser().getLastName());
                    responseDTO.setBookTitle(borrow.getBook().getTitle());

                    return responseDTO;
                }
        ).toList();

    }


    public void deleteBorrowById(Long id) {

        Borrow borrow = borrowRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Borrow Not Found!"));

        borrowRepository.deleteById(id);
    }
}
