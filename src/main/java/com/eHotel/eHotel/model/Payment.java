package com.eHotel.eHotel.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Integer paymentId;

    @Column(name = "renting_id")
    private Integer rentingId;

    @Column(name = "payment_date")
    private LocalDateTime paymentDate;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "payment_method")
    private String paymentMethod;

    public Payment() {

    }

    public Integer getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Integer id) {
        this.paymentId = id;
    }

    public Integer getRentingId() {
        return rentingId;
    }

    public void setRentingId(Integer id) {
        this.rentingId = id;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime date) {
        this.paymentDate = date;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String method) {
        this.paymentMethod = method;
    }
}
