package sk.stuba.fei.uim.oop.karty.akcne;

import sk.stuba.fei.uim.oop.hra.StreleneKackyLite;
import sk.stuba.fei.uim.oop.karty.AkcnaKarta;
import java.util.Collections;

public class AkcnaKartaRosambo extends AkcnaKarta {
    public AkcnaKartaRosambo(String vypisKarty) {
        super(vypisKarty);
    }

    @Override
    public void pouziKartu(StreleneKackyLite streleneKackyLite) {
        Collections.shuffle(streleneKackyLite.vratAktualnyRybnik());
    }

    @Override
    public boolean daSaKartaPouzit(StreleneKackyLite streleneKackyLite) {
        return true;
    }
}
