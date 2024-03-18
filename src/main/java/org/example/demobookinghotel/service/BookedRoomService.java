package org.example.demobookinghotel.service;

import org.example.demobookinghotel.model.BookedRoom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface BookedRoomService extends GeneralService<BookedRoom> {
    Page<BookedRoom> findAll(Pageable pageable);
    Optional<BookedRoom> findById(Long id);
}
