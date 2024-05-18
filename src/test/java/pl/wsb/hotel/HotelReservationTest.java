package pl.wsb.hotel;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pl.wsb.hotel.exceptions.ClientNotFoundException;
import pl.wsb.hotel.exceptions.ReservationNotFoundException;
import pl.wsb.hotel.exceptions.RoomNotFoundException;
import pl.wsb.hotel.exceptions.RoomReservedException;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;

public class HotelReservationTest {

    private final Hotel hotelUnderTests = new Hotel("VeryFancy");
    private String clientId;
    private String firstRoomId;
    private String secondRoomId;
    private LocalDate startReservationDate;

    @BeforeEach
    void setUp() {
        // given
        clientId = hotelUnderTests.addClient("John","Doe", LocalDate.of(1990, 2, 6));
        firstRoomId = hotelUnderTests.addRoom(12, 1, true, "");
        secondRoomId = hotelUnderTests.addRoom(10, 1, false, "");
        startReservationDate = LocalDate.of(2024, 6, 15);
    }

    @Test
    void shouldAddNewReservationAndReturnId() throws ClientNotFoundException, RoomReservedException, RoomNotFoundException {
        // when
        String newReservationId = hotelUnderTests.addNewReservation(clientId, firstRoomId, startReservationDate);
        Map reservations = hotelUnderTests.getRoomReservations();
        RoomReservation reservation = (RoomReservation) reservations.get(newReservationId);

        // then
        assertTrue(reservations.containsKey(newReservationId));
        assertEquals(clientId, reservation.getClient().getId());
        assertEquals(firstRoomId, reservation.getRoom().getId());
        assertEquals(startReservationDate, reservation.getStartDate());
    }

    @Test
    void shouldReturnIfReservationIsConfirmed() throws ClientNotFoundException, RoomReservedException, RoomNotFoundException, ReservationNotFoundException {
        // given
        String newReservationId = hotelUnderTests.addNewReservation(clientId, firstRoomId, startReservationDate);
        Map reservations = (Map) hotelUnderTests.getRoomReservations();
        RoomReservation reservation = (RoomReservation) reservations.get(newReservationId);

        // when
        boolean beforeConfirmation = reservation.getIsConfirmed();
        String confirmedReservation = hotelUnderTests.confirmReservation(newReservationId);

        // then
        assertFalse(beforeConfirmation);
        assertEquals(("Reservation with ID " + newReservationId + " confirmed!"), confirmedReservation);
    }

    @Test
    void shouldReturnInformationIfRoomIsReservedAtTheDate() throws ClientNotFoundException, RoomReservedException, RoomNotFoundException {
        // given
        LocalDate endReservationDate = LocalDate.of(20024, 6, 20);
        LocalDate testDate1 = LocalDate.of(2024, 6, 12);
        LocalDate testDate2 = LocalDate.of(2024, 6, 18);

        String reservationId = hotelUnderTests.addNewReservation(clientId, firstRoomId, startReservationDate);
        hotelUnderTests.setReservationEndDate(reservationId, endReservationDate);

        // when
        boolean notReserved1 = hotelUnderTests.isRoomReserved(firstRoomId, testDate1);
        boolean notReserved2 = hotelUnderTests.isRoomReserved(secondRoomId, testDate2);
        boolean reserved = hotelUnderTests.isRoomReserved(firstRoomId, testDate2);

        // then
        assertEquals(false, notReserved1);
        assertEquals(false, notReserved2);
        assertEquals(true, reserved);
    }

    @Test
    void shouldReturnNumberOfUnconfirmedReservationAtTheDate() throws ClientNotFoundException, RoomReservedException, RoomNotFoundException {
        // given
        String reservationId1 = hotelUnderTests.addNewReservation(clientId, firstRoomId, startReservationDate);
        String reservationId2 = hotelUnderTests.addNewReservation(clientId, secondRoomId, startReservationDate);
        hotelUnderTests.confirmReservation(reservationId1);

        // when
        int numberOfUnconfirmedReservations = hotelUnderTests.getNumberOfUnconfirmedReservation(startReservationDate);

        // then
        assertEquals(1, numberOfUnconfirmedReservations, "Number of unconfirmed reservations is incorrect");
    }

    @Test
    void shouldReturnRoomIdsReservedByAClientList() throws ClientNotFoundException, RoomReservedException, RoomNotFoundException {
        // given
        String otherClientId = hotelUnderTests.addClient("Adam", "Smith", LocalDate.of(1985, 1, 1));
        hotelUnderTests.addNewReservation(otherClientId, firstRoomId, LocalDate.of(2024, 8, 12));
        hotelUnderTests.addNewReservation(clientId, firstRoomId, startReservationDate);
        hotelUnderTests.addNewReservation(clientId, secondRoomId, startReservationDate);

        // when
        Collection clientReservedRooms = hotelUnderTests.getRoomIdsReservedByClient(clientId);
        Collection otherClientReservedRooms = hotelUnderTests.getRoomIdsReservedByClient(otherClientId);

        // then
        assertTrue(clientReservedRooms.contains(firstRoomId));
        assertTrue(clientReservedRooms.contains(secondRoomId));
        assertTrue(otherClientReservedRooms.contains(firstRoomId));
        assertFalse(otherClientReservedRooms.contains(secondRoomId));
    }
}
