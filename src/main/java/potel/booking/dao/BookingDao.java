package potel.booking.dao;

import java.util.List;

import potel.booking.vo.RoomType;

public interface BookingDao {

	List<RoomType> findAllRoomType();
	
	List<RoomType> findRoomTypeById(int roomTypeId);

}
