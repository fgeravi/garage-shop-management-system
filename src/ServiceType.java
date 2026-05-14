public enum ServiceType {

    // serviceName(hourlyRate, estimatedHours)

    MAINTENANCE(100, 2),
    ALIGNMENT(120, 1),
    HEADERS(130, 8),
    SUPERCHARGER(160, 14),
    TUNE(150, 3);

    // Variables each service stores
    private double hourlyRate;
    private int estimatedHours;

    // Constructor for enum
    ServiceType(double hourlyRate, int estimatedHours) {

        this.hourlyRate = hourlyRate;
        this.estimatedHours = estimatedHours;
    }

    // Returns hourly rate
    public double getHourlyRate() {
        return hourlyRate;
    }

    // Returns estimated labor hours
    public int getEstimatedHours() {
        return estimatedHours;
    }

    // Calculates total estimated job cost
    public double getEstimatedCost() {

        return hourlyRate * estimatedHours;
    }
}