package jeu;

import java.util.ArrayList;
import java.util.List;

class Machine {
    private List<Verif> verifyers;

    public Machine() {
        this.verifyers = new ArrayList<>();
    }

    public void ajouterVerifier(Verif verifier) {
        verifyers.add(verifier);
    }

    public List<Verif> getVerifyers() {
        return verifyers;
    }

}
