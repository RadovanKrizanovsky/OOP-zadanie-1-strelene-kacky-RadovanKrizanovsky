package sk.stuba.fei.uim.oop.karty.akcne;

import sk.stuba.fei.uim.oop.hra.StreleneKackyLite;
import sk.stuba.fei.uim.oop.karty.AkcnaKarta;
import sk.stuba.fei.uim.oop.utility.KeyboardInput;
import sk.stuba.fei.uim.oop.vynimky.VynimkaMierenieNaUzZamierene;
import sk.stuba.fei.uim.oop.vynimky.VynimkaZlyVyber;

public class AkcnaKartaZamierit extends AkcnaKarta {
    public AkcnaKartaZamierit(String vypisKarty) {
        super(vypisKarty);
    }

    @Override
    public void pouziKartu(StreleneKackyLite streleneKackyLite) {
        int miestoZamierenia = 0;
        while ((miestoZamierenia < 1 || miestoZamierenia > 6) || streleneKackyLite.vratZamierenieNaPolicku(miestoZamierenia-1)) {
            try {
                miestoZamierenia = KeyboardInput.readInt("| Zamierit na policko cislo");
                if (miestoZamierenia < 1 || miestoZamierenia > 6) {
                    throw new VynimkaZlyVyber("Zamierit mozes len na policka od 1 po 6");
                }
                if (streleneKackyLite.vratZamierenieNaPolicku(miestoZamierenia-1)) {
                    throw new VynimkaMierenieNaUzZamierene();
                }
            } catch (VynimkaZlyVyber vynimka) {
                vynimka.vypisChyboveHlasenie();
            } catch (VynimkaMierenieNaUzZamierene vynimka) {
                vynimka.vypisChyboveHlasenie();
            }
        }
        streleneKackyLite.nastavZamierenieNaPolicku((miestoZamierenia-1), true);
    }

    @Override
    public boolean daSaKartaPouzit(StreleneKackyLite streleneKackyLite) {
        for (int i = 0; i < 6; i++) {
            if (!(streleneKackyLite.vratZamierenieNaPolicku(i))) {
                return true;
            }
        }
        return false;
    }
}
