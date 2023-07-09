package sk.stuba.fei.uim.oop.vynimky;

public class VynimkaMierenieNaUzZamierene extends Exception{
    public VynimkaMierenieNaUzZamierene() {
        super("Toto policko uz zamierene je");
    }

    public void vypisChyboveHlasenie() {
        System.out.println("::::::::::::::::::::::::::::::::::::::::::::");
        System.out.println("| " + super.getMessage());
        System.out.println("::::::::::::::::::::::::::::::::::::::::::::");
    }
}
