package sk.stuba.fei.uim.oop.vynimky;

public class VynimkaPrilisDlheMeno extends Exception {
    public VynimkaPrilisDlheMeno() {
        super("Meno musi pozostavat najviac z 30 znakov");
    }

    public void vypisChyboveHlasenie() {
        System.out.println("::::::::::::::::::::::::::::::::::::::::::::");
        System.out.println("| " + super.getMessage());
        System.out.println("::::::::::::::::::::::::::::::::::::::::::::");
    }
}
