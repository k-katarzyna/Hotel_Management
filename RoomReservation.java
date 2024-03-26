import java.time.LocalDate;

public class RoomReservation {
    private LocalDate data; // Data rezerwacji
    private boolean czyPotwierdzona; // Status potwierdzenia rezerwacji
    private Client klient; // Klient, który dokonał rezerwacji
    private Room pokoj; // Pokój, który został zarezerwowany

    public RoomReservation(LocalDate data, Client klient, Room pokoj) {
        this.data = data;
        this.klient = klient;
        this.pokoj = pokoj;
        this.czyPotwierdzona = false; // Domyślnie rezerwacja nie jest potwierdzona
    }

    // Metoda do potwierdzania rezerwacji
    public void potwierdzRezerwacje() {
        this.czyPotwierdzona = true;
    }

    // Gettery
    public LocalDate getData() {
        return data;
    }

    public boolean isCzyPotwierdzona() {
        return czyPotwierdzona;
    }

    public Client getKlient() {
        return klient;
    }

    public Room getPokoj() {
        return pokoj;
    }

    // Settery mogą być dodane w razie potrzeby
    // ...
}
