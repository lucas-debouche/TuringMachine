package jeu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private Partie partie;
    private Machine machine;
    private DefaultListModel<String> joueursModel;
    private DefaultListModel<String> historiqueModel;

    public Main() {
        partie = new Partie();
        machine = new Machine();
        machine.initialiserPartie();

        // Création de la fenêtre
        JFrame frame = new JFrame("Jeu de Proposition - Swing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 500);
        frame.setLayout(new BorderLayout());

        // Zone pour ajouter des joueurs
        JPanel joueurPanel = new JPanel();
        JTextField joueurNomField = new JTextField(15);
        JButton ajouterJoueurButton = new JButton("Ajouter Joueur");
        joueursModel = new DefaultListModel<>();
        JList<String> joueursListView = new JList<>(joueursModel);

        ajouterJoueurButton.addActionListener(e -> {
            String nom = joueurNomField.getText().trim();
            if (!nom.isEmpty()) {
                Joueur joueur = new Joueur(nom);
                partie.getJoueurs().add(joueur);
                joueursModel.addElement(nom);
                joueurNomField.setText("");
            } else {
                showAlert("Erreur", "Le nom du joueur ne peut pas être vide.");
            }
        });

        joueurPanel.add(new JLabel("Ajouter un joueur :"));
        joueurPanel.add(joueurNomField);
        joueurPanel.add(ajouterJoueurButton);
        frame.add(joueurPanel, BorderLayout.NORTH);

        // Zone pour commencer une partie
        JButton commencerPartieButton = new JButton("Commencer Partie");
        commencerPartieButton.addActionListener(e -> {
            if (!partie.getJoueurs().isEmpty()) {
                partie.commencerPartie();
                showAlert("Info", "La partie a commencé !");
            } else {
                showAlert("Erreur", "Ajoutez au moins un joueur avant de commencer.");
            }
        });

        // Zone pour faire des propositions
        JPanel propositionPanel = new JPanel();
        JTextField propositionField = new JTextField(15);
        JButton proposerButton = new JButton("Proposer");
        historiqueModel = new DefaultListModel<>();
        JList<String> historiqueListView = new JList<>(historiqueModel);

        proposerButton.addActionListener(e -> {
            if (partie.isEtatPartie()) {
                String[] valeursStr = propositionField.getText().trim().split(" ");
                List<Integer> valeurs = new ArrayList<>();
                try {
                    for (String val : valeursStr) {
                        valeurs.add(Integer.parseInt(val));
                    }
                    Proposition proposition = new Proposition(valeurs);
                    boolean resultat = machine.analyserProposition(proposition);
                    partie.getHistorique().add(proposition);
                    historiqueModel.addElement("Proposition: " + valeurs + " -> Résultat: " + (resultat ? "Validée" : "Refusée"));
                    propositionField.setText("");
                } catch (NumberFormatException ex) {
                    showAlert("Erreur", "Veuillez entrer uniquement des nombres.");
                }
            } else {
                showAlert("Erreur", "Commencez une partie avant de faire une proposition.");
            }
        });

        propositionPanel.add(new JLabel("Faire une proposition :"));
        propositionPanel.add(propositionField);
        propositionPanel.add(proposerButton);
        frame.add(propositionPanel, BorderLayout.CENTER);

        // Liste des joueurs et historique des propositions
        frame.add(new JScrollPane(joueursListView), BorderLayout.WEST);
        frame.add(new JScrollPane(historiqueListView), BorderLayout.EAST);

        // Bouton pour terminer la partie
        JButton terminerPartieButton = new JButton("Terminer Partie");
        terminerPartieButton.addActionListener(e -> {
            if (partie.isEtatPartie()) {
                partie.terminerPartie();
                showAlert("Info", "La partie est terminée !");
            } else {
                showAlert("Erreur", "Aucune partie en cours.");
            }
        });

        JPanel southPanel = new JPanel();
        southPanel.add(commencerPartieButton);
        southPanel.add(terminerPartieButton);
        frame.add(southPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private void showAlert(String title, String message) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }
}