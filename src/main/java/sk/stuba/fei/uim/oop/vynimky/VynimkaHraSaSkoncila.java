package sk.stuba.fei.uim.oop.vynimky;

import sk.stuba.fei.uim.oop.hraci.VsetciHraci;

public class VynimkaHraSaSkoncila extends Exception{
    public VynimkaHraSaSkoncila() {
        super("Hra sa skoncila!");
    }

    public void vypisChyboveHlasenie() {
        System.out.println("============================================");
        System.out.print("              ");
        System.out.println(super.getMessage());
        System.out.println("============================================");
    }

    public void zavercnyVypis(VsetciHraci vsetciHraci) {
        for (int i = 0; i < vsetciHraci.vratPocetHracov(); i++) {
            if (vsetciHraci.vratPocetZivotovHraca(i) > 0) {
                String vypis =("Vyhral " + vsetciHraci.vratMenoHraca(i) + "!");
                for (int j = 0; j < ((44-vypis.length())/2); j++) {
                    System.out.print(" ");
                }
                System.out.print(vypis + "\n");
                System.out.println("============================================");
            }
        }
    }
}
