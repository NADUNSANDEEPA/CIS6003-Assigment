package com.abcrestaurant.abcrestaurant.Repository;

import com.abcrestaurant.abcrestaurant.Entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    // Insert Admin
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO admin (user_id, full_name, email, phone, created_at, updated_at) VALUES (?1, ?2, ?3, ?4, ?5, ?6)", nativeQuery = true)
    void insertAdmin(String fullName, String email, String phone, Long userId, Timestamp createdAt, Timestamp updatedAt);

    // Find Admin by ID
    @Query(value = "SELECT * FROM admin WHERE id = ?1", nativeQuery = true)
    Admin findAdminById(Long id);

    // Update Admin
    @Modifying
    @Transactional
    @Query(value = "UPDATE admin SET full_name = ?2, email = ?3, phone = ?4, updated_at = ?5 WHERE id = ?1", nativeQuery = true)
    void updateAdmin(Long id, String fullName, String email, String phone, Timestamp updatedAt);

    // Delete Admin by ID
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM admin WHERE id = ?1", nativeQuery = true)
    void deleteAdminById(Long id);
}
