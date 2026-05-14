public class Vehicle {

    // =========================
    // Instance Variables
    // =========================

    // These belong to EACH vehicle object
    // Every car has its own values

    private int year;
    private String make;
    private String model;
    private String engineType;

    // =========================
    // Constructor
    // =========================

    /*
        Constructor rules:
        - SAME NAME as class
        - NO return type
        - Runs automatically when object is created
    */

    public Vehicle(int year, String make, String model, String engineType) {

        /*
            "this" means:
            the current object's variable

            Left side:
            object's variable

            Right side:
            parameter coming into constructor
        */

        this.year = year;
        this.make = make;
        this.model = model;
        this.engineType = engineType;
    }

    // =========================
    // Getters
    // =========================

    /*
        Getters allow OTHER classes
        to safely access private variables
    */

    public int getYear() {
        return year;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getEngineType() {
        return engineType;
    }
}