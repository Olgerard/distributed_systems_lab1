package staff;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.LocalDate;
import java.util.Set;

import hotel.BookingDetail;
import hotel.BookingInterface;

public class BookingClient extends AbstractScriptedSimpleTest {

	private BookingInterface bm = null;

	public static void main(String[] args) throws Exception {
		BookingClient client = new BookingClient();
		client.run();
	}

	/***************
	 * CONSTRUCTOR *
	 ***************/
	public BookingClient() {
		try {
			Registry registry = LocateRegistry.getRegistry(null);
			BookingInterface stub = (BookingInterface) registry.lookup("HotelServer");
			bm = stub;
			System.out.println("Server found by client");
		} catch (Exception e) {
			System.err.println("Client exception: " + e.toString());
			e.printStackTrace();
		}
	}

	@Override
	public boolean isRoomAvailable(Integer roomNumber, LocalDate date) {
		try {
			return bm.isRoomAvailable(roomNumber, date);
		} catch (Exception e) {
			System.err.println("Client method (isRoomAvailable) exception: " + e.toString());
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public void addBooking(BookingDetail bookingDetail) throws Exception {
		try {
			bm.addBooking(bookingDetail);
		} catch (Exception e) {
			System.err.println("Client method (addBooking) exception: " + e.toString());
			e.printStackTrace();
		}
	}

	@Override
	public Set<Integer> getAvailableRooms(LocalDate date) {
		try {
			return bm.getAvailableRooms(date);
		} catch (Exception e) {
			System.err.println("Client method (getAvailableRooms) exception: " + e.toString());
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Set<Integer> getAllRooms() {
		try {
			return bm.getAllRooms();
		} catch (Exception e) {
			System.err.println("Client method (getAllRooms) exception: " + e.toString());
			e.printStackTrace();
			return null;
		}
	}
}
