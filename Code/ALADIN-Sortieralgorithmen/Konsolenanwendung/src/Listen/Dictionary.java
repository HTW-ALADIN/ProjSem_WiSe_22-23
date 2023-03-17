package Listen;

import java.util.ArrayList;
import java.util.List;

// Dictionary, welches Wörter speichert, aus denen das Feld generiert wird
public class Dictionary {
    List<String> woerterliste = new ArrayList();

    public void fuelleListe() {
        this.woerterliste.add("Apfel");
        this.woerterliste.add("Maus");
        this.woerterliste.add("Pastete");
        this.woerterliste.add("Fahrrad");
        this.woerterliste.add("Feierabend");
        this.woerterliste.add("Warmduscher");
        this.woerterliste.add("Hüftgold");
        this.woerterliste.add("Zechpreller");
        this.woerterliste.add("Bierkönig/in");
        this.woerterliste.add("Flitzpiepe");
        this.woerterliste.add("Hokuspokus");
        this.woerterliste.add("Kaulquappe");
        this.woerterliste.add("Quadratlatschen");
        this.woerterliste.add("Rumpelstilzchen");
        this.woerterliste.add("Wackeldackel");
        this.woerterliste.add("Waschbrettbauch");
        this.woerterliste.add("Doktorfisch");
        this.woerterliste.add("Drahtesel");
        this.woerterliste.add("Glückspilz");
        this.woerterliste.add("Palmendieb");
        this.woerterliste.add("Wetterfrosch");
        this.woerterliste.add("Blubberwasser");
        this.woerterliste.add("Verschlimmbessern");
        this.woerterliste.add("Mondversessen");
        this.woerterliste.add("Digga");
        this.woerterliste.add("Cringe");
        this.woerterliste.add("Akkurat");
        this.woerterliste.add("Mittwoch");
        this.woerterliste.add("Bergfest");
        this.woerterliste.add("Rumoxidieren");
        this.woerterliste.add("Prokastinieren");
    }

    public String gibWort(int stelle) {
        return (String)this.woerterliste.get(stelle);
    }

    public List<String> get_woerterliste() {
        return this.woerterliste;
    }
}
