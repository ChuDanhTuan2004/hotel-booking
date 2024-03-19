package org.example.demobookinghotel.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
    @NotBlank(message = "Vui long nhap kieu phong")
    private String roomType;
    @Min(value = 0, message = "Vui long nhap gia lon hon 0")
    @NotNull(message = "Vui long nhap gia phong")
    private BigDecimal roomPrice;
    private boolean isBooked = false;
    private String description;
    private String directionImage;
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
        booking.setBookingConfirmationCode(bookingCode);
    }
}