package src.main.java.pl.wsb.hotel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // create clients //
        Client client_1 = new Client(
                "001",
                "Adam",
                "Smith",
                LocalDate.of(1995, 5, 12),
                "+12 944-738-608",
                "adam.smith@gmail.com",
                1,
                false);

        Client client_2 = new Client(
                "002",
                "John",
                "Doe",
                LocalDate.of(1985, 2, 16),
                "+80 737-960-012",
                "john.doe@yahoo.com",
                4,
                true);

        // create rooms //
        Room room_1 = new Room(
                "01",
                14.2,
                3,
                true,
                1,
                80.0,
                "Wi-fi, TV, air-conditioning, mini-fridge");

        Room room_2 = new Room(
                "02",
                16.0,
                3,
                false,
                2,
                75.0,
                "Wi-fi, TV, air-conditioning");

        // create a reservation (1) and print details //
        RoomReservation reservation_1 = new RoomReservation(
                LocalDate.now(),
                client_1,
                room_1,
                LocalDate.of(2024, 3, 29),
                LocalDate.of(2024, 4, 1),
                false);

        System.out.println("1st reservation:");
        reservation_1.getReservationDetails();
        System.out.println();

        // create another reservation (2) and print details //
        List<Room> rooms = new ArrayList<>();
        rooms.add(room_1);
        rooms.add(room_2);

        RoomReservation reservation_2 = new RoomReservation(
                LocalDate.now(),
                client_2,
                rooms,
                LocalDate.of(2024, 4, 28),
                LocalDate.of(2024, 5, 4),
                true);

        reservation_2.confirmReservation();

        System.out.println("2nd reservation:");
        reservation_2.getReservationDetails();
    }
}
