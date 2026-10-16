package org.example.controller;

import jakarta.validation.Valid;
import org.example.entity.Booking;
import org.example.service.BookingService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<Booking> getAllBookings(){
        return bookingService.getAllBookings();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Booking getBookingById(@PathVariable Long id){
        return bookingService.getBookingById(id);
    }

    @GetMapping("/client/{clientId}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Booking> getBookingsByClientId(@PathVariable Long clientId){
        return bookingService.getBookingsByClientId(clientId);
    }

    @PostMapping("/room/{roomId}/client/{clientId}")
    public Booking createBooking(@PathVariable Long roomId, @PathVariable Long clientId, @Valid @RequestBody Booking booking){
        return bookingService.saveBooking(roomId, clientId, booking);
    }

    @DeleteMapping
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteAllBookings(){
        bookingService.deleteAllBookings();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteBookingById(@PathVariable Long id){
        bookingService.deleteBookingById(id);
    }

    @PutMapping("/{id}/client/{clientId}/room/{roomId}")
    @PreAuthorize("hasRole('ADMIN')")
    public Booking updateBooking(@PathVariable Long id, @PathVariable Long clientId, @PathVariable Long roomId, @Valid @RequestBody Booking booking){
        return bookingService.updateBooking(id, clientId, roomId, booking);
    }
}
