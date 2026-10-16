package org.example.service;

import org.example.entity.Hotel;
import org.example.entity.Room;
import org.example.entity.RoomType;
import org.example.exception.ResourceNotFoundException;
import org.example.repository.RoomRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class RoomService {

    private final RoomRepository roomRepository;
    private final HotelService hotelService;

    public RoomService(RoomRepository roomRepository, HotelService hotelService) {
        this.roomRepository = roomRepository;
        this.hotelService = hotelService;
    }

    public List<Room> getAllRooms(){
        return roomRepository.findAll();
    }

    public Room getRoomById(Long id){
        return roomRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Комнаты с id " + id+ "не найдено"));
    }

    public List<Room> getRoomsByHoTelId(Long hotelId){
        return roomRepository.findByHotelId(hotelId);
    }

    public List<Room> getRoomsByType(RoomType type){
        return roomRepository.findByType(type);
    }

    public List<Room> getFilterRooms(RoomType type, BigDecimal maxPrice,
                                     BigDecimal minPrice,Integer maxCapacity, Integer minCapacity){
        return roomRepository.filterRooms(type, maxPrice, minPrice, maxCapacity, minCapacity);
    }

    public List<Room> getAvailableRoomsInHotel(Long hotelId, LocalDate startDate, LocalDate endDate){
        return roomRepository.findAvailableRoom(hotelId, startDate, endDate);
    }

    public Long countRooms(){
        return roomRepository.count();
    }

    public Room saveRoom(Long hotelId, Room room){
        Hotel hotel = hotelService.getHotelById(hotelId);
        room.setHotel(hotel);
        return roomRepository.save(room);
    }

    public void deleteAllRooms(){
        roomRepository.deleteAll();
    }

    public void deleteRoomById(Long id){
        if(!roomRepository.existsById(id)) throw new ResourceNotFoundException("Комнаты с id " + id+ "не найдено");
        roomRepository.deleteById(id);
    }

    public Room updateRoom(Long id, Long hotelId, Room updateRoom){
        Room existRoom = getRoomById(id);
        Hotel hotel = hotelService.getHotelById(hotelId);
        existRoom.setCapacity(updateRoom.getCapacity());
        existRoom.setNumber(updateRoom.getNumber());
        existRoom.setType(updateRoom.getType());
        existRoom.setPrice(updateRoom.getPrice());
        existRoom.setHotel(hotel);
        return roomRepository.save(existRoom);
    }


}
