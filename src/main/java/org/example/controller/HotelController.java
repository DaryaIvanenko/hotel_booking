package org.example.controller;

import jakarta.validation.Valid;
import org.example.entity.Hotel;
import org.example.service.HotelService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {
    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping
    public List<Hotel> getAllHotels(){
        return hotelService.getAllHotels();
    }

    @GetMapping("/{id}")
    public Hotel getHotelById(@PathVariable Long id){
        return hotelService.getHotelById(id);
    }

    @GetMapping("/count")
    public Long getCountHotels(){
        return hotelService.countHotels();
    }

    @PostMapping
    public Hotel createHotel(@Valid @RequestBody Hotel hotel){
        return hotelService.saveHotel(hotel);
    }

    @DeleteMapping
    public void deleteAllHotels(){
        hotelService.deleteAllHotels();
    }

    @DeleteMapping("/{id}")
    public void deleteHotelById(@PathVariable Long id){
        hotelService.deleteHotelById(id);
    }

    @PutMapping("/{id}")
    public Hotel updateHotel(@PathVariable Long id, @Valid @RequestBody Hotel updateHotel){
        return hotelService.updateHotel(id, updateHotel);
    }
}
