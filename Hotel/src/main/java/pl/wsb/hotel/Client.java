package pl.wsb.hotel;

import java.time.LocalDate;
import java.util.UUID;

public class Client {

    // fields //
    protected String id = UUID.randomUUID().toString();
    protected String firstName;
    protected String lastName;
    protected LocalDate birthDate;
    protected String phoneNumber;
    protected String emailAddress;

    // constructors //
    public Client(String firstName,
                  String lastName,
                  LocalDate birthDate,
                  String phoneNumber,
                  String emailAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    public Client(String firstName,
                  String lastName,
                  LocalDate birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    // getters //
    public String getId() {
        return id;
    }

    public int getAge() {
        LocalDate now = LocalDate.now();
        int age = now.getYear() - birthDate.getYear();
        if (birthDate.getDayOfYear() > now.getDayOfYear()) {
            age--;
        }
        return age;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public void printInformation(){
        System.out.println();
        System.out.println("Client ID: " + id);
        System.out.println("Client full name: " + getFullName());
        System.out.println("Client age: " + getAge());
        System.out.println("Client phone number: " + phoneNumber);
        System.out.println("Client email address: " + emailAddress);
    }
}
