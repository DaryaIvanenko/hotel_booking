package org.example.controller;

import jakarta.validation.Valid;
import org.example.entity.Booking;
import org.example.service.BookingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping
    public List<Booking> getAllBookings(){
        return bookingService.getAllBookings();
    }

    @GetMapping("/{id}")
    public Booking getBookingById(@PathVariable Long id){
        return bookingService.getBookingById(id);
    }

    @PostMapping("/room/{roomId}")
    public Booking createBooking(@PathVariable Long roomId, @Valid @RequestBody Booking booking){
        return bookingService.saveBooking(roomId, booking);
    }

    @DeleteMapping
    public void deleteAllBookings(){
        bookingService.deleteAllBookings();
    }

    @DeleteMapping("/{id}")
    public void deleteBookingById(@PathVariable Long id){
        bookingService.deleteBookingById(id);
    }
}
