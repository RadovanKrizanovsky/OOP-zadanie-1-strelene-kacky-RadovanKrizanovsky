package sk.stuba.fei.uim.oop.hraci;

import sk.stuba.fei.uim.oop.hra.StreleneKackyLite;
import sk.stuba.fei.uim.oop.karty.AkcnaKarta;
import sk.stuba.fei.uim.oop.karty.baliky.BalikAkcneKarty;

import java.util.ArrayList;

public class Hrac {
    private final String meno;
    private int pocetZivotov;
    private final ArrayList<AkcnaKarta> akcneKarty;

    public Hrac(String meno, int pocetZivotov) {
        this.meno = meno;
        this.pocetZivotov = pocetZivotov;
        akcneKarty = new ArrayList<>();
    }

    public String vratMeno() {
        return meno;
    }

    public int vratPocetZivotov() {
        return pocetZivotov;
    }

    public void odoberZivot() {
        this.pocetZivotov--;
    }

    public AkcnaKarta vratAkcnuKartu(int index) {
        return akcneKarty.get(index);
    }

    public void pridajAkcnuKartu(AkcnaKarta akcnaKarta) {
        this.akcneKarty.add(akcnaKarta);
    }

    public void odoberAkcnuKartu(int index) {
        this.akcneKarty.remove(index);
    }

    public void vypisAkcneKarty() {
        System.out.print("| Karty Hraca " + this.meno + ": ");
        for (int i = 0; i < 3; i++) {
            System.out.print("|" + (i+1) + "|" + this.akcneKarty.get(i).vratVypisKarty() + "| ");
        }
        System.out.println();
    }

    public void vypisPoradiePlusZivoty() {
        System.out.print("| Na rade je " + this.meno);
        System.out.print(" (Ma este " + this.pocetZivotov + " kaciek)\n");
    }

    public boolean mozePouzitNejakuKartu(StreleneKackyLite streleneKackyLite) {
        for (int i = 0; i < 3; i++) {
            if (this.akcneKarty.get(i).daSaKartaPouzit(streleneKackyLite)) {
                return true;
            }
        }
        return false;
    }

    public void akMasKartyVratIchDoBalika(BalikAkcneKarty balikAkcneKarty) {
        if (this.akcneKarty.size() > 0) {
            for (AkcnaKarta akcnaKarta : this.akcneKarty) {
                balikAkcneKarty.vlozKartuNaSpodokBalika(akcnaKarta);
            }
            this.akcneKarty.clear();
        }
    }
}
