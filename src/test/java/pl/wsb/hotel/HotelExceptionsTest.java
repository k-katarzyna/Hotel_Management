package pl.wsb.hotel;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import pl.wsb.hotel.exceptions.ClientNotFoundException;
import pl.wsb.hotel.exceptions.ReservationNotFoundException;
import pl.wsb.hotel.exceptions.RoomNotFoundException;
import pl.wsb.hotel.exceptions.RoomReservedException;

import java.time.LocalDate;

public class HotelExceptionsTest {

    private final Hotel hotelUnderTests = new Hotel("Very Fancy");

    @Test
    void shouldThrowClientNotFoundExceptionWhileCallingAddNewReservation() throws RoomNotFoundException, RoomReservedException {
        // given
        String fakeClientId = "clientFakeId";
        String roomId = hotelUnderTests.addRoom(12, 1, true, "");
        LocalDate date = LocalDate.of(2024, 5, 20);

        // when
        try {
            hotelUnderTests.addNewReservation(fakeClientId, roomId, date);
            fail("Expected ClientNotFoundException to be thrown");
        }
        catch (ClientNotFoundException exception) {
            // then
            assertEquals("Client with ID " + fakeClientId + " not found.", exception.getMessage());
        }
    }

    @Test
    void shouldThrowRoomNotFoundExceptionWhileCallingAddNewReservation() throws ClientNotFoundException, RoomNotFoundException {
        // given
        String clientId = hotelUnderTests.addClient("John", "Doe", LocalDate.of(1990, 5, 5));
        String fakeRoomId = "fakeRoomId";
        LocalDate date = LocalDate.of(2024, 5, 20);

        // when
        try {
            hotelUnderTests.addNewReservation(clientId, fakeRoomId, date);
            fail("Expected RoomNotFoundException to be thrown");
        }
        catch (RoomNotFoundException exception) {
            // then
            assertEquals("Room with ID " + fakeRoomId + " not found.", exception.getMessage());
        }
    }

    @Test
    void shouldThrowRoomReservedExceptionIfRoomIsNotAvailableAtTheDate() throws ClientNotFoundException, RoomNotFoundException {
        // given
        String clientId = hotelUnderTests.addClient("John", "Doe", LocalDate.of(1990, 5, 5));
        String roomId = hotelUnderTests.addRoom(12, 1, true, "");
        LocalDate date = LocalDate.of(2024, 5, 20);
        String reservationId = hotelUnderTests.addNewReservation(clientId, roomId, date);
        hotelUnderTests.setReservationEndDate(reservationId, LocalDate.of(2024, 5, 25));

        // when
        try {
            hotelUnderTests.addNewReservation(clientId, roomId, date);
            fail("Expected RoomReservedException to be thrown");
        }
        catch (RoomReservedException exception) {
            // then
            assertEquals(String.format("On %s, the room %s is booked.", date, roomId), exception.getMessage());
        }
    }

    @Test
    void shouldThrowReservationNotFoundExceptionIfReservationDoesNotExist() throws ClientNotFoundException, RoomReservedException {
        // given
        String fakeReservationId = "fakeReservationId";

        // when
        try {
            hotelUnderTests.confirmReservation(fakeReservationId);
            fail("Expected ReservationNotFoundException to be thrown");
        }
        catch (ReservationNotFoundException exception) {
            // then
            assertEquals("Reservation with ID " + fakeReservationId + " not found.", exception.getMessage());
        }
    }

    @Test
    void shouldThrowRoomNotFoundExceptionWhileCallingIsRoomReserved() {
        // given
        String fakeRoomId = "fakeRoomId";
        LocalDate date = LocalDate.of(2024, 5, 20);

        // when
        try {
            hotelUnderTests.isRoomReserved(fakeRoomId, date);
            fail("Expected RoomNotFoundException to be thrown");
        }
        catch (RoomNotFoundException exception) {
            // then
            assertEquals("Room with ID " + fakeRoomId + " not found.", exception.getMessage());
        }
    }

    @Test
    void shouldThrowClientNotFoundExceptionWhileCallingGetRoomIdsReserverByClient () {
        // given
        String fakeClientId = "fakeRoomId";

        // when
        try {
        hotelUnderTests.getRoomIdsReservedByClient(fakeClientId);
        fail("Expected ClientNotFoundException to be thrown");
        }
        catch (ClientNotFoundException exception) {
        // then
        assertEquals("Client with ID " + fakeClientId + " not found.", exception.getMessage());
        }
    }
}
