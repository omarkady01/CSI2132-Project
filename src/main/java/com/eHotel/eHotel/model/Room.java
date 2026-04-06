package com.eHotel.eHotel.model;
import jakarta.persistence.*;


@Entity
@Table(name = "room")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Integer roomId;

    @Column(name = "hotel_id")
    private Integer hotelId;

    //@Column(name = "hotel_name")
    //@Transient
    //private String hotelName;

    @Column(name = "room_number")
    private String roomNumber;

    //@Column(name = "city")
    //@Transient
    //private String city;

    //@Column(name = "rating")
    //@Transient
    //private Integer rating;

    @Column(name = "capacity")
    private String capacity;

    @Column(name = "price") 
    private Double price;

    @Column(name = "view_type")
    private String viewType;

    @Column(name = "extendable")
    private Boolean extendable;

    @Column(name = "problems")
    private String problems;

    @Column(name = "is_active")
    private Boolean isActive;

    @ManyToOne
    @JoinColumn(name = "hotel_id", insertable = false, updatable = false)
    private Hotel hotel;


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

   // public String getHotelName() {
      //  return hotelName;
    //}

    //public String getCity() {
      //  return city;
    //}

    //public Integer getRating() {
      //  return rating;
    //}

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

   /*public void setHotelName(String name) {
        this.hotelName = name;
    }

    public void setCity(String name) {
        this.city = name;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }*/

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
}
