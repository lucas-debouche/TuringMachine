package jeu;

import java.util.List;

class Critere {
    private String description;

    public Critere(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public boolean verifierCondition(Proposition proposition, Scenario scenario) {
        // Vérifiez que la proposition contient 3 valeurs
        if (proposition.getValeurs().size() != 3) {
            throw new IllegalArgumentException("La proposition doit contenir exactement 3 valeurs.");
        }

        int etage = scenario.getEtage(); // 🏠 : Etage
        int salle = scenario.getSalle(); // 🔶 : Salle
        int position = scenario.getPosition(); // 🟣 : Position

        System.out.println(scenario.getCodeCorrect());
        // Vérifiez la description du critère
        switch (description) {


            case "🔶 < 🏠" :
                if (proposition.getValeurs().get(1) < proposition.getValeurs().get(0)) {
                    return salle < etage;
                }
                return false;
            case "🔶 = 🏠":
                if (proposition.getValeurs().get(1) == proposition.getValeurs().get(0)) {
                    return salle == etage;
                }
                return false;

            case "🔶 > 🏠":
                if (proposition.getValeurs().get(1) > proposition.getValeurs().get(0)) {
                    return salle > etage;
                }
                return false;

            case "🟣 impaire":
                if (proposition.getValeurs().get(2) % 2 != 0) {
                    return position % 2 != 0;
                }
                return false;
            case "🟣 paire":
                if (proposition.getValeurs().get(2) % 2 == 0) {
                    return position % 2 == 0;
                }
                return false;
            case "🟣 = 5":
                if (proposition.getValeurs().get(2) == 5) {
                    return position == 5;
                }
                return false;

            case "🟣 + 🏠 < 10":
                if (proposition.getValeurs().get(2) + proposition.getValeurs().get(0) < 10) {
                    return position + etage < 10;
                }
                return false;
            case "🟣 + 🏠 = 10":
                if (proposition.getValeurs().get(2) + proposition.getValeurs().get(0) == 10) {
                    return position + etage == 10;
                }
                return false;
            case "🟣 + 🏠 > 10":
                if (proposition.getValeurs().get(2) + proposition.getValeurs().get(0) > 10) {
                    return position + etage > 10;
                }
                return false;

            case "🟣 > 🏠 et 🔶":
                if (proposition.getValeurs().get(2) > proposition.getValeurs().get(0) && proposition.getValeurs().get(2) > proposition.getValeurs().get(1)) {
                    return position > etage && position > salle;
                }
                return false;
            case "🏠 < 🔶 et 🟣":
                if (proposition.getValeurs().get(0) < proposition.getValeurs().get(1) && proposition.getValeurs().get(0) < proposition.getValeurs().get(2)) {
                    return etage < salle && etage < position;
                }
                return false;
            case "🔶 > 🏠 et 🟣":
                if (proposition.getValeurs().get(1) > proposition.getValeurs().get(0) && proposition.getValeurs().get(1) > proposition.getValeurs().get(2)) {
                    return salle > etage && salle > position;
                }

                return false;
            case "🟣 multiple de 2":
                if (proposition.getValeurs().get(2) % 2 == 0) {
                    return position % 2 == 0;
                }
                return false;
            case "🟣 multiple de 3":
                if (proposition.getValeurs().get(2) % 3 == 0) {
                    return position % 3 == 0;
                }
                return false;
            case "🟣 multiple de 5":
                if (proposition.getValeurs().get(2) % 5 == 0) {
                    return position % 5 == 0;
                }
                return false;

            case "Toutes différentes":
                if (proposition.getValeurs().get(0) != proposition.getValeurs().get(1) && proposition.getValeurs().get(0) != proposition.getValeurs().get(2) && proposition.getValeurs().get(1) != proposition.getValeurs().get(2)) {
                    return etage != salle && etage != position && salle != position;
                }
                return false;
            case "Deux égales une différente":
                if (proposition.getValeurs().get(0) == proposition.getValeurs().get(1) && proposition.getValeurs().get(0) != proposition.getValeurs().get(2) ||
                        (proposition.getValeurs().get(0) == proposition.getValeurs().get(2) && proposition.getValeurs().get(0) != proposition.getValeurs().get(1)) ||
                        (proposition.getValeurs().get(1) == proposition.getValeurs().get(2) && proposition.getValeurs().get(1) != proposition.getValeurs().get(0))) {
                    return (etage == salle && etage != position) ||
                            (etage == position && etage != salle) ||
                            (salle == position && salle != etage);
                }
                return false;

            case "Toutes égales":
                if (proposition.getValeurs().get(0) == proposition.getValeurs().get(1) && proposition.getValeurs().get(0) == proposition.getValeurs().get(2)) {
                    return etage == salle && etage == position && salle == position;
                }
                return false;

            default:
                throw new IllegalArgumentException("Critère inconnu : " + description);
        }
    }

}
