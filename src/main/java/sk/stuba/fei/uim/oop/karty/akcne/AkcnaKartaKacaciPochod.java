package sk.stuba.fei.uim.oop.karty.akcne;

import sk.stuba.fei.uim.oop.hra.StreleneKackyLite;
import sk.stuba.fei.uim.oop.karty.AkcnaKarta;

public class AkcnaKartaKacaciPochod extends AkcnaKarta {
    public AkcnaKartaKacaciPochod(String vypisKarty) {
        super(vypisKarty);
    }

    @Override
    public void pouziKartu(StreleneKackyLite streleneKackyLite) {
        streleneKackyLite.pridajKackuVoduNaSpodokBalika(streleneKackyLite.vratPrvuKartuHraciaPlocha());
        streleneKackyLite.odstranPrvuKartuRybnik();
        streleneKackyLite.pridajKackuVoduNaSpodokBalika(streleneKackyLite.vratPrvuKartuBalikKackyVody());
        streleneKackyLite.odstranPrvuKartuBalikKackyVody();
    }

    @Override
    public boolean daSaKartaPouzit(StreleneKackyLite streleneKackyLite) {
        return true;
    }
}
