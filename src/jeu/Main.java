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
        JFrame frame = new JFrame("Machine Turing");

        // Maximiser la fenêtre pour l'afficher en plein écran
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);  // Plein écran
        frame.setLayout(new BorderLayout());

        // Ecran d'accueil
        JPanel welcomePanel = new JPanel();
        welcomePanel.setLayout(new BoxLayout(welcomePanel, BoxLayout.Y_AXIS));
        welcomePanel.setBackground(new Color(83, 83, 83));

        JLabel title = new JLabel("Bienvenue dans le Turing Challenge");
        title.setFont(new Font("Roboto", Font.BOLD, 36));
        title.setForeground(Color.WHITE);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitle = new JLabel("Choisissez le nombre de vérificateurs pour commencer");
        subtitle.setFont(new Font("Roboto", Font.PLAIN, 18));
        subtitle.setForeground(Color.WHITE);
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        welcomePanel.add(Box.createRigidArea(new Dimension(0, 100)));
        welcomePanel.add(title);
        welcomePanel.add(Box.createRigidArea(new Dimension(0, 100)));
        welcomePanel.add(subtitle);

        // Sélectionner un scénario au hasard parmi les 3, et passer le nombre de critères
        scenario = getScenarioAleatoire(6); // Valeur par défaut (6 critères)

        // Ajouter des boutons radio pour sélectionner le nombre de vérificateurs
        ButtonGroup verifierGroup = new ButtonGroup();

        JRadioButton verifier4 = new JRadioButton("4 Vérificateurs");
        verifier4.setFont(new Font("Roboto", Font.PLAIN, 16));
        verifier4.setForeground(Color.WHITE);
        verifier4.setBackground(new Color(83, 83, 83));
        verifier4.setAlignmentX(Component.CENTER_ALIGNMENT);
        JRadioButton verifier5 = new JRadioButton("5 Vérificateurs");
        verifier5.setFont(new Font("Roboto", Font.PLAIN, 16));
        verifier5.setForeground(Color.WHITE);
        verifier5.setBackground(new Color(83, 83, 83));
        verifier5.setAlignmentX(Component.CENTER_ALIGNMENT);
        JRadioButton verifier6 = new JRadioButton("6 Vérificateurs");
        verifier6.setFont(new Font("Roboto", Font.PLAIN, 16));
        verifier6.setForeground(Color.WHITE);
        verifier6.setBackground(new Color(83, 83, 83));
        verifier6.setAlignmentX(Component.CENTER_ALIGNMENT);

        verifierGroup.add(verifier4);
        verifierGroup.add(verifier5);
        verifierGroup.add(verifier6);

        // Définir un nombre de vérificateurs par défaut
        verifier5.setSelected(true);

        welcomePanel.add(Box.createRigidArea(new Dimension(0, 10)));
        welcomePanel.add(verifier4);
        welcomePanel.add(Box.createRigidArea(new Dimension(0, 10)));
        welcomePanel.add(verifier5);
        welcomePanel.add(Box.createRigidArea(new Dimension(0, 10)));
        welcomePanel.add(verifier6);


        // Bouton pour démarrer la partie
        JButton startButton = new JButton("Démarrer la partie");
        styleButton(startButton);
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        welcomePanel.add(Box.createRigidArea(new Dimension(0, 200)));
        welcomePanel.add(startButton);

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
                    startGame(frame);
                }

            }
        });

        frame.add(welcomePanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private void styleButton(JButton button) {
        button.setFont(new Font("Roboto", Font.BOLD, 20));
        button.setBackground(new Color(50, 205, 50));
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createLineBorder(new Color(50, 205, 50), 20, true));
        button.setOpaque(false);
        button.setBorderPainted(false);
        button.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(button.getBackground());
                g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 50, 50);
                super.paint(g, c);
            }
        });
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
        JLabel titleCritere = new JLabel("Critère: ");
        titleCritere.setBackground(new Color(240, 240, 240));
        titleCritere.setFont(new Font("Roboto", Font.BOLD, 16));
        criteriaPanel.add(Box.createRigidArea(new Dimension(0, 50)));
        criteriaPanel.add(titleCritere);
        criteriaPanel.add(Box.createRigidArea(new Dimension(0, 50)));

        for (Verif verifier : machine.getVerifyers()) {
            JTextArea criteriaArea = new JTextArea("- " +verifier.getCritere().getDescription());
            criteriaArea.setEditable(false);
            criteriaArea.setBackground(new Color(240, 240, 240));
            criteriaArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            criteriaArea.setFont(new Font("Roboto", Font.PLAIN, 14));
            criteriaArea.setLineWrap(true);
            criteriaArea.setWrapStyleWord(true);
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
                buttons[i][j].setFont(new Font("Roboto", Font.PLAIN, 20));
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
        JPanel verifyPanel = new JPanel();
        verifyPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        verifyPanel.setBorder(null);  // Supprimer la bordure du panneau
        JButton verifyButton = new JButton("Vérifier");
        styleButton(verifyButton);
        verifyButton.setPreferredSize(new Dimension(500, 50));
        verifyPanel.add(verifyButton);

        frame.add(verifyPanel, BorderLayout.SOUTH);

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
