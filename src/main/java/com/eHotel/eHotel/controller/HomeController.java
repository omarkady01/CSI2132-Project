package com.eHotel.eHotel.controller;
import com.eHotel.eHotel.repo.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.eHotel.eHotel.model.SearchFilter;
import java.util.*;
import org.springframework.web.bind.support.SessionStatus;
import com.eHotel.eHotel.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Controller
@SessionAttributes("searchFilter")
public class HomeController {

    private final RoomRepository roomRepository;
    private final CustomerRepository customerRepository;
    private final BookingRepository bookingRepository;

    public HomeController(RoomRepository roomRepository, CustomerRepository customerRepository, BookingRepository bookingRepository) {
        this.roomRepository = roomRepository;
        this.customerRepository = customerRepository;
        this.bookingRepository = bookingRepository;
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

    System.out.println("After city step:");
    System.out.println("City = " + searchFilter.getCity());
    System.out.println("Capacity = " + searchFilter.getCapacity());
    System.out.println("Min Price = " + searchFilter.getMinPrice());
    System.out.println("Max Price = " + searchFilter.getMaxPrice());    



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

    System.out.println("After capacity step:");
    System.out.println("City = " + searchFilter.getCity());
    System.out.println("Capacity = " + searchFilter.getCapacity());
    System.out.println("Min Price = " + searchFilter.getMinPrice());
    System.out.println("Max Price = " + searchFilter.getMaxPrice());

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

    System.out.println("After price step:");
    System.out.println("City = " + searchFilter.getCity());
    System.out.println("Capacity = " + searchFilter.getCapacity());
    System.out.println("Min Price = " + searchFilter.getMinPrice());
    System.out.println("Max Price = " + searchFilter.getMaxPrice());


        return "redirect:/quiz/dates";
    }

    @PostMapping("/quiz/price/skip")
    public String skipPriceRange(@ModelAttribute("searchFilter") SearchFilter searchFilter) {
        searchFilter.setMinPrice(null);
        searchFilter.setMaxPrice(null);
        return "redirect:/quiz/dates";
    }

    @GetMapping("/quiz/results")
    public String quizResults(@ModelAttribute("searchFilter") SearchFilter searchFilter, Model model) {
        List<Room> rooms = roomRepository.searchRooms(searchFilter.getCity(), searchFilter.getCapacity(), searchFilter.getMinPrice(), searchFilter.getMaxPrice(), searchFilter.getCheckInDate(), searchFilter.getCheckOutDate());
        
        model.addAttribute("rooms", rooms);
        model.addAttribute("searchFilter", searchFilter);
        return "quiz-results";
    }

    @GetMapping("/quiz/dates")
    public String datesStep(Model model){
        model.addAttribute("today", java.time.LocalDate.now().toString());
        return "quiz-dates";
    }

    @PostMapping("/quiz/dates")
    public String saveDates(@RequestParam String checkInDate, @RequestParam String checkOutDate, @ModelAttribute("searchFilter") SearchFilter searchFilter, Model model) {
        
        java.time.LocalDate today = java.time.LocalDate.now();
        java.time.LocalDate checkIn = java.time.LocalDate.parse(checkInDate);
        java.time.LocalDate checkOut = java.time.LocalDate.parse(checkOutDate);

        if (checkIn.isBefore(today)) {
            model.addAttribute("today", today.toString());
            model.addAttribute("errorMessage", "Check in date Cannot be in the Past!");
            return "quiz-dates";
        }

        if (checkOut.isBefore(checkIn)) {
            model.addAttribute("today", today.toString());
            model.addAttribute("errorMessage", "Check out Date Must be After Check in Date!");
            return "quiz-dates";
        }

        searchFilter.setCheckInDate(checkInDate);
        searchFilter.setCheckOutDate(checkOutDate);

        return "redirect:/quiz/results";
    }

    @PostMapping("/quiz/dates/skip")
    public String skipDates(@ModelAttribute("searchFilter") SearchFilter searchFilter) {
        searchFilter.setCheckInDate(null);
        searchFilter.setCheckOutDate(null);
        return "redirect:/quiz/results";
    }

    // RESETTING VALUES STORED IN THE SESSION

    @GetMapping("/quiz/reset")
    public String resetQuiz(SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return "redirect:/quiz/city";
    }

    // BOOKING HANDELING

    @GetMapping("/booking")
    public String bookingPage(@RequestParam Integer roomId, @ModelAttribute("searchFilter") SearchFilter searchFilter, Model model) {

        Optional<Room> roomOptional = roomRepository.findById(roomId);

        if(roomOptional.isEmpty()) {
            return "redirect:/quiz/results";
        }

        model.addAttribute("room", roomOptional.get());
        model.addAttribute("searchFilter", searchFilter);

        return "booking";
    }



}
