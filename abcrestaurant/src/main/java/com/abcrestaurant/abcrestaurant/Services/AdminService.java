package com.abcrestaurant.abcrestaurant.Services;

import com.abcrestaurant.abcrestaurant.Entity.Admin;
import com.abcrestaurant.abcrestaurant.Repository.AdminRepository;
import com.abcrestaurant.abcrestaurant.Repository.UserRepository;
import com.abcrestaurant.abcrestaurant.Response.CommonResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private UserRepository UserRepository;

    public CommonResponse<List<Admin>> getAllAdmins() {
        CommonResponse<List<Admin>> commonResponse = new CommonResponse<>();
        try {
            List<Admin> admins = adminRepository.findAll();
            if (admins != null && !admins.isEmpty()) {
                commonResponse.setStatusCode(HttpStatus.OK.value());
                commonResponse.setMessage("Admins retrieved successfully");
                commonResponse.setData(admins);
            } else {
                commonResponse.setStatusCode(HttpStatus.NO_CONTENT.value());
                commonResponse.setMessage("No admins found");
                commonResponse.setData(null);
            }
        } catch (Exception e) {
            commonResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            commonResponse.setMessage("An error occurred while retrieving admins.");
            commonResponse.setData(null);
        }
        commonResponse.setDate(LocalDateTime.now());
        return commonResponse;
    }


    public CommonResponse<Admin> getAdminById(Long id) {
        CommonResponse<Admin> commonResponse = new CommonResponse<>();
        try {
            Optional<Admin> optionalAdmin = adminRepository.findById(id);
            if (optionalAdmin != null) {
                commonResponse.setStatusCode(HttpStatus.OK.value());
                commonResponse.setMessage("Admin retrieved successfully");
                commonResponse.setData(optionalAdmin.get());
            } else {
                commonResponse.setStatusCode(HttpStatus.NO_CONTENT.value());
                commonResponse.setMessage("No admin found with the given ID");
                commonResponse.setData(null);
            }
        } catch (Exception e) {
            commonResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            commonResponse.setMessage("An error occurred while retrieving the admin.");
            commonResponse.setData(null);
        }
        commonResponse.setDate(LocalDateTime.now());
        return commonResponse;
    }
    

    public CommonResponse<Admin> createAdmin(Admin admin) {
        Admin createdAdmin = adminRepository.save(admin);
        
        CommonResponse<Admin> commonResponse = new CommonResponse<>();
        LocalDateTime now = LocalDateTime.now();

        if (createdAdmin != null) {
            commonResponse.setStatusCode(200);
            commonResponse.setMessage("Admin created successfully");
            commonResponse.setData(createdAdmin);
        } else {
            commonResponse.setMessage("Failed to create admin");
            commonResponse.setData(null);
            commonResponse.setStatusCode(500);
        }
        commonResponse.setDate(now);

        return commonResponse;
    }

    public CommonResponse<Admin> updateAdmin(Long id, Admin adminDetails) {
        CommonResponse<Admin> commonResponse = new CommonResponse<>();
        LocalDateTime now = LocalDateTime.now();

        if (adminRepository.existsById(id)) {
            adminDetails.setId(id);
            Admin updatedAdmin = adminRepository.save(adminDetails);

            commonResponse.setStatusCode(200);
            commonResponse.setMessage("Admin updated successfully");
            commonResponse.setData(updatedAdmin);
        } else {
            commonResponse.setStatusCode(404);
            commonResponse.setMessage("Admin not found");
            commonResponse.setData(null);
        }
        commonResponse.setDate(now);

        return commonResponse;
    }

    public CommonResponse<Admin> deleteAdmin(Long id) {
        CommonResponse<Admin> commonResponse = new CommonResponse<>();
        LocalDateTime now = LocalDateTime.now();

        if (adminRepository.existsById(id)) {
            adminRepository.deleteById(id);
            commonResponse.setStatusCode(200);
            commonResponse.setMessage("Admin deleted successfully");
        } else {
            commonResponse.setStatusCode(404);
            commonResponse.setMessage("Admin not found");
        }
        commonResponse.setDate(now);

        return commonResponse;
    }
}
