package com.eHotel.eHotel.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private Integer bookingId;

    @Column(name = "customer_id")
    private Integer customerId;

    @Column(name = "room_id")
    private Integer roomId;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "booking_date")
    private LocalDateTime bookingDate;

    @Column(name = "status")
    private String status;

    public Booking() {}

    public Integer getBookingId() {
        return bookingId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEnDate() {
        return endDate;
    }

    public LocalDateTime getBookingDate() {
        return bookingDate;
    }

    public String getStatus() {
        return status;
    }

    public void setBookingId(Integer id) {
        this.bookingId = id;
    }

    public void setCustomerId(Integer id) {
        this.customerId = id;
    }

    public void setRoomId(Integer id) {
        this.roomId = id;
    }

    public void setStartDate(LocalDate date) {
        this.startDate = date;
    }

    public void setEndDate(LocalDate date) {
        this.endDate = date;
    }

    public void setBookingDate(LocalDateTime date) {
        this.bookingDate = date;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
