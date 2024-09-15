package com.abcrestaurant.abcrestaurant.Controller;

import com.abcrestaurant.abcrestaurant.Entity.Customer;
import com.abcrestaurant.abcrestaurant.Response.CommonResponse;
import com.abcrestaurant.abcrestaurant.Services.CustomerService;
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
@RequestMapping("/customers")
@Tag(name = "Customer Controller", description = "APIs for managing customer accounts")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/getAllCustomers")
    @Operation(summary = "Get All Customers", description = "Retrieve a list of all customer accounts")
    @ApiResponse(responseCode = "200", description = "List of customers retrieved successfully")
    public ResponseEntity<CommonResponse<List<Customer>>> getAllCustomers() {
        CommonResponse<List<Customer>> commonResponse = customerService.getAllCustomers();

        if (commonResponse != null) {
            HttpStatus status = HttpStatus.valueOf(commonResponse.getStatusCode());

            if (commonResponse.getData() == null || commonResponse.getData().isEmpty()) {
                commonResponse.setMessage("No customers found.");
                commonResponse.setStatusCode(HttpStatus.NO_CONTENT.value());
                commonResponse.setDate(LocalDateTime.now());
                return new ResponseEntity<>(commonResponse, HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(commonResponse, status);
        } else {
            CommonResponse<List<Customer>> errorResponse = new CommonResponse<>();
            errorResponse.setMessage("An error occurred while retrieving customers.");
            errorResponse.setData(null);
            errorResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            errorResponse.setDate(LocalDateTime.now());

            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getCustomer/{id}")
    @Operation(summary = "Get Customer by ID", description = "Retrieve a specific customer account by its ID")
    public ResponseEntity<CommonResponse<Customer>> getCustomerById(@PathVariable Long id) {
        CommonResponse<Customer> response = customerService.getCustomerById(id);
        if (response != null) {
            HttpStatus status = HttpStatus.valueOf(response.getStatusCode());
            return new ResponseEntity<>(response, status);
        } else {
            CommonResponse<Customer> noDataResponse = new CommonResponse<>();
            noDataResponse.setMessage("Customer not found.");
            noDataResponse.setData(null);
            noDataResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
            noDataResponse.setDate(LocalDateTime.now());

            return new ResponseEntity<>(noDataResponse, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/createCustomer")
    @Operation(summary = "Create Customer", description = "Create a new customer account")
    public ResponseEntity<CommonResponse<Customer>> createCustomer(@RequestBody Customer customer) {
        try {
            CommonResponse<Customer> response = customerService.createCustomer(customer);
            return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatusCode()));
        } catch (Exception e) {
            CommonResponse<Customer> errorResponse = new CommonResponse<>();
            errorResponse.setMessage("An error occurred while creating the customer.");
            errorResponse.setData(null);
            errorResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            errorResponse.setDate(LocalDateTime.now());

            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateCustomer/{id}")
    @Operation(summary = "Update Customer", description = "Update the details of an existing customer account by its ID")
    public ResponseEntity<CommonResponse<Customer>> updateCustomer(@PathVariable Long id, @RequestBody Customer customerDetails) {
        try {
            CommonResponse<Customer> response = customerService.updateCustomer(id, customerDetails);
            return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatusCode()));
        } catch (Exception e) {
            CommonResponse<Customer> errorResponse = new CommonResponse<>();
            errorResponse.setMessage("An error occurred while updating the customer.");
            errorResponse.setData(null);
            errorResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            errorResponse.setDate(LocalDateTime.now());

            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteCustomer/{id}")
    @Operation(summary = "Delete Customer", description = "Delete a customer account by its ID")
    public ResponseEntity<CommonResponse<Void>> deleteCustomer(@PathVariable Long id) {
        try {
            CommonResponse<Void> response = customerService.deleteCustomer(id);
            return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatusCode()));
        } catch (Exception e) {
            CommonResponse<Void> errorResponse = new CommonResponse<>();
            errorResponse.setMessage("An error occurred while deleting the customer.");
            errorResponse.setData(null);
            errorResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            errorResponse.setDate(LocalDateTime.now());

            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
