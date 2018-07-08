public class Vakt {

    private int dato;
    private boolean kveld;
    private boolean helg;

    public Vakt(boolean kveld, int dato, boolean helg) {
        this.dato = dato;
        this.kveld = kveld;
        this.helg = helg;
    }

    public int getDato() {
        return dato;
    }

    public boolean getKveld() {
        return kveld;
    }

    public boolean getHelg() {
        return helg;
    }
}
