import java.util.Random;

public class Transport extends Thread {
    static int MAGAZYN = 1;
    static int ZALADUNEK = 2;
    static int ROZLADUNEK = 3;
    static int WYJAZD = 4;
    static int TANKOWANIE = 1000;
    static int KONIEC_PALIWA = -1;
    static int POMOC = -2;
    private int paliwo;
    private int nr;
    private int stan;
    private Magazyn mag;
    Random rand;

    public Transport(int nr, int paliwo, Magazyn mag) {
        this.nr = nr;
        this.paliwo = paliwo;
        this.mag = mag;
        this.stan = ZALADUNEK;
        this.rand = new Random();
    }

    public void run() {
        while(true) {
            if (this.stan == MAGAZYN) {
                if (this.rand.nextInt(2) == 1) {
                    this.stan = WYJAZD;
                    this.paliwo = TANKOWANIE;
                    System.out.println("TIR o numerze " + this.nr + " wjezdza.");
                    this.stan = this.mag.wyjedz(this.nr);
                } else {
                    System.out.println("Poczekam jeszcze chwile.");
                }
            } else if (this.stan == WYJAZD) {
                System.out.println("Wyjechalem, TIR numer " + this.nr);
                this.stan = ZALADUNEK;
            } else if (this.stan == ZALADUNEK) {
                this.paliwo = this.rand.nextInt(300);
                System.out.println("TIR o numerze " + this.nr + " tankuje.");
                if (this.paliwo < 400) {
                    this.stan = ROZLADUNEK;
                } else {
                    try {
                        sleep(1000L);
                    } catch (InterruptedException var2) {
                    }
                }
            } else if (this.stan == ROZLADUNEK) {
                System.out.println("TIR o numerze " + this.nr + " zbliza sie do magazynu");
                this.stan = this.mag.wyjedzDoMagazynu();
                if (this.stan == ROZLADUNEK) {
                    this.paliwo -= this.rand.nextInt(300);
                    System.out.println("Rezerwa " + this.paliwo);
                    if (this.paliwo < 0) {
                        this.stan = KONIEC_PALIWA;
                    }
                }
            } else if (this.stan == KONIEC_PALIWA) {
                System.out.println("TIR o numerze " + this.nr + " wymaga pomocy z magazynu");
                this.stan = POMOC;
            } else if (this.stan == POMOC) {
                try {
                    sleep(1000L);
                } catch (InterruptedException var3) {
                }

                this.mag.pomoc(this.nr);
            }
        }
    }
}
