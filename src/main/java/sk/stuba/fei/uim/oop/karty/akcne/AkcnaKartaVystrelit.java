package sk.stuba.fei.uim.oop.karty.akcne;

import sk.stuba.fei.uim.oop.hra.StreleneKackyLite;
import sk.stuba.fei.uim.oop.karty.AkcnaKarta;
import sk.stuba.fei.uim.oop.utility.KeyboardInput;
import sk.stuba.fei.uim.oop.vynimky.VynimkaStrielanieNaNezamierenePolicko;
import sk.stuba.fei.uim.oop.vynimky.VynimkaZlyVyber;

import java.util.Objects;

public class AkcnaKartaVystrelit extends AkcnaKarta {
    public AkcnaKartaVystrelit(String vypisKarty) {
        super(vypisKarty);
    }

    @Override
    public void pouziKartu(StreleneKackyLite streleneKackyLite) {
        int miestoVystrelu = 0;
        while ((miestoVystrelu < 1 || miestoVystrelu > 6) || !(streleneKackyLite.vratZamierenieNaPolicku(miestoVystrelu-1))) {
            try {
                miestoVystrelu = KeyboardInput.readInt("| Vystrelit na policko cislo");
                if (miestoVystrelu < 1 || miestoVystrelu > 6) {
                    throw new VynimkaZlyVyber("| Vystrelit mozes len na policka od 1 po 6");
                }
                if (!(streleneKackyLite.vratZamierenieNaPolicku(miestoVystrelu-1))) {
                    throw new VynimkaStrielanieNaNezamierenePolicko();
                }
            } catch (VynimkaZlyVyber vynimka) {
                vynimka.vypisChyboveHlasenie();
            } catch (VynimkaStrielanieNaNezamierenePolicko vynimka) {
                vynimka.vypisChyboveHlasenie();
            }
        }
        if (streleneKackyLite.jePrvePismenoVypisuKartyK(miestoVystrelu-1)) {
            String komuOdobratZivot = streleneKackyLite.vratMenoMajitelaKarty(miestoVystrelu-1);
            for (int i = 0; i < streleneKackyLite.vratAktualnyPocetHracov(); i++) {
                if (Objects.equals(streleneKackyLite.vratMenoHraca(i), komuOdobratZivot)) {
                    streleneKackyLite.odoberHracoviZivot(i);
                }
            }
            streleneKackyLite.odstranRybnikovePolicko(miestoVystrelu-1);
        }
        streleneKackyLite.nastavZamierenieNaPolicku(miestoVystrelu-1, false);
    }

    @Override
    public boolean daSaKartaPouzit(StreleneKackyLite streleneKackyLite) {
        for (int i = 0; i < 6; i++) {
            if (streleneKackyLite.vratZamierenieNaPolicku(i)){
                return true;
            }
        }
        return false;
    }
}
