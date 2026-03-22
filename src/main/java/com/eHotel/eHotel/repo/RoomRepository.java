package com.eHotel.eHotel.repo;
import com.eHotel.eHotel.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Integer> {

    @Query(value = """
         SELECT r.* FROM room r JOIN hotel h ON r.hotel_id = h.hotel_id WHERE (:city IS NULL OR :city = '' OR h.city = :city) AND (:capacity IS NULL OR :capacity = '' OR r.capacity = :capacity) AND (:minPrice IS NULL OR r.price >= :minPrice) AND (:maxPrice IS NULL OR r.price<=:maxPrice) 
         """, nativeQuery = true)
         
    List<Room> searchRooms(@Param("city") String city, @Param("capacity") String capacity, @Param("minPrice") Double minPrice, @Param("maxPrice") Double maxPrice);

}
