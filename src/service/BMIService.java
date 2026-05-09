package service;
public class BMIService {

    
    public double calculateBMI(double weight, double heightCm) {

        
        double heightMeters = heightCm / 100.0;

        return weight / (heightMeters * heightMeters);
    }
}


