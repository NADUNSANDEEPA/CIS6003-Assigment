package com.abcrestaurant.abcrestaurant.Controller;

import com.abcrestaurant.abcrestaurant.Entity.Admin;
import com.abcrestaurant.abcrestaurant.Response.CommonResponse;
import com.abcrestaurant.abcrestaurant.Services.AdminService;
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
@RequestMapping("/admins")
@Tag(name = "Admin Controller", description = "APIs for managing admin accounts")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/getAllAdmin")
    @Operation(summary = "Get All Admins", description = "Retrieve a list of all admin accounts")
    @ApiResponse(responseCode = "200", description = "List of admins retrieved successfully")
    public ResponseEntity<CommonResponse<List<Admin>>> getAllAdmins() {
        CommonResponse<List<Admin>> commonResponse = adminService.getAllAdmins();
    
        if (commonResponse != null) {
            HttpStatus status = HttpStatus.valueOf(commonResponse.getStatusCode());
            
            if (commonResponse.getData() == null || commonResponse.getData().isEmpty()) {
                commonResponse.setMessage("No admins found.");
                commonResponse.setStatusCode(HttpStatus.NO_CONTENT.value());
                commonResponse.setDate(LocalDateTime.now());
                return new ResponseEntity<>(commonResponse, HttpStatus.NO_CONTENT);
            }
            
            return new ResponseEntity<>(commonResponse, status);
        } else {
            CommonResponse<List<Admin>> errorResponse = new CommonResponse<>();
            errorResponse.setMessage("An error occurred while retrieving admins.");
            errorResponse.setData(null);
            errorResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            errorResponse.setDate(LocalDateTime.now());
    
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAdminAcc/{id}")
    @Operation(summary = "Get Admin by ID", description = "Retrieve a specific admin account by its ID")
    public ResponseEntity<CommonResponse<Admin>> getAdminById(@PathVariable Long id) {
        CommonResponse<Admin> response = adminService.getAdminById(id);
        if (response != null) {
            HttpStatus status = HttpStatus.valueOf(response.getStatusCode());
            return new ResponseEntity<>(response, status);
        } else {
            CommonResponse<Admin> noDataResponse = new CommonResponse<>();
            noDataResponse.setMessage("Admin not found.");
            noDataResponse.setData(null);
            noDataResponse.setStatusCode(HttpStatus.NOT_FOUND.value()); 
            noDataResponse.setDate(LocalDateTime.now());
            
            return new ResponseEntity<>(noDataResponse, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/saveAdmin")
    @Operation(summary = "Create Admin", description = "Create a new admin account")
    public ResponseEntity<CommonResponse<Admin>> createAdmin(@RequestBody Admin admin) {
        try {
            CommonResponse<Admin> response = adminService.createAdmin(admin);
            return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatusCode()));
        } catch (Exception e) {
            CommonResponse<Admin> errorResponse = new CommonResponse<>();
            errorResponse.setMessage("An error occurred while creating the admin.");
            errorResponse.setData(null);
            errorResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value()); 
            errorResponse.setDate(LocalDateTime.now());
            
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateAdmin/{id}")
    @Operation(summary = "Update Admin", description = "Update the details of an existing admin account by its ID")
    public ResponseEntity<CommonResponse<Admin>> updateAdmin(@PathVariable Long id, @RequestBody Admin adminDetails) {
        try {
            CommonResponse<Admin> response = adminService.updateAdmin(id, adminDetails);
            return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatusCode()));
        } catch (Exception e) {
            CommonResponse<Admin> errorResponse = new CommonResponse<>();
            errorResponse.setMessage("An error occurred while updating the admin.");
            errorResponse.setData(null);
            errorResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value()); 
            errorResponse.setDate(LocalDateTime.now());
            
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/removeAdmin/{id}")
    @Operation(summary = "Delete Admin", description = "Delete an admin account by its ID")
    public ResponseEntity<CommonResponse<Admin>> deleteAdmin(@PathVariable Long id) {
        try {
            CommonResponse<Admin> response = adminService.deleteAdmin(id);
            return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatusCode()));
        } catch (Exception e) {
            CommonResponse<Admin> errorResponse = new CommonResponse<>();
            errorResponse.setMessage("An error occurred while deleting the admin.");
            errorResponse.setData(null);
            errorResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value()); 
            errorResponse.setDate(LocalDateTime.now());
            
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
