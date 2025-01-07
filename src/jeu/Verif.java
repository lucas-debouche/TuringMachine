// Classe Verif
package jeu;

class Verif {
    private Critere critere;

    public Verif(Critere critere) {
        this.critere = critere;
    }

    public boolean validerProposition(Proposition proposition) {
        for (int valeur : proposition.getValeurs()) {
            if (!critere.verifierCondition(valeur)) {
                return false;
            }
        }
        return true;
    }
}