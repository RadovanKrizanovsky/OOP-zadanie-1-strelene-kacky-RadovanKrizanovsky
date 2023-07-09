package sk.stuba.fei.uim.oop.karty.baliky;

import sk.stuba.fei.uim.oop.hraci.VsetciHraci;
import sk.stuba.fei.uim.oop.karty.Karta;
import sk.stuba.fei.uim.oop.karty.KartaKacka;
import sk.stuba.fei.uim.oop.karty.KartaVody;
import sk.stuba.fei.uim.oop.rybnik.HraciaPlocha;

import java.util.ArrayList;
import java.util.Collections;

public class BalikKackyVody {
    private final ArrayList<Karta> balik;

    public BalikKackyVody() {
        balik = new ArrayList<>();
    }

    public ArrayList<Karta> vratBalik() {
        return balik;
    }

    public void naplnPremiesajBalik(VsetciHraci vsetciHraci) {
        for (int i = 0; i < vsetciHraci.vratPocetHracov(); i++) {
            for (int j = 0; j < 5; j++) {
                this.pridajKartuNaSpodok(new KartaKacka(("Kacka Hraca " + vsetciHraci.vratMenoHraca(i))));
            }
        }
        for (int i = 0; i < 5; i++) {
            this.pridajKartuNaSpodok(new KartaVody("Voda"));
        }
        this.premiesaj();
    }

    public void premiesaj(){
        Collections.shuffle(this.balik);
    }

    public Karta vratPrvuKartu() {
        return this.balik.get(0);
    }

    public void odstranPrvuKartu() {
        this.balik.remove(0);
    }

    public void pridajKartuNaSpodok(Karta karta) {
        this.balik.add(karta);
    }

    public void vylozKarty(HraciaPlocha hraciaPlocha) {
        for (int i = 0; i < 6; i++) {
            hraciaPlocha.pridajKartuNaKoniec(this.vratPrvuKartu());
            this.odstranPrvuKartu();
        }
    }
}
