package jeu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    private final Machine machine;
    private Scenario scenario;

    public Main() {
        machine = new Machine();
        JFrame frame = new JFrame("Machine Turing");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);  // Plein écran
        frame.setLayout(new BorderLayout());

        // Écran d'accueil
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

        ButtonGroup verifierGroup = new ButtonGroup();

        JRadioButton verifier4 = new JRadioButton("4 Vérificateurs");
        JRadioButton verifier5 = new JRadioButton("5 Vérificateurs");
        JRadioButton verifier6 = new JRadioButton("6 Vérificateurs");

        verifier4.setFont(new Font("Roboto", Font.PLAIN, 16));
        verifier5.setFont(new Font("Roboto", Font.PLAIN, 16));
        verifier6.setFont(new Font("Roboto", Font.PLAIN, 16));

        verifier4.setForeground(Color.WHITE);
        verifier5.setForeground(Color.WHITE);
        verifier6.setForeground(Color.WHITE);

        verifier4.setBackground(new Color(83, 83, 83));
        verifier5.setBackground(new Color(83, 83, 83));
        verifier6.setBackground(new Color(83, 83, 83));

        verifier4.setAlignmentX(Component.CENTER_ALIGNMENT);
        verifier5.setAlignmentX(Component.CENTER_ALIGNMENT);
        verifier6.setAlignmentX(Component.CENTER_ALIGNMENT);

        verifierGroup.add(verifier4);
        verifierGroup.add(verifier5);
        verifierGroup.add(verifier6);

        verifier5.setSelected(true);

        welcomePanel.add(Box.createRigidArea(new Dimension(0, 10)));
        welcomePanel.add(verifier4);
        welcomePanel.add(Box.createRigidArea(new Dimension(0, 10)));
        welcomePanel.add(verifier5);
        welcomePanel.add(Box.createRigidArea(new Dimension(0, 10)));
        welcomePanel.add(verifier6);

        JButton startButton = new JButton("Démarrer la partie");
        styleButton(startButton);
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton historiqueButton = new JButton("Afficher l'historique");
        styleButton(historiqueButton);
        historiqueButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        welcomePanel.add(Box.createRigidArea(new Dimension(0, 200)));
        welcomePanel.add(startButton);
        welcomePanel.add(Box.createRigidArea(new Dimension(0, 50)));
        welcomePanel.add(historiqueButton);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int verifierCount = 5;

                if (verifier4.isSelected()) {
                    verifierCount = 4;
                } else if (verifier6.isSelected()) {
                    verifierCount = 6;
                }

                // Générer un code aléatoire
                Scenario scenario = new Scenario(getRandomScenario());

                // Créer le scénario avec le numéro de salle et le code correct

                machine.getVerifyers().clear();

                // Ajouter les vérificateurs (logique inchangée)
                for (int i = 1; i <= verifierCount; i++) {
                    switch (i) {
                        case 1:
                            machine.ajouterVerifier(new Verif(
                                    List.of(
                                            new Critere("🟣 impaire"),
                                            new Critere("🟣 paire"),
                                            new Critere("🟣 = 5")
                                    ), 1));
                            break;
                        case 2:
                            machine.ajouterVerifier(new Verif(
                                    List.of(
                                            new Critere("🔶 < 🏠"),
                                            new Critere("🔶 = 🏠"),
                                            new Critere("🔶 > 🏠")
                                    ), 2));
                            break;
                        case 3:
                            machine.ajouterVerifier(new Verif(
                                    List.of(
                                            new Critere("🟣 + 🏠 < 10"),
                                            new Critere("🟣 + 🏠 = 10"),
                                            new Critere("🟣 + 🏠 > 10")
                                    ), 3));
                            break;
                        case 4:
                            machine.ajouterVerifier(new Verif(
                                    List.of(
                                            new Critere("🟣 > 🏠 et 🔶"),
                                            new Critere("🏠 < 🔶 et 🟣"),
                                            new Critere("🔶 > 🏠 et 🟣")
                                    ), 4));
                            break;
                        case 5:
                            machine.ajouterVerifier(new Verif(
                                    List.of(
                                            new Critere("🟣 multiple de 2"),
                                            new Critere("🟣 multiple de 3"),
                                            new Critere("🟣 multiple de 5")
                                    ), 5));
                            break;
                        case 6:
                            machine.ajouterVerifier(new Verif(
                                    List.of(
                                            new Critere("Toutes différentes"),
                                            new Critere("Deux égales une différente"),
                                            new Critere("Toutes égales")
                                    ), 6));
                            break;
                    }
                }

                startGame(frame,scenario);
            }
        });

        historiqueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {}
        });



        frame.add(welcomePanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private void styleButton(JButton button) {
        button.setFont(new Font("Roboto", Font.BOLD, 20));
        button.setBackground(new Color(50, 205, 50));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
    }

    private int getRandomScenario   () {
        List<Integer> scenarios = List.of(312, 431, 522);
        return scenarios.get(new Random().nextInt(scenarios.size()));
    }


    private void startGame(JFrame frame,Scenario scenario) {
        // Supprimer l'écran d'introduction
        frame.getContentPane().removeAll();

        // Zone pour afficher les vérificateurs
        JPanel verifierPanel = new JPanel();
        verifierPanel.setLayout(new BoxLayout(verifierPanel, BoxLayout.Y_AXIS));

        JLabel titleVerifier = new JLabel("Cliquez sur 3 vérificateurs pour les sélectionner :");
        titleVerifier.setFont(new Font("Roboto", Font.BOLD, 20));
        verifierPanel.add(titleVerifier);
        verifierPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        List<JPanel> verifierBoxes = new ArrayList<>();
        List<Verif> selectedVerifiers = new ArrayList<>();

        for (Verif verifier : machine.getVerifyers()) {
            JPanel verifierBox = new JPanel();
            verifierBox.setLayout(new BorderLayout());
            verifierBox.setPreferredSize(new Dimension(200, 120)); // Taille ajustée
            verifierBox.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
            verifierBox.setBackground(Color.WHITE);

            JPanel topPanel = new JPanel();
            topPanel.setBackground(new Color(200, 200, 200));
            JLabel title = new JLabel("Vérificateur " + verifier.getId());
            title.setFont(new Font("Roboto", Font.BOLD, 18)); // Taille légèrement augmentée
            topPanel.add(title);
            verifierBox.add(topPanel, BorderLayout.NORTH);

            JPanel bottomPanel = new JPanel();
            bottomPanel.setLayout(new GridLayout(1, 3, 10, 10)); // Espacement horizontal et vertical ajouté
            List<JPanel> criterePanels = new ArrayList<>(); // Stocke les panneaux des critères
            for (Critere critere : verifier.getCriteres()) {
                JPanel criterePanel = new JPanel();
                criterePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                criterePanel.setPreferredSize(new Dimension(120, 50)); // Taille individuelle des critères ajustée

                JLabel critereLabel = new JLabel(critere.getDescription(), SwingConstants.CENTER);
                critereLabel.setFont(new Font("Roboto", Font.PLAIN, 16)); // Taille augmentée pour plus de lisibilité
                criterePanel.add(critereLabel);

                bottomPanel.add(criterePanel);
                criterePanels.add(criterePanel); // Ajouter le panneau à la liste
            }
            verifierBox.add(bottomPanel, BorderLayout.CENTER);

            // Gestion de la sélection
            verifierBox.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    if (selectedVerifiers.contains(verifier)) {
                        selectedVerifiers.remove(verifier); // Retirer de la sélection
                        topPanel.setBackground(new Color(200, 200, 200)); // Rendre le fond blanc
                        topPanel.revalidate(); // Assurer que la couleur est appliquée
                        topPanel.repaint(); // Forcer le rafraîchissement
                    } else if (selectedVerifiers.size() < 3) {
                        selectedVerifiers.add(verifier); // Ajouter à la sélection
                        topPanel.setBackground(new Color(173, 216, 230));// Indiquer la sélection (bleu clair)
                        topPanel.revalidate(); // Assurer que la couleur est appliquée
                        topPanel.repaint(); // Forcer le rafraîchissement
                    } else {
                        showAlert("Erreur", "Vous ne pouvez sélectionner que 3 vérificateurs !");
                    }
                }
            });

            verifierBoxes.add(verifierBox);
            verifierPanel.add(verifierBox);
            verifierPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            verifier.setCriterePanels(criterePanels);
        }

        JScrollPane verifierScroll = new JScrollPane(verifierPanel);
        verifierScroll.setPreferredSize(new Dimension(650, 700)); // Augmenter la largeur et la hauteur de la zone des vérificateurs
        frame.add(verifierScroll, BorderLayout.WEST);

        // Zone pour saisir le code
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(1, 3, 20, 0));

        JPanel trianglePanel = new JPanel();
        JPanel squarePanel = new JPanel();
        JPanel circlePanel = new JPanel();

        String[] buttonLabels = {"0", "1", "2", "3", "4", "5"};
        JButton[][] buttons = new JButton[3][6];
        Color selectedColor = new Color(255, 223, 186);

        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.VERTICAL;

        for (int i = 0; i < 3; i++) {
            JPanel currentPanel = (i == 0) ? trianglePanel : (i == 1) ? squarePanel : circlePanel;
            currentPanel.setLayout(layout);

            for (int j = 0; j < 6; j++) {
                buttons[i][j] = new JButton(buttonLabels[j]);
                buttons[i][j].setFont(new Font("Roboto", Font.PLAIN, 20));
                buttons[i][j].setPreferredSize(new Dimension(60, 60));
                buttons[i][j].setBackground(new Color(240, 240, 240));
                buttons[i][j].setFocusPainted(false);
                buttons[i][j].setBorder(BorderFactory.createLineBorder(Color.GRAY, 2, true));

                final int col = i;
                final int num = j;
                gbc.gridx = 0;
                gbc.gridy = j;

                buttons[i][j].addActionListener(e -> {
                    for (JButton button : buttons[col]) {
                        button.setBackground(new Color(240, 240, 240));
                    }
                    buttons[col][num].setBackground(selectedColor);
                });
                currentPanel.add(buttons[i][j], gbc);
            }
        }

        JLabel triangleLabel = new JLabel("🏠 : Etage");
        JLabel squareLabel = new JLabel("🔶 : Salle");
        JLabel circleLabel = new JLabel("🟣 : Position");

        trianglePanel.add(triangleLabel);
        squarePanel.add(squareLabel);
        circlePanel.add(circleLabel);

        inputPanel.add(trianglePanel);
        inputPanel.add(squarePanel);
        inputPanel.add(circlePanel);
        frame.add(inputPanel, BorderLayout.CENTER);

        // Bouton pour valider la proposition
        JPanel verifyPanel = new JPanel();
        verifyPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        JButton verifyButton = new JButton("Vérifier");
        styleButton(verifyButton);
        verifyButton.setPreferredSize(new Dimension(200, 50));
        verifyPanel.add(verifyButton);
        frame.add(verifyPanel, BorderLayout.SOUTH);

        verifyButton.addActionListener(e -> {

            if (selectedVerifiers.size() != 3) {
                showAlert("Erreur", "Vous devez sélectionner exactement 3 vérificateurs !");
                return;
            }

            StringBuilder code = new StringBuilder();
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 6; j++) {
                    if (buttons[i][j].getBackground().equals(selectedColor)) {
                        code.append(buttonLabels[j]);
                        break;
                    }
                }
            }

            List<Integer> valeurs = new ArrayList<>();
            for (char c : code.toString().toCharArray()) {
                valeurs.add(Character.getNumericValue(c));
            }
            Proposition proposition = new Proposition(valeurs);
            //On vérifie si le code saisi correspond à la solution
            if (scenario.verifierCode(proposition)) {
                showAlert("Bravo !", "Vous avez trouvé le code secret !");
                return;
            } else {
                showAlert("Dommage", "Le code saisi est incorrect !");
            }
            for (Verif verifier : selectedVerifiers) {
                Critere validCritere = verifier.verifierProposition(proposition, scenario);
                if (validCritere != null) {
                    JOptionPane.showMessageDialog(null, "Critère validé : " + validCritere.getDescription(), "Succès", JOptionPane.INFORMATION_MESSAGE);
                    JPanel criterePanel = verifier.getCriterePanels().get(verifier.getCriteres().indexOf(validCritere));
                    criterePanel.setBackground(Color.GREEN);
                    criterePanel.revalidate();
                    criterePanel.repaint();
                } else {
                    JOptionPane.showMessageDialog(null, "Aucun critère validé pour Vérificateur " + verifier.getId(), "Échec", JOptionPane.ERROR_MESSAGE);
                    JPanel criterePanel = verifier.getCriterePanels().get(verifier.getId() - 1); // Id ou index correspondant
                    criterePanel.setBackground(Color.RED);
                    criterePanel.revalidate();
                    criterePanel.repaint();
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
