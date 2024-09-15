package com.abcrestaurant.abcrestaurant.Repository;

import com.abcrestaurant.abcrestaurant.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Insert User
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO users (username, password, role, created_at, updated_at) VALUES (?1, ?2, ?3, ?4, ?5)", nativeQuery = true)
    void insertUser(String username, String password, String role, Timestamp createdAt, Timestamp updatedAt);

    // Find User by ID
    @Query(value = "SELECT * FROM users WHERE id = ?1", nativeQuery = true)
    User findUserById(Long id);

    // Find User by Username
    @Query(value = "SELECT * FROM users WHERE username = ?1", nativeQuery = true)
    User findUserByUsername(String username);

    // Update User
    @Modifying
    @Transactional
    @Query(value = "UPDATE users SET password = ?2, role = ?3, updated_at = ?4 WHERE id = ?1", nativeQuery = true)
    void updateUser(Long id, String password, String role, Timestamp updatedAt);

    // Delete User by ID
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM users WHERE id = ?1", nativeQuery = true)
    void deleteUserById(Long id);
}
