package pl.wsb.hotel;

import java.time.LocalDate;

public class PremiumClient extends Client {
    public enum PremiumAccountType {
        PREMIUM,
        PREMIUM_PLUS
    }

    private PremiumAccountType premiumType;

    // constructor //
    public PremiumClient(String firstName,
                         String lastName,
                         LocalDate birthDate,
                         String phoneNumber,
                         String emailAddress,
                         PremiumAccountType premiumType) {
        super(firstName, lastName, birthDate, phoneNumber, emailAddress);
        this.premiumType = premiumType;
    }

    @Override
    public void printInformation() {
        super.printInformation();
        System.out.println("Premium Account Type: " + premiumType);
    }

    @Override
    public String getFullName() {
        return "[premium] " + super.getFullName();
    }
}