package pl.wsb.hotel;

import pl.wsb.hotel.interfaces.ExcludeFromCodeCoverage;

import java.time.LocalDate;

@ExcludeFromCodeCoverage
public abstract class SpecialService {
    protected String name;

    public abstract void orderService();
    public abstract String getServiceDescription();
    public abstract boolean isAvailable(LocalDate date);

    public void printInformation(){
        System.out.println();
        System.out.println("Service name: " + name);
        this.orderService();
        System.out.println("Service description: " + getServiceDescription());
        System.out.println(String.format("Available at %s: %s", LocalDate.now(), isAvailable(LocalDate.now()) ? "Yes" : "No"));
    }
}
