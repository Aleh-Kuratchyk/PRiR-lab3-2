public class Main {
    static int iloscMiejsc = 10;
    static int iloscTirow = 50;

    public Main() {
    }

    public static void main(String[] args) {
        Magazyn mag = new Magazyn(iloscMiejsc, iloscTirow);

        for(int i = 0; i < iloscTirow; ++i) {
            (new Transport(i, 1000, mag)).start();
        }

    }
}