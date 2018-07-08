import javax.swing.*;
import java.util.ArrayList;
import static javax.swing.JOptionPane.*;

public class VaktOversikt {

    public static void main(String[] args) {

        ArrayList<Maned> maneder = new ArrayList<Maned>();
        String[] muligheter = {"Legg til måned", "Administrer vakter", "Lønn", "Avlsutt"};
        int valg;
        int stick = 0;
        while(stick != 1){
            valg = showOptionDialog(null, "Hei og velkommen!", "Vakter", YES_NO_OPTION, INFORMATION_MESSAGE, null, muligheter, muligheter[0]);

            switch (valg) {

                case 0: // legg til måned

                    String[] alleManeder = {"Januar", "Februar", "Mars", "April", "Mai", "Juni", "Juli", "August", "September"
                            ,"Oktober", "November", "Desember"};

                    for(int i = 0; i < alleManeder.length; i++) {
                        for(int j = 0; j < maneder.size(); j++) {
                            if (alleManeder[i] == maneder.get(j).getNavn()){
                                alleManeder[i] = "----";
                            }
                        }
                    }
                    ArrayList<String> manederKopi = new ArrayList<String>();
                    for(int i = 0; i < alleManeder.length; i++) {
                        if(alleManeder[i] == "----") {
                            continue;
                        }else{
                            manederKopi.add(alleManeder[i]);
                        }
                    }
                    String[] alleManederUpdated = new String[manederKopi.size()];
                    for(int i = 0; i < manederKopi.size(); i++) {
                        alleManederUpdated[i] = manederKopi.get(i);
                    }

                    String choices = (String)showInputDialog(null,"Velg måned",
                            "valg",
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            alleManederUpdated,
                            alleManederUpdated[0]);

                    String[] dager  = {"27","28","29","30","31"};
                    String dagerValgt = (String)showInputDialog(null,"Velg antall dager i denne måneden",
                            "valg",
                            QUESTION_MESSAGE,
                            null,
                            dager,
                            dager[0]);

                    int dagers = Integer.parseInt(dagerValgt);
                    Maned manedO = new Maned(choices , dagers);
                    maneder.add(manedO);
                    showMessageDialog(null, "Måneden " + manedO.getNavn() + " er registrert.");
                    break;


                case 1: // Administrer vakter
                    if(maneder.size() == 0) {
                        showMessageDialog(null, "Du må først legge til en måned. Prøv igjen.");
                    } else {

                        String[] alles = new String[maneder.size()];
                        for (int i = 0; i < maneder.size(); i++) {
                            alles[i] = maneder.get(i).getNavn();
                        }

                        String choices3 = (String) showInputDialog(null, "Velg måned",
                                "Liste over måneder.",
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                alles,
                                alles[0]);

                        for (int i = 0; i < maneder.size(); i++) {
                            if (choices3 == maneder.get(i).getNavn()) {

                                Vakt[] vaktliste = maneder.get(i).getHeleVaktlisten();

                                String[] alleVakter = new String[maneder.get(i).getVaktListeLength()];
                                for (int j = 0; j < alleVakter.length; j++) {
                                    if (vaktliste[j] == null) {
                                        alleVakter[j] = String.valueOf(j + 1) + " - ";
                                    } else {
                                        String type = "";
                                        if (vaktliste[j].getKveld()) {
                                            type += String.valueOf(j + 1) + " - Kveld";
                                        } else {
                                            type += String.valueOf(j + 1) + " - Dag";
                                        }
                                        if (vaktliste[j].getHelg()) {
                                            type += ", 'H'";
                                        } else {
                                            type += ", 'U'";
                                        }
                                        alleVakter[j] = type;
                                    }
                                }
                                String choices4 = (String) showInputDialog(null, "Velg en ledig dato for å legge til ny vakt.\n" +
                                                "Velg en eksisterende vakt for å endre / slette.",
                                        "Liste over vakter i " + choices3,
                                        JOptionPane.QUESTION_MESSAGE,
                                        null,
                                        alleVakter,
                                        alleVakter[0]);

                                String bareDatos = choices4.substring(0, 2);
                                String bareDato = bareDatos.trim();
                                int bareDatoen = Integer.parseInt(bareDato);

                                if (choices4.trim().length() > 4) {
                                    //slette / endre
                                    String[] endreSlette = {"Endre vakt", "Slette vakt"};

                                    String choice5 = (String) showInputDialog(null, "Valg",
                                            "Dato: " + String.valueOf(bareDato),
                                            JOptionPane.QUESTION_MESSAGE,
                                            null,
                                            endreSlette,
                                            endreSlette[0]);

                                    if (choice5 == "Endre vakt") {

                                        boolean typeVakt = false;
                                        Object[] options = {"Dagvakt", "Kveldsvakt"};
                                        int n = JOptionPane.showOptionDialog(null, "Velg type vakt.", "Velg en av alternativene",
                                                JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null,
                                                options, options[0]);

                                        if (n == 0) {
                                            typeVakt = false;
                                        } else if (n == 1) {
                                            typeVakt = true;
                                        }

                                        boolean erDetHelg = false;
                                        Object[] options2 = {"Ukedag", "Helg"};
                                        int o = JOptionPane.showOptionDialog(null, "Velg type vakt.", "Velg en av alternativene",
                                                JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null,
                                                options2, options2[0]);

                                        if (o == 0) {
                                            erDetHelg = false;
                                        } else if (o == 1) {
                                            erDetHelg = true;
                                        }
                                        Vakt nyVakt = new Vakt(erDetHelg, bareDatoen, typeVakt);
                                        maneder.get(i).endreVakt(nyVakt);
                                        showMessageDialog(null, "Vakt endret");

                                    } else if (choice5 == "Slette vakt") {
                                        boolean slettet = maneder.get(i).slettVakt(bareDatoen);
                                        if (slettet) {
                                            showMessageDialog(null, "Vakt slettet");
                                        } else {
                                            showMessageDialog(null, "Noe gikk galt.");
                                        }
                                    }

                                } else {
                                    // legge til vakt

                                    boolean typeVakt = false;
                                    Object[] options = {"Dagvakt", "Kveldsvakt"};
                                    int n = JOptionPane.showOptionDialog(null, "Velg type vakt.", "Velg en av alternativene",
                                            JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null,
                                            options, options[0]);

                                    if (n == 0) {
                                        typeVakt = false;
                                    } else if (n == 1) {
                                        typeVakt = true;
                                    }

                                    boolean erDetHelg = false;
                                    Object[] options2 = {"Ukedag", "Helg"};
                                    int o = JOptionPane.showOptionDialog(null, "Velg type vakt.", "Velg en av alternativene",
                                            JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null,
                                            options2, options2[0]);

                                    if (o == 0) {
                                        erDetHelg = false;
                                    } else if (o == 1) {
                                        erDetHelg = true;
                                    }

                                    Vakt nyVakt = new Vakt(typeVakt, bareDatoen, erDetHelg);
                                    boolean reg = maneder.get(i).leggTilVakt(nyVakt);

                                    if (reg) {
                                        showMessageDialog(null, "Vakt registrert.");
                                    } else {
                                        showMessageDialog(null, "Noe gikk galt.");
                                    }
                                }
                            }
                        }
                    }
                    break;

                case 2: // Lønn
                    if(maneder.size() == 0) {
                        showMessageDialog(null, "Du må først legge til en måned. Prøv igjen.");
                    } else {

                        String[] valgene = {"Totalt beregnet lønn", "Månedlig beregnet lønn"};

                        Object[] options = {"Total", "Månedlig"};
                        int lonnValg = JOptionPane.showOptionDialog(null, "Beregnet lønn", "Velg en av alternativene",
                                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null,
                                options, options[0]);

                        if (lonnValg == 0) {
                            double lonn = 0;
                            String manederBrukt = "";
                            for (int i = 0; i < maneder.size(); i++) {
                                lonn += maneder.get(i).totalLonn();
                                manederBrukt += maneder.get(i).getNavn()
                                        + ", ";
                            }
                            int intlonn = (int) lonn;
                            manederBrukt = manederBrukt.substring(0, manederBrukt.length() - 2);
                            showMessageDialog(null, "Totalt beregnet lønn: " + String.valueOf(intlonn) + " kr." + "\n" +
                                    "(" + manederBrukt + ").");
                        } else if (lonnValg == 1) {
                            String[] manedene = new String[maneder.size()];
                            for (int i = 0; i < maneder.size(); i++) {
                                manedene[i] = maneder.get(i).getNavn();
                            }
                            String choices6 = (String) showInputDialog(null, "Velg måned",
                                    "Liste over måneder.",
                                    JOptionPane.QUESTION_MESSAGE,
                                    null,
                                    manedene,
                                    manedene[0]);

                            double lonnBestemt = 0.0;
                            for (int i = 0; i < maneder.size(); i++) {
                                if (choices6 == maneder.get(i).getNavn()) {
                                    ArrayList<Vakt> vaktListe = maneder.get(i).getBarefylteVaktliste();
                                    for (int j = 0; j < vaktListe.size(); j++) {
                                        lonnBestemt += maneder.get(i).enkelLonn(vaktListe.get(j).getDato() - 1);
                                    }
                                }
                            }
                            showMessageDialog(null, "Total beregnet lonn for " + choices6 + ": " +
                                    String.valueOf((int) lonnBestemt) + " kr. ");
                        }
                    }
                    break;
                default:
                    break;
                case 3:
                    stick += 1;
            }
        }
    }
}
