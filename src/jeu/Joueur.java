// Classe Joueur
package jeu;

import java.util.ArrayList;
import java.util.List;

class Joueur {
    private List<Partie> historiqueParties;

    public Joueur() {
        this.historiqueParties = new ArrayList<>();
    }

    public void addPartie(Partie p) {
        this.historiqueParties.add(p);
    }

    public List<Partie> getHistoriqueParties() {
        return historiqueParties;
    }

}