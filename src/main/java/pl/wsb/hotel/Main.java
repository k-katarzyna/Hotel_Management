package pl.wsb.hotel;

import pl.wsb.hotel.interfaces.ExcludeFromCodeCoverage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@ExcludeFromCodeCoverage
public class Main {
    public static void main(String[] args) {

        // create clients //
        Client client_1 = new Client(
                "Adam",
                "Smith",
                LocalDate.of(1995, 5, 12),
                "+12 944-738-608",
                "adam.smith@gmail.com");

        Client client_2 = new Client(
                "John",
                "Doe",
                LocalDate.of(1985, 2, 16),
                "+80 737-960-012",
                "john.doe@yahoo.com");

        // create rooms //
        Room room_1 = new Room(
                14.2,
                3,
                true,
                1,
                80.0,
                "Wi-fi, TV, air-conditioning, mini-fridge");

        Room room_2 = new Room(
                16.0,
                3,
                false,
                2,
                75.0,
                "Wi-fi, TV, air-conditioning");

        // create a reservation (1) and print details //
        RoomReservation reservation_1 = new RoomReservation(
                client_1,
                room_1,
                LocalDate.of(2024, 3, 29),
                LocalDate.of(2024,4,1),
                false,
                1,
                false);

        System.out.println("___1st reservation___\n");
        reservation_1.printFullReservationDetails();

        // create another reservation (2) and print details //
        List<Room> rooms = new ArrayList<>();
        rooms.add(room_1);
        rooms.add(room_2);

        RoomReservation reservation_2 = new RoomReservation(
                client_2,
                rooms,
                LocalDate.of(2024, 4, 28),
                LocalDate.of(2024,5,4),
                true,
                4,
                true);
        reservation_2.confirmReservation();

        System.out.println("\n___2nd reservation___\n");
        reservation_2.printFullReservationDetails();

        SpecialService s1 = new LuggageService();
        SpecialService s2 = new TimeService();

        System.out.println("\n" + "___LuggageService___");
        s1.printInformation();
        System.out.println("\n" + "___TimeService___");
        s2.printInformation();
    }
}
