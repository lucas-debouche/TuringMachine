package jeu;

import java.util.ArrayList;
import java.util.List;

public class Partie {
    private List<Joueur> joueurs;
    private boolean etatPartie;

    public Partie() {
        this.joueurs = new ArrayList<>();
        this.etatPartie = false;
    }

    public void ajouterJoueur(Joueur joueur) {
        joueurs.add(joueur);
    }

    public List<Joueur> getJoueurs() {
        return joueurs;
    }

    public boolean isEtatPartie() {
        return etatPartie;
    }

    public void commencerPartie() {
        this.etatPartie = true;
    }

    public void terminerPartie() {
        this.etatPartie = false;
    }
}
