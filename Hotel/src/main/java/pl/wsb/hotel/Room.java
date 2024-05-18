package pl.wsb.hotel;

import pl.wsb.hotel.interfaces.ExcludeFromCodeCoverage;

import java.util.UUID;

public class Room {
    private String id = UUID.randomUUID().toString();
    private double area;
    private int floor;
    private boolean hasKingSizeBed;
    private String description;
    private int numberOfBeds;
    private double pricePerNight;
    private String amenities;

    // constructors //
    public Room(double area,
                int floor,
                boolean hasKingSizeBed,
                int numberOfBeds,
                double pricePerNight,
                String amenities) {
        this.area = area;
        this.floor = floor;
        this.hasKingSizeBed = hasKingSizeBed;
        this.numberOfBeds = numberOfBeds;
        this.pricePerNight = pricePerNight;
        this.amenities = amenities;
    }

    public Room(double area,
                int floor,
                boolean hasKingSizeBed,
                String description) {
        this.area = area;
        this.floor = floor;
        this.hasKingSizeBed = hasKingSizeBed;
        this.description = description;
    }

    // getters
    public String getId() {
        return id;
    }

    public double getArea() {
        return area;
    }

    public int getFloor() {
        return floor;
    }

    public boolean getHasKingSizeBed() {
        return hasKingSizeBed;
    }

    public String getDescription() {
        return description;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    @ExcludeFromCodeCoverage
    public void printInformation(){
        System.out.println();
        System.out.println("Room ID: " + id);
        System.out.println("Room Area: " + area);
        System.out.println("Room Floor: " + floor);
        System.out.println("Room Has King Size Bed: " + hasKingSizeBed);
        System.out.println("Room description: " + description);
        System.out.println("Room Number of Beds: " + numberOfBeds);
        System.out.println("Room Price per Night: " + pricePerNight);
        System.out.println("Room Amenities: " + amenities);
    }
}
