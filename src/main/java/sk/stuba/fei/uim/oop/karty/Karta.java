package sk.stuba.fei.uim.oop.karty;

public abstract class Karta {
    private final String vypisKarty;

    public Karta(String vypisKarty) {
        this.vypisKarty = vypisKarty;
    }

    public String vratVypisKarty() {
        return vypisKarty;
    }
}
