package org.example.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "hotels")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Название отеля не может быть пустым")
    private String name;
    @Min(value = 1, message = "Отель должен иметь минимум 1 звезду")
    @Max(value = 5, message = "Отель может иметь максимум 5 звезд")
    @Column(name = "count_stars")
    private int countStars;
    @NotBlank(message = "Адрес не может быть пустым")
    private String address;

    public Hotel() {
    }

    public Hotel(String name, int countStars, String address) {
        this.name = name;
        this.countStars = countStars;
        this.address = address;
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

    public int getCountStars() {
        return countStars;
    }

    public void setCountStars(int countStars) {
        this.countStars = countStars;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
