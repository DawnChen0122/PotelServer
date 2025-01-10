package potel.booking.service.impl;

import java.util.List;

import javax.naming.NamingException;

import potel.booking.dao.BookingDao;
import potel.booking.dao.Impl.BookingDaoImpl;
import potel.booking.service.BookingService;
import potel.booking.vo.RoomType;


public class BookingServiceImpl implements BookingService{

	private BookingDao bookingDao;
	
	public BookingServiceImpl() throws NamingException{
		bookingDao = new BookingDaoImpl();
	}
	
	
	@Override
	public List<RoomType> findRoomType() {
		
		return bookingDao.findAllRoomType();
	}


	@Override
	public List<RoomType> findRoomTypeById(int roomTypeId) {
		
		return bookingDao.findRoomTypeById(roomTypeId);
	}


	@Override
	public byte[] findImageDataById(int imageId) {
		return bookingDao.findImageDataById(imageId); // 調用 DAO 方法獲取圖片數據
	}

	

}
