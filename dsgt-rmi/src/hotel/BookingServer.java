package hotel;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.util.Set;

public class BookingServer {
    public static void main(String[] args) throws Exception {
        try{
            BookingManager bookingManager = new BookingManager();
            BookingInterface stub = (BookingInterface) UnicastRemoteObject.exportObject(bookingManager, 0);

            Registry registry = LocateRegistry.getRegistry();
            registry.bind("HotelServer", stub);

            System.err.println("Server ready");
        } catch(Exception e){
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}