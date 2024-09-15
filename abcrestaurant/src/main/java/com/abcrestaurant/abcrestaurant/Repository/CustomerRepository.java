package com.abcrestaurant.abcrestaurant.Repository;

import com.abcrestaurant.abcrestaurant.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    
}
