import java.time.*;
public final class Spotkanie extends Wydarzenie {
    private final String priorytet;

    public Spotkanie(String opis, LocalTime czasPoczatku, LocalTime czasKonca, String priorytet) {
        super(opis,czasPoczatku,czasKonca);
        this.priorytet = priorytet;
    }

    public String toString(){//zamieniamy sobie na stringi zeby mozna bylo wypisac
        return "opis Spotkania: " + opis
                + " czas Poczatku: " + czasPoczatku
                + " czas konca: " + czasKonca
                + " priorytet: " + priorytet;
    }

    public String getPriorytet() {
        return priorytet;
    }
}