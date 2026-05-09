package ui;

import javax.swing.*;
import java.awt.*;
import app.MainApp;
import service.UnitConverterService;

public class UnitConverterPanel extends JPanel {

    private MainApp mainApp;
    private UnitConverterService service;

    private JTextField inputField;
    private JLabel resultLabel;

    private JComboBox<String> categoryBox;
    private JComboBox<String> fromBox;
    private JComboBox<String> toBox;

    public UnitConverterPanel(MainApp mainApp) {

        this.mainApp = mainApp;
        this.service = new UnitConverterService();

        setLayout(new BorderLayout(15, 15));
        setBackground(new Color(240, 250, 255));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // ========================
        // Title
        // ========================
        JLabel title = new JLabel("Unit Converter", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        add(title, BorderLayout.NORTH);

        // ========================
        // Center Panel
        // ========================
        JPanel centerPanel = new JPanel(new GridLayout(7, 1, 10, 10));

        inputField = new JTextField();

        categoryBox = new JComboBox<>(new String[]{"Length", "Weight"});

        fromBox = new JComboBox<>();
        toBox = new JComboBox<>();

        updateUnitOptions(); // load default units

        categoryBox.addActionListener(e -> updateUnitOptions());

        JButton convertBtn = new JButton("Convert");

        resultLabel = new JLabel("Result: ");
        resultLabel.setFont(new Font("Arial", Font.BOLD, 16));

        convertBtn.addActionListener(e -> convert());

        centerPanel.add(new JLabel("Select Category:"));
        centerPanel.add(categoryBox);
        centerPanel.add(new JLabel("Enter Value:"));
        centerPanel.add(inputField);
        centerPanel.add(fromBox);
        centerPanel.add(toBox);
        centerPanel.add(convertBtn);

        add(centerPanel, BorderLayout.CENTER);
        add(resultLabel, BorderLayout.EAST);

        // ========================
        // Back Button
        // ========================
        JButton backBtn = new JButton("Back to Dashboard");
        backBtn.addActionListener(e -> mainApp.showPanel("dashboard"));

        add(backBtn, BorderLayout.SOUTH);
    }

    // ========================
    // Update Units Based on Category
    // ========================
    private void updateUnitOptions() {

        fromBox.removeAllItems();
        toBox.removeAllItems();

        String category = (String) categoryBox.getSelectedItem();

        if ("Length".equals(category)) {

            String[] lengthUnits = {
                    "meter", "cm", "km",
                    "inch", "foot", "yard", "mile"
            };

            for (String unit : lengthUnits) {
                fromBox.addItem(unit);
                toBox.addItem(unit);
            }

        } else {

            String[] weightUnits = {
                    "kg", "g", "mg",
                    "pound", "ton", "quintal"
            };

            for (String unit : weightUnits) {
                fromBox.addItem(unit);
                toBox.addItem(unit);
            }
        }
    }

    // ========================
    // Convert Logic
    // ========================
    private void convert() {

        try {

            double value = Double.parseDouble(inputField.getText());

            if (value <= 0) {
                JOptionPane.showMessageDialog(this,
                        "Value must be greater than 0.");
                return;
            }

            String category = (String) categoryBox.getSelectedItem();
            String from = (String) fromBox.getSelectedItem();
            String to = (String) toBox.getSelectedItem();

            double result = 0;

            if ("Length".equals(category)) {
                result = service.convertLength(value, from, to);
            } else {
                result = service.convertWeight(value, from, to);
            }

            resultLabel.setText("Result: " + String.format("%.4f", result));

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Please enter a valid number.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
