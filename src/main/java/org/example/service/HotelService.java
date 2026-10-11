package org.example.service;

import org.example.entity.Hotel;
import org.example.exception.ResourceNotFoundException;
import org.example.repository.HotelRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService {
    private final HotelRepository hotelRepository;

    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }
    public List<Hotel> getAllHotels(){
        return hotelRepository.findAll();
    }

    public Hotel getHotelById(Long id){
        if (!hotelRepository.existsById(id)) throw new ResourceNotFoundException("Отель с id " + id + " не найден");
        return hotelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Отель с id " + id + "не найден"));
    }

    public Long countHotels(){
        return hotelRepository.count();
    }

    public Hotel saveHotel(Hotel hotel){
        return hotelRepository.save(hotel);
    }

    public void deleteHotelById(Long id){
        hotelRepository.deleteById(id);
    }

    public void deleteAllHotels(){
        hotelRepository.deleteAll();
    }

    public Hotel updateHotel(Long id, Hotel updateHotel){
        Hotel existHotel = getHotelById(id);
        existHotel.setName(updateHotel.getName());
        existHotel.setAddress(updateHotel.getAddress());
        existHotel.setCountStars(updateHotel.getCountStars());
        return hotelRepository.save(existHotel);
    }
}
