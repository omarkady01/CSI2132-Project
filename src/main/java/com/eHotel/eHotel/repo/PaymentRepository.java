package com.eHotel.eHotel.repo;

import com.eHotel.eHotel.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}
