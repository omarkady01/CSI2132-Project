package com.eHotel.eHotel.model;

import jakarta.persistence.*;

@Entity
@Table(name = "available_rooms_per_area")
public class AvailableRoomsPerArea {

    @Id
    @Column(name = "area")
    private String area;

    @Column(name="available_rooms")
    private Integer availableRooms;

    public String getArea() {
        return area;
    }

    public Integer getAvailableRooms() {
        return availableRooms;
    }

}
