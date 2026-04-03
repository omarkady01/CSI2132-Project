package com.eHotel.eHotel.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Table(name = "renting")
public class Renting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "renting_id")
    private Integer rentingId;

    @Column(name = "booking_id")
    private Integer bookingId;

    @Column(name = "customer_id")
    private Integer customerId;

    @Column(name = "room_id")
    private Integer roomId;

    @Column(name = "employee_id")
    private Integer employeeId;

    @Column(name = "check_in_date")
    private LocalDate checkInDate;

    @Column(name = "check_out_date")
    private LocalDate checkOutDate;

    @Column(name = "renting_date")
    private LocalDateTime rentingDate;

    @Column(name = "status")
    private String status;

    public Renting() {
    }

    public Integer getRentingId() {
        return rentingId;
    }

    public void setRentingId(Integer id) {
        this.rentingId = id;
    }

    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer id) {
        this.bookingId = id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer id) {
        this.customerId = id;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer id) {
        this.roomId = id;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer id) {
        this.employeeId = id;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDate date) {
        this.checkInDate = date;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate date) {
        this.checkOutDate = date;
    }

    public LocalDateTime getRentingDate() {
        return rentingDate;
    }

    public void setRentingDate(LocalDateTime date) {
        this.rentingDate = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
