package com.eHotel.eHotel.model;
import jakarta.persistence.*;

@Entity
@Table(name = "room")
public class Room {

    @Id
    @Column(name = "room_id")
    private Integer roomId;

    @Column(name = "hotel_id")
    private Integer hotelId;

    @Column(name = "room_number")
    private String roomNumber;

    @Column(name = "price") 
    private Double price;

    @Column(name = "capacity")
    private String capacity;

    @Column(name = "view_type")
    private String viewType;

    @Column(name = "extendable")
    private Boolean extendable;

    @Column(name = "problems")
    private String problems;

    @Column(name = "is_active")
    private Boolean isActive;

    public Room() {}

    // GETTERS

    public Integer getRoomId() {
        return roomId;
    }
    
    public Integer getHotelId() {
        return hotelId;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public Double getPrice() {
        return price;
    }

    public String getCapacity() {
        return capacity;
    }

    public String getViewType() {
        return viewType;
    }

    public Boolean getExtendable() {
        return extendable;
    }

    public String getProblems() {
        return problems;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    // SETTERS

    public void setRoomId(Integer id) {
        this.roomId = id;
    }

    public void setHotelId(Integer id) {
        this.hotelId = id;
    }

    public void setRoomNumber(String number) {
        this.roomNumber = number;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public void setViewType(String type) {
        this.viewType = type;
    }

    public void setExtendable(Boolean val) {
        this.extendable = val;
    }

    public void setProblems(String problem) {
        this.problems = problem;
    }

    public void setIsActive(Boolean val) {
        this.isActive = val;
    }

}
