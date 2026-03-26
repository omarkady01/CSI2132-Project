package com.eHotel.eHotel.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "customer")

public class Customer {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="customer_id")
    private Integer customerId;

    @Column(name = "cust_name")
    private String custName;

    @Column(name = "address")
    private String address;

    @Column(name = "id_type")
    private String idType;

    @Column(name = "id_number")
    private String idNumber;

    @Column(name = "registration_date")
    private LocalDate registrationDate;

    public Customer() {}

    public Integer getCutomerId() {
        return customerId;
    }

    public String getCustName() {
        return custName;
    }

    public String getAddress() {
        return address;
    }

    public String getIdType() {
        return idType;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public LocalDate getRegistrationDate() {
        
        return registrationDate;
    }

    public void setCustomerId(Integer id) {
        this.customerId = id;
    }

    public void setCustName(String name) {
        this.custName = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setIdType(String type) {
        this.idType = type;
    }

    public void setIdNumber(String num) {
        this.idNumber = num;
    }

    public void setRegistrationDate(LocalDate date) {
        this.registrationDate = date;
    }

}
