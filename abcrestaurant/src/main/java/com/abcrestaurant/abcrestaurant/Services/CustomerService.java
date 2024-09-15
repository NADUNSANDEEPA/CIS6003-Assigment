package com.abcrestaurant.abcrestaurant.Services;

import com.abcrestaurant.abcrestaurant.Entity.Customer;
import com.abcrestaurant.abcrestaurant.Repository.CustomerRepository;
import com.abcrestaurant.abcrestaurant.Response.CommonResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public CommonResponse<List<Customer>> getAllCustomers() {
        CommonResponse<List<Customer>> commonResponse = new CommonResponse<>();
        try {
            List<Customer> customers = customerRepository.findAll();
            if (customers != null && !customers.isEmpty()) {
                commonResponse.setStatusCode(200);
                commonResponse.setMessage("Customers retrieved successfully");
                commonResponse.setData(customers);
            } else {
                commonResponse.setStatusCode(204);
                commonResponse.setMessage("No customers found");
                commonResponse.setData(customers);
            }
        } catch (Exception e) {
            commonResponse.setStatusCode(500);
            commonResponse.setMessage("An error occurred while retrieving customers.");
            commonResponse.setData(null);
        }
        commonResponse.setDate(LocalDateTime.now());
        return commonResponse;
    }

    public CommonResponse<Customer> getCustomerById(Long id) {
        CommonResponse<Customer> commonResponse = new CommonResponse<>();
        try {
            Optional<Customer> customer = customerRepository.findById(id);
            if (customer.isPresent()) {
                commonResponse.setStatusCode(200);
                commonResponse.setMessage("Customer retrieved successfully");
                commonResponse.setData(customer.get());
            } else {
                commonResponse.setStatusCode(404);
                commonResponse.setMessage("Customer not found");
                commonResponse.setData(null);
            }
        } catch (Exception e) {
            commonResponse.setStatusCode(500);
            commonResponse.setMessage("An error occurred while retrieving the customer.");
            commonResponse.setData(null);
        }
        commonResponse.setDate(LocalDateTime.now());
        return commonResponse;
    }

    public CommonResponse<Customer> createCustomer(Customer customer) {
        CommonResponse<Customer> commonResponse = new CommonResponse<>();
        try {
            Customer createdCustomer = customerRepository.save(customer);
            commonResponse.setStatusCode(201);
            commonResponse.setMessage("Customer created successfully");
            commonResponse.setData(createdCustomer);
        } catch (Exception e) {
            commonResponse.setStatusCode(500);
            commonResponse.setMessage("An error occurred while creating the customer.");
            commonResponse.setData(null);
        }
        commonResponse.setDate(LocalDateTime.now());
        return commonResponse;
    }

    public CommonResponse<Customer> updateCustomer(Long id, Customer customerDetails) {
        CommonResponse<Customer> commonResponse = new CommonResponse<>();
        try {
            if (customerRepository.existsById(id)) {
                customerDetails.setId(id);
                Customer updatedCustomer = customerRepository.save(customerDetails);
                commonResponse.setStatusCode(200);
                commonResponse.setMessage("Customer updated successfully");
                commonResponse.setData(updatedCustomer);
            } else {
                commonResponse.setStatusCode(404);
                commonResponse.setMessage("Customer not found");
                commonResponse.setData(null);
            }
        } catch (Exception e) {
            commonResponse.setStatusCode(500);
            commonResponse.setMessage("An error occurred while updating the customer.");
            commonResponse.setData(null);
        }
        commonResponse.setDate(LocalDateTime.now());
        return commonResponse;
    }

    public CommonResponse<Void> deleteCustomer(Long id) {
        CommonResponse<Void> commonResponse = new CommonResponse<>();
        try {
            if (customerRepository.existsById(id)) {
                customerRepository.deleteById(id);
                commonResponse.setStatusCode(204);
                commonResponse.setMessage("Customer deleted successfully");
                commonResponse.setData(null);
            } else {
                commonResponse.setStatusCode(404);
                commonResponse.setMessage("Customer not found");
                commonResponse.setData(null);
            }
        } catch (Exception e) {
            commonResponse.setStatusCode(500);
            commonResponse.setMessage("An error occurred while deleting the customer.");
            commonResponse.setData(null);
        }
        commonResponse.setDate(LocalDateTime.now());
        return commonResponse;
    }
}
