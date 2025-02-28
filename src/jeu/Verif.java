package jeu;

import java.util.List;

class Verif {
    private List<Critere> criteres; // Liste de critères
    private int id;

    public Verif(List<Critere> criteres, int id) {
        this.criteres = criteres;
        this.id = id; // Initialisation explicite de l'ID
    }


    public List<Critere> getCriteres() {
        return criteres;
    }

    public int getId() {
        return id;
    }

    public Critere verifierProposition(Proposition proposition, Scenario scenario) {
        // Parcourir les critères et retourner le premier qui valide la proposition
        for (Critere critere : criteres) {
            if (critere.verifierCondition(proposition,scenario, critere)) {
                return critere; // Critère validé
            }
        }
        return null; // Aucun critère validé
    }

}
