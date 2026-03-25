package com.eHotel.eHotel.repo;
import com.eHotel.eHotel.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Integer> {

    @Query(value = """
         SELECT r.* FROM room r JOIN hotel h ON r.hotel_id = h.hotel_id WHERE (:city IS NULL OR :city = '' OR LOWER(h.city) = LOWER(:city)) AND (:capacity IS NULL OR :capacity = '' OR r.capacity = :capacity) AND (:minPrice IS NULL OR r.price >= :minPrice) AND (:maxPrice IS NULL OR r.price <= :maxPrice) AND (:checkInDate IS NULL OR :checkOutDate IS NULL OR r.room_id NOT IN (SELECT b.room_id FROM booking b WHERE b.status = 'Booked' AND CAST(:checkInDate AS date) < b.end_date AND CAST(:checkOutDate AS date) > b.start_date)) AND (:checkInDate IS NULL OR :checkOutDate IS NULL OR r.room_id NOT IN (SELECT rt.room_id FROM renting rt WHERE rt.status = 'Active' AND CAST(:checkInDate as date) < rt.check_out_date AND CAST(:checkOutDate AS date) > rt.check_in_date))
         """, nativeQuery = true)
         
    List<Room> searchRooms(@Param("city") String city, @Param("capacity") String capacity, @Param("minPrice") Double minPrice, @Param("maxPrice") Double maxPrice, @Param("checkInDate") String checkInDate, @Param("checkOutDate") String checkOutDate);

}
