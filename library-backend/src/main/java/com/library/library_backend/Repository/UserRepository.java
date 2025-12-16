package com.library.library_backend.Repository;

import com.library.library_backend.Entity.SystemUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<SystemUser, Long> {

    boolean existsByEmail(String email);
}
