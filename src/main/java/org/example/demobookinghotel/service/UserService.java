package org.example.demobookinghotel.service;

import org.example.demobookinghotel.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserService extends GeneralService<User> {
    Page<User> findAll(Pageable pageable);
    Optional<User> findById(Long id);
}
