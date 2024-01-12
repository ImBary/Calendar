import java.time.*;
import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Kalendarz kalendarz = new Kalendarz(7);
        kalendarz.dodaniePrzykladowychSpotkan();
        System.out.println("kalendarz");
        int wybor;
        do{
            menuInfo();
            wybor = scanner.nextInt();
            menu(scanner, wybor, kalendarz);
        }while (wybor != 0);
    }
    private static void menuInfo(){
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.println("1) Dodanie spotkania na wybrany dzien");
        System.out.println("2) Dodanie Zadania na wybrany dzien");
        System.out.println("3) usuniecie podanego spotkania w danym dniu");
        System.out.println("4) usuniecie podanego Zadania w danym dniu");
        System.out.println("5) Wyswietlenie wszystkich spotkan w wybranym dniu");
        System.out.println("6) Wyswietlenie wszystkich Zadan w wybranym dniu");
        System.out.println("7) Wyswietlenie Spotkan o wybranym dniu o wybranym priorytecie");
        System.out.println("8) Wyswietlenie zadan o wybranym dniu o wybranym priorytecie");
        System.out.println("9) Wyswietlenie Spotkan w wybranym dniu o wybranym priorytecie i zaczynajacych sie pozniej niz podany czas");
        System.out.println("10) Wyswietlenie Zadan w wybranym dnniu o wybranym statusie konczacych sie nie wczesniej niz do podanego czasu ");
        System.out.println("0) exit");
        System.out.println("--------------------------------------------------------------------------------------------");
    }
    private static void menu(Scanner scanner, int wybor,  Kalendarz kalendarz){
        switch (wybor){
            case 1 -> stworzSpotkanie(scanner,kalendarz);//tworzymy obiekt Spotkanie z danymi od usera
            case 2 -> stworzZadanie(scanner,kalendarz);
            case 3-> usuwanie(kalendarz,scanner,1);//Spotkania
            case 4-> usuwanie(kalendarz,scanner,2);//Zadania
            case 5 -> show(kalendarz.filtrListBy(a-> a instanceof Spotkanie,ktoryDzien(scanner)));
            case 6 -> show(kalendarz.filtrListBy(a-> a instanceof Zadanie,ktoryDzien(scanner)));
            case 7 -> {
                String priorytet = podajPriorytet(scanner);
                show(kalendarz.filtrListBy(a -> a instanceof Spotkanie && ((Spotkanie) a).getPriorytet().equalsIgnoreCase(priorytet),ktoryDzien(scanner)));
            }
            case 8 -> {
                String status = podajStatus(scanner);
                show(kalendarz.filtrListBy(a -> a instanceof Zadanie && ((Zadanie) a).getStatus().equalsIgnoreCase(status),ktoryDzien(scanner)));
            }
            case 9 -> {
                LocalTime poczatek = podajCzasy(scanner);
                String priorytet = podajPriorytet(scanner);
                show(kalendarz.filtrListBy(a -> a instanceof Spotkanie && (a.getCzasPoczatku().isAfter(poczatek) || a.getCzasKonca().equals(poczatek)) && ((Spotkanie) a).getPriorytet().equalsIgnoreCase(priorytet),ktoryDzien(scanner)));
            }
            case 10 -> {
                LocalTime koniec = podajCzasy(scanner);
                String status = podajPriorytet(scanner);
                show(kalendarz.filtrListBy(a -> a instanceof Zadanie && (a.getCzasPoczatku().isBefore(koniec) || a.getCzasKonca().equals(koniec)) && ((Zadanie) a).getStatus().equalsIgnoreCase(status),ktoryDzien(scanner)));
            }

            case 0 -> System.out.println("bye!");
            default -> System.out.println("zla wartosc");
        }
    }
    private static void show(ArrayList<Wydarzenie> dziennik){
        for(Wydarzenie wartosci : dziennik){
            System.out.println(wartosci);
        }
    }
    private static void stworzSpotkanie(Scanner scanner, Kalendarz kalendarz){
        Scanner sc2 = new Scanner(System.in);
        int dzien = ktoryDzien(scanner);
        String opis;
        System.out.println("Podaj opis spotkania: ");
        opis = sc2.nextLine();
        System.out.println("Podaj czas zaczecia pozniejszy niz 08:00: ");
        LocalTime czas1 = LocalTime.parse(scanner.next());
        System.out.println("Podaj czas zakonczenia: ");
        LocalTime czas2 = LocalTime.parse(scanner.next());
        System.out.println("Podaj priorytet High,Medium,Low: ");
        String priorytet = scanner.next();

        Spotkanie spotkanie = new Spotkanie(opis,czas1,czas2,priorytet);
        kalendarz.dodajWydarzenie(spotkanie,dzien);
    }
    private static void stworzZadanie(Scanner scanner, Kalendarz kalendarz){
        Scanner sc2 = new Scanner(System.in);
        int dzien = ktoryDzien(scanner);
        String opis;
        System.out.println("Podaj opis zadania: ");
        opis = sc2.nextLine();
        System.out.println("Podaj czas zaczecia: ");
        LocalTime czas1 = LocalTime.parse(scanner.next());
        System.out.println("Podaj czas zakonczenia: ");
        LocalTime czas2 = LocalTime.parse(scanner.next());
        System.out.println("Podaj status: planowane, potwierdzone, realizowane, wykonane ");
        String status = scanner.next();
        Zadanie zadanie = new Zadanie(opis,czas1,czas2,status);
        kalendarz.dodajWydarzenie(zadanie,dzien);
    }
    private static LocalTime podajCzasy(Scanner scanner){
        System.out.println("Podaj Godzine: ");
        return LocalTime.parse(scanner.next());
    }
    private  static String podajPriorytet(Scanner scanner){
        System.out.println("Podaj priorytet: High,Medium,Low ");
        return scanner.next();
    }
    private  static String podajStatus(Scanner scanner){
        System.out.println("Podaj Status : planowane, realizowane,wykonane ");
        return scanner.next();
    }
    private static int ktoreSpotkanie(Scanner scanner){
        int ktora;
        System.out.println("podaj ktore Spotkanie chcesz usunac");
        ktora = scanner.nextInt();
        return ktora-1;
    }
    private static int ktoryDzien(Scanner scanner){
        int ktory;
        System.out.println("Podaj dzien: ");
        ktory = scanner.nextInt();
        return ktory - 1;
    }
    private static void usuwanie(Kalendarz kalendarz,Scanner scanner, int typ){
        int dzien = ktoryDzien(scanner);
        int ktore = ktoreSpotkanie(scanner);
        if(typ == 1){
            ArrayList<Integer> indeksy = new ArrayList<>(kalendarz.indexFiltr(a->a instanceof Spotkanie,dzien));
            int ktore2 = indeksy.get(ktore);
            kalendarz.removeWydarzenie(dzien, kalendarz.getWydarzenie(dzien).get(ktore2));
        }else if(typ == 2){
            ArrayList<Integer> indeksy = new ArrayList<>(kalendarz.indexFiltr(a->a instanceof Zadanie,dzien));
            int ktore2 = indeksy.get(ktore);
            kalendarz.removeWydarzenie(dzien, kalendarz.getWydarzenie(dzien).get(ktore2));
        }
    }
}