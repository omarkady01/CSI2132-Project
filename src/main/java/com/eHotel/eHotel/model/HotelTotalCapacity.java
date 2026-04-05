package com.eHotel.eHotel.model;

import jakarta.persistence.*;

@Entity
@Table(name = "hotel_total_capacity")
public class HotelTotalCapacity {

    @Id
    @Column(name="hotel_id")
    private Integer hotelId;

    @Column(name = "hotel_name")
    private String hotelName;

    @Column(name = "total_capacity")
    private Integer totalCapacity;

    public Integer getHotelId() {
        return hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public Integer getTotalCapacity() {
        return totalCapacity;
    }

}
