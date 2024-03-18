package org.example.demobookinghotel.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomId;
    private String roomType;
    private BigDecimal roomPrice;
    private boolean isBooked = false;
    private String description;
    @Lob
    private Blob directionImage;
    @OneToMany(mappedBy="room", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<BookedRoom> bookings;
    public Room(){
        this.bookings = new ArrayList<>();
    }
    public void addBooking(BookedRoom booking){
        if (bookings == null){
            bookings = new ArrayList<>();
        }
        bookings.add(booking);
        booking.setRoom(this);
        isBooked = true;
        int bookingCode = (int) (Math.random() * 1000);
        System.out.println(bookingCode);
        booking.setBookingConfirmationCode(bookingCode);
    }
}