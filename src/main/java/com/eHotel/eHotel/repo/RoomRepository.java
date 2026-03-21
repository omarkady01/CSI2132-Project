package com.eHotel.eHotel.repo;
import com.eHotel.eHotel.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Integer> {

    List<Room> findByCapacity(String capacity);

}
