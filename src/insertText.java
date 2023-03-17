import java.util.*;
import java.util.List;

public class insertText {
    private List<String> infinitiv = new ArrayList<>(Arrays.asList("soll rennen","soll schreien","soll wachsen","soll spielen","soll fahren","soll lesen","soll essen","soll bauen","soll stehen","soll traben","soll arbeiten","soll schwimmen","soll bl\u00fchen","soll gackern","soll kriechen","soll scheinen","soll fallen","soll rieseln","soll rauschen","soll flie\u00dfen","soll lernen","soll unterrichten","soll verhaften","soll l\u00f6schen","soll heilen","soll pflegen","soll backen","soll kochen","soll schlachten","soll tanzen","soll singen","soll malen","soll schreiben","soll fliegen","soll bedienen","soll bauen","soll planen","soll verhandeln","soll kaufen","soll reparieren","soll k\u00fcmmern","soll stricken","soll schnitzen","soll treffen","soll plaudern","soll lieben","soll pflegen","soll besuchen","soll raten","soll scherzen","soll reden","soll laufen","soll schlafen","soll tragen","soll werfen","soll springen","soll brechen","soll schlagen","soll bewegen","soll trinken","soll riechen","soll schmecken","soll f\u00fchlen","soll h\u00f6ren","soll sehen","soll wissen","soll denken","soll f\u00fchlen","soll empfinden","soll sprechen","soll reden","soll antworten","soll fragen","soll forschen","soll entdecken","soll erforschen","soll l\u00f6sen","soll analysieren","soll interpretieren","soll rechnen","soll l\u00f6sen","soll entscheiden","soll beurteilen","soll bewerten","soll kategorisieren","soll vergleichen","soll abw\u00e4gen","soll bewundern","soll begeistern","soll bekommen","soll beziehen","soll besitzen","soll empfangen","soll erhalten","soll verdienen","soll gewinnen","soll bezahlen"));
    private List<String> partizip = new ArrayList<>(Arrays.asList("hat gerannt", "hat geschrien", "hat gewachsen", "hat gespielt", "hat gefahren", "hat gelesen", "hat gegessen", "hat gebaut", "hat gestanden", "hat getrabt", "hat gearbeitet", "hat geschwommen", "hat gebl\u00fcht", "hat gegackert", "hat gekrochen", "hat gescheinen", "hat gefallen", "hat gerieseln", "hat gerauscht", "hat geflossen", "hat gelernt", "hat unterrichtet", "hat verhaftet", "hat gel\u00f6scht", "hat geheilt", "hat gepflegt", "hat gebacken", "hat gekocht", "hat geschlachtet", "hat getanzt", "hat gesungen", "hat gemalt", "hat geschrieben", "hat geflogen", "hat bedient", "hat gebaut", "hat geplant", "hat verhandelt", "hat gekauft", "hat repariert", "hat gek\u00fcmmert", "hat gestrickt", "hat geschnitzt", "hat getroffen", "hat geplaudert", "hat geliebt", "hat gepflegt", "hat besucht", "hat geraten", "hat gescherzt", "hat geredet", "hat gelaufen", "hat geschlafen", "hat getragen", "hat geworfen", "hat gesprungen", "hat gebrochen", "hat geschlagen", "hat bewegt", "hat getrunken", "hat gerochen", "hat geschmeckt", "hat gef\u00fchlt", "hat geh\u00f6rt", "hat gesehen", "hat gewusst", "hat gedacht", "hat gef\u00fchlt", "hat empfunden", "hat gesprochen", "hat geredet", "hat beantwortet", "hat gefragt", "hat forschungen", "hat entdeckt", "hat erforscht","hat gel\u00f6st", "hat analysiert", "hat interpretiert", "hat gerechnet", "hat gel\u00f6st", "hat entschieden", "hat beurteilt", "hat bewertet", "hat kategoriert", "hat verglichen", "hat abgewogen", "hat bewundert", "hat begeistert", "hat bekommen", "hat bezogen", "hat besessen", "hat empfangen", "hat erhalten", "hat verdient", "hat gewonnen", "hat bezahlt"));
    private List<String> substantiv = new ArrayList<>(Arrays.asList("Hund", "Frau", "Baum", "Kind", "Auto", "Buch", "Apfel", "Haus", "Tisch", "Pferd", "Mann", "See", "K\u00e4tzchen", "Vogel", "Fisch", "Blume", "H\u00fchner", "Schmetterling", "K\u00e4fer", "Stuhl", "Sonne", "Regen", "Schnee", "Sand", "Meer", "Fluss", "Wasser", "Berg", "Sch\u00fcler", "Lehrer", "Polizist", "Feuerwehrmann", "Arzt", "Krankenschwester", "B\u00e4cker", "K\u00f6chin", "Metzger", "T\u00e4nzer", "S\u00e4nger", "Schauspieler", "Musiker", "K\u00fcnstler", "Schriftsteller", "Journalist", "Pilot", "Flugbegleiter", "Fahrer", "Ingenieur", "Architekt", "Jurist", "Banker", "Verk\u00e4ufer", "Kunde", "Mutter", "Vater", "Schwester", "Bruder", "Gro\u00dfmutter", "Gro\u00dfvater", "Freund", "Freundin", "Ehemann", "Ehefrau", "Enkel", "Enkelin", "Timm", "Schwiegermutter", "Schwager", "Schw\u00e4gerin", "Neffe", "Nichte", "Onkel", "Tante", "Cousin", "Cousine", "Br\u00e4utigam", "Braut", "Junge", "M\u00e4dchen", "Bub", "Angela Merkel", "Helmut Schmidt", "J\u00fcrgen Klopp", "Mario G\u00f6tze", "J\u00fcrgen Klinsmann", "Bastian Schweinsteiger", "Professor Munkelt", "Manuel Neuer", "Boris Becker", "Steffi Graf", "Katja Ebstein", "Udo Lindenberg", "Till Lindermann", "Kraftklub", "Jan B\u00f6hmermann", "Joko Winterscheidt", "Bernd das Brot", "Caroline Kebekus", "Klaas Heufer-Umlauf", "Helene Fischer"));
    private List<Object> list;
    private int position;
    private int rndWort;

    public insertText(EPK epk) {
        setList(epk.getList());
        fillStart();
        fillBetweenGates();
        fillAfterEndgate();
    }

//Füllen des Startelements bis zum ersten Gate
    public void fillStart() {
        List<String> infinitiv = getInfinitiv();
        List<Object> list = getList();
        Random rnd = new Random();

        int i = 0;
        while (i < list.size()) {
            if (list.get(i) instanceof Event && ((Event) list.get(i)).getId() == 1) {
                for (int j = 0; j < list.size(); j++) {
                    if (list.get(j) instanceof Kante
                            && ((Kante) list.get(j)).getRefStart().equals(list.get(i))) {
                        int position = 0;
                        int kantenIndex = j;
                        setRndWort(rnd.nextInt(infinitiv.size()));
                        //solange das Gate Ende nicht erreicht wurde, wird befüllt und nach weiteren Kanten & Elementen gesucht
                        while (!(((Kante) list.get(kantenIndex)).getRefEnd() instanceof Gate)) {
                            kantenIndex = fillText(kantenIndex);
                            setPosition(position++);
                        }
                        break;
                    }
                }
            }
            i++;
        }
    }


    public void fillBetweenGates(){
        List<String> infinitiv = getInfinitiv();
        List<Object> list = getList();
        Random rnd = new Random();

        int i=0;
        while(i < list.size()){
            if(list.get(i) instanceof RhombusOrLoop){
                //suche nach der Kante mit dem Start Rhoops als Startreferenz
                for(int j = 0; j < list.size(); j++){
                    if(list.get(j) instanceof ForwardKante
                            && ((ForwardKante) list.get(j)).getRefStart().equals(((RhombusOrLoop) list.get(i)).getRefStart())){
                        int position = 0;
                        int kantenIndex = j;
                        setRndWort(rnd.nextInt(infinitiv.size()));
                        //solange das Gate Ende nicht erreicht wurde, wird befüllt und nach weiteren Kanten & Elementen gesucht
                        while(!(((ForwardKante) list.get(kantenIndex)).getRefEnd().equals(((RhombusOrLoop) list.get(i)).getRefEnd()))) {
                            kantenIndex = fillText(kantenIndex);
                            setPosition(position++);
                        }
                    }
                }
            }
            i++;
        }
    }

    //Füllen zwischen Rhomben und Loops und nach dem letzten Gate
    public void fillAfterEndgate(){
        List<String> infinitiv = getInfinitiv();
        List<Object> list = getList();
        Random rnd = new Random();
        boolean lastElement = false;
        int kantenIndex;

        int i=0;
        while(i < list.size()){
            if(list.get(i) instanceof RhombusOrLoop){
                //suche nach der Kante mit dem Start Rhoops als Startreferenz
                for(int j = 0; j < list.size(); j++){
                    if(list.get(j) instanceof ForwardKante
                            && ((ForwardKante) list.get(j)).getRefStart().equals(((RhombusOrLoop) list.get(i)).getRefEnd())){
                        int position = 0;
                        kantenIndex = j;
                        setRndWort(rnd.nextInt(infinitiv.size()));
                        //solange das Gate Ende nicht erreicht wurde, wird befüllt und nach weiteren Kanten & Elementen gesucht
                        while(!(((ForwardKante) list.get(kantenIndex)).getRefEnd() instanceof Gate)) {
                            kantenIndex = fillText(kantenIndex);
                            setPosition(position++);
                            if(((ForwardKante) list.get(kantenIndex)).getRefEnd() instanceof Event
                                    && ((Event) ((ForwardKante) list.get(kantenIndex)).getRefEnd()).getId() == 2){
                                lastElement = true;
                                break;
                            }
                        }
                    }
                    if(lastElement){
                        lastElement = false;
                        break;
                    }
                }
            }
            i++;
        }
        System.out.print("");
    }

    public int fillText(int kantenIndex){
        List<String> infinitiv = getInfinitiv();
        List<String> partizip = getPartizip();
        List<String> substantiv = getSubstantiv();
        List<Object> list = getList();
        int substantivWort = 0;
        Random rnd = new Random();

        if (((ForwardKante) list.get(kantenIndex)).getRefEnd() instanceof Event
                && ((Event) ((ForwardKante) list.get(kantenIndex)).getRefEnd()).getPosition() == null) {
            //befüllen der Startreferenz, wenn nicht schon getan (da es ggf vorher eine Endrefernz war)
            if(((ForwardKante) list.get(kantenIndex)).getRefStart() instanceof Function
                    && ((Function) ((ForwardKante) list.get(kantenIndex)).getRefStart()).getPosition() == null){
                ((Function) ((ForwardKante) list.get(kantenIndex)).getRefStart()).setText(substantiv.get(substantivWort) + " " + infinitiv.get(rndWort));
                ((Function) ((ForwardKante) list.get(kantenIndex)).getRefStart()).setPosition(position);
                setPosition(position++);
                try{infinitiv.remove(rndWort);setInfinitiv(infinitiv);}catch(Exception ignored){}
                //Nach dem Event muss ein neues Partizip/Infinitiv Verbpaar gezogen werden
            }
            try{((Event) ((ForwardKante) list.get(kantenIndex)).getRefEnd()).setText(substantiv.get(substantivWort) + " " + partizip.get(rndWort));
            ((Event) ((ForwardKante) list.get(kantenIndex)).getRefEnd()).setPosition(position);
            partizip.remove(rndWort);setPartizip(partizip);}catch(Exception ignored){}
            rndWort = rnd.nextInt(infinitiv.size());
        }

        else if (((ForwardKante) list.get(kantenIndex)).getRefEnd() instanceof Function
                && ((Function) ((ForwardKante) list.get(kantenIndex)).getRefEnd()).getPosition() == null) {
            if(((ForwardKante) list.get(kantenIndex)).getRefStart() instanceof Event
                    && ((Event) ((ForwardKante) list.get(kantenIndex)).getRefStart()).getPosition() == null){
                ((Event) ((ForwardKante) list.get(kantenIndex)).getRefStart()).setText(substantiv.get(substantivWort) + " " + partizip.get(rndWort));
                ((Event) ((ForwardKante) list.get(kantenIndex)).getRefStart()).setPosition(position);
                position++;
                try{partizip.remove(rndWort);setPartizip(partizip);}catch(Exception ignored){}
            }
            ((Function) ((ForwardKante) list.get(kantenIndex)).getRefEnd()).setText(substantiv.get(substantivWort) + " " + infinitiv.get(rndWort));
            ((Function) ((ForwardKante) list.get(kantenIndex)).getRefEnd()).setPosition(position);
            try{infinitiv.remove(rndWort);setInfinitiv(infinitiv);}catch(Exception ignored){}
        }

        //finden der nächsten Kante und setzen des kantenindexes darauf
        for(int k = 0; k < list.size(); k++){
            if(list.get(k) instanceof ForwardKante
                    && ((ForwardKante) list.get(k)).getRefStart().equals(((ForwardKante) list.get(kantenIndex)).getRefEnd())){
                kantenIndex = k;
                if (((ForwardKante) list.get(kantenIndex)).getRefEnd() instanceof Gate) {
                    try{substantiv.remove(substantivWort);setSubstantiv(substantiv);}catch(Exception ignored){}
                }
                break;
            }
        }
        return kantenIndex;
    }

    public void setPosition(int position) {
        this.position = position;
    }
    public void setRndWort(int rndWort) {
        this.rndWort = rndWort;
    }
    public void setList(List<Object> list) {
        this.list = list;
    }
    public void setInfinitiv(List<String> infinitiv) {this.infinitiv = infinitiv;}
    public void setPartizip(List<String> partizip) {this.partizip = partizip;}
    public void setSubstantiv(List<String> substantiv) {this.substantiv = substantiv;}

    public List<String> getInfinitiv() {
        return infinitiv;
    }
    public List<String> getPartizip() {
        return partizip;
    }
    public List<String> getSubstantiv() {
        return substantiv;
    }
    public List<Object> getList() {
        return list;
    }
}
