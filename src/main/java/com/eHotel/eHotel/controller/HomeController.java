package com.eHotel.eHotel.controller;
import com.eHotel.eHotel.model.Room;
import com.eHotel.eHotel.repo.RoomRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {

    private final RoomRepository roomRepository;

    public HomeController(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/search")
    public String searchPage(@RequestParam(required = false) String city, @RequestParam(required = false) String capacity, @RequestParam(required = false) Double minPrice, @RequestParam(required = false) Double maxPrice, Model model) {

        System.out.println("City: "+city);
        System.out.println("Capacity: "+capacity);
        System.out.println("Lowest Price: "+minPrice);
        System.out.println("Highest Price: "+maxPrice);
        List<Room> rooms;

        if (capacity!=null && !capacity.isEmpty()) {
            rooms = roomRepository.findByCapacity(capacity); //CAPACITY SPECIFIED BY USER
        } else {
            rooms = roomRepository.findAll(); //NO CAPACITY SPECIFIED, RETURNING ALL
        }

        model.addAttribute("rooms", rooms);
        return "search";
    }

}
