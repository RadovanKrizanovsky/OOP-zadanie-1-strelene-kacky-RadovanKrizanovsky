package sk.stuba.fei.uim.oop.karty.akcne;

import sk.stuba.fei.uim.oop.hra.StreleneKackyLite;
import sk.stuba.fei.uim.oop.karty.AkcnaKarta;

public class AkcnaKartaKacaciTanec extends AkcnaKarta {
    public AkcnaKartaKacaciTanec(String vypisKarty) {
        super(vypisKarty);
    }

    @Override
    public void pouziKartu(StreleneKackyLite streleneKackyLite) {
        streleneKackyLite.vratKartyKackyVody().addAll(streleneKackyLite.vratAktualnyRybnik());
        streleneKackyLite.vratAktualnyRybnik().clear();
        streleneKackyLite.vratBalikKackyVody().premiesaj();
        streleneKackyLite.vylozKartyDoAktualnehoRybnika();
    }

    @Override
    public boolean daSaKartaPouzit(StreleneKackyLite streleneKackyLite) {
        return true;
    }
}
