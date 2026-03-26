package com.eHotel.eHotel.repo;

import com.eHotel.eHotel.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Optional<Customer> findByIdNumber(String idNumber);

}
