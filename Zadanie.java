import java.time.LocalTime;

public final class Zadanie extends Wydarzenie {
    private final String status;

    public Zadanie(String opis, LocalTime czasPoczatku, LocalTime czasKonca, String status) {
        super(opis,czasKonca,czasPoczatku);
        this.status = status;
    }
    public String toString(){//zamieniamy sobie na stringi zeby mozna bylo wypisac
        return "opis Zadania: " + opis
                + " czas Poczatku: " + czasPoczatku
                + " czas konca: " + czasKonca
                + " status: " + status;
    }
    public String getStatus(){
        return status;
    }
}