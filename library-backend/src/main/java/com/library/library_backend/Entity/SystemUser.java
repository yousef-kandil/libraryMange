package com.library.library_backend.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "USERS")
public class SystemUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "FIRST_NAME",nullable = false)
    private String firstName;
    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;
    @Column(name = "EMAIL" ,nullable = false, unique = true)
    private String email;
    @Column(name = "PASSWORD",nullable = false)
    private String password;
    @Column(name = "PHONE")
    private String phone;
    @JsonFormat(shape = JsonFormat.Shape.STRING , pattern = "yyyy-MM-dd")
    @Column(name = "BIRTHDATE")
    private LocalDate birthdate;
    @JsonFormat(shape = JsonFormat.Shape.STRING , pattern = "yyyy-MM-dd")
    @Column(name = "JOIN_DATE")
    private LocalDate joinDate = LocalDate.now();
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @ToString.Exclude
    @Column(name = "BORROWS")
    private List<Borrow> borrows  = new ArrayList<>();
}
