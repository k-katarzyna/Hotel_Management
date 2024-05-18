package pl.wsb.hotel;

import java.time.LocalDate;

public class LuggageService extends SpecialService{
    public LuggageService(){
        this.name = "Luggage storage";
    }
    public void orderService(){
        System.out.println("Luggage successfully thrown o... stored!");
    }
    public String getServiceDescription(){
        return "The fastest and best " + name;
    }
    public boolean isAvailable(LocalDate date){
        return (date.getDayOfMonth() % 2) == 0; //only available on even days
    }
}