package jeu;

// Classe Partie
import java.util.ArrayList;
import java.util.List;

public class Partie {
    private List<Joueur> joueurs;
    private List<Proposition> historique;
    private boolean etatPartie;

    public Partie() {
        this.joueurs = new ArrayList<>();
        this.historique = new ArrayList<>();
        this.etatPartie = false;
    }

    public void commencerPartie() {
        this.etatPartie = true;
        System.out.println("La partie commence !");
    }

    public void sauvegarderPartie() {
        System.out.println("La partie a été sauvegardée.");
    }

    public void terminerPartie() {
        this.etatPartie = false;
        System.out.println("La partie est terminée !");
    }

    public List<Joueur> getJoueurs() {
        return joueurs;
    }

    public List<Proposition> getHistorique() {
        return historique;
    }

    public boolean isEtatPartie() {
        return etatPartie;
    }
}