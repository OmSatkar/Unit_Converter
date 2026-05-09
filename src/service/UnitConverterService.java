package service;

public class UnitConverterService {

        
//WEIGHT CONVERSION (Base: KG)
           

        public double convertWeight(double value, String fromUnit, String toUnit) {

            
            double valueInKg = toKg(value, fromUnit);

            
            return fromKg(valueInKg, toUnit);
        }

        private double toKg(double value, String unit) {

            switch (unit.toLowerCase()) {
                case "kg": return value;
                case "g": return value / 1000.0;
                case "mg": return value / 1_000_000.0;
                case "pound": return value / 2.20462;
                case "ton": return value * 1000.0;
                case "quintal": return value * 100.0;
                default: throw new IllegalArgumentException("Invalid weight unit");
            }
        }

        private double fromKg(double valueInKg, String unit) {

            switch (unit.toLowerCase()) {
                case "kg": return valueInKg;
                case "g": return valueInKg * 1000.0;
                case "mg": return valueInKg * 1_000_000.0;
                case "pound": return valueInKg * 2.20462;
                case "ton": return valueInKg / 1000.0;
                case "quintal": return valueInKg / 100.0;
                default: throw new IllegalArgumentException("Invalid weight unit");
            }
        }


      
//LENGTH CONVERSION (Base: Meter)
          

        public double convertLength(double value, String fromUnit, String toUnit) {

        
            double valueInMeters = toMeters(value, fromUnit);

          
            return fromMeters(valueInMeters, toUnit);
        }

        private double toMeters(double value, String unit) {

            switch (unit.toLowerCase()) {
                case "meter": return value;
                case "cm": return value / 100.0;
                case "km": return value * 1000.0;
                case "inch": return value * 0.0254;
                case "foot": return value * 0.3048;
                case "yard": return value * 0.9144;
                case "mile": return value * 1609.34;
                default: throw new IllegalArgumentException("Invalid length unit");
            }
        }

        private double fromMeters(double valueInMeters, String unit) {

            switch (unit.toLowerCase()) {
                case "meter": return valueInMeters;
                case "cm": return valueInMeters * 100.0;
                case "km": return valueInMeters / 1000.0;
                case "inch": return valueInMeters / 0.0254;
                case "foot": return valueInMeters / 0.3048;
                case "yard": return valueInMeters / 0.9144;
                case "mile": return valueInMeters / 1609.34;
                default: throw new IllegalArgumentException("Invalid length unit");
            }
        }
    }
	


















//    // ======================
//    // Weight Conversions
//    // ======================
//
//    public double kgToGram(double kg) {
//        return kg * 1000;
//    }
//
//    public double kgToMilligram(double kg) {
//        return kg * 1_000_000;
//    }
//
//    public double kgToTon(double kg) {
//        return kg * 0.001;
//    }
//
//    public double kgToQuintal(double kg) {
//        return kg * 0.01;
//    }
//
//    public double kgToPound(double kg) {
//        return kg * 2.20462;
//    }
//
//    // ======================
//    // Length Conversions
//    // ======================
//
//    public double kmToMeter(double km) {
//        return km * 1000;
//    }
//
//    public double kmToCentimeter(double km) {
//        return km * 100000;
//    }
//
//    public double kmToMillimeter(double km) {
//        return km * 1_000_000;
//    }
//
//    public double kmToMiles(double km) {
//        return km * 0.621371;
//    }
//
//    public double kmToYard(double km) {
//        return km * 1093.61;
//    }
//
//    public double kmToInch(double km) {
//        return km * 39370.1;
//    }
