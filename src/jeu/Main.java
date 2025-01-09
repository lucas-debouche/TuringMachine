package jeu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    private Machine machine;
    private Partie partie;
    private Scenario scenario;

    public Main() {
        machine = new Machine();
        partie = new Partie();  // Créer une instance de Partie
        JFrame frame = new JFrame("Jeu de Proposition - Swing");

        // Maximiser la fenêtre pour l'afficher en plein écran
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);  // Plein écran
        frame.setUndecorated(true);  // Retirer les bordures et la barre de titre
        frame.setLayout(new BorderLayout());

        // Sélectionner un scénario au hasard parmi les 3, et passer le nombre de critères
        scenario = getScenarioAleatoire(6); // Valeur par défaut (6 critères)

        // Zone de sélection des vérificateurs
        JPanel initialPanel = new JPanel();
        initialPanel.setLayout(new BoxLayout(initialPanel, BoxLayout.Y_AXIS));

        // Ajouter des boutons radio pour sélectionner le nombre de vérificateurs
        JPanel verifierCountPanel = new JPanel();
        ButtonGroup verifierGroup = new ButtonGroup();

        JRadioButton verifier4 = new JRadioButton("4 Vérificateurs");
        JRadioButton verifier5 = new JRadioButton("5 Vérificateurs");
        JRadioButton verifier6 = new JRadioButton("6 Vérificateurs");

        verifierGroup.add(verifier4);
        verifierGroup.add(verifier5);
        verifierGroup.add(verifier6);

        verifierCountPanel.add(verifier4);
        verifierCountPanel.add(verifier5);
        verifierCountPanel.add(verifier6);

        // Définir un nombre de vérificateurs par défaut
        verifier5.setSelected(true);

        initialPanel.add(new JLabel("Nombre de vérificateurs :"));
        initialPanel.add(verifierCountPanel);

        // Bouton pour démarrer la partie avec un style agréable
        JButton startButton = new JButton("Démarrer la partie");
        startButton.setFont(new Font("Arial", Font.BOLD, 16));
        startButton.setBackground(new Color(50, 205, 50)); // Vert
        startButton.setForeground(Color.WHITE);
        startButton.setBorderPainted(false);
        startButton.setFocusPainted(false);
        startButton.setPreferredSize(new Dimension(200, 50));
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        initialPanel.add(startButton);

        // Événement de démarrage
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int verifierCount = 5; // Valeur par défaut

                if (verifier4.isSelected()) {
                    verifierCount = 4;
                } else if (verifier5.isSelected()) {
                    verifierCount = 5;
                } else if (verifier6.isSelected()) {
                    verifierCount = 6;
                }

                try {
                    if (verifierCount >= 4 && verifierCount <= 6) {
                        // Créer les vérificateurs
                        List<Critere> criteres = new ArrayList<>();
                        for (int i = 0; i < verifierCount; i++) {
                            Critere critere = scenario.getCritere(i);
                            if (critere != null) {
                                criteres.add(critere);
                            }
                        }

                        // Ajouter ces critères à la machine
                        for (Critere critere : criteres) {
                            Verif verifier = new Verif(critere);
                            machine.ajouterVerifier(verifier);
                        }

                        // Lancer la partie automatiquement
                        showAlert("Info", "La partie a commencé !");
                        startGame(frame);
                    } else {
                        showAlert("Erreur", "Nombre de vérificateurs incorrect.");
                    }
                } catch (NumberFormatException ex) {
                    showAlert("Erreur", "Veuillez entrer un nombre valide pour les vérificateurs.");
                }
            }
        });

        frame.add(initialPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private Scenario getScenarioAleatoire(int nombreDeCritere) {
        Random random = new Random();
        int scenarioIndex = random.nextInt(3); // Sélection aléatoire entre 0, 1, 2

        // 3 scénarios possibles
        switch (scenarioIndex) {
            case 0:
                return new Scenario(315, nombreDeCritere);
            case 1:
                return new Scenario(411, nombreDeCritere);
            case 2:
                return new Scenario(522, nombreDeCritere);
            default:
                return null;
        }
    }

    private void startGame(JFrame frame) {
        // Supprimer l'écran d'introduction
        frame.getContentPane().removeAll();

        // Zone pour afficher les critères
        JPanel criteriaPanel = new JPanel();
        criteriaPanel.setLayout(new BoxLayout(criteriaPanel, BoxLayout.Y_AXIS));
        for (Verif verifier : machine.getVerifyers()) {
            JTextArea criteriaArea = new JTextArea("Critère: " + verifier.getCritere().getDescription());
            criteriaArea.setEditable(false);
            criteriaArea.setBackground(new Color(240, 240, 240));
            criteriaArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            criteriaPanel.add(criteriaArea);
        }

        // Affichage des critères sur le côté gauche
        JScrollPane criteriaScroll = new JScrollPane(criteriaPanel);
        criteriaScroll.setPreferredSize(new Dimension(300, 600)); // Augmenter la largeur de la zone des critères
        frame.add(criteriaScroll, BorderLayout.WEST);

        // Zone pour saisir le code (pavé numérique avec symboles)
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(1, 3, 20, 0)); // 3 colonnes, espacement entre les colonnes

        // 3 colonnes pour le triangle, carré, cercle avec leurs pavés
        JPanel trianglePanel = new JPanel();
        JPanel squarePanel = new JPanel();
        JPanel circlePanel = new JPanel();

        // Créer un pavé numérique pour chaque symbole (0 à 5)
        String[] buttonLabels = {"0", "1", "2", "3", "4", "5"};
        JButton[][] buttons = new JButton[3][6]; // 3 colonnes, 6 boutons par colonne
        Color selectedColor = new Color(255, 223, 186); // Couleur de surbrillance

        // Utiliser GridBagLayout pour que chaque colonne prenne toute la hauteur
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.VERTICAL;  // Remplir la hauteur de la cellule

        for (int i = 0; i < 3; i++) {
            JPanel currentPanel = null;

            if (i == 0) {
                currentPanel = trianglePanel;
            } else if (i == 1) {
                currentPanel = squarePanel;
            } else if (i == 2) {
                currentPanel = circlePanel;
            }

            currentPanel.setLayout(layout); // Utilisation du GridBagLayout pour la mise en forme

            for (int j = 0; j < 6; j++) {
                buttons[i][j] = new JButton(buttonLabels[j]);
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 20));
                buttons[i][j].setPreferredSize(new Dimension(60, 60));
                buttons[i][j].setBackground(new Color(240, 240, 240));
                buttons[i][j].setFocusPainted(false);
                buttons[i][j].setBorder(BorderFactory.createLineBorder(Color.GRAY, 2, true)); // Coins arrondis
                // Ajouter l'action au bouton
                final int col = i; // Colonne du bouton (triangle, carré, cercle)
                final int num = j; // Numéro du bouton (0 à 5)
                gbc.gridx = 0;
                gbc.gridy = j; // Position des boutons sur l'axe vertical

                buttons[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Réinitialiser la couleur de tous les boutons dans cette colonne
                        for (JButton button : buttons[col]) {
                            button.setBackground(new Color(240, 240, 240)); // Retirer la surbrillance
                        }
                        // Appliquer la surbrillance au bouton sélectionné
                        buttons[col][num].setBackground(selectedColor);
                    }
                });
                currentPanel.add(buttons[i][j], gbc);
            }
        }

        // Ajouter des labels aux panels pour décrire les symboles
        JLabel triangleLabel = new JLabel("🏠 : Etage");
        JLabel squareLabel = new JLabel("🔶🔵 : Identifiant de la salle");
        JLabel circleLabel = new JLabel("🟣 : Position");

        trianglePanel.add(triangleLabel);
        squarePanel.add(squareLabel);
        circlePanel.add(circleLabel);

        inputPanel.add(trianglePanel);
        inputPanel.add(squarePanel);
        inputPanel.add(circlePanel);

        // Ajouter le panneau d'entrée
        frame.add(inputPanel, BorderLayout.CENTER);

        // Affichage du bouton de vérification
        JButton verifyButton = new JButton("Vérifier");
        verifyButton.setFont(new Font("Arial", Font.BOLD, 16));
        verifyButton.setBackground(new Color(50, 205, 50)); // Vert
        verifyButton.setForeground(Color.WHITE);
        verifyButton.setPreferredSize(new Dimension(200, 50));
        verifyButton.setBorderPainted(false);
        verifyButton.setFocusPainted(false);
        frame.add(verifyButton, BorderLayout.SOUTH);

        // Lorsque le joueur fait une proposition, vérifier la réponse
        verifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Collecte du code et validation
                String code = "";
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 6; j++) {
                        if (buttons[i][j].getBackground().equals(selectedColor)) {
                            code += buttonLabels[j];
                            break;
                        }
                    }
                }
                //On le transforme en int
                int codeInt = Integer.parseInt(code);
                // Vérification du code
                if (codeInt == scenario.getNumeroSalle()) {
                    showAlert("Bravo !", "Vous avez trouvé le code !");
                    frame.dispose();
                    new Main();
                } else {
                    showAlert("Dommage...", "Ce n'est pas le bon code. Réessayez !");
                }
            }

        });

        frame.setVisible(true);
    }

    private void showAlert(String title, String message) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }
}
