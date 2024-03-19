package org.example.demobookinghotel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoBookingHotelApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoBookingHotelApplication.class, args);

        System.out.println("http://localhost:8080/admin/showAllRoom");
    }

}
