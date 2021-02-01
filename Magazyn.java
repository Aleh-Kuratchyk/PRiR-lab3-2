public class Magazyn {
    static int MAGAZYN = 1;
    static int ZALADUNEK = 2;
    static int ROZLADUNEK = 3;
    static int WYJAZD = 4;
    static int POMOC = -2;
    private int iloscMiejsc;
    private int iloscMiejscZajetych;
    private int iloscTransportu;

    public Magazyn(int iloscMiejsc, int iloscTransportu) {
        this.iloscMiejsc = iloscMiejsc;
        this.iloscTransportu = iloscTransportu;
        this.iloscMiejscZajetych = 0;
    }

    public synchronized int wyjedzDoMagazynu() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException var2) {
        }

        if (this.iloscMiejscZajetych < this.iloscMiejsc) {
            ++this.iloscMiejscZajetych;
            System.out.println("TIR wjezdza do magazynu na miejsce numer " + this.iloscMiejscZajetych);
            return MAGAZYN;
        } else {
            return ROZLADUNEK;
        }
    }

    public synchronized int wyjedz(int numer) {
        --this.iloscMiejscZajetych;
        System.out.println("TIR o numerze " + numer + " wyjezdza z magazynu");
        return WYJAZD;
    }

    public synchronized int pomoc(int numer) {
        if (this.iloscMiejscZajetych < this.iloscMiejsc) {
            System.out.println("TIR o numerze " + numer + " jest gotowy do wyjazdu.");
            return MAGAZYN;
        } else {
            System.out.println("TIR o numerze " + numer + " oczekuje na pomoc.");
            return POMOC;
        }
    }
}