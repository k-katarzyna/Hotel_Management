package pl.wsb.hotel;

import pl.wsb.hotel.interfaces.ExcludeFromCodeCoverage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

public class RoomReservation {
    private String id = UUID.randomUUID().toString();
    private LocalDate reservationDate = LocalDate.now();
    private boolean isConfirmed = false;
    private Client client;
    private Room room;
    private List<Room> rooms;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean breakfast;
    private int totalGuests;
    private boolean withParkingSpot;

    // constructors //
    public RoomReservation(Client client,
                           Room room,
                           LocalDate startDate,
                           LocalDate endDate,
                           boolean breakfast,
                           int totalGuests,
                           boolean withParkingSpot) {
        this.client = client;
        this.room = room; // one room reservation
        this.startDate = startDate;
        this.endDate = endDate;
        this.breakfast = breakfast;
        this.totalGuests = totalGuests;
        this.withParkingSpot = withParkingSpot;
    }
    public RoomReservation(Client client,
                           List<Room> rooms,
                           LocalDate startDate,
                           LocalDate endDate,
                           boolean breakfast,
                           int totalGuests,
                           boolean withParkingSpot) {
        this.client = client;
        this.rooms = new ArrayList<>(rooms); // multiple rooms reservation
        this.startDate = startDate;
        this.endDate = endDate;
        this.breakfast = breakfast;
        this.totalGuests = totalGuests;
        this.withParkingSpot = withParkingSpot;
    }
    public RoomReservation(Client client,
                           Room room,
                           LocalDate startDate) {
        this.client = client;
        this.room = room; // one room reservation
        this.startDate = startDate;
    }

    // confirm reservation //
    public void confirmReservation() {
        this.isConfirmed = true;
    }

    // getters, setters
    public String getId() {
        return id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Room getRoom() {
        return room;
    }

    public boolean getIsConfirmed() {
        return isConfirmed;
    }

    public Client getClient() {
        return client;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    // get number of reserved rooms//
    public int getNumberOfReservedRooms() {
        if (room != null) {
            return 1;
        } else if (rooms != null) {
            return rooms.size();
        }
        return 0;
    }

    // calculate number of nights //
    private long calculateNumberOfNights(LocalDate startDate, LocalDate endDate) {
        return ChronoUnit.DAYS.between(startDate, endDate);
    }

    // calculate price //
    public double calculateTotalCost() {
        double roomCostPerNight = 0.0;

        if (room != null) {
            roomCostPerNight += room.getPricePerNight();
        } else if (rooms != null) {
            for (Room room : rooms) {
                roomCostPerNight += room.getPricePerNight();
            }
        }
        double breakfastCostPerPerson = breakfast ? 10.0 : 0.0;
        long totalNights = calculateNumberOfNights(startDate, endDate);
        double dailyBreakfastCost = breakfastCostPerPerson * totalGuests ;

        return (roomCostPerNight + dailyBreakfastCost) * totalNights;
    }

    // print reservation details //
    @ExcludeFromCodeCoverage
    public void printFullReservationDetails() {
        System.out.println("--FULL RESERVATION DETAILS--");
        System.out.println();
        System.out.println("Reservation date: " + reservationDate);
        System.out.println("With parking spot: " + (withParkingSpot ? "Yes": "No"));
        System.out.println("With breakfast: " + (breakfast ? "Yes": "No"));
        System.out.println("Total guests: " + totalGuests);
        System.out.println("Number of reserved rooms: " + getNumberOfReservedRooms());
        System.out.println("Total cost $: " + calculateTotalCost());
        System.out.println("Stay from: " + startDate + " to " + endDate);
        System.out.println("Confirmed: " + (isConfirmed ? "Yes": "No") + "\n");
        client.printInformation();
        if (room != null) {
            room.printInformation();
        } else if (rooms != null) {
            for (int i = 0; i < rooms.size(); i++) {
                Room currentRoom = rooms.get(i);
                System.out.println();
                System.out.println("Room " + (i + 1) + ":");
                currentRoom.printInformation();
            }
        }

    }
}
