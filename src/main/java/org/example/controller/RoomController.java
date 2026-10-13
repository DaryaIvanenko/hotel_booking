package org.example.controller;

import jakarta.validation.Valid;
import org.example.entity.Room;
import org.example.entity.RoomType;
import org.example.service.RoomService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {
    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public List<Room> getAllRooms(){
        return roomService.getAllRooms();
    }

    @GetMapping("/{id}")
    public Room getRoomById(@PathVariable Long id){
        return roomService.getRoomById(id);
    }

    @GetMapping("/count")
    public Long countRooms(){
        return roomService.countRooms();
    }

    @GetMapping("/hotel/{id}")
    public List<Room> getRoomsByHotelId(@PathVariable Long id){
        return roomService.getRoomsByHoTelId(id);
    }

    @GetMapping("/type/{type}")
    public List<Room> getRoomsByType(@PathVariable RoomType type){
        return roomService.getRoomsByType(type);
    }

    @GetMapping("/filter")
    public List<Room> getFilterRooms(@RequestParam(required = false) RoomType type, @RequestParam(required = false) BigDecimal maxPrice,
                                     @RequestParam(required = false) BigDecimal minPrice,@RequestParam(required = false) Integer maxCapacity,
                                     @RequestParam(required = false) Integer minCapacity){
        return roomService.getFilterRooms(type, maxPrice, minPrice, maxCapacity, minCapacity);
    }

    @PostMapping("/hotel/{hotelId}")
    public Room createRoom(@PathVariable Long hotelId, @Valid @RequestBody Room room){
        return roomService.saveRoom(hotelId, room);
    }

    @DeleteMapping
    public void deleteAllRooms(){
        roomService.deleteAllRooms();
    }

    @DeleteMapping("/{id}")
    public void deleteRoomById(@PathVariable Long id){
        roomService.deleteRoomById(id);
    }

    @PutMapping("/{id}")
    public Room updateRoom(@PathVariable Long id, @Valid @RequestBody Room room){
        return roomService.updateRoom(id, room);
    }
}
