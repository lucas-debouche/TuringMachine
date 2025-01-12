package jeu;

import java.util.ArrayList;
import java.util.List;

public class Scenario {

    private List<Integer> codeCorrect; // Les chiffres du code (Étage, Salle, Position)

    public Scenario(int numeroSalle) {
        // Le numéro du scénario (315, 432, 522)
        this.codeCorrect = extraireCode(numeroSalle);
    }

    public List<Integer> getCodeCorrect() {
        return codeCorrect;
    }

    // Extraire le code à partir du numéro (ex. : 315 -> [3, 1, 5])
    private List<Integer> extraireCode(int numeroSalle) {
        List<Integer> code = new ArrayList<>();
        code.add(numeroSalle / 100);         // Centaine -> Étape
        code.add((numeroSalle / 10) % 10);   // Dizaines -> Salle
        code.add(numeroSalle % 10);          // Unité -> Position
        return code;
    }

    public int getEtage() {
        return codeCorrect.get(0);
    }

    public int getSalle() {
        return codeCorrect.get(1);
    }

    public int getPosition() {
        return codeCorrect.get(2);
    }

    public boolean verifierCode(Proposition proposition) {
        return codeCorrect.equals(proposition.getValeurs());
    }
}
