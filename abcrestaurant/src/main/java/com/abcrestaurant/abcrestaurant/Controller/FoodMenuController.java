package com.abcrestaurant.abcrestaurant.Controller;

import com.abcrestaurant.abcrestaurant.Entity.FoodMenu;
import com.abcrestaurant.abcrestaurant.Response.CommonResponse;
import com.abcrestaurant.abcrestaurant.Services.FoodMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/foodMenu")
@Tag(name = "Food Menu Controller", description = "APIs for managing food menu items")
public class FoodMenuController {

    @Autowired
    private FoodMenuService foodMenuService;

    @GetMapping("/getAllFoodMenus")
    @Operation(summary = "Get All Food Menus", description = "Retrieve a list of all food menu items")
    @ApiResponse(responseCode = "200", description = "List of food menus retrieved successfully")
    public ResponseEntity<CommonResponse<List<FoodMenu>>> getAllFoodMenus() {
        CommonResponse<List<FoodMenu>> commonResponse = foodMenuService.getAllFoodMenus();

        if (commonResponse != null) {
            HttpStatus status = HttpStatus.valueOf(commonResponse.getStatusCode());

            if (commonResponse.getData() == null || commonResponse.getData().isEmpty()) {
                commonResponse.setMessage("No food menus found.");
                commonResponse.setStatusCode(HttpStatus.NO_CONTENT.value());
                commonResponse.setDate(LocalDateTime.now());
                return new ResponseEntity<>(commonResponse, HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(commonResponse, status);
        } else {
            CommonResponse<List<FoodMenu>> errorResponse = new CommonResponse<>();
            errorResponse.setMessage("An error occurred while retrieving food menus.");
            errorResponse.setData(null);
            errorResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            errorResponse.setDate(LocalDateTime.now());

            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getFoodMenu/{id}")
    @Operation(summary = "Get Food Menu by ID", description = "Retrieve a specific food menu item by its ID")
    public ResponseEntity<CommonResponse<FoodMenu>> getFoodMenuById(@PathVariable Long id) {
        CommonResponse<FoodMenu> response = foodMenuService.getFoodMenuById(id);
        if (response != null) {
            HttpStatus status = HttpStatus.valueOf(response.getStatusCode());
            return new ResponseEntity<>(response, status);
        } else {
            CommonResponse<FoodMenu> noDataResponse = new CommonResponse<>();
            noDataResponse.setMessage("Food menu not found.");
            noDataResponse.setData(null);
            noDataResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
            noDataResponse.setDate(LocalDateTime.now());

            return new ResponseEntity<>(noDataResponse, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/createFoodMenu")
    @Operation(summary = "Create Food Menu", description = "Create a new food menu item")
    public ResponseEntity<CommonResponse<FoodMenu>> createFoodMenu(@RequestBody FoodMenu foodMenu) {
        try {
            CommonResponse<FoodMenu> response = foodMenuService.createFoodMenu(foodMenu);
            return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatusCode()));
        } catch (Exception e) {
            CommonResponse<FoodMenu> errorResponse = new CommonResponse<>();
            errorResponse.setMessage("An error occurred while creating the food menu.");
            errorResponse.setData(null);
            errorResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            errorResponse.setDate(LocalDateTime.now());

            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateFoodMenu/{id}")
    @Operation(summary = "Update Food Menu", description = "Update the details of an existing food menu item by its ID")
    public ResponseEntity<CommonResponse<FoodMenu>> updateFoodMenu(@PathVariable Long id, @RequestBody FoodMenu foodMenuDetails) {
        try {
            CommonResponse<FoodMenu> response = foodMenuService.updateFoodMenu(id, foodMenuDetails);
            return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatusCode()));
        } catch (Exception e) {
            CommonResponse<FoodMenu> errorResponse = new CommonResponse<>();
            errorResponse.setMessage("An error occurred while updating the food menu.");
            errorResponse.setData(null);
            errorResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            errorResponse.setDate(LocalDateTime.now());

            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteFoodMenu/{id}")
    @Operation(summary = "Delete Food Menu", description = "Delete a food menu item by its ID")
    public ResponseEntity<CommonResponse<FoodMenu>> deleteFoodMenu(@PathVariable Long id) {
        try {
            CommonResponse<FoodMenu> response = foodMenuService.deleteFoodMenu(id);
            return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatusCode()));
        } catch (Exception e) {
            CommonResponse<FoodMenu> errorResponse = new CommonResponse<>();
            errorResponse.setMessage("An error occurred while deleting the food menu.");
            errorResponse.setData(null);
            errorResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            errorResponse.setDate(LocalDateTime.now());

            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
