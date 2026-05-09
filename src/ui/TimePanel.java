package ui;

import javax.swing.*;
import java.awt.*;
import app.MainApp;
import service.TimeService;

public class TimePanel extends JPanel {

    private MainApp mainApp;
    private TimeService timeService;

    private JTextField inputField;
    private JLabel resultLabel;
    private JComboBox<String> conversionBox;

    public TimePanel(MainApp mainApp) {

        this.mainApp = mainApp;
        this.timeService = new TimeService();

        setLayout(new BorderLayout(15, 15));
        setBackground(new Color(245, 250, 255));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Title
        JLabel title = new JLabel("Time Converter", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        add(title, BorderLayout.NORTH);

        // Center Panel
        JPanel centerPanel = new JPanel(new GridLayout(5, 1, 10, 10));

        inputField = new JTextField();

        String[] options = {
                "Seconds to Minutes",
                "Minutes to Seconds",
                "Minutes to Hours",
                "Hours to Minutes",
                "Hours to Seconds",
                "Seconds to Hours"
        };

        conversionBox = new JComboBox<>(options);

        JButton convertBtn = new JButton("Convert");

        resultLabel = new JLabel("Result: ");
        resultLabel.setFont(new Font("Arial", Font.BOLD, 16));

        convertBtn.addActionListener(e -> convert());

        centerPanel.add(new JLabel("Enter Value:"));
        centerPanel.add(inputField);
        centerPanel.add(conversionBox);
        centerPanel.add(convertBtn);
        centerPanel.add(resultLabel);

        add(centerPanel, BorderLayout.CENTER);

        // Back Button
        JButton backBtn = new JButton("Back to Dashboard");
        backBtn.addActionListener(e -> mainApp.showPanel("dashboard"));

        add(backBtn, BorderLayout.SOUTH);
    }

    private void convert() {

        try {
            double value = Double.parseDouble(inputField.getText());

            if (value < 0) {
                JOptionPane.showMessageDialog(this,
                        "Time cannot be negative.");
                return;
            }

            int choice = conversionBox.getSelectedIndex() + 1;

            double result = timeService.convert(choice, value);

            resultLabel.setText("Result: " + String.format("%.4f", result));

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Please enter a valid number.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
