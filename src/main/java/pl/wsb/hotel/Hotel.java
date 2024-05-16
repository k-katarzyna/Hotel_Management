package pl.wsb.hotel;

import pl.wsb.hotel.exceptions.ClientNotFoundException;
import pl.wsb.hotel.exceptions.RoomNotFoundException;
import pl.wsb.hotel.exceptions.RoomReservedException;
import pl.wsb.hotel.exceptions.ReservationNotFoundException;
import pl.wsb.hotel.interfaces.HotelCapability;

import java.time.LocalDate;
import java.util.*;

public class Hotel implements HotelCapability {
    private String name;
    private List<SpecialService> specialServices;
    private Map<String, Client> clients;
    private Map<String, RoomReservation> roomReservations;
    private Map<String, Room> rooms;

    public Hotel(String name){
        this.name = name;
        specialServices = new ArrayList<>();
        clients = new HashMap<>();
        roomReservations = new HashMap<>();
        rooms = new HashMap<>();
    }

    public void addService(SpecialService s) {
        specialServices.add(s);
    }

    // getters, setters
    public List<SpecialService> getSpecialServices() {
        return specialServices;
    }

    public Map<String, Client> getClients() {
        return clients;
    }

    public Map<String, RoomReservation> getRoomReservations() {
        return roomReservations;
    }

    public Map<String, Room> getRooms() {
        return rooms;
    }

    // set reservation end date for checking if room is available at specified date
    public void setReservationEndDate(String reservationId, LocalDate endDate) {
        RoomReservation reservation = roomReservations.get(reservationId);
        reservation.setEndDate(endDate);
    }

    // helper method - check if room is reserved at specified date //
    private boolean isRoomReservedAt(RoomReservation reservation, String roomId, LocalDate date) {
        return reservation.getRoom().getId().equals(roomId) &&
                (date.isAfter(reservation.getStartDate()) || date.isEqual(reservation.getStartDate())) &&
                date.isBefore(reservation.getEndDate());
    }

    // HotelCapability Interface methods
    public String addClient(String firstName,
                            String lastName,
                            LocalDate birthDate) {
        Client newClient = new Client(firstName, lastName, birthDate);
        String newClientId = newClient.getId();
        clients.put(newClientId, newClient);
        return newClientId;
    }

    public String getClientFullName(String clientID) {
        Client client = clients.get(clientID);
        if (client != null) {
            return client.getFullName();
        }
        return null;
    }

    public int getNumberOfUnderageClients() {
        int countUnderageClients = 0;
        for (Client client : clients.values()) {
            if (client.getAge() < 18) {
                countUnderageClients++;
            }
        }
        return countUnderageClients;
    }

    public String addRoom(double area,
                          int floor,
                          boolean hasKingSizeBed,
                          String description){
        Room newRoom = new Room(area, floor, hasKingSizeBed, description);
        String roomId = newRoom.getId();
        rooms.put(roomId, newRoom);
        return roomId;
    }

    public double getRoomArea(String roomId) {
        Room room = rooms.get(roomId);
            if (room != null){
                return room.getArea();
            }
        return 0;
    }

    public int getNumberOfRoomsWithKingSizeBed(int floor) {
        int nRoomsWithKingSizeBed = 0;
        for (Room room : rooms.values()) {
            if (room.getFloor() == floor && room.getHasKingSizeBed()) {
                nRoomsWithKingSizeBed++;
            }
        }
        return nRoomsWithKingSizeBed;
    }

    public String addNewReservation(String clientId, String roomId, LocalDate date) throws ClientNotFoundException, RoomNotFoundException, RoomReservedException {

        Client client = clients.get(clientId);
        if (client == null) {
            throw new ClientNotFoundException("Client with ID " + clientId + " not found.");
        }

        Room room = rooms.get(roomId);
        if (room == null) {
            throw new RoomNotFoundException("Room with ID " + roomId + " not found.");
        }

        for (RoomReservation reservation : roomReservations.values()) {
            if (isRoomReservedAt(reservation, roomId, date)) {
                throw new RoomReservedException(roomId, date);
            }
        }
        RoomReservation newReservation = new RoomReservation(client, room, date);
        String newReservationId = newReservation.getId();
        roomReservations.put(newReservationId, newReservation);

        return newReservationId;
    }

    public String confirmReservation(String reservationId) throws ReservationNotFoundException {
        RoomReservation reservation = roomReservations.get(reservationId);
        if (reservation == null) {
            throw new ReservationNotFoundException("Reservation with ID " + reservationId + " not found.");
        }
        reservation.confirmReservation();
        return ("Reservation with ID " + reservationId + " confirmed!");
    }

    public boolean isRoomReserved(String roomId, LocalDate date) throws RoomNotFoundException {
        Room room = rooms.get(roomId);
        if (room == null) {
            throw new RoomNotFoundException("Room with ID " + roomId + " not found.");
        }
        for (RoomReservation reservation : roomReservations.values())
            if (isRoomReservedAt(reservation, roomId, date)) {
                return true;
            }
        return false;
    }

    public int getNumberOfUnconfirmedReservation(LocalDate date) {
        int numberOfUnconfirmedReservations = 0;
        for (RoomReservation reservation : roomReservations.values()) {
            if (!reservation.getIsConfirmed() && Objects.equals(reservation.getStartDate(), date))
                numberOfUnconfirmedReservations++;
        }
        return numberOfUnconfirmedReservations;
    }

    public Collection<String> getRoomIdsReservedByClient(String clientId) throws ClientNotFoundException {
        Client client = clients.get(clientId);
        if (client == null) {
            throw new ClientNotFoundException("Client with ID " + clientId + " not found.");
        }

        Collection<String> reservedRoomIds = new ArrayList<>();
        for (RoomReservation reservation : roomReservations.values()) {
            if (reservation.getClient().getId().equals(clientId)) {
                reservedRoomIds.add(reservation.getRoom().getId());
            }
        }
        return reservedRoomIds;
    }
}
