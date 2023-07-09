package sk.stuba.fei.uim.oop.vynimky;

public class VynimkaPouzivamTurboKackuNaVodu extends Exception{
    public VynimkaPouzivamTurboKackuNaVodu() {
        super("Turbo kacku moze pouzit len na kacku");
    }

    public void vypisChyboveHlasenie() {
        System.out.println("::::::::::::::::::::::::::::::::::::::::::::");
        System.out.println("| " + super.getMessage());
        System.out.println("::::::::::::::::::::::::::::::::::::::::::::");
    }
}
