package test;

import java.util.Scanner;

import dao.UserDAO;
import model.User;
import service.CurrencyService;
import service.CalculatorService;
import service.BMIService;
import service.TimeService;
import service.UnitConverterService;

public class TestAuth {

    
// Helper Method: Safe Integer Input
    public static int getIntInput(Scanner sc) {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input! Please enter a number: ");
            }
        }
    }

   
// Helper Method: Safe Double Input
    
    public static double getDoubleInput(Scanner sc) {
        while (true) {
            try {
                return Double.parseDouble(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Invalid number! Please enter again: ");
            }
        }
    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        UserDAO userDAO = new UserDAO();

        while (true) {

            showMainMenu();
            int choice = getIntInput(sc);

            switch (choice) {

                case 1:
                    handleRegister(sc, userDAO);
                    break;

                case 2:
                    handleLogin(sc, userDAO);
                    break;

                case 3:
                    System.out.println("Exiting application...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    
// Display Main Menu

    public static void showMainMenu() {
        System.out.println("\n===== MAIN MENU =====");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Exit");
        System.out.print("Choose option: ");
    }


//Registration
    public static void handleRegister(Scanner sc, UserDAO userDAO) {

        System.out.print("Enter username: ");
        String username = sc.nextLine().trim();

        System.out.print("Enter password: ");
        String password = sc.nextLine();

        User user = new User(username, password);

        boolean success = userDAO.registerUser(user);

        if (success) {
            System.out.println("Registration Successful!");
        } else {
            System.out.println("Registration Failed!");
        }
    }

//Login
        public static void handleLogin(Scanner sc, UserDAO userDAO) {

        System.out.print("Enter username: ");
        String username = sc.nextLine().trim();

        System.out.print("Enter password: ");
        String password = sc.nextLine();

        User user = userDAO.loginUser(username, password);

        if (user != null) {
            System.out.println("Login Successful! Welcome " + user.getUsername());
            showDashboard(sc);
        } else {
            System.out.println("Invalid username or password.");
        }
    }

// Dashboard After Login
        public static void showDashboard(Scanner sc) {

        CurrencyService currencyService = new CurrencyService();
        CalculatorService calculatorService = new CalculatorService();
        BMIService bmiService = new BMIService();
        UnitConverterService unitService = new UnitConverterService();

        while (true) {

            System.out.println("\n===== DASHBOARD =====");
            System.out.println("1. Unit Conversion");
            System.out.println("2. Currency Convert");
            System.out.println("3. BMI Calculator");
            System.out.println("4. Arithmetic Calculator");
            System.out.println("5. Time");
            System.out.println("6. Logout");
            System.out.print("Choose option: ");

            int choice = getIntInput(sc);

            switch (choice) {

                case 1:
                	handleUnitConversion(sc, unitService);
                    break;

                case 2:
                	handleCurrencyConversion(sc, currencyService);
                    break;

                case 3:
                    handleBMI(sc, bmiService);
                    break;

                case 4:
                	handleCalculator(sc, calculatorService);
                    break;
                case 5:
                    handleTime(sc);
                    break;


                case 6:
                    System.out.println("Logged out...");
                    return;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }

// Currency
     public static void handleCurrencyConversion(Scanner sc, CurrencyService service) {

        String[] currencyCodes = {"USD","INR","EUR","GBP","JPY","AUD","CAD"};
        String[] currencyNames = {
                "USD - Dollar",
                "INR - Indian Rupee",
                "EUR - Euro",
                "GBP - British Pound",
                "JPY - Japanese Yen",
                "AUD - Australian Dollar",
                "CAD - Canadian Dollar"
        };

        System.out.println("\nAvailable Currencies:");
        for (int i = 0; i < currencyNames.length; i++) {
            System.out.println((i + 1) + ". " + currencyNames[i]);
        }

        System.out.print("Choose FROM currency: ");
        int fromChoice = getIntInput(sc);

        System.out.print("Choose TO currency: ");
        int toChoice = getIntInput(sc);

        if (fromChoice < 1 || fromChoice > 7 || toChoice < 1 || toChoice > 7) {
            System.out.println("Invalid selection!");
            return;
        }

        String from = currencyCodes[fromChoice - 1];
        String to = currencyCodes[toChoice - 1];

        System.out.print("Enter amount: ");
        double amount = getDoubleInput(sc);

        if (amount <= 0) {
            System.out.println("Amount must be greater than 0.");
            return;
        }

        double result = service.convert(from, to, amount);

        if (result == -1) {
            System.out.println("Conversion failed.");
        } else {
            System.out.printf("Converted Amount: %.2f%n", result);
        }
    }

// Calculator
     public static void handleCalculator(Scanner sc, CalculatorService service) {

        System.out.println("\n===== CALCULATOR =====");
        System.out.println("1. Add");
        System.out.println("2. Subtract");
        System.out.println("3. Multiply");
        System.out.println("4. Divide");
        System.out.print("Choose operation: ");

        int choice = getIntInput(sc);

        System.out.print("Enter first number: ");
        double a = getDoubleInput(sc);

        System.out.print("Enter second number: ");
        double b = getDoubleInput(sc);

        double result;

        switch (choice) {
            case 1: result = service.add(a, b); break;
            case 2: result = service.subtract(a, b); break;
            case 3: result = service.multiply(a, b); break;
            case 4:
                if (b == 0) {
                    System.out.println("Cannot divide by zero.");
                    return;
                }
                result = service.divide(a, b);
                break;
            default:
                System.out.println("Invalid operation.");
                return;
        }

        System.out.println("Result: " + result);
    }

// BMI
     public static void handleBMI(Scanner sc, BMIService service) {

        System.out.print("Enter weight (kg): ");
        double weight = getDoubleInput(sc);

        System.out.print("Enter height (cm): ");
        double heightCm = getDoubleInput(sc);

        if (heightCm <= 0) {
            System.out.println("Invalid height.");
            return;
        }

        double bmi = service.calculateBMI(weight, heightCm);
        System.out.printf("Your BMI is: %.2f%n", bmi);
        
        if (bmi < 18.5)
            System.out.println("Category: Underweight");
        else if (bmi < 25)
            System.out.println("Category: Normal");
        else if (bmi < 30)
            System.out.println("Category: Overweight");
        else
            System.out.println("Category: Obese");

    }

    public static void handleUnitConversion(Scanner sc, UnitConverterService service) {

        while (true) {

            System.out.println("\n===== UNIT CONVERSION =====");
            System.out.println("1. Weight");
            System.out.println("2. Length");
            System.out.println("3. Back to Dashboard");
            System.out.print("Choose option: ");

            int type = getIntInput(sc);

            if (type == 3) {
                System.out.println("Returning to Dashboard...");
                return;
            }

            System.out.print("Enter value: ");
            double value = getDoubleInput(sc);

            if (value <= 0) {
                System.out.println("Value must be greater than 0.");
                continue;
            }

            try {

                if (type == 1) {

                    String[] weightUnits = {"kg", "g", "mg", "pound", "ton", "quintal"};

                    System.out.println("\nAvailable Weight Units:");
                    for (int i = 0; i < weightUnits.length; i++) {
                        System.out.println((i + 1) + ". " + weightUnits[i]);
                    }

                    System.out.print("Choose FROM unit: ");
                    int fromChoice = getIntInput(sc);

                    System.out.print("Choose TO unit: ");
                    int toChoice = getIntInput(sc);

                    if (fromChoice < 1 || fromChoice > weightUnits.length ||
                        toChoice < 1 || toChoice > weightUnits.length) {
                        System.out.println("Invalid selection.");
                        continue;
                    }

                    String from = weightUnits[fromChoice - 1];
                    String to = weightUnits[toChoice - 1];

                    double result = service.convertWeight(value, from, to);
                    System.out.printf("Result: %.4f %s%n", result, to);

                } 
                else if (type == 2) {

                    String[] lengthUnits = {"meter", "cm", "km", "inch", "foot", "yard", "mile"};

                    System.out.println("\nAvailable Length Units:");
                    for (int i = 0; i < lengthUnits.length; i++) {
                        System.out.println((i + 1) + ". " + lengthUnits[i]);
                    }

                    System.out.print("Choose FROM unit: ");
                    int fromChoice = getIntInput(sc);

                    System.out.print("Choose TO unit: ");
                    int toChoice = getIntInput(sc);

                    if (fromChoice < 1 || fromChoice > lengthUnits.length ||
                        toChoice < 1 || toChoice > lengthUnits.length) {
                        System.out.println("Invalid selection.");
                        continue;
                    }

                    String from = lengthUnits[fromChoice - 1];
                    String to = lengthUnits[toChoice - 1];

                    double result = service.convertLength(value, from, to);
                    System.out.printf("Result: %.4f %s%n", result, to);

                } 
                else {
                    System.out.println("Invalid option.");
                }

            } catch (Exception e) {
                System.out.println("Conversion error.");
            }

        }

    }


// Time
  public static void handleTime(Scanner sc) {

     TimeService timeService = new TimeService();

     System.out.println("\n===== TIME CONVERTER =====");
     System.out.println("1. Seconds to Minutes");
     System.out.println("2. Minutes to Seconds");
     System.out.println("3. Minutes to Hours");
     System.out.println("4. Hours to Minutes");
     System.out.println("5. Hours to Seconds");
     System.out.println("6. Seconds to Hours");
     System.out.print("Choose option: ");

     int choice = getIntInput(sc);

     System.out.print("Enter value: ");
     double value = getDoubleInput(sc);

     if (value < 0) {
         System.out.println("Time cannot be negative.");
         return;
     }

     try {
         double result = timeService.convert(choice, value);
         System.out.printf("Converted Value: %.2f%n", result);
     } catch (Exception e) {
         System.out.println("Invalid option.");
     }
 }
}

