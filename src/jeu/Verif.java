package jeu;

import java.util.List;

class Verif {
    private List<Critere> criteres; // Liste de critères
    private int id = 0;

    public Verif(List<Critere> criteres) {
        this.criteres = criteres;
        this.id ++;
    }

    public List<Critere> getCriteres() {
        return criteres;
    }
}