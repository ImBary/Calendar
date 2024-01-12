import java.time.*;
public abstract class Wydarzenie {
    protected LocalTime czasPoczatku;
    protected LocalTime czasKonca;
    protected String opis;

    public Wydarzenie(String opis,LocalTime czasKonca,LocalTime czasPoczatku){
        this.czasPoczatku = czasPoczatku;
        this.czasKonca = czasKonca;
        this.opis = opis;
    }
    public LocalTime getCzasPoczatku(){return czasPoczatku;}
    public LocalTime getCzasKonca(){return czasKonca;}



}