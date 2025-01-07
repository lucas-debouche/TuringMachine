// Classe Joueur
package jeu;

import java.util.ArrayList;
import java.util.List;

class Joueur {
    private String nom;
    private int score;
    private List<Proposition> historiquePropositions;

    public Joueur(String nom) {
        this.nom = nom;
        this.score = 0;
        this.historiquePropositions = new ArrayList<>();
    }

    public void faireProposition(Proposition proposition) {
        historiquePropositions.add(proposition);
        System.out.println(nom + " a fait une proposition: " + proposition);
    }

    public void quitterPartie() {
        System.out.println(nom + " a quitté la partie.");
    }

    public String getNom() {
        return nom;
    }

    public int getScore() {
        return score;
    }

    public List<Proposition> getHistoriquePropositions() {
        return historiquePropositions;
    }

    public void incrementerScore() {
        this.score++;
    }
}