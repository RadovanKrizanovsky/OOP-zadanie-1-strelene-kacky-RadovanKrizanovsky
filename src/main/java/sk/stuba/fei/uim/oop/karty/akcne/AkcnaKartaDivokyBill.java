package sk.stuba.fei.uim.oop.karty.akcne;

import sk.stuba.fei.uim.oop.hra.StreleneKackyLite;
import sk.stuba.fei.uim.oop.karty.AkcnaKarta;
import sk.stuba.fei.uim.oop.utility.KeyboardInput;
import sk.stuba.fei.uim.oop.vynimky.VynimkaZlyVyber;

import java.util.Objects;

public class AkcnaKartaDivokyBill extends AkcnaKarta {
    public AkcnaKartaDivokyBill(String vypisKarty) {
        super(vypisKarty);
    }

    @Override
    public void pouziKartu(StreleneKackyLite streleneKackyLite) {
        int miestoPouzitia = 0;
        while ((miestoPouzitia < 1 || miestoPouzitia > 6)) {
            try {
                miestoPouzitia = KeyboardInput.readInt("| Pouzit divokeho Billa na policko cislo");
                if (miestoPouzitia < 1 || miestoPouzitia > 6) {
                    throw new VynimkaZlyVyber("| Divokeho Billa mozes pouzit len na policka od 1 po 6");
                }
            } catch (VynimkaZlyVyber vynimka) {
                vynimka.vypisChyboveHlasenie();
            }
        }
        if (streleneKackyLite.jePrvePismenoVypisuKartyK(miestoPouzitia - 1)) {
            String komuOdobratZivot = streleneKackyLite.vratMenoMajitelaKarty(miestoPouzitia - 1);
            for (int i = 0; i < streleneKackyLite.vratAktualnyPocetHracov(); i++) {
                if (Objects.equals(streleneKackyLite.vratMenoHraca(i), komuOdobratZivot)) {
                    streleneKackyLite.odoberHracoviZivot(i);
                }
            }
            streleneKackyLite.odstranRybnikovePolicko(miestoPouzitia-1);
            streleneKackyLite.nastavZamierenieNaPolicku(miestoPouzitia-1, false);
        }
    }

    @Override
    public boolean daSaKartaPouzit(StreleneKackyLite streleneKackyLite) {
        return true;
    }
}
