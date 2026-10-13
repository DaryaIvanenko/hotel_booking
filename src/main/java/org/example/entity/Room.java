package org.example.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.awt.print.Book;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Номер комнаты не может быть пустым")
    @Min(value = 100, message = "Номер комнаты не может быть меньше 100")
    @Column(nullable = false)
    private Integer number;

    @NotNull(message = "Тип комнаты не может быть пустым")
    @Column(nullable = false)
    private RoomType type;

    @NotNull(message = "Количество мест в номере не может быть пустым")
    @Min(value = 1, message = "Количество мест в номере не может быть меньше одного")
    @Column(nullable = false)
    private Integer capacity;

    @NotNull(message = "Стоимость комнаты не может быть пустой")
    @DecimalMin(value = "0.1", message = "Цена должна быть больше нуля")
    @Column(nullable = false)
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id", nullable = false)
    @JsonBackReference
    private Hotel hotel;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Booking> bookings = new ArrayList<>();

    public Room() {
    }

    public Room(Integer number, RoomType type, Integer capacity, BigDecimal price, Hotel hotel, List<Booking> bookings) {
        this.number = number;
        this.type = type;
        this.capacity = capacity;
        this.price = price;
        this.hotel = hotel;
        this.bookings = bookings;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public RoomType getType() {
        return type;
    }

    public void setType(RoomType type) {
        this.type = type;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", number=" + number +
                ", type=" + type +
                ", capacity=" + capacity +
                ", price=" + price +
                ", hotel=" + hotel +
                '}';
    }
}
