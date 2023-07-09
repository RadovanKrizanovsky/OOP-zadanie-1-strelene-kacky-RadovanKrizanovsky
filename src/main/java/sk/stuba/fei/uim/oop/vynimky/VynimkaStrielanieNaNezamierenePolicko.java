package sk.stuba.fei.uim.oop.vynimky;

public class VynimkaStrielanieNaNezamierenePolicko extends Exception{
    public VynimkaStrielanieNaNezamierenePolicko() {
        super("Vystrelit mozes len na zamierene policka");
    }

    public void vypisChyboveHlasenie() {
        System.out.println("::::::::::::::::::::::::::::::::::::::::::::");
        System.out.println("| " + super.getMessage());
        System.out.println("::::::::::::::::::::::::::::::::::::::::::::");
    }
}
