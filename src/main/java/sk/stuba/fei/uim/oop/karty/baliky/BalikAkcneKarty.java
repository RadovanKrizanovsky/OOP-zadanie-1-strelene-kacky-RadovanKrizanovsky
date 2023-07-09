package sk.stuba.fei.uim.oop.karty.baliky;

import sk.stuba.fei.uim.oop.hraci.VsetciHraci;
import sk.stuba.fei.uim.oop.karty.AkcnaKarta;
import sk.stuba.fei.uim.oop.karty.akcne.*;

import java.util.ArrayList;
import java.util.Collections;

public class BalikAkcneKarty {
    private final ArrayList<AkcnaKarta> balik;

    public BalikAkcneKarty() {
        balik = new ArrayList<>();
    }

    public void naplnPremiesajBalik() {
        for (int i = 0; i < 10; i++) {
            this.balik.add(new AkcnaKartaZamierit("Zamierit"));
        }
        for (int i = 0; i < 12; i++) {
            this.balik.add(new AkcnaKartaVystrelit("Vystrelit"));
        }
        this.balik.add(new AkcnaKartaDivokyBill("Divoky Bill"));
        this.balik.add(new AkcnaKartaDivokyBill("Divoky Bill"));
        for (int i = 0; i < 6; i++) {
            this.balik.add(new AkcnaKartaKacaciPochod("Kacaci Pochod"));
        }
        this.balik.add(new AkcnaKartaTurboKacka("Turbo Kacka"));
        this.balik.add(new AkcnaKartaRosambo("Rosambo"));
        this.balik.add(new AkcnaKartaRosambo("Rosambo"));
        this.balik.add(new AkcnaKartaKacaciTanec("Kacaci Tanec"));
        this.premiesaj();
    }

    private void premiesaj(){
        Collections.shuffle(this.balik);
    }

    public void rozdajHracomKarty(VsetciHraci vsetciHraci){
        for (int i = 0; i < vsetciHraci.vratPocetHracov(); i++) {
            for (int j = 0; j < 3; j++) {
                vsetciHraci.pridajHracoviAkcnuKartu(this.vratPrvuKartu(), i);
                this.odstranPrvuKartu();
            }
        }
    }

    public void vlozKartuNaSpodokBalika(AkcnaKarta karta) {
        this.balik.add(karta);
    }

    public void odstranPrvuKartu() {
        this.balik.remove(0);
    }

    public AkcnaKarta vratPrvuKartu() {
        return this.balik.get(0);
    }
}
