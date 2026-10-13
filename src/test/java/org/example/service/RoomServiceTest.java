package org.example.service;

import org.example.entity.Room;
import org.example.entity.RoomType;
import org.example.repository.RoomRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RoomServiceTest {
    @Mock
    private RoomRepository roomRepository;
    @Mock
    private HotelService hotelService;
    @InjectMocks
    private RoomService roomService;

    @Test
    void getRoomsByHotelId(){
        Long hotelId = 1L;
        Room room = new Room();
        room.setType(RoomType.STANDARD);

        when(roomRepository.findByHotelId(hotelId)).thenReturn(List.of(room));

        List<Room> rooms = roomService.getRoomsByHoTelId(hotelId);

        assertEquals(1, rooms.size());
        assertEquals(RoomType.STANDARD, rooms.get(0).getType());
    }
}
