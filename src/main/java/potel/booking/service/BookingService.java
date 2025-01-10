package potel.booking.service;

import java.util.List;


import potel.booking.vo.RoomType;

public interface BookingService {

	List<RoomType> findRoomType();

	List<RoomType> findRoomTypeById(int roomTypeId);

}
