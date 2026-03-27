package com.eHotel.eHotel.model;

import jakarta.persistence.*;

@Entity
@Table(name = "hotel")
public class Hotel {

    @Id
    @Column(name = "hotel_id")
    private Integer hotelId;

    @Column(name = "hotel_name")
    private String hotelName;

    @Column(name = "city")
    private String city;

    @Column(name = "rating")
    private Integer rating;

    public Hotel() {}

    public Integer getHotelId() {
        return hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public String getCity() {
        return city;
    }

    public Integer getRating() {
        return rating;
    }

    public void setHotelId(Integer id) {
        this.hotelId = id;
    }

    public void setHotelName(String name) {
        this.hotelName = name;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setRating(Integer rate) {
        this.rating = rate;
    }


}
