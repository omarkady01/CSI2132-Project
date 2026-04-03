package com.eHotel.eHotel.model;

import jakarta.persistence.*;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @Column(name = "employee_id")
    private Integer employeeId;

    @Column(name = "hotel_id")
    private Integer hotelId;

    @Column(name = "emp_name")
    private String empName;

    @Column(name = "address")
    private String address;

    @Column(name = "sin")
    private String sin;

    public Employee() {

    }

    public void setEmployeeId(Integer id) {
        this.employeeId = id;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer id) {
        this.hotelId = id;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String name) {
        this.empName = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSin() {
        return sin;
    }

    public void setSin(String sin) {
        this.sin = sin;
    }

}
