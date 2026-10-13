package org.example.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "hotels")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Название отеля не может быть пустым")
    @Column(nullable = false)
    private String name;

    @Min(value = 1, message = "Отель должен иметь минимум 1 звезду")
    @Max(value = 5, message = "Отель может иметь максимум 5 звезд")
    @Column(name = "count_stars", nullable = false)
    private Integer countStars;

    @NotBlank(message = "Адрес не может быть пустым")
    @Column(nullable = false)
    private String address;

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Room> rooms = new ArrayList<>();

    public Hotel() {
    }

    public Hotel(String name, Integer countStars, String address, List<Room> rooms) {
        this.name = name;
        this.countStars = countStars;
        this.address = address;
        this.rooms = rooms;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCountStars() {
        return countStars;
    }

    public void setCountStars(Integer countStars) {
        this.countStars = countStars;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", countStars=" + countStars +
                ", address='" + address + '\'' +
                '}';
    }
}
