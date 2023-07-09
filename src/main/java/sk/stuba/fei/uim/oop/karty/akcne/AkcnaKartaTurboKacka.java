package sk.stuba.fei.uim.oop.karty.akcne;

import sk.stuba.fei.uim.oop.hra.StreleneKackyLite;
import sk.stuba.fei.uim.oop.karty.AkcnaKarta;
import sk.stuba.fei.uim.oop.karty.Karta;
import sk.stuba.fei.uim.oop.utility.KeyboardInput;
import sk.stuba.fei.uim.oop.vynimky.VynimkaPouzivamTurboKackuNaVodu;
import sk.stuba.fei.uim.oop.vynimky.VynimkaZlyVyber;

import java.util.ArrayList;

public class AkcnaKartaTurboKacka extends AkcnaKarta {
    public AkcnaKartaTurboKacka(String vypisKarty) {
        super(vypisKarty);
    }

    @Override
    public void pouziKartu(StreleneKackyLite streleneKackyLite) {
        int ktoruKacku = 0;
        boolean pouzivamToNaKacku = false;
        while ((ktoruKacku < 1 || ktoruKacku > 6) || !pouzivamToNaKacku) {
            try {
                ktoruKacku = KeyboardInput.readInt("| Pouzit turbo kacku na policko cislo");
                if (ktoruKacku < 1 || ktoruKacku > 6) {
                    throw new VynimkaZlyVyber("| Turbo kacku mozes pouzit len na policko 1 az 6");
                }
                pouzivamToNaKacku = (streleneKackyLite.jePrvePismenoVypisuKartyK(ktoruKacku-1));
                if (!pouzivamToNaKacku) {
                    throw new VynimkaPouzivamTurboKackuNaVodu();
                }
            } catch (VynimkaZlyVyber vynimka) {
                vynimka.vypisChyboveHlasenie();
            } catch (VynimkaPouzivamTurboKackuNaVodu vynimka) {
                vynimka.vypisChyboveHlasenie();
            }
        }
        ArrayList<Karta> novyRybnik = new ArrayList<>();
        novyRybnik.add(streleneKackyLite.vratRybnikovuKartu(ktoruKacku-1));
        streleneKackyLite.odstranRybnikovePolicko(ktoruKacku-1);
        novyRybnik.addAll(streleneKackyLite.vratAktualnyRybnik());
        streleneKackyLite.zmenAktualnyRybnik(novyRybnik);
    }

    @Override
    public boolean daSaKartaPouzit(StreleneKackyLite streleneKackyLite) {
        for (int i = 0; i < streleneKackyLite.vratVelkostAktualnehoRybnika(); i++) {
            if (streleneKackyLite.jePrvePismenoVypisuKartyK(i)) {
                return true;
            }
        }
        return false;
    }
}
