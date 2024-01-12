import java.time.LocalTime;
import java.util.ArrayList;
import java.util.function.Predicate;

public class Kalendarz {
    private ArrayList<ArrayList<Wydarzenie>> dziennik = new ArrayList<>();//arraylista arraylist[[spotaknie1,spotkanie2...],[spotkanie1,spotkanie2...],...]

    public void dodaniePrzykladowychSpotkan(){
        Spotkanie spotkaniePoniedzialekProba1 = new Spotkanie("sniadanie",LocalTime.of(9,30),LocalTime.of(10, 0),"High");
        Spotkanie spotkaniePoniedzialekProba2 = new Spotkanie("obiad",LocalTime.of(12,30),LocalTime.of(13,0),"High");
        Spotkanie spotkaniePoniedzialekProba5 = new Spotkanie("deser",LocalTime.of(13,30),LocalTime.of(14,45),"Medium");
        Spotkanie spotkaniePoniedzialekProba4 = new Spotkanie("trening",LocalTime.of(15,30),LocalTime.of(17,0),"Low");
        Spotkanie spotkaniePoniedzialekProba6 = new Spotkanie("kino",LocalTime.of(20,30),LocalTime.of(21,0),"Medium");
        Spotkanie spotkaniePoniedzialekProba3 = new Spotkanie("kolacja",LocalTime.of(21,30),LocalTime.of(21,45),"Low");
        dziennik.get(0).add(spotkaniePoniedzialekProba1);
        dziennik.get(1).add(spotkaniePoniedzialekProba2);
        dziennik.get(0).add(spotkaniePoniedzialekProba5);
        dziennik.get(1).add(spotkaniePoniedzialekProba4);
        dziennik.get(0).add(spotkaniePoniedzialekProba6);
        dziennik.get(1).add(spotkaniePoniedzialekProba3);
        Zadanie spotkaniePoniedzialekProba11 = new Zadanie("smieci",LocalTime.of(9,30),LocalTime.of(10, 0),"realizowane");
        Zadanie spotkaniePoniedzialekProba21 = new Zadanie("pies",LocalTime.of(12,30),LocalTime.of(13,0),"planowane");
        Zadanie spotkaniePoniedzialekProba51 = new Zadanie("ksiazka",LocalTime.of(13,30),LocalTime.of(14,45),"wykonane");
        Zadanie spotkaniePoniedzialekProba41 = new Zadanie("gitara",LocalTime.of(15,30),LocalTime.of(17,0),"realizowane");
        Zadanie spotkaniePoniedzialekProba61 = new Zadanie("pilka",LocalTime.of(20,30),LocalTime.of(21,0),"wykonane");
        Zadanie spotkaniePoniedzialekProba31 = new Zadanie("myszka",LocalTime.of(21,30),LocalTime.of(21,45),"planowane");
        dziennik.get(1).add(spotkaniePoniedzialekProba11);
        dziennik.get(0).add(spotkaniePoniedzialekProba21);
        dziennik.get(1).add(spotkaniePoniedzialekProba51);
        dziennik.get(0).add(spotkaniePoniedzialekProba41);
        dziennik.get(1).add(spotkaniePoniedzialekProba61);
        dziennik.get(0).add(spotkaniePoniedzialekProba31);
    }
    public void dodajWydarzenie(Spotkanie spotkanie,int dzien){
        dziennik.get(dzien).add(spotkanie);
    }
    public void dodajWydarzenie(Zadanie zadanie,int dzien){
        dziennik.get(dzien).add(zadanie);
    }
    public Kalendarz(int days) {
        for (int i = 0; i < days; i++){
            dziennik.add(new ArrayList<>());//[[1],[2]...[days]] ustawiamy sobie ile chcemy list(dni) w kalendarzu
        }
    }
    public void removeWydarzenie(int dzien, Wydarzenie wydarzenie){
        dziennik.get(dzien).remove(wydarzenie);//usuwamy na tej samej zasadzie co dodajemy
    }
    public ArrayList<Wydarzenie> getWydarzenie(int dzien){
        return dziennik.get(dzien);//liste na indexie dzien [[dzien],[dzien2]]
    }

    public ArrayList<Wydarzenie> filtrListBy(Predicate<Wydarzenie>check,  int wDniu){
        ArrayList<Wydarzenie> cleanedArr = new ArrayList<>();
        for(Wydarzenie wydarzenie : getWydarzenie(wDniu)){
            if(check.test(wydarzenie)){
                cleanedArr.add(wydarzenie);
            }
        }
        return cleanedArr;
    }
    public ArrayList<Integer>  indexFiltr(Predicate<Wydarzenie>check,int wDniu){
        ArrayList<Integer> indeksyWydarzen = new ArrayList<>();
        int i =  0;
        for(Wydarzenie wydarzenie : getWydarzenie(wDniu)){
            if(check.test(wydarzenie)){
                indeksyWydarzen.add(i);
            }
            i++;
        }
        return indeksyWydarzen;
    }
}