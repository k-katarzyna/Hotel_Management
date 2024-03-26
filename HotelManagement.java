import java.time.LocalDate;

public class HotelManagement {
    public static void main(String[] args) {
        // Tworzenie instancji klasy Klient
        Client klient = new Client("Jan", "Kowalski", LocalDate.of(1990, 1, 1));

        // Wywołanie metody getWiek() dla klienta, aby sprawdzić, czy poprawnie oblicza wiek
        System.out.println("Wiek klienta: " + klient.getWiek() + " lat");

        // Tworzenie instancji klasy Pokoj
        Room pokoj = new Room("Standardowy pokój", 15.0, 2, true);

        // Wywołanie getterów z klasy Pokoj, aby wyświetlić informacje o pokoju
        System.out.println("Opis pokoju: " + pokoj.getOpis());
        System.out.println("Powierzchnia pokoju: " + pokoj.getPowierzchnia() + " m2");
        System.out.println("Czy pokój ma łóżko king-size? " + (pokoj.isCzyMaLozkoKrolewskie() ? "Tak" : "Nie"));

        // Tworzenie instancji klasy RezerwacjaPokoju
        RoomReservation rezerwacja = new RoomReservation(LocalDate.now(), klient, pokoj);

        // Wywołanie metody potwierdzRezerwacje() na obiekcie rezerwacja
        rezerwacja.potwierdzRezerwacje();

        // Sprawdzenie statusu rezerwacji
        System.out.println("Rezerwacja na dzień: " + rezerwacja.getData() +
                " dla klienta " + rezerwacja.getKlient().getPelneImie() +
                " jest " + (rezerwacja.isCzyPotwierdzona() ? "potwierdzona" : "niepotwierdzona") + ".");
    }
}
