package sk.stuba.fei.uim.oop.vynimky;

public class VynimkaZlyVyber extends Exception {
    public VynimkaZlyVyber(String message) {
        super(message);
    }

    public void vypisChyboveHlasenie() {
        System.out.println("::::::::::::::::::::::::::::::::::::::::::::");
        System.out.println("| " + super.getMessage());
        System.out.println("::::::::::::::::::::::::::::::::::::::::::::");
    }
}
