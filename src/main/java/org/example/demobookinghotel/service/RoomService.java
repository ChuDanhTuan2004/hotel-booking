package org.example.demobookinghotel.service;

import org.example.demobookinghotel.model.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface RoomService extends GeneralService<Room> {
    Page<Room> findAll(Pageable pageable);
    Optional<Room> findById(Long id);
}
