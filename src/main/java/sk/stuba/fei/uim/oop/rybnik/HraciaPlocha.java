package sk.stuba.fei.uim.oop.rybnik;

import sk.stuba.fei.uim.oop.karty.Karta;
import sk.stuba.fei.uim.oop.karty.baliky.BalikKackyVody;

import java.util.ArrayList;

public class HraciaPlocha {
    private ArrayList<Karta> rybnik = new ArrayList<>();
    private boolean[] zamierenie;

    public HraciaPlocha() {
        boolean[] aniJednoZamierene = {false, false, false, false, false, false};
        this.nastavZamierenie(aniJednoZamierene);
    }

    private void nastavZamierenie(boolean[] zamierenie) {
        this.zamierenie = zamierenie;
    }

    public void nastavZamierenie(int index, boolean zamierenie) {
        this.zamierenie[index] = zamierenie;
    }

    public ArrayList<Karta> vratRybnik() {
        return rybnik;
    }

    public boolean[] vratZamierenia() {
        return zamierenie;
    }

    public char vratPrvePismenoVypisuKartyNaPolicku(int index){
        return this.vratVypisKarty(index).charAt(0);
    }

    public String zistiMenoMajitelaKarty(int index) {
        return this.rybnik.get(index).vratVypisKarty().substring(12);
    }

    public void vypisRybnika() {
        System.out.println("------------------(Rybnik)------------------");
        for (int i = 0; i < 6; i++) {
            System.out.print("|" + (i+1) + ".| ");
            System.out.print(this.vratVypisKarty(i));
            this.medzeri(20 - this.vratDlzkuVypisuKarty(i));
            System.out.print("-> ");
            if (this.zamierenie[i]) {
                System.out.print("Zamierene");
            } else {
                System.out.print("Nezamierene");
            }
            System.out.print(" |" + (i+1) + ".|\n");
        }
        System.out.println("--------------------------------------------");
    }

    private void medzeri(int pocetMedzier) {
        for (int i = 0; i < pocetMedzier; i++) {
            System.out.print(" ");
        }
    }

    public void doplnKartuAkTreba (BalikKackyVody balikKackyVody) {
        if (this.vratVelkostRybnika() < 6) {
            this.pridajKartuNaKoniec(balikKackyVody.vratPrvuKartu());
            balikKackyVody.odstranPrvuKartu();
        }
    }

    private String vratVypisKarty(int index){
        return this.rybnik.get(index).vratVypisKarty();
    }

    private int vratDlzkuVypisuKarty(int index) {
        return this.vratVypisKarty(index).length();
    }

    private int vratVelkostRybnika() {
        return this.rybnik.size();
    }

    public void zmenRybnik(ArrayList<Karta> rybnik) {
        this.rybnik = rybnik;
    }

    public void pridajKartuNaKoniec(Karta karta) {
        this.rybnik.add(karta);
    }

    public Karta vratKartuCislo(int index){
        return this.rybnik.get(index);
    }

    public Karta vratPrvuKartu() {
        return this.vratKartuCislo(0);
    }

    public void odstranKartu(int index){
        this.rybnik.remove(index);
    }

    public void odstranPrvuKartu() {
        this.odstranKartu(0);
    }
}