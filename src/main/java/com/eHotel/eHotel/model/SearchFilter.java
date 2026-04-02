package com.eHotel.eHotel.model;

public class SearchFilter {

    private String city;
    private String capacity;
    private Double minPrice;
    private Double maxPrice;
    private String checkInDate;
    private String checkOutDate;
    private boolean dateNull;

    public SearchFilter() {

    }

    public String getCity() {
        return city;
    }

    public String getCapacity() {
        return capacity;
    }

    public Double getMinPrice() {
        return minPrice;
    }

    public Double getMaxPrice() {
        return maxPrice;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public void setCity(String city) {
        this.city = normalizeString(city);
    }

    public void setCapacity(String capacity) {
        this.capacity = normalizeString(capacity);
    }

    public void setMinPrice(Double price) {
        this.minPrice = price;
    }

    public void setMaxPrice(Double price) {
        this.maxPrice = price;
    }

    public void setCheckInDate(String date) {
        this.checkInDate = normalizeString(date);
    }

    public void setCheckOutDate(String date) {
        this.checkOutDate = normalizeString(date);
    }

    private String normalizeString(String value) {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }
        return value.trim();
    }

    public void setDateNull(boolean val) {
        this.dateNull = val;
    }

    public boolean getDateNull() {
        return dateNull;
    }

}

