// Classe Critere
package jeu;

class Critere {
    private String description;

    public Critere(String description) {
        this.description = description;
    }

    public boolean verifierCondition(int valeur) {
        // Exemple de critère : la valeur doit être positive
        return valeur > 0;
    }

    public String getDescription() {
        return description;
    }
}
