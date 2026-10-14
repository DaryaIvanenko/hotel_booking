package org.example.service;

import org.example.entity.Booking;
import org.example.entity.Client;
import org.example.entity.Room;
import org.example.exception.ResourceNotFoundException;
import org.example.repository.BookingRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookingService {
    private final BookingRepository bookingRepository;
    private final RoomService roomService;
    private final ClientService clientService;

    public BookingService(BookingRepository bookingRepository, RoomService roomService, ClientService clientService) {
        this.bookingRepository = bookingRepository;
        this.roomService = roomService;
        this.clientService = clientService;
    }

    public List<Booking> getAllBookings(){
        return bookingRepository.findAll();
    }

    public Booking getBookingById(Long id){
        return bookingRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Бронирования с id " + id + " не найдено"));
    }

    public Booking saveBooking(Long roomId, Long clientId, Booking booking){
        Room room = roomService.getRoomById(roomId);
        Client client = clientService.getClientById(clientId);
        LocalDate startDate = booking.getStartDate();
        LocalDate endDate = booking.getEndDate();
        if(bookingRepository.existOverlappingBookings(roomId, startDate, endDate)){
            throw new IllegalStateException("На эти даты комната уже занята!");
        }
        booking.setRoom(room);
        booking.setClient(client);
        return bookingRepository.save(booking);
    }

    public void deleteBookingById(Long id){
        if(!bookingRepository.existsById(id)) throw new ResourceNotFoundException("Бронирования с id " + id + " не найдено");
        bookingRepository.deleteById(id);
    }

    public void deleteAllBookings(){
        bookingRepository.deleteAll();
    }
}
