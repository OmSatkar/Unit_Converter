package ui;

import javax.swing.*;
import java.awt.*;
import app.MainApp;
import service.CurrencyService;

public class CurrencyPanel extends JPanel {

    private MainApp mainApp;
    private CurrencyService currencyService;

    private JTextField amountField;
    private JComboBox<String> fromBox;
    private JComboBox<String> toBox;
    private JLabel resultLabel;

    public CurrencyPanel(MainApp mainApp) {

        this.mainApp = mainApp;
        this.currencyService = new CurrencyService();

        setLayout(new BorderLayout(15, 15));
        setBackground(new Color(255, 248, 240));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Title
        JLabel title = new JLabel("Currency Converter", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        add(title, BorderLayout.NORTH);

        // Center Panel
        JPanel centerPanel = new JPanel(new GridLayout(6, 1, 10, 10));

        amountField = new JTextField();

        String[] currencyCodes = {
                "USD",
                "INR",
                "EUR",
                "GBP",
                "JPY",
                "AUD",
                "CAD"
        };

        fromBox = new JComboBox<>(currencyCodes);
        toBox = new JComboBox<>(currencyCodes);

        JButton convertBtn = new JButton("Convert");

        resultLabel = new JLabel("Result: ");
        resultLabel.setFont(new Font("Arial", Font.BOLD, 16));

        convertBtn.addActionListener(e -> convert());

        centerPanel.add(new JLabel("Enter Amount:"));
        centerPanel.add(amountField);
        centerPanel.add(new JLabel("From Currency:"));
        centerPanel.add(fromBox);
        centerPanel.add(new JLabel("To Currency:"));
        centerPanel.add(toBox);

        add(centerPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new GridLayout(2,1,10,10));
        bottomPanel.add(convertBtn);
        bottomPanel.add(resultLabel);

        add(bottomPanel, BorderLayout.EAST);

        // Back Button
        JButton backBtn = new JButton("Back to Dashboard");
        backBtn.addActionListener(e -> mainApp.showPanel("dashboard"));

        add(backBtn, BorderLayout.SOUTH);
    }

    private void convert() {

        try {

            double amount = Double.parseDouble(amountField.getText());

            if (amount <= 0) {
                JOptionPane.showMessageDialog(this,
                        "Amount must be greater than 0.");
                return;
            }

            String from = (String) fromBox.getSelectedItem();
            String to = (String) toBox.getSelectedItem();

            double result = currencyService.convert(from, to, amount);

            if (result == -1) {
                JOptionPane.showMessageDialog(this,
                        "Conversion failed.");
                return;
            }

            resultLabel.setText("Result: " + String.format("%.2f", result));

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Enter a valid amount.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
