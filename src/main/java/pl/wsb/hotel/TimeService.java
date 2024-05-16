package pl.wsb.hotel;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class TimeService extends SpecialService{
    public TimeService(){
        this.name = "Current time";
    }
    public void orderService(){
        System.out.println(LocalTime.now().truncatedTo(ChronoUnit.SECONDS));
    }
    public String getServiceDescription(){
        return "Only the most accurate " + name;
    }
    public boolean isAvailable(LocalDate date){
        return Math.round(Math.random()) == 1; //random 0/1
    }
}