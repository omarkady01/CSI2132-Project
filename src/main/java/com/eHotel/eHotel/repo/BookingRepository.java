package com.eHotel.eHotel.repo;

import com.eHotel.eHotel.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;



public interface BookingRepository extends JpaRepository<Booking, Integer>{
    List<Booking> findByStatus(String status);

}
