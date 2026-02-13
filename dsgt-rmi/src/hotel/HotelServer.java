package hotel;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.util.Set;

public class HotelServer implements HotelInterface {
    private BookingManager bookingManager;

    public static void main(String[] args) throws Exception {
        try{
            HotelServer hotelServer = new HotelServer();
            HotelInterface stub = (HotelInterface) UnicastRemoteObject.exportObject(hotelServer, 0);

            Registry registry = LocateRegistry.getRegistry();
            registry.bind("HotelServer", stub);

            System.err.println("Server ready");
        } catch(Exception e){
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }

    public HotelServer() {
        bookingManager = new BookingManager();
    }

    @Override
    public Set<Integer> getAllRooms() throws RemoteException {
        return bookingManager.getAllRooms();
    }

    @Override
    public boolean isRoomAvailable(Integer roomNumber, LocalDate date) throws RemoteException {
        return bookingManager.isRoomAvailable(roomNumber, date);
    }

    @Override
    public void addBooking(BookingDetail bookingDetail) throws RemoteException {
        bookingManager.addBooking(bookingDetail);
    }

    @Override
    public Set<Integer> getAvailableRooms(LocalDate date) throws RemoteException {
        return bookingManager.getAvailableRooms(date);
    }
}
