package pl.wsb.hotel;

public class Room {
    private String id;
    private  double area;
    private int floor;
    private boolean hasKingSizeBed;
    private int numberOfBeds;
    private double pricePerNight;
    private String amenities;

    // constructor //
    public Room(String id,
                double area,
                int floor,
                boolean hasKingSizeBed,
                int numberOfBeds,
                double pricePerNight,
                String amenities) {
        this.id = id;
        this.area = area;
        this.floor = floor;
        this.hasKingSizeBed = hasKingSizeBed;
        this.numberOfBeds = numberOfBeds;
        this.pricePerNight = pricePerNight;
        this.amenities = amenities;
    }

    // getters //
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

    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public String getAmenities() {
        return amenities;
    }
}
