package com.library.library_backend.Controller;


import com.library.library_backend.dto.Request.UserRequestDTO;
import com.library.library_backend.Service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @Operation(summary = "create new user", description = "api to create a new user")
    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody UserRequestDTO userRequestDTO) {
        return new ResponseEntity<>(userService.createUser(userRequestDTO), HttpStatus.CREATED);
    }



    @Operation(summary = "update user by id", description = "api to update user ")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserRequestDTO userRequestDTO) {
        return new ResponseEntity<>(userService.updateUser(id , userRequestDTO) , HttpStatus.OK);
    }



    @Operation(summary = "get user by id", description = "api to get user by id ")
    @GetMapping("/show/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getUserById(id) , HttpStatus.OK);
    }



    @Operation(summary = "show all user", description = "api to show all user ")
    @GetMapping("/list")
    public ResponseEntity<?> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers() , HttpStatus.OK);
    }


    @Operation(summary = "delete user by id", description = "api to delete user by id")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }
}
