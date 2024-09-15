package com.abcrestaurant.abcrestaurant.Response;



import java.time.LocalDateTime;

import com.abcrestaurant.abcrestaurant.Entity.Admin;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommonResponse<T> {
    private String message;
    private LocalDateTime date;
    private int statusCode;
    private T data;
}
