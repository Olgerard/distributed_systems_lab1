package hotel;

import java.time.LocalDate;
import java.util.*;

public class BookingManager implements BookingInterface{

	private Room[] rooms;

	public BookingManager() {
		this.rooms = initializeRooms();
	}

	public Set<Integer> getAllRooms() {
		Set<Integer> allRooms = new HashSet<Integer>();
		Iterable<Room> roomIterator = Arrays.asList(rooms);
		for (Room room : roomIterator) {
			allRooms.add(room.getRoomNumber());
		}
		return allRooms;
	}

	public boolean isRoomAvailable(Integer roomNumber, LocalDate date) {
		for (Room room : rooms){
			if (room.getRoomNumber().equals(roomNumber)){
				for(BookingDetail bookingDetail :  room.getBookings()){
					if (bookingDetail.getDate().equals(date)){
						return false;
					}
				}
			}
		}
		return true;
	}

	public void addBooking(BookingDetail bookingDetail) {
		int roomNumber = bookingDetail.getRoomNumber();
		for  (Room room : rooms){
			if  (room.getRoomNumber().equals(roomNumber)){
				room.getBookings().add(bookingDetail);
				return;
			}
		}
	}

	public Set<Integer> getAvailableRooms(LocalDate date) {
		Set<Integer> free_rooms = new HashSet<>();
		for (Room room : rooms){
			boolean free = true;
			for(BookingDetail bookingDetail :  room.getBookings()){
                if (bookingDetail.getDate().equals(date)) {
                    free = false;
                    break;
                }
			}
			if (free){
				free_rooms.add(room.getRoomNumber());
			}
		}
		return free_rooms;
	}

	private static Room[] initializeRooms() {
		Room[] rooms = new Room[4];
		rooms[0] = new Room(101);
		rooms[1] = new Room(102);
		rooms[2] = new Room(201);
		rooms[3] = new Room(203);
		return rooms;
	}
}
