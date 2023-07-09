package sk.stuba.fei.uim.oop.hra;

import sk.stuba.fei.uim.oop.hraci.VsetciHraci;
import sk.stuba.fei.uim.oop.karty.Karta;
import sk.stuba.fei.uim.oop.karty.baliky.BalikAkcneKarty;
import sk.stuba.fei.uim.oop.karty.baliky.BalikKackyVody;
import sk.stuba.fei.uim.oop.rybnik.HraciaPlocha;
import sk.stuba.fei.uim.oop.utility.KeyboardInput;
import sk.stuba.fei.uim.oop.vynimky.VynimkaHraSaSkoncila;
import sk.stuba.fei.uim.oop.vynimky.VynimkaZlyVyber;

import java.util.ArrayList;


public class StreleneKackyLite {
    private final VsetciHraci vsetciHraci;
    private final BalikKackyVody balikKackyVody;
    private final BalikAkcneKarty balikAkcneKarty;
    private final HraciaPlocha hraciaPlocha;

    public StreleneKackyLite() {
        vsetciHraci = new VsetciHraci();
        balikKackyVody = new BalikKackyVody();
        balikAkcneKarty = new BalikAkcneKarty();
        hraciaPlocha = new HraciaPlocha();
        pripravaHry();
        zacniHru();
    }

    private void pripravaHry() {
        System.out.println("--------------------------------------------");
        vsetciHraci.zapisHracov();
        balikKackyVody.naplnPremiesajBalik(this.vsetciHraci);
        balikAkcneKarty.naplnPremiesajBalik();

        for (int i = 0; i < 6; i++) {
            this.hraciaPlocha.pridajKartuNaKoniec(this.vratPrvuKartuBalikKackyVody());
            this.odstranPrvuKartuBalikKackyVody();
        }
        this.balikAkcneKarty.rozdajHracomKarty(this.vsetciHraci);
    }

    private void zacniHru() {
        try {
            while (this.vsetciHraci.kolkoEsteHra() > 1) {
                for (int i = 0; i < this.vratAktualnyPocetHracov(); i++) {
                    if (this.vsetciHraci.vratPocetZivotovHraca(i) > 0) {
                        if (this.vsetciHraci.vratHraca(i).mozePouzitNejakuKartu(this)) {
                            int moznost = 0;
                            boolean daSaKartaPouzit = true;
                            while (moznost < 1 || moznost > 3 || !daSaKartaPouzit) {
                                moznost = 0;
                                try {
                                    this.hraciaPlocha.vypisRybnika();
                                    this.vsetciHraci.vratHraca(i).vypisPoradiePlusZivoty();
                                    this.vsetciHraci.vratHraca(i).vypisAkcneKarty();
                                    moznost = KeyboardInput.readInt("| Pouzit kartu cislo");
                                    if (moznost < 1 || moznost > 3) {
                                        throw new VynimkaZlyVyber("Zly vyber");
                                    }
                                    daSaKartaPouzit = this.vsetciHraci.vratHraca(i).vratAkcnuKartu(moznost-1).daSaKartaPouzit(this);
                                    if (!daSaKartaPouzit) {
                                        throw new VynimkaZlyVyber("Zly vyber");
                                    }
                                } catch (VynimkaZlyVyber vynimka) {
                                    vynimka.vypisChyboveHlasenie();
                                }
                            }
                            this.vsetciHraci.vratHraca(i).vratAkcnuKartu(moznost-1).pouziKartu(this);
                            this.balikAkcneKarty.vlozKartuNaSpodokBalika(this.vsetciHraci.vratHraca(i).vratAkcnuKartu(moznost-1));
                            this.vsetciHraci.vratHraca(i).odoberAkcnuKartu(moznost-1);
                            this.vsetciHraci.vratHraca(i).pridajAkcnuKartu(this.balikAkcneKarty.vratPrvuKartu());
                            this.balikAkcneKarty.odstranPrvuKartu();
                            this.hraciaPlocha.doplnKartuAkTreba(this.balikKackyVody);
                            if (vsetciHraci.kolkoEsteHra() < 2) {
                                throw new VynimkaHraSaSkoncila();
                            }
                        } else {
                            this.hraciaPlocha.vypisRybnika();
                            this.vsetciHraci.vratHraca(i).vypisPoradiePlusZivoty();
                            this.vsetciHraci.vratHraca(i).vypisAkcneKarty();
                            int ktoruVratit = 0;
                            while (ktoruVratit < 1 || ktoruVratit > 3) {
                                ktoruVratit = 0;
                                try {
                                    System.out.println("--------------------------------------------");
                                    System.out.println("| Nemozes pouzit ziadnu kartu, niektoru vrat do balika a vyber si z vrchu novu");
                                    ktoruVratit = KeyboardInput.readInt("| Chcem vratit kartu cislo");
                                    if (ktoruVratit < 1 || ktoruVratit > 3) {
                                        throw new VynimkaZlyVyber("Zly vyber");
                                    }
                                } catch (VynimkaZlyVyber vynimka) {
                                    vynimka.vypisChyboveHlasenie();
                                }
                            }
                            this.balikAkcneKarty.vlozKartuNaSpodokBalika(this.vsetciHraci.vratHraca(i).vratAkcnuKartu(ktoruVratit-1));
                            this.vsetciHraci.vratHraca(i).odoberAkcnuKartu(ktoruVratit-1);
                            this.vsetciHraci.vratHraca(i).pridajAkcnuKartu(balikAkcneKarty.vratPrvuKartu());
                            this.balikAkcneKarty.odstranPrvuKartu();
                        }
                    } else {
                        this.vsetciHraci.vratHraca(i).akMasKartyVratIchDoBalika(balikAkcneKarty);
                    }
                }
            }
        } catch (VynimkaHraSaSkoncila vynimka) {
            vynimka.vypisChyboveHlasenie();
            vynimka.zavercnyVypis(vsetciHraci);
        }
    }

    private boolean[] vratZamierenia() {
        return this.hraciaPlocha.vratZamierenia();
    }

    public String vratMenoHraca(int index) {
        return this.vsetciHraci.vratMenoHraca(index);
    }

    public int vratAktualnyPocetHracov() {
        return this.vsetciHraci.vratPocetHracov();
    }

    public BalikKackyVody vratBalikKackyVody() {
        return balikKackyVody;
    }

    public void nastavZamierenieNaPolicku(int index, boolean hodnota){
        this.hraciaPlocha.nastavZamierenie(index, hodnota);
    }

    public boolean jePrvePismenoVypisuKartyK(int index) {
        return (this.hraciaPlocha.vratPrvePismenoVypisuKartyNaPolicku(index) == 'K');
    }

    public void odoberHracoviZivot(int index){
        this.vsetciHraci.odoberZivotHracoviCislo(index);
    }

    public void odstranRybnikovePolicko(int index){
        this.hraciaPlocha.odstranKartu(index);
    }

    public String vratMenoMajitelaKarty(int index) {
        return this.hraciaPlocha.zistiMenoMajitelaKarty(index);
    }

    public boolean vratZamierenieNaPolicku(int index) {
        return this.vratZamierenia()[index];
    }

    public ArrayList<Karta> vratKartyKackyVody() {
        return balikKackyVody.vratBalik();
    }

    public Karta vratRybnikovuKartu(int index) {
        return this.hraciaPlocha.vratKartuCislo(index);
    }

    public ArrayList<Karta> vratAktualnyRybnik() {
        return this.hraciaPlocha.vratRybnik();
    }

    public void zmenAktualnyRybnik(ArrayList<Karta> novyRybnik) {
        this.hraciaPlocha.zmenRybnik(novyRybnik);
    }

    public int vratVelkostAktualnehoRybnika() {
        return this.vratAktualnyRybnik().size();
    }

    public void vylozKartyDoAktualnehoRybnika() {
        this.balikKackyVody.vylozKarty(this.hraciaPlocha);
    }

    public void pridajKackuVoduNaSpodokBalika(Karta karta) {
        this.balikKackyVody.pridajKartuNaSpodok(karta);
    }

    public Karta vratPrvuKartuHraciaPlocha() {
        return this.hraciaPlocha.vratPrvuKartu();
    }

    public void odstranPrvuKartuRybnik() {
        this.hraciaPlocha.odstranPrvuKartu();
    }

    public Karta vratPrvuKartuBalikKackyVody() {
         return this.balikKackyVody.vratPrvuKartu();
    }

    public void odstranPrvuKartuBalikKackyVody() {
        this.balikKackyVody.odstranPrvuKartu();
    }
}
