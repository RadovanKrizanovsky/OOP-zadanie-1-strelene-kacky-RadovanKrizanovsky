package sk.stuba.fei.uim.oop.karty;

import sk.stuba.fei.uim.oop.hra.StreleneKackyLite;

public abstract class AkcnaKarta extends Karta {
    public AkcnaKarta(String vypisKarty) {
        super(vypisKarty);
    }
    public abstract void pouziKartu(StreleneKackyLite streleneKackyLite);
    public abstract boolean daSaKartaPouzit(StreleneKackyLite streleneKackyLite);
}
