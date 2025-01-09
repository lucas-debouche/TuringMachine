package jeu;

import java.util.List;

class Proposition {
    private List<Integer> valeurs;

    public Proposition(List<Integer> valeurs) {
        this.valeurs = valeurs;
    }

    public List<Integer> getValeurs() {
        return valeurs;
    }

    public void addSymbole(int symbole) {
        valeurs.add(symbole);

    }

}
