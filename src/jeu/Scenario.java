package jeu;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Scenario {

    private int numeroSalle;
    private List<Critere> criteres;

    public Scenario(int numeroSalle, int nombreDeCritere) {
        this.numeroSalle = numeroSalle;
        this.criteres = new ArrayList<>();

        // Liste des critères pour chaque scénario
        List<String> allCriteres = new ArrayList<>();

        if (numeroSalle == 315) {
            allCriteres.add("Le numéro de l'étage est impair");
            allCriteres.add("La salle se situe à gauche du couloir (la position est un nombre impair)");
            allCriteres.add("Le dernier chiffre est plus grand que l'étage de la salle");
            allCriteres.add("Le premier chiffre (étage) est plus petit que l'identifiant de la salle");
            allCriteres.add("Le numéro de l'étage est supérieur à 2");
            allCriteres.add("Le numéro de l'identifiant de la salle est inférieur à 5");
            allCriteres.add("La salle se trouve à un étage inférieur à sa position");
            allCriteres.add("La somme des deux derniers chiffres est inférieure à 10");
            allCriteres.add("La salle est située dans les 3 premiers étages (moins de 4)");
            allCriteres.add("Le numéro d'identifiant de la salle est impair ");
            allCriteres.add("Le premier chiffre (étage) est inférieur à 4");
        } else if (numeroSalle == 411) {
            allCriteres.add("Le numéro de l'étage est pair");
            allCriteres.add("Le numéro d'identifiant de la salle est impair");
            allCriteres.add("La salle se trouve à gauche du couloir(la position est un nombre impair)");
            allCriteres.add("Le premier chiffre (étage) est supérieur à 3");
            allCriteres.add("L'identifiant de la salle est un nombre inférieur à 5");
            allCriteres.add("L'étage est supérieur à l'identifiant de la salle");
            allCriteres.add("La somme des trois chiffres est un nombre pair");
            allCriteres.add("Le dernier chiffre de la salle est inférieur ou égale à l'identifiant");
            allCriteres.add("La salle est située dans un étage inférieur à 5");
            allCriteres.add("La salle est dans un étage supérieur à 2");
            allCriteres.add("Le numéro d'identifiant de la salle est un nombre impair et inférieur à 2");
        } else if (numeroSalle == 522) {
            allCriteres.add("Le numéro de l'étage est impair");
            allCriteres.add("L'identifiant de la salle est pair");
            allCriteres.add("La salle se trouve à droite du couloir(la position est un nombre pair)");
            allCriteres.add("Le premier chiffre (étage) est plus grand que l'identifiant de la salle");
            allCriteres.add("Le numéro d'étage est supérieur ou égale à 3");
            allCriteres.add("Le chiffre de l'identifiant est un chiffre pair");
            allCriteres.add("L'identifiant de la salle est supérieur ou égale à 2 mais inférieur ou égale à 4");
            allCriteres.add("Le dernier chiffre est inférieur ou égale à l'identifiant de la salle");
            allCriteres.add("La salle est à un étage supérieur à la somme de son identifiant et sa position");
            allCriteres.add("L'identifiant de la salle est un nombre pair");
            allCriteres.add("La somme des trois chiffres est un nombre impair");
        }





        // Tirer au hasard un nombre de critères parmi ceux disponibles
        Random rand = new Random();

        // Créer une liste d'indices de critères disponibles
        List<Integer> indices = new ArrayList<>();
        for (int i = 0; i < allCriteres.size(); i++) {
            indices.add(i);
        }

        // Sélectionner un nombre de critères unique
        while (criteres.size() < nombreDeCritere && !indices.isEmpty()) {
            int index = rand.nextInt(indices.size());
            int critereIndex = indices.remove(index); // Retirer l'indice sélectionné de la liste
            criteres.add(new Critere(allCriteres.get(critereIndex))); // Ajouter le critère à la liste
        }


    }

    public int getNumeroSalle() {
        return numeroSalle;
    }

    public Critere getCritere(int index) {
        // Vérification si l'index est valide
        if (index >= 0 && index < criteres.size()) {
            return criteres.get(index);
        } else {
            return null;  // Retourne null si l'index est invalide
        }
    }

    public List<Critere> getCriteres() {
        return criteres;
    }
}
