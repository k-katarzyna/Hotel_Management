package pl.wsb.hotel;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Map;

class HotelBaseDataTest {

    private final Hotel hotelUnderTests = new Hotel("Very Fancy");

    @Test
    void shouldAddNewClientAndReturnId() {
        // given
        String name = "John";
        String lastName = "Doe";
        LocalDate birthDate = LocalDate.of(1990, 2, 6);
        int age = LocalDate.now().getYear() - birthDate.getYear();

        // when
        String newClientId = hotelUnderTests.addClient(name, lastName, birthDate);
        Map clients = hotelUnderTests.getClients();
        Client client = (Client) clients.get(newClientId);

        // then
        assertTrue(clients.containsKey(newClientId));
        assertEquals("John Doe", client.getFullName());
        assertEquals(age, client.getAge());
    }

    @Test
    void shouldReturnClientFullName() {
        // given
        String newClientId = hotelUnderTests.addClient(
                "John",
                "Doe",
                LocalDate.of(1990, 2, 6));

        // when
        String clientFullName = hotelUnderTests.getClientFullName(newClientId);
        String fakeClientFullName = hotelUnderTests.getClientFullName("fakeClientId");

        // then
        assertEquals("John Doe", clientFullName);
        assertEquals(null, fakeClientFullName);
    }

    @Test
    void shouldReturnNumberOfUnderageClients() {
        // given
        hotelUnderTests.addClient("Lisa", "Doe", LocalDate.of(1995, 2, 1));
        hotelUnderTests.addClient("John", "Doe", LocalDate.of(2014, 8, 6));
        hotelUnderTests.addClient("Adam", "Doe", LocalDate.of(2006, 4, 6));
        hotelUnderTests.addClient("Anna", "Jones", LocalDate.of(2006, 6, 1));

        // when
        int numberOfUnderageClients = hotelUnderTests.getNumberOfUnderageClients();

        // then //tested 15th May 2024
        assertEquals(2, numberOfUnderageClients);
    }

    @Test
    void shouldAddNewRoomAndReturnId() {
        // given
        double area = 12.5;
        int floor = 3;
        String description = "Sunny and cosy single room.";

        // when
        String newRoomId = hotelUnderTests.addRoom(area, floor, false, description);
        Map rooms = hotelUnderTests.getRooms();
        Room room = (Room) rooms.get(newRoomId);

        // then
        assertTrue(rooms.containsKey(newRoomId));
        assertEquals(area, room.getArea());
        assertEquals(floor, room.getFloor());
        assertEquals(description, room.getDescription());
    }

    @Test
    void shouldReturnRoomArea() {
        // given
        String roomId = hotelUnderTests.addRoom(16.3, 0, false, "");

        //when
        double roomArea = hotelUnderTests.getRoomArea(roomId);
        double fakeRoomArea = hotelUnderTests.getRoomArea("fakeRoomId");

        // then
        assertEquals(16.3, roomArea);
        assertEquals(0.0, fakeRoomArea);
    }

    @Test
    void shouldReturnNumberOfRoomsWithKingSizeBedOnSpecifiedFloor() {
        // given
        hotelUnderTests.addRoom(12.2, 1, true, "");
        hotelUnderTests.addRoom(9.0, 1, false, "");
        hotelUnderTests.addRoom(12.2, 2, true, "");
        hotelUnderTests.addRoom(14, 2, true, "");
        hotelUnderTests.addRoom(10, 2, false, "");

        // when
        int floor1 = hotelUnderTests.getNumberOfRoomsWithKingSizeBed(1);
        int floor2 = hotelUnderTests.getNumberOfRoomsWithKingSizeBed(2);

        // then
        assertEquals(1, floor1);
        assertEquals(2, floor2);
    }

    @Test
    void shouldAddSpecialServicesToTheList() {
        //given
        SpecialService timeService = new TimeService();
        SpecialService luggageService = new LuggageService();

        // when
        hotelUnderTests.addService(timeService);
        hotelUnderTests.addService(luggageService);
        String timeServiceDescription = hotelUnderTests.getSpecialServices().get(0).getServiceDescription();
        String luggageServiceDescription = hotelUnderTests.getSpecialServices().get(1).getServiceDescription();
        boolean luggageServiceAvailable = hotelUnderTests.getSpecialServices().get(1).isAvailable(
                LocalDate.of(2024, 6, 2));

        // then
        assertEquals(timeService, (hotelUnderTests.getSpecialServices().get(0)));
        assertEquals(luggageService, (hotelUnderTests.getSpecialServices().get(1)));
        assertEquals("Only the most accurate " + timeService.name, timeServiceDescription);
        assertEquals("The fastest and best " + luggageService.name, luggageServiceDescription);
        assertEquals(true, luggageServiceAvailable);
    }
}
