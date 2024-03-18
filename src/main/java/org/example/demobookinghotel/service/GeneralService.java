package org.example.demobookinghotel.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface GeneralService<T>{
    void save(T t);
    void delete(Long id);
}
