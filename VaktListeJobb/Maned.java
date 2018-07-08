import java.util.ArrayList;

class Maned {

    private Vakt[] vaktliste;
    private String navn;
    private int antDager;

    public Maned(String navn, int antDager) {
        this.vaktliste = new Vakt[antDager];
        this.navn = navn;
        this.antDager = antDager;
    }

    public String getNavn() {
        return navn;
    }

    public int getAntDager() {
        return antDager;
    }

    public boolean leggTilVakt(Vakt vakt) {
        if(vaktliste[vakt.getDato() - 1] == null) {

            vaktliste[vakt.getDato() - 1] = vakt;
            return true;
        }
        return false;
    }

    public String printAlle() {
        String print = "";
        for(int i = 0; i < vaktliste.length; i++) {
            if(vaktliste[i] == null) {
                continue;
            }
            if(vaktliste[i].getKveld()) {
                print += "dag: " + String.valueOf(i + 1) + " - Kveld\n";
            }
            else{
                print += "dag: " + String.valueOf(i + 1) + " - Dag\n";
            }
        }
        return print;
    }


    public boolean endreVakt(Vakt nyVakt) {
        vaktliste[nyVakt.getDato() - 1] = nyVakt;
        return true;
    }

    public boolean slettVakt(int dato) {
        for(int i = 0; i < vaktliste.length; i++) {
            if(vaktliste[i] == null) continue;
            if(vaktliste[i].getDato() == dato) {
                vaktliste[i]= null;
                return true;
            }
        }
        return false;
    }

    public double enkelLonn(int dato) {
        double res = 0.0;
        double sats = 159.26;
        double timer = 7.5;
        if(vaktliste[dato].getHelg()){
            res += timer * 50.0;
        }
        res += sats * timer;

        if(vaktliste[dato].getKveld()) {
            res += 55.0 * 4;
        }
        return res;
    }

    public double totalLonn() {
        double res = 0.0;
        for(int i = 0; i < vaktliste.length; i++) {
            if(vaktliste[i] == null) {
                continue;
            }else{
                res += enkelLonn(i);
            }
        }
        return res;
    }


    public Vakt getVaktListe(int dato) {
        return vaktliste[dato];
    }

    public int getVaktListeLength() {
        return vaktliste.length;
    }

    public Vakt[] getHeleVaktlisten() {
        return vaktliste;
    }

    public ArrayList<Vakt> getBarefylteVaktliste() {
        ArrayList<Vakt> nyListe = new ArrayList<Vakt>() ;
        for(int i = 0; i < vaktliste.length; i++) {
            if(vaktliste[i] == null) {
                continue;
            }else{
                nyListe.add(vaktliste[i]);
            }
        }
        return nyListe;
    }
}
