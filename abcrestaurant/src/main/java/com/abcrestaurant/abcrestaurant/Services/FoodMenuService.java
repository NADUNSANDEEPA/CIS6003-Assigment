package com.abcrestaurant.abcrestaurant.Services;

import com.abcrestaurant.abcrestaurant.Entity.FoodMenu;
import com.abcrestaurant.abcrestaurant.Repository.FoodMenuRepository;
import com.abcrestaurant.abcrestaurant.Response.CommonResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class FoodMenuService {

    @Autowired
    private FoodMenuRepository foodMenuRepository;

    // Retrieve all food menu items
    public CommonResponse<List<FoodMenu>> getAllFoodMenus() {
        CommonResponse<List<FoodMenu>> commonResponse = new CommonResponse<>();
        try {
            List<FoodMenu> foodMenus = foodMenuRepository.findAll();
            if (!foodMenus.isEmpty()) {
                commonResponse.setStatusCode(HttpStatus.OK.value());
                commonResponse.setMessage("Food menus retrieved successfully");
                commonResponse.setData(foodMenus);
            } else {
                commonResponse.setStatusCode(HttpStatus.NO_CONTENT.value());
                commonResponse.setMessage("No food menus found");
                commonResponse.setData(null);
            }
        } catch (Exception e) {
            commonResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            commonResponse.setMessage("An error occurred while retrieving food menus.");
            commonResponse.setData(null);
        }
        commonResponse.setDate(LocalDateTime.now());
        return commonResponse;
    }

    // Retrieve a specific food menu item by ID
    public CommonResponse<FoodMenu> getFoodMenuById(Long id) {
        CommonResponse<FoodMenu> commonResponse = new CommonResponse<>();
        try {
            Optional<FoodMenu> optionalFoodMenu = foodMenuRepository.findById(id);
            if (optionalFoodMenu.isPresent()) {
                commonResponse.setStatusCode(HttpStatus.OK.value());
                commonResponse.setMessage("Food menu retrieved successfully");
                commonResponse.setData(optionalFoodMenu.get());
            } else {
                commonResponse.setStatusCode(HttpStatus.NO_CONTENT.value());
                commonResponse.setMessage("No food menu found with the given ID");
                commonResponse.setData(null);
            }
        } catch (Exception e) {
            commonResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            commonResponse.setMessage("An error occurred while retrieving the food menu.");
            commonResponse.setData(null);
        }
        commonResponse.setDate(LocalDateTime.now());
        return commonResponse;
    }

    // Create a new food menu item
    public CommonResponse<FoodMenu> createFoodMenu(FoodMenu foodMenu) {
        FoodMenu createdFoodMenu = foodMenuRepository.save(foodMenu);
        CommonResponse<FoodMenu> commonResponse = new CommonResponse<>();
        LocalDateTime now = LocalDateTime.now();

        if (createdFoodMenu != null) {
            commonResponse.setStatusCode(200);
            commonResponse.setMessage("Food menu created successfully");
            commonResponse.setData(createdFoodMenu);
        } else {
            commonResponse.setMessage("Failed to create food menu");
            commonResponse.setData(null);
            commonResponse.setStatusCode(500);
        }
        commonResponse.setDate(now);

        return commonResponse;
    }

    // Update an existing food menu item
    public CommonResponse<FoodMenu> updateFoodMenu(Long id, FoodMenu foodMenuDetails) {
        CommonResponse<FoodMenu> commonResponse = new CommonResponse<>();
        LocalDateTime now = LocalDateTime.now();

        if (foodMenuRepository.existsById(id)) {
            foodMenuDetails.setId(id); // Ensure the same ID is used
            FoodMenu updatedFoodMenu = foodMenuRepository.save(foodMenuDetails);

            commonResponse.setStatusCode(200);
            commonResponse.setMessage("Food menu updated successfully");
            commonResponse.setData(updatedFoodMenu);
        } else {
            commonResponse.setStatusCode(404);
            commonResponse.setMessage("Food menu not found");
            commonResponse.setData(null);
        }
        commonResponse.setDate(now);

        return commonResponse;
    }

    // Delete a food menu item by ID
    public CommonResponse<FoodMenu> deleteFoodMenu(Long id) {
        CommonResponse<FoodMenu> commonResponse = new CommonResponse<>();
        LocalDateTime now = LocalDateTime.now();

        if (foodMenuRepository.existsById(id)) {
            foodMenuRepository.deleteById(id);
            commonResponse.setStatusCode(200);
            commonResponse.setMessage("Food menu deleted successfully");
        } else {
            commonResponse.setStatusCode(404);
            commonResponse.setMessage("Food menu not found");
        }
        commonResponse.setDate(now);

        return commonResponse;
    }
}
