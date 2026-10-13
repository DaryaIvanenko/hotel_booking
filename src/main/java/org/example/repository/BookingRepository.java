package org.example.repository;

import org.example.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    @Query("SELECT Count(b) > 0 FROM Booking b WHERE b.room.id = :roomId AND" +
            ":startDate < b.endDate AND :endDate > b.startDate")
    boolean existOverlappingBookings(@Param("roomId") Long roomId,
                                     @Param("startDate") LocalDate startDate,
                                     @Param("endDate") LocalDate endDate);
}
