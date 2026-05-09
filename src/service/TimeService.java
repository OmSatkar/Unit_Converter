package service;

public class TimeService {

    public double convert(int choice, double value) {

        double result = 0;

        switch (choice) {

            case 1:
                result = value / 60;
                break;

            case 2:
                result = value * 60;
                break;

            case 3:
                result = value / 60;
                break;

            case 4:
                result = value * 60;
                break;

            case 5:
                result = value * 3600;
                break;

            case 6:
                result = value / 3600;
                break;

            default:
                throw new IllegalArgumentException("Invalid choice");
        }

        return result;
    }
}

