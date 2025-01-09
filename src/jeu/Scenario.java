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
            allCriteres.add("L'identifiant de la salle est un nombre impair");
            allCriteres.add("La salle se trouve à droite du couloir");
            allCriteres.add("Le premier chiffre (étage) est plus petit que l'identifiant de la salle");
            allCriteres.add("Le numéro de l'étage est supérieur à 200");
            allCriteres.add("Le numéro de l'identifiant de la salle est inférieur à 50");
            allCriteres.add("La salle se trouve à un étage inférieur à son identifiant");
            allCriteres.add("La somme des deux derniers chiffres est inférieure à 10");
            allCriteres.add("La salle est située dans le premier étage (moins de 400)");
            allCriteres.add("Le numéro d'identifiant de la salle est impair et inférieur à 20");
            allCriteres.add("L'étage est supérieur à 300");
            allCriteres.add("Le premier chiffre (étage) est inférieur à 4");
        } else if (numeroSalle == 411) {
            allCriteres.add("Le numéro de l'étage est impair");
            allCriteres.add("Le numéro d'identifiant de la salle est impair");
            allCriteres.add("La salle se trouve à gauche du couloir");
            allCriteres.add("Le premier chiffre (étage) est supérieur à 3");
            allCriteres.add("Le numéro d'étage est divisible par 3");
            allCriteres.add("L'identifiant de la salle est un nombre inférieur à 50");
            allCriteres.add("L'étage est supérieur à l'identifiant de la salle");
            allCriteres.add("La somme des trois chiffres est un nombre impair");
            allCriteres.add("Le dernier chiffre de la salle est inférieur à l'identifiant");
            allCriteres.add("La salle est située dans un étage inférieur à 500");
            allCriteres.add("La salle est dans un étage supérieur à 200");
            allCriteres.add("Le numéro d'identifiant de la salle est un nombre impair et inférieur à 20");
        } else if (numeroSalle == 522) {
            allCriteres.add("Le numéro de l'étage est pair");
            allCriteres.add("L'identifiant de la salle est pair");
            allCriteres.add("La salle se trouve à droite du couloir");
            allCriteres.add("Le premier chiffre (étage) est plus petit que l'identifiant de la salle");
            allCriteres.add("Le numéro d'étage est inférieur à 6");
            allCriteres.add("Le deuxième chiffre de l'identifiant est un chiffre pair");
            allCriteres.add("L'identifiant de la salle est supérieur à 20 mais inférieur à 60");
            allCriteres.add("Le dernier chiffre est inférieur à l'identifiant de la salle");
            allCriteres.add("La salle se trouve dans un étage inférieur à 6");
            allCriteres.add("La salle est à un étage inférieur à son identifiant");
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
