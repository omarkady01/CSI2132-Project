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
import com.eHotel.eHotel.model.Hotel;
import com.eHotel.eHotel.repo.HotelRepository;
import com.eHotel.eHotel.model.Booking;
import com.eHotel.eHotel.model.Employee;
import com.eHotel.eHotel.model.Renting;
import com.eHotel.eHotel.model.Payment;
import com.eHotel.eHotel.repo.EmployeeRepository;
import com.eHotel.eHotel.repo.RentingRepository;
import com.eHotel.eHotel.repo.PaymentRepository;


@Controller
@SessionAttributes("searchFilter")
public class HomeController {

    private final RoomRepository roomRepository;
    private final CustomerRepository customerRepository;
    private final BookingRepository bookingRepository;
    private final HotelRepository hotelRepository;
    private final EmployeeRepository employeeRepository;
    private final RentingRepository rentingRepository;
    private final PaymentRepository paymentRepository;


    public HomeController(RoomRepository roomRepository, CustomerRepository customerRepository, BookingRepository bookingRepository, HotelRepository hotelRepository, EmployeeRepository employeeRepository, RentingRepository rentingRepository, PaymentRepository paymentRepository) {
        this.roomRepository = roomRepository;
        this.customerRepository = customerRepository;
        this.bookingRepository = bookingRepository;
        this.hotelRepository = hotelRepository;
        this.employeeRepository = employeeRepository;
        this.rentingRepository = rentingRepository;
        this.paymentRepository = paymentRepository;
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

        searchFilter.setDateNull(true);
        LocalDate checkIn = LocalDate.now();
        LocalDate checkOut = checkIn.plusDays(1);

        searchFilter.setCheckInDate(checkIn.toString());
        searchFilter.setCheckOutDate(checkOut.toString());
            

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

        Room room = roomOptional.get();

        Optional<Hotel> hotelOptional = hotelRepository.findById(room.getHotelId());

        if (hotelOptional.isEmpty()) {
            return "redirect:/quiz/results";
        }

        model.addAttribute("room", room);
        model.addAttribute("searchFilter", searchFilter);
        model.addAttribute("hotel", hotelOptional.get());

        boolean usedDefaultDates = searchFilter.getDateNull();
        LocalDate checkIn;
        LocalDate checkOut;

        if(usedDefaultDates == true) {
            checkIn = LocalDate.now();
            checkOut = checkIn.plusDays(1);
        } else {
            checkIn = LocalDate.parse(searchFilter.getCheckInDate());
            checkOut = LocalDate.parse(searchFilter.getCheckOutDate());
        }

        model.addAttribute("usedDefaultDates", usedDefaultDates);
        model.addAttribute("autoCheckIn", checkIn.toString());
        model.addAttribute("autoCheckOut", checkOut.toString());
        return "booking";
    }

    @PostMapping("/booking/confirm")
    public String confirmBooking(@RequestParam Integer roomId, @RequestParam String custName, @RequestParam String address, @RequestParam String idType, @RequestParam String idNumber, @ModelAttribute("searchFilter") SearchFilter searchFilter, Model model) {

        LocalDate checkIn;
        LocalDate checkOut;

        if(searchFilter.getCheckInDate() == null || searchFilter.getCheckOutDate() == null) {
            checkIn = LocalDate.now();
            checkOut = checkIn.plusDays(1);

        } else {
            checkIn = LocalDate.parse(searchFilter.getCheckInDate());
            checkOut = LocalDate.parse(searchFilter.getCheckOutDate());
        }

        System.out.println(checkIn.toString());
        System.out.println(checkOut.toString());


        Optional<Customer> existingCustomer = customerRepository.findByIdNumber(idNumber);
        Customer customer;

        if (existingCustomer.isPresent()) {
            customer = existingCustomer.get();
        } else {
            customer = new Customer();
            customer.setCustName(custName);
            customer.setAddress(address);
            customer.setIdType(idType);
            customer.setIdNumber(idNumber);
            customer.setRegistrationDate(LocalDate.now());
            customer = customerRepository.save(customer);
        }

        model.addAttribute("customerName", customer.getCustName());
        model.addAttribute("checkInDate", checkIn.toString());
        model.addAttribute("checkOutDate", checkOut.toString());

        Booking booking = new Booking();
        booking.setCustomerId(customer.getCustomerId());
        booking.setRoomId(roomId);
        booking.setStartDate(checkIn);
        booking.setEndDate(checkOut);
        booking.setBookingDate(LocalDateTime.now());
        booking.setStatus("Booked");
        bookingRepository.save(booking);


        Optional<Room> roomOptional = roomRepository.findById(roomId);

        if(roomOptional.isPresent()) {
            Room room = roomOptional.get();
            model.addAttribute("room", room);
            model.addAttribute("hotel", room.getHotel());
        }

        long nights = java.time.temporal.ChronoUnit.DAYS.between(checkIn, checkOut);
        double totalPrice = 0.0;

        if(roomOptional.isPresent()) {
            totalPrice = roomOptional.get().getPrice()*nights;
        }
        
        model.addAttribute("nights", nights);
        model.addAttribute("totalPrice", totalPrice);

        model.addAttribute("message", "Booking Confirmed, Enjoy your Stay "+customer.getCustName()+"!");

        return "booking-confirmed";

    }

    // EMPLOYEE SECTION

    @GetMapping("/employee/bookings")
    public String employeeBookings(Model model) {
        List<Booking> bookings = bookingRepository.findByStatus("Booked");
        List<Employee> employees = employeeRepository.findAll();

         System.out.println("Bookings size = " + bookings.size());
    System.out.println("Employees size = " + employees.size());

        model.addAttribute("bookings", bookings);
        model.addAttribute("employees", employees);

        return "employee-bookings";
    }

    @PostMapping("/employee/convert-to-renting")
    public String convertToRenting(@RequestParam Integer bookingId, @RequestParam Integer employeeId, Model model) {

        Optional<Booking> bookingOptional = bookingRepository.findById(bookingId);

        if(bookingOptional.isEmpty()) {
            model.addAttribute("message", "Booking Not Found!");
            return "employee-result";
        }

        Booking booking = bookingOptional.get();

        Renting renting = new Renting();
        renting.setBookingId(booking.getBookingId());
        renting.setCustomerId(booking.getCustomerId());
        renting.setRoomId(booking.getRoomId());
        renting.setEmployeeId(employeeId);
        renting.setCheckInDate(booking.getStartDate());
        renting.setCheckOutDate(booking.getEndDate());
        renting.setRentingDate(LocalDateTime.now());
        renting.setStatus("Active");

        renting = rentingRepository.saveAndFlush(renting);

        booking.setStatus("Converted");
        bookingRepository.save(booking);

        model.addAttribute("message", "Booking has been Converted to Renting!");
        model.addAttribute("rentingId", renting.getRentingId());

        return "employee-result";
    }

    @GetMapping("/employee/payment")
    public String paymentPage(@RequestParam Integer rentingId, Model model) {
        model.addAttribute("rentingId", rentingId);
        return "employee-payment";
    }

    @PostMapping("/employee/payment")
    public String savePayment(@RequestParam Integer rentingId, @RequestParam Double amount, @RequestParam String paymentMethod, Model model) {
        Payment payment = new Payment();
        payment.setRentingId(rentingId);
        payment.setAmount(amount);
        payment.setPaymentMethod(paymentMethod);
        payment.setPaymentDate(LocalDateTime.now());

        paymentRepository.save(payment);

        model.addAttribute("message", "Payment Added Successfully!");

        return "employee-result";
    }



}
