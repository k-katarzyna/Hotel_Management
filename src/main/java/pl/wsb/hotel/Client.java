package pl.wsb.hotel;

import java.time.LocalDate;

public class Client {

    // fields //
    private String id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String phoneNumber;
    private String emailAddress;
    private int totalGuests;
    private boolean withParkingSpot;

    // constructor //
    public Client(String id,
                  String firstName,
                  String lastName,
                  LocalDate birthDate,
                  String phoneNumber,
                  String emailAddress,
                  int totalGuests,
                  boolean withParkingSpot) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.totalGuests = totalGuests;
        this.withParkingSpot = withParkingSpot;
    }

    // getters //
    public int getAge() {
        return LocalDate.now().getYear() - birthDate.getYear();
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getId() {
        return id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public int getTotalGuests() {
        return totalGuests;
    }

    public String getWithParkingSpot() {
        return withParkingSpot ? "Yes": "No";
    }
}
