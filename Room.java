import java.util.UUID;

public class Room {
    private UUID id; // Unikalny identyfikator pokoju
    private String opis; // Opis pokoju
    private double powierzchnia; // Powierzchnia pokoju w metrach kwadratowych
    private int pietro; // Numer piętra, na którym znajduje się pokój
    private boolean czyMaLozkoKrolewskie; // Informacja czy pokój posiada łóżko typu king-size

    public Room(String opis, double powierzchnia, int pietro, boolean czyMaLozkoKrolewskie) {
        this.id = UUID.randomUUID(); // Generowanie unikalnego ID
        this.opis = opis;
        this.powierzchnia = powierzchnia;
        this.pietro = pietro;
        this.czyMaLozkoKrolewskie = czyMaLozkoKrolewskie;
    }

    // Gettery (settery mogą być dodane jeśli będą potrzebne)
    public UUID getId() {
        return id;
    }

    public String getOpis() {
        return opis;
    }

    public double getPowierzchnia() {
        return powierzchnia;
    }

    public int getPietro() {
        return pietro;
    }

    public boolean isCzyMaLozkoKrolewskie() {
        return czyMaLozkoKrolewskie;
    }

    // Settery mogą być tutaj, jeśli będzie taka potrzeba
    // ...
}
