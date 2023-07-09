package sk.stuba.fei.uim.oop.hraci;

import sk.stuba.fei.uim.oop.karty.AkcnaKarta;
import sk.stuba.fei.uim.oop.utility.KeyboardInput;
import sk.stuba.fei.uim.oop.vynimky.VynimkaPrilisDlheMeno;
import sk.stuba.fei.uim.oop.vynimky.VynimkaZlyVyber;

import java.util.ArrayList;
import java.util.Objects;

public class VsetciHraci {
    private final ArrayList<Hrac> hraci;
    private int pocetHracov;

    public VsetciHraci() {
        pocetHracov = 0;
        hraci = new ArrayList<>();
    }

    public void zapisHracov(){
        do {
            try {
                System.out.println("| Hra Strelene Kacky Lite");
                pocetHracov = KeyboardInput.readInt("| Kolko hracov bude hrat hru? Pocet hracov");
                if (pocetHracov < 2 || pocetHracov > 6) {
                    throw new VynimkaZlyVyber("Pocet hracov musi byt aspon 2 a najviac 6");
                }
            }catch (VynimkaZlyVyber vynimka) {
                vynimka.vypisChyboveHlasenie();
            }
        } while(pocetHracov < 2 || pocetHracov > 6);

        for (int i = 0; i < pocetHracov; i++) {
            String meno = "";
            boolean zaciatok = true;
            boolean menoUzExistuje = false;
            while (zaciatok || meno.length() > 30 || menoUzExistuje) {
                zaciatok = false;
                menoUzExistuje =false;
                try {
                    System.out.print("| Meno " + (i+1) + ". hraca: ");
                    meno = KeyboardInput.readString();
                    if (meno.length() > 30) {
                        throw new VynimkaPrilisDlheMeno();
                    }
                    for (Hrac hrac : this.hraci) {
                        if (Objects.equals(hrac.vratMeno(), meno)) {
                            menoUzExistuje = true;
                            throw new VynimkaZlyVyber("Hrac s takymto menom uz existuje");
                        }
                    }
                } catch (VynimkaPrilisDlheMeno vynimka) {
                    vynimka.vypisChyboveHlasenie();
                } catch (VynimkaZlyVyber vynimka){
                    vynimka.vypisChyboveHlasenie();
                }
            }
            if (meno.length() < 1) {
                meno = ("Cislo " + (i+1));
            }
            this.hraci.add(new Hrac(meno, 5));
        }
    }

    public int kolkoEsteHra() {
        int pocetAktivnych = 0;
        for (int i = 0; i < hraci.size(); i++) {
            if (this.vratPocetZivotovHraca(i) > 0) {
                pocetAktivnych++;
            }
        }
        return pocetAktivnych;
    }

    public Hrac vratHraca(int index) {
        return this.hraci.get(index);
    }

    public int vratPocetHracov() {
        return this.hraci.size();
    }

    public int vratPocetZivotovHraca(int index) {
        return this.hraci.get(index).vratPocetZivotov();
    }

    public void odoberZivotHracoviCislo(int index){
        this.hraci.get(index).odoberZivot();
    }

    public String vratMenoHraca(int index) {
        return this.hraci.get(index).vratMeno();
    }

    public void pridajHracoviAkcnuKartu(AkcnaKarta akcnaKarta, int indexHraca) {
        this.hraci.get(indexHraca).pridajAkcnuKartu(akcnaKarta);
    }
}
