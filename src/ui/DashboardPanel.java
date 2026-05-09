package ui;

import javax.swing.*;
import java.awt.*;
import app.MainApp;

public class DashboardPanel extends JPanel {

    private MainApp mainApp;

    public DashboardPanel(MainApp mainApp) {

        this.mainApp = mainApp;

        setLayout(new BorderLayout());
        setBackground(new Color(200, 220, 240));

        JLabel title = new JLabel("Dashboard", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 26));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 2, 20, 20));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(40, 60, 40, 60));

        JButton calculatorBtn = new JButton("Calculator");
        calculatorBtn.addActionListener(e -> {
            mainApp.showPanel("calculator");
        });

        JButton bmiBtn = new JButton("BMI Calculator");
        bmiBtn.addActionListener(e -> {
            mainApp.showPanel("bmi");
        });


        JButton unitBtn = new JButton("Unit Converter");
        unitBtn.addActionListener(e -> {
            mainApp.showPanel("unit");
        });

        JButton timeBtn = new JButton("Time Converter");
        timeBtn.addActionListener(e -> {
            mainApp.showPanel("time");
        });

        JButton currencyBtn = new JButton("Currency Converter");
        currencyBtn.addActionListener(e -> {
            mainApp.showPanel("currency");
        });

        JButton logoutBtn = new JButton("Logout");
        logoutBtn.addActionListener(e -> {
            mainApp.showPanel("login");
        });

        buttonPanel.add(calculatorBtn);
        buttonPanel.add(bmiBtn);
        buttonPanel.add(unitBtn);
        buttonPanel.add(timeBtn);
        buttonPanel.add(currencyBtn);
        buttonPanel.add(logoutBtn);

        add(title, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
    }
}
