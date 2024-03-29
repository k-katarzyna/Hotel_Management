package src.main.java.pl.wsb.hotel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.time.temporal.ChronoUnit;

public class RoomReservation {

    // fields //
    private LocalDate date;
    private boolean isConfirmed;
    private Client client;
    private Room room;
    private List<Room> rooms;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean breakfast;

    // isConfirmed default to false //
    public RoomReservation(){
        isConfirmed = false;
    }

    // constructors //
    public RoomReservation(LocalDate date,
                           Client client,
                           Room room,
                           LocalDate startDate,
                           LocalDate endDate,
                           boolean breakfast) {
        this.date = date;
        this.client = client;
        this.room = room; // one room reservation
        this.startDate = startDate;
        this.endDate = endDate;
        this.breakfast = breakfast;
    }
    public RoomReservation(LocalDate date,
                           Client client,
                           List<Room> rooms,
                           LocalDate startDate,
                           LocalDate endDate,
                           boolean breakfast) {
        this.date = date;
        this.client = client;
        this.rooms = new ArrayList<>(rooms); // multiple rooms reservation
        this.startDate = startDate;
        this.endDate = endDate;
        this.breakfast = breakfast;
    }

    // confirm reservation //
    public void confirmReservation() {
        this.isConfirmed = true;
    }

    // calculate number of nights //
    private long calculateNumberOfNights(LocalDate startDate, LocalDate endDate) {
        return ChronoUnit.DAYS.between(startDate, endDate);
    }

    // calculate price //
    private double calculateTotalCost() {
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
        double dailyBreakfastCost = breakfastCostPerPerson * client.getTotalGuests() ;

        return (roomCostPerNight + dailyBreakfastCost) * totalNights;
    }

    // print reservation details //
    public void getReservationDetails() {
        System.out.println("--RESERVATION DETAILS--");
        System.out.println("Reservation date: " + date);
        System.out.println("Client Full Name: " + client.getFullName());
        System.out.println("Client ID: " + client.getId());
        System.out.println("Client Age: " + client.getAge());
        System.out.println("Client Phone number: " + client.getPhoneNumber());
        System.out.println("Client Email address: " + client.getEmailAddress());
        System.out.println("With parking spot: " + client.getWithParkingSpot());
        System.out.println("With breakfast: " + (breakfast ? "Yes": "No"));
        System.out.println("Total cost $: " + calculateTotalCost());
        System.out.println("Stay from: " + startDate + " to " + endDate);
        System.out.println("Confirmed: " + (isConfirmed ? "Yes": "No") + "\n");

        if (room != null) {
            System.out.println("Rooms reserved: 1");
            System.out.println("Room ID: " + room.getId());
            System.out.println("Room Area: " + room.getArea() + " m2");
            System.out.println("Room Floor: " + room.getFloor());
            System.out.println("King Size Bed: " + (room.getHasKingSizeBed() ? "Yes" : "No"));
            System.out.println("Amenities: " + room.getAmenities());
        } else if (rooms != null) {
            System.out.println("Rooms reserved: " + rooms.size());
            for (int i = 0; i < rooms.size(); i++) {
                Room currentRoom = rooms.get(i);
                System.out.println("Room " + (i + 1) + " ID: " + currentRoom.getId());
                System.out.println("Room " + (i + 1) + " Area: " + currentRoom.getArea() + " m2");
                System.out.println("Room " + (i + 1) + " Floor: " + currentRoom.getFloor());
                System.out.println("Room " + (i + 1) + " King Size Bed: " + (currentRoom.getHasKingSizeBed() ? "Yes" : "No"));
                System.out.println("Room " + (i + 1) + " Amenities: " + currentRoom.getAmenities());
            }
        }

    }
}
