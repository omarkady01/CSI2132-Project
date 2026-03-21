package com.eHotel.eHotel.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/search")
    public String searchPage(@RequestParam(required = false) String city, @RequestParam(required = false) String capacity, @RequestParam(required = false) Double minPrice, @RequestParam(required = false) Double maxPrice) {

        System.out.println("City: "+city);
        System.out.println("Capacity: "+capacity);
        System.out.println("Lowest Price: "+minPrice);
        System.out.println("Highest Price: "+maxPrice);
        return "search";
    }

}
