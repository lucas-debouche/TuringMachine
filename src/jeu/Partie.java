package jeu;

import java.util.ArrayList;
import java.util.List;

public class Partie {
    public String nom;
    private boolean etatPartie;
    public int id;
    public int verifierCount;

    // Variable statique pour suivre l'ID globalement
    private static int idCounter = 0;

    public Partie(boolean etatPartie, int verifierCount) {
        this.id = idCounter++;
        this.nom = getNomPartie(this.id, verifierCount);
        this.etatPartie = etatPartie;
        this.verifierCount = verifierCount;
    }

    public String getNomPartie(int id, int verifierCount) {
        return "#" + id + " " + verifierCount;
    }

    public void terminerPartie() {
        this.etatPartie = false;
    }
}
