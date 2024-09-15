package com.abcrestaurant.abcrestaurant.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Table(name = "food_menu")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FoodMenu {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "item_name", nullable = false, length = 100)
    private String itemName;

    @Column(name = "description")
    private String description;

    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "category", length = 50)
    private String category;

    @Enumerated(EnumType.STRING)
    @Column(name = "availability", columnDefinition = "ENUM('AVAILABLE', 'UNAVAILABLE') DEFAULT 'AVAILABLE'")
    private Availability availability;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Enum for availability status
    public enum Availability {
        AVAILABLE,
        UNAVAILABLE
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        if (this.availability == Availability.AVAILABLE) {
            this.updatedAt = LocalDateTime.now();
        }
    }
}
