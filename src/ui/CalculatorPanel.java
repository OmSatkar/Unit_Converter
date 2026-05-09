package ui;

import javax.swing.*;
import java.awt.*;
import app.MainApp;

public class CalculatorPanel extends JPanel {

    private MainApp mainApp;

    private JTextField display;

    private String operator = "";
    private double firstNumber = 0;

    public CalculatorPanel(MainApp mainApp) {

        this.mainApp = mainApp;

        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(230, 240, 255));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // =========================
        // Display Field
        // =========================
        display = new JTextField();
        display.setFont(new Font("Arial", Font.BOLD, 28));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        display.setBackground(Color.WHITE);
        display.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        add(display, BorderLayout.NORTH);

        // =========================
        // Buttons Panel
        // =========================
        JPanel buttonPanel = new JPanel(new GridLayout(4, 4, 10, 10));

        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", "C", "=", "+"
        };

        for (String text : buttons) {

            JButton btn = new JButton(text);
            btn.setFont(new Font("Arial", Font.BOLD, 18));
            btn.setFocusPainted(false);
            btn.setBackground(Color.WHITE);

            // Highlight operators
            if (text.matches("[+\\-*/=]")) {
                btn.setBackground(new Color(180, 200, 255));
            }

            btn.addActionListener(e -> handleButton(text));

            buttonPanel.add(btn);
        }

        add(buttonPanel, BorderLayout.CENTER);

        // =========================
        // Back Button
        // =========================
        JButton backBtn = new JButton("Back to Dashboard");
        backBtn.setFont(new Font("Arial", Font.PLAIN, 14));
        backBtn.setFocusPainted(false);

        backBtn.addActionListener(e -> mainApp.showPanel("dashboard"));

        add(backBtn, BorderLayout.SOUTH);
    }

    // =========================
    // Button Logic
    // =========================
    private void handleButton(String value) {

        // If number pressed
        if (value.matches("[0-9]")) {
            display.setText(display.getText() + value);
        }

        // If operator pressed
        else if (value.matches("[+\\-*/]")) {

            if (display.getText().isEmpty()) return;

            firstNumber = Double.parseDouble(display.getText());
            operator = value;
            display.setText("");
        }

        // If equals pressed
        else if (value.equals("=")) {

            if (display.getText().isEmpty() || operator.isEmpty()) return;

            double secondNumber = Double.parseDouble(display.getText());
            double result = 0;

            switch (operator) {
                case "+":
                    result = firstNumber + secondNumber;
                    break;

                case "-":
                    result = firstNumber - secondNumber;
                    break;

                case "*":
                    result = firstNumber * secondNumber;
                    break;

                case "/":
                    if (secondNumber == 0) {
                        JOptionPane.showMessageDialog(this, "Cannot divide by zero.");
                        return;
                    }
                    result = firstNumber / secondNumber;
                    break;
            }

            display.setText(String.valueOf(result));
            operator = "";
            firstNumber = result; // allows chaining operations
        }

        // Clear pressed
        else if (value.equals("C")) {
            display.setText("");
            operator = "";}}}
