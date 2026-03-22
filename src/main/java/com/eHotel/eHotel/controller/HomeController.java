package com.eHotel.eHotel.controller;
import com.eHotel.eHotel.model.Room;
import com.eHotel.eHotel.repo.RoomRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.eHotel.eHotel.model.SearchFilter;
import java.util.List;

@Controller
@SessionAttributes("searchFilter")
public class HomeController {

    private final RoomRepository roomRepository;

    public HomeController(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @ModelAttribute("searchFilter")
    public SearchFilter searchFilter() {
        return new SearchFilter();
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/quiz/city")
    public String cityStep() {
        return "quiz-city";
    }

    @PostMapping("/quiz/city")
    public String saveCity(@RequestParam String city, @ModelAttribute("searchFilter") SearchFilter searchFilter) {
        searchFilter.setCity(city);
        return "redirect:/quiz/capacity";
    }

    @PostMapping("/quiz/city/skip")
    public String skipCity(@ModelAttribute("searchFilter") SearchFilter searchFilter) {
            searchFilter.setCity(null);
            return "redirect:/quiz/capacity";
    }

    @GetMapping("/quiz/capacity")
    public String capacityStep() {
        return "quiz-capacity";
    }

    @PostMapping("/quiz/capacity")
    public String saveCapacity(@RequestParam String capacity, @ModelAttribute("searchFilter") SearchFilter searchFilter) {
        searchFilter.setCapacity(capacity);
        return "redirect:/quiz/price";
    }

    @PostMapping("/quiz/capacity/skip")
    public String skipCapacity(@ModelAttribute("searchFilter") SearchFilter searchFilter) {
        searchFilter.setCapacity(null);
        return "redirect:/quiz/price";
    }

    @GetMapping("/quiz/price")
    public String priceStep() {
        return "quiz-price";
    }

    @PostMapping("/quiz/price")
    public String savePriceRange(@RequestParam Double minPrice, @RequestParam Double maxPrice, @ModelAttribute("searchFilter") SearchFilter searchFilter) {
       
        if (minPrice > maxPrice) {
            Double temp = minPrice;
            minPrice = maxPrice;
            maxPrice = temp;
        }
       
        searchFilter.setMinPrice(minPrice);
        searchFilter.setMaxPrice(maxPrice);
        return "redirect:/quiz/results";
    }

    @PostMapping("/quiz/price/skip")
    public String skipPriceRange(@ModelAttribute("searchFilter") SearchFilter searchFilter) {
        searchFilter.setMinPrice(null);
        searchFilter.setMaxPrice(null);
        return "redirect:/quiz/results";
    }

    /*@GetMapping("/search")
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
    }*/

}
