package app;

import javax.swing.*;
import java.awt.*;

import ui.LoginPanel;
import ui.TimePanel;
import ui.DashboardPanel;
import ui.CalculatorPanel;
import ui.CurrencyPanel;
import ui.UnitConverterPanel;
import ui.BMIPanel;

public class MainApp extends JFrame {

    CardLayout cardLayout;
    JPanel mainPanel;

    public MainApp() {

        setTitle("Utility Application");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add(new LoginPanel(this), "login");
        mainPanel.add(new DashboardPanel(this), "dashboard");
        mainPanel.add(new CalculatorPanel(this), "calculator");
        mainPanel.add(new UnitConverterPanel(this), "unit");
        mainPanel.add(new TimePanel(this), "time");
        mainPanel.add(new CurrencyPanel(this), "currency");
        mainPanel.add(new BMIPanel(this), "bmi");


        add(mainPanel);

        cardLayout.show(mainPanel, "login");

        setVisible(true);
    }

    public void showPanel(String name) {
        cardLayout.show(mainPanel, name);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainApp());
    }
}
