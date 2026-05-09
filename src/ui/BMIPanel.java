package ui;

import javax.swing.*;
import java.awt.*;
import app.MainApp;
import service.BMIService;

public class BMIPanel extends JPanel {

    private MainApp mainApp;
    private BMIService bmiService;

    private JTextField weightField;
    private JTextField heightField;
    private JLabel resultLabel;

    public BMIPanel(MainApp mainApp) {

        this.mainApp = mainApp;
        this.bmiService = new BMIService();

        setLayout(new BorderLayout(15, 15));
        setBackground(new Color(245, 255, 245));
        setBorder(BorderFactory.createEmptyBorder(30, 60, 30, 60));

        // Title
        JLabel title = new JLabel("BMI Calculator", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 26));
        add(title, BorderLayout.NORTH);

        // Center Panel
        JPanel centerPanel = new JPanel(new GridLayout(6, 1, 10, 10));

        weightField = new JTextField();
        heightField = new JTextField();

        JButton calculateBtn = new JButton("Calculate BMI");

        resultLabel = new JLabel("Result: ");
        resultLabel.setFont(new Font("Arial", Font.BOLD, 16));

        calculateBtn.addActionListener(e -> calculateBMI());

        centerPanel.add(new JLabel("Enter Weight (kg):"));
        centerPanel.add(weightField);
        centerPanel.add(new JLabel("Enter Height (cm):"));
        centerPanel.add(heightField);
        centerPanel.add(calculateBtn);
        centerPanel.add(resultLabel);

        add(centerPanel, BorderLayout.CENTER);

        // Back Button
        JButton backBtn = new JButton("Back to Dashboard");
        backBtn.addActionListener(e -> mainApp.showPanel("dashboard"));

        add(backBtn, BorderLayout.SOUTH);
    }

    private void calculateBMI() {

        try {

            double weight = Double.parseDouble(weightField.getText());
            double heightCm = Double.parseDouble(heightField.getText());

            if (weight <= 0 || heightCm <= 0) {
                JOptionPane.showMessageDialog(this,
                        "Weight and height must be positive values.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            double bmi = bmiService.calculateBMI(weight, heightCm);

            String category;

            if (bmi < 18.5)
                category = "Underweight";
            else if (bmi < 25)
                category = "Normal Weight";
            else if (bmi < 30)
                category = "Overweight";
            else
                category = "Obese";

            resultLabel.setText("Result: " +
                    String.format("%.2f", bmi) +
                    " (" + category + ")");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Please enter valid numeric values.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
