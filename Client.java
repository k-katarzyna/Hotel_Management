import java.time.LocalDate; // importowanie klasy LocalDate z biblioteki do obsługi dat
import java.time.Period; // importowanie klasy Period do obliczania okresów czasu
import java.util.UUID; // importowanie klasy UUID do generowania unikalnych identyfikatorów

// Deklaracja publicznej klasy Klient
public class Client {
    private UUID id; // Prywatne pole 'id' do przechowywania unikalnego identyfikatora
    private String imie; // Prywatne pole 'imie' do przechowywania imienia klienta
    private String nazwisko; // Prywatne pole 'nazwisko' do przechowywania nazwiska klienta
    private LocalDate dataUrodzenia; // Prywatne pole 'dataUrodzenia' do przechowywania daty urodzenia

    // Konstruktor klasy Klient przyjmujący imię, nazwisko i datę urodzenia
    public Client(String imie, String nazwisko, LocalDate dataUrodzenia) {
        this.id = UUID.randomUUID(); // Przypisywanie nowego unikalnego identyfikatora do pola 'id'
        this.imie = imie; // Przypisywanie imienia do pola 'imie'
        this.nazwisko = nazwisko; // Przypisywanie nazwiska do pola 'nazwisko'
        this.dataUrodzenia = dataUrodzenia; // Przypisywanie daty urodzenia do pola 'dataUrodzenia'
    }

    // Metoda 'wiek' do obliczania aktualnego wieku klienta
    public int getWiek() {
        return Period.between(dataUrodzenia, LocalDate.now()).getYears();
    }

    // Metoda 'pelneImie' do pobierania pełnego imienia i nazwiska klienta
    public String getPelneImie() {
        return imie + " " + nazwisko;
    }

    // Gettery do pozostałych pól (settery mogą być dodane w razie potrzeby)
    public UUID getId() {
        return id;
    }

    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public LocalDate getDataUrodzenia() {
        return dataUrodzenia;
    }

    // Tu można dodać settery, jeśli istnieje potrzeba modyfikacji pól
    // ...
}
