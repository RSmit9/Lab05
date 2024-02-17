        import javax.swing.*;
        import javax.swing.border.Border;
        import java.awt.*;
        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;
        import java.util.Random;


public class RockPaperScissorsFrame extends JFrame implements ActionListener
    {
            private final JButton quitButton;
            private final JTextArea resultsTextArea;
            private final JTextField playerWinsField;
            private final JTextField computerWinsField;
            private final JTextField tiesField;

            private int playerWins = 0;
            private int computerWins = 0;
            private int ties = 0;

            private final Random random;

                public RockPaperScissorsFrame() {
                    super("Rock Paper Scissors Game");
                        random = new Random();
                        JButton rockButton = createButton("Rock", "rock.png");
                        JButton paperButton = createButton("Paper", "paper.png");
                        JButton scissorsButton = createButton("Scissors", "scissors.png");
                        quitButton = createButton("Quit", null);

                        resultsTextArea = new JTextArea(10, 30);
                        resultsTextArea.setEditable(false);
                        JScrollPane scrollPane = new JScrollPane(resultsTextArea);

                        playerWinsField = createStatField();
                        computerWinsField = createStatField();
                        tiesField = createStatField();

                            JPanel buttonPanel = new JPanel();
                            buttonPanel.setLayout(new GridLayout(2, 1, 5, 5));
                            buttonPanel.setBorder(BorderFactory.createTitledBorder("Choose"));
                            buttonPanel.add(rockButton);
                            buttonPanel.add(paperButton);
                            buttonPanel.add(scissorsButton);
                            buttonPanel.add(quitButton);

                            JPanel statsPanel = new JPanel(new GridLayout(3, 2));
                            statsPanel.setBorder(BorderFactory.createTitledBorder("Stats"));
                            statsPanel.add(new JLabel("Player Wins:"));
                            statsPanel.add(playerWinsField);
                            statsPanel.add(new JLabel("Computer Wins:"));
                            statsPanel.add(computerWinsField);
                            statsPanel.add(new JLabel("Ties:"));
                            statsPanel.add(tiesField);

                            JPanel resultsPanel = new JPanel(new BorderLayout());
                            resultsPanel.setBorder(BorderFactory.createTitledBorder("Results"));
                            resultsPanel.add(scrollPane, BorderLayout.CENTER);

                            Container container = getContentPane();
                            container.setLayout(new GridLayout(1, 3));
                            container.add(buttonPanel);
                            container.add(statsPanel);
                            container.add(resultsPanel);

                            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            pack();
                            setLocationRelativeTo(null);
                            setVisible(true);

                            rockButton.addActionListener(this);
                            paperButton.addActionListener(this);
                            scissorsButton.addActionListener(this);
                            quitButton.addActionListener(this);
    }
    private JButton createButton(String label, String iconFileName)
    {
        JButton button = new JButton(label);
        if (iconFileName != null)
        {
            ImageIcon icon = new ImageIcon(getClass().getResource(iconFileName));
            button.setIcon(icon);
        }
        return button;
    }
    private JTextField createStatField()
    {
        JTextField field = new JTextField(5);
        field.setEditable(false);
        return field;
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == quitButton) {
            dispose(); // Quit the game
        } else

            {
                String playerChoice = e.getActionCommand();
                String computerChoice = generateComputerChoice();
                String result = determineResult(playerChoice, computerChoice);
                updateStats(result);
                updateResultsTextArea(result);
            }
    }
    private String generateComputerChoice()
    {
        int choice = random.nextInt(3);
        switch (choice)
        {
            case 0:
                return "Rock";
            case 1:
                return "Paper";
            default:
                return "Scissors";
        }
    }
    private String determineResult(String playerChoice, String computerChoice) {

        if (playerChoice.equals(computerChoice))
        {
            ties++;
            return "Tie";
                } else if ((playerChoice.equals("Rock") && computerChoice.equals("Scissors")) ||
                        (playerChoice.equals("Paper") && computerChoice.equals("Rock")) ||
                        (playerChoice.equals("Scissors") && computerChoice.equals("Paper"))) {
                    playerWins++;
                    return "Player wins";
                } else
                    {
                        computerWins++;
                        return "Computer wins";
                    }
    }

    private void updateStats(String result)
    {
        playerWinsField.setText(String.valueOf(playerWins));
        computerWinsField.setText(String.valueOf(computerWins));
        tiesField.setText(String.valueOf(ties));
    }
    private void updateResultsTextArea(String result)
    {
        resultsTextArea.append(result + "\n");
    }
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(RockPaperScissorsFrame::new);
    }
}
