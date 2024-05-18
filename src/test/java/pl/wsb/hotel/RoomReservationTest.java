package pl.wsb.hotel;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RoomReservationTest {

    Client client1 = new PremiumClient(
            "Harry",
            "Potter",
            LocalDate.of(1991, 7, 30),
            "+496251234",
            "harry.potter@hogwart.uk",
            PremiumClient.PremiumAccountType.PREMIUM_PLUS);
    Client client2 = new Client(
            "John",
            "Doe",
            LocalDate.of(1988, 2, 3),
            "","");
    Room room1 = new Room(
            14.2,
            3,
            true,
            1,
            80.0,
            "Wi-fi, TV, air-conditioning, mini-fridge");
    Room room2 = new Room(
            16.0,
            3,
            false,
            2,
            75.0,
            "Wi-fi, TV, air-conditioning");
    List<Room> rooms = new ArrayList<>();

    @Test
    void getNumberOfReservedRooms() {
        // given
        rooms.add(room1);
        rooms.add(room2);

        RoomReservation oneRoomRreservation = new RoomReservation(
                client1,
                room1,
                LocalDate.of(2024, 5, 29),
                LocalDate.of(2024,6,1),
                false,
                1,
                false);

        RoomReservation multipleRoomReservation = new RoomReservation(
                client2,
                rooms,
                LocalDate.of(2024, 6, 28),
                LocalDate.of(2024,7,4),
                true,
                4,
                true);

        // when
        int oneRoom = oneRoomRreservation.getNumberOfReservedRooms();
        int multipleRoom = multipleRoomReservation.getNumberOfReservedRooms();

        // then
        assertEquals(1, oneRoom);
        assertEquals(2, multipleRoom);
    }

    @Test
    void calculateTotalCost() {
        // given
        rooms.add(room1);
        rooms.add(room2);

        RoomReservation reservation = new RoomReservation(
                client2,
                rooms,
                LocalDate.of(2024, 6, 28),
                LocalDate.of(2024,7,4),
                true,
                4,
                true);
        double totalCost = 6*75+6*80+4*6*10;

        // when
        double calculatedTotalCost = reservation.calculateTotalCost();

        //then
        assertEquals(totalCost, calculatedTotalCost);
    }
}