package org.example.repository;

import org.example.entity.Room;
import org.example.entity.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findByHotelId(Long hotelId);
    List<Room> findByType(RoomType type);
    List<Room> findByPriceLessThanEqual(BigDecimal price);

    @Query("SELECT r FROM Room r WHERE (:type IS NULL OR r.type = :type) AND " +
            "(:maxPrice IS NULL OR r.price <= :maxPrice) AND " +
            "(:minPrice IS NULL OR r.price >= :minPrice) AND " +
            "(:maxCapacity IS NULL OR r.capacity <= :maxCapacity) AND " +
            "(:minCapacity IS NULL OR r.capacity >= :minCapacity)")
    List<Room> filterRooms(@Param("type") RoomType type,
                           @Param("maxPrice") BigDecimal maxPrice,
                           @Param("minPrice") BigDecimal minPrice,
                           @Param("maxCapacity") Integer maxCapacity,
                           @Param("minCapacity") Integer minCapacity);
}
