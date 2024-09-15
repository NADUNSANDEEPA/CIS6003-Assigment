package com.abcrestaurant.abcrestaurant.Repository;

import com.abcrestaurant.abcrestaurant.Entity.FoodMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FoodMenuRepository extends JpaRepository<FoodMenu, Long> {

    // // Custom query to find all available food items
    // List<FoodMenu> findByAvailability(FoodMenu.Availability availability);
    
    // // Custom query to find food items by category
    // List<FoodMenu> findByCategory(String category);
    
    // // Custom query to find food items by price less than a given value
    // List<FoodMenu> findByPriceLessThan(BigDecimal price);
}
