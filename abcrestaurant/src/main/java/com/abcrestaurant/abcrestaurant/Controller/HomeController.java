package com.abcrestaurant.abcrestaurant.Controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class HomeController {

     @GetMapping("/HOME")
     public String getMethodName(@RequestParam String param) {
         return new String();
     }
}
