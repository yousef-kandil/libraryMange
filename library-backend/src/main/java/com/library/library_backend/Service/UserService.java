package com.library.library_backend.Service;

import com.library.library_backend.Entity.Borrow;
import com.library.library_backend.Repository.BorrowRepository;
import com.library.library_backend.dto.Request.UserRequestDTO;
import com.library.library_backend.dto.Responce.UserResponseDTO;
import com.library.library_backend.Entity.SystemUser;
import com.library.library_backend.Repository.UserRepository;
import com.library.library_backend.Utils.ObjectMapperUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BorrowRepository borrowRepository;


    public UserResponseDTO createUser(UserRequestDTO dto) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("User Already Found!");
        }
        SystemUser systemUser = ObjectMapperUtils.map(dto, SystemUser.class);

        SystemUser saveUser = userRepository.save(systemUser);

        return ObjectMapperUtils.map(saveUser, UserResponseDTO.class);


        //-------------------------------------------------------------------------
//        SystemUser systemUser = new SystemUser();
//        systemUser.setFirstName(dto.getFirstName());
//        systemUser.setLastName(dto.getLastName());
//        systemUser.setEmail(dto.getEmail());
//        systemUser.setPhone(dto.getPhone());
//        systemUser.setPassword(dto.getPassword());
//        systemUser.setBirthdate(dto.getBirthdate());
//       systemUser.setBorrows(new ArrayList<>());


        //-------------------------------------------------------------------------

//        response.setId(saveUser.getId());
//        response.setFirstName(saveUser.getFirstName());
//        response.setLastName(saveUser.getLastName());
//        response.setEmail(saveUser.getEmail());
//        response.setPhone(saveUser.getPhone());
//        response.setBirthdate(saveUser.getBirthdate());
//        response.setJoinDate(saveUser.getJoinDate());
//        response.setBorrowCount(saveUser.getBorrows().size());

    }


    public UserResponseDTO updateUser(Long id, UserRequestDTO dto) {


        SystemUser existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User Not Found!"));

        if (!existingUser.getEmail().equals(dto.getEmail())) {
            if (userRepository.existsByEmail(dto.getEmail())) {
                throw new RuntimeException("Email Already Exists");
            }
        }

        ObjectMapperUtils.map(dto, existingUser);
        SystemUser saveUser = userRepository.save(existingUser);

        return ObjectMapperUtils.map(saveUser, UserResponseDTO.class);

        //-------------------------------------------------------------------------

//        existingUser.setFirstName(dto.getFirstName());
//        existingUser.setLastName(dto.getLastName());
//        existingUser.setEmail(dto.getEmail());
//        existingUser.setPhone(dto.getPhone());
//        existingUser.setPassword(dto.getPassword());
//        existingUser.setBirthdate(dto.getBirthdate());

//        SystemUser saveUser = userRepository.save(systemUser);


        //-------------------------------------------------------------------------

//        UserResponseDTO response = new UserResponseDTO();
//        response.setId(saveUser.getId());
//        response.setFirstName(saveUser.getFirstName());
//        response.setLastName(saveUser.getLastName());
//        response.setEmail(saveUser.getEmail());
//        response.setPhone(saveUser.getPhone());
//        response.setBirthdate(saveUser.getBirthdate());
//        response.setJoinDate(saveUser.getJoinDate());
//        response.setBorrowCount(saveUser.getBorrows().size());

    }


    public UserResponseDTO getUserById(Long id) {
        SystemUser user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User Not Found!"));

        UserResponseDTO response = ObjectMapperUtils.map(user, UserResponseDTO.class);
        response.setBorrowCount(user.getBorrows().size());
        return response;

        //-------------------------------------------------------------------------

//        UserResponseDTO response = new UserResponseDTO();
//        response.setId(user.getId());
//        response.setFirstName(user.getFirstName());
//        response.setLastName(user.getLastName());
//        response.setEmail(user.getEmail());
//        response.setPhone(user.getPhone());
//        response.setBirthdate(user.getBirthdate());
//        response.setJoinDate(user.getJoinDate());
//        response.setBorrowCount(user.getBorrows().size());


    }


    public List<UserResponseDTO> getAllUsers() {

        return userRepository.findAll().stream().map(user -> {

            UserResponseDTO dto = ObjectMapperUtils.map(user, UserResponseDTO.class);

            dto.setBorrowCount(user.getBorrows().size());

            return dto;
        }).toList();


        //-------------------------------------------------------------------------

//        return userRepository.findAll().stream().map(user -> new UserResponseDTO(
//                user.getId(),
//                user.getFirstName(),
//                user.getLastName(),
//                user.getEmail(),
//                user.getPhone(),
//                user.getBirthdate(),
//                user.getJoinDate(),
//                user.getBorrows().size()
//        )).toList();
    }


    public void deleteUserById(Long id) {
        SystemUser user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User Not Found!"));

        boolean hasActiveBorrow = borrowRepository.existsByUserAndStatus(user, Borrow.borrowStatus.BORROWED);
        if (hasActiveBorrow) {
            throw new RuntimeException("The user cannot be deleted because he has borrowed books");
        }

        userRepository.deleteById(id);
    }

}
