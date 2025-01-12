package jeu;

import java.util.List;
import java.util.Objects;

class Critere {
    private String description;
    public int valide = 0;

    public Critere(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public boolean verifierCondition(Proposition proposition, Scenario scenario, Critere critere) {
        // Vérifiez que la proposition contient 3 valeurs
        if (proposition.getValeurs().size() != 3) {
            throw new IllegalArgumentException("La proposition doit contenir exactement 3 valeurs.");
        }

        int etage = scenario.getEtage(); // 🏠 : Etage
        int salle = scenario.getSalle(); // 🔶 : Salle
        int position = scenario.getPosition(); // 🟣 : Position

        System.out.println(scenario.getCodeCorrect());
        // Vérifiez la description du critère
        return switch (description) {
            case "🔶 < 🏠" -> {
                if (proposition.getValeurs().get(1) < proposition.getValeurs().get(0)) {
                    critere.valide = (salle < etage) ? 2 : 1;
                    yield true;
                }
                yield false;
            }
            case "🔶 = 🏠" -> {
                if (Objects.equals(proposition.getValeurs().get(1), proposition.getValeurs().get(0))) {
                    critere.valide = (salle == etage) ? 2 : 1;
                    yield true;
                }
                yield false;
            }
            case "🔶 > 🏠" -> {
                if (proposition.getValeurs().get(1) > proposition.getValeurs().get(0)) {
                    critere.valide = (salle > etage) ? 2 : 1;
                    yield true;
                }
                yield false;
            }
            case "🟣 impaire" -> {
                if (proposition.getValeurs().get(2) % 2 != 0 && proposition.getValeurs().get(2) != 5) {
                    critere.valide = (position % 2 != 0) ? 2 : 1;
                    yield true;
                }
                yield false;
            }
            case "🟣 paire", "🟣 multiple de 2" -> {
                if (proposition.getValeurs().get(2) % 2 == 0) {
                    critere.valide = (position % 2 == 0) ? 2 : 1;
                    yield true;
                }
                yield false;
            }
            case "🟣 = 5" -> {
                if (proposition.getValeurs().get(2) == 5) {
                    critere.valide = (position == 5) ? 2 : 1;
                    yield true;
                }
                yield false;
            }
            case "🟣 + 🏠 < 6" -> {
                if (proposition.getValeurs().get(2) + proposition.getValeurs().get(0) < 6) {
                    critere.valide = (position + etage < 6) ? 2 : 1;
                    yield true;
                }
                yield false;
            }
            case "🟣 + 🏠 = 6" -> {
                if (proposition.getValeurs().get(2) + proposition.getValeurs().get(0) == 6) {
                    critere.valide = (position + etage == 6) ? 2 : 1;
                    yield true;
                }
                yield false;
            }
            case "🟣 + 🏠 > 6" -> {
                if (proposition.getValeurs().get(2) + proposition.getValeurs().get(0) > 6) {
                    critere.valide = (position + etage > 6) ? 2 : 1;
                    yield true;
                }
                yield false;
            }
            case "🟣 > 🏠 et 🔶" -> {
                if (proposition.getValeurs().get(2) > proposition.getValeurs().get(0) && proposition.getValeurs().get(2) > proposition.getValeurs().get(1)) {
                    critere.valide = (position > etage && position > salle) ? 2 : 1;
                    yield true;
                }
                yield false;
            }
            case "🏠 < 🔶 et 🟣" -> {
                if (proposition.getValeurs().get(0) < proposition.getValeurs().get(1) && proposition.getValeurs().get(0) < proposition.getValeurs().get(2)) {
                    critere.valide = (etage < salle && etage < position) ? 2 : 1;
                    yield true;
                }
                yield false;
            }
            case "🔶 > 🏠 et 🟣" -> {
                if (proposition.getValeurs().get(1) > proposition.getValeurs().get(0) && proposition.getValeurs().get(1) > proposition.getValeurs().get(2)) {
                    critere.valide = (salle > etage && salle > position) ? 2 : 1;
                    yield true;
                }
                yield false;
            }
            case "🟣 multiple de 3" -> {
                if (proposition.getValeurs().get(2) % 3 == 0) {
                    critere.valide = (position % 3 == 0) ? 2 : 1;
                    yield true;
                }
                yield false;
            }
            case "🟣 multiple de 5" -> {
                if (proposition.getValeurs().get(2) % 5 == 0) {
                    critere.valide = (position % 5 == 0) ? 2 : 1;
                    yield true;
                }
                yield false;
            }
            case "Toutes différentes" -> {
                if (!Objects.equals(proposition.getValeurs().get(0), proposition.getValeurs().get(1)) &&
                        !Objects.equals(proposition.getValeurs().get(0), proposition.getValeurs().get(2)) &&
                        !Objects.equals(proposition.getValeurs().get(1), proposition.getValeurs().get(2))) {
                    critere.valide = (etage != salle && etage != position && salle != position) ? 2 : 1;
                    yield true;
                }
                yield false;
            }
            case "Deux égales une différente" -> {
                if ((Objects.equals(proposition.getValeurs().get(0), proposition.getValeurs().get(1)) && !Objects.equals(proposition.getValeurs().get(0), proposition.getValeurs().get(2))) ||
                        (Objects.equals(proposition.getValeurs().get(0), proposition.getValeurs().get(2)) && !Objects.equals(proposition.getValeurs().get(0), proposition.getValeurs().get(1))) ||
                        (Objects.equals(proposition.getValeurs().get(1), proposition.getValeurs().get(2)) && !Objects.equals(proposition.getValeurs().get(1), proposition.getValeurs().get(0)))) {
                    critere.valide = ((etage == salle && etage != position) ||
                            (etage == position && etage != salle) ||
                            (salle == position && salle != etage)) ? 2 : 1;
                    yield true;
                }
                yield false;
            }
            case "Toutes égales" -> {
                if (Objects.equals(proposition.getValeurs().get(0), proposition.getValeurs().get(1)) &&
                        Objects.equals(proposition.getValeurs().get(0), proposition.getValeurs().get(2))) {
                    critere.valide = (etage == salle && etage == position) ? 2 : 1;
                    yield true;
                }
                yield false;
            }

            default -> throw new IllegalArgumentException("Critère inconnu : " + description);
        };
    }

}
