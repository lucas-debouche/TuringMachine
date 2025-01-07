// Classe Proposition
package jeu;

import java.util.List;

class Proposition {
    private List<Integer> valeurs;
    private boolean resultat;

    public Proposition(List<Integer> valeurs) {
        this.valeurs = valeurs;
        this.resultat = false;
    }

    public boolean verifierAvecVerificateur(Verif verificateur) {
        resultat = verificateur.validerProposition(this);
        return resultat;
    }

    public List<Integer> getValeurs() {
        return valeurs;
    }

    public boolean isResultat() {
        return resultat;
    }
}