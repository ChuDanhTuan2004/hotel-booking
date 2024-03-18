package org.example.demobookinghotel.service.impl;

import org.example.demobookinghotel.model.BookedRoom;
import org.example.demobookinghotel.repository.BookingRepository;
import org.example.demobookinghotel.service.BookedRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class BookedRoomServiceImpl implements BookedRoomService {
    @Autowired
    BookingRepository bookingRepository;
    @Override
    public Page<BookedRoom> findAll(Pageable pageable) {
        return bookingRepository.findAll(pageable);
    }

    @Override
    public Optional<BookedRoom> findById(Long id) {
        return bookingRepository.findById(id);
    }

    @Override
    public void save(BookedRoom bookedRoom) {
        bookingRepository.save(bookedRoom);
    }

    @Override
    public void delete(Long id) {
        bookingRepository.deleteById(id);
    }
}
