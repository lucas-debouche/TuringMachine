// Classe Machine
package jeu;

import java.util.ArrayList;
import java.util.List;

class Machine {
    private List<Verif> verifyers;

    public Machine() {
        this.verifyers = new ArrayList<>();
    }

    public void initialiserPartie() {
        System.out.println("Machine initialisée avec des critères.");
    }

    public boolean analyserProposition(Proposition proposition) {
        for (Verif verifier : verifyers) {
            if (!verifier.validerProposition(proposition)) {
                return false;
            }
        }
        return true;
    }

    public void genererReponse() {
        System.out.println("Machine génère une réponse.");
    }

    public List<Verif> getVerifyers() {
        return verifyers;
    }

    public void ajouterVerifier(Verif verifier) {
        verifyers.add(verifier);
    }
}