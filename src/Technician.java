public class Technician {

    private String name;
    private Specialty specialty;
    private int shiftStart;
    private int shiftEnd;
    private boolean available;

    public Technician(String name,
                      Specialty specialty,
                      int shiftStart,
                      int shiftEnd,
                      boolean available) {

        this.name = name;
        this.specialty = specialty;
        this.shiftStart = shiftStart;
        this.shiftEnd = shiftEnd;
        this.available = available;
    }

    public String getName() {
        return name;
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public boolean isAvailable() {
        return available;
    }

    public boolean canWorkOn(String engineType) {

        if (specialty == Specialty.ALL) {
            return true;
        }

        return specialty.toString().equalsIgnoreCase(engineType);
    }

    public boolean canWorkAt(String appointmentTime, int estimatedHours) {

        int startHour = convertTimeToHour(appointmentTime);
        int endHour = startHour + estimatedHours;

        return startHour >= shiftStart && endHour <= shiftEnd;
    }

    private int convertTimeToHour(String time) {

        String upperTime = time.toUpperCase();
        String[] parts = upperTime.split(" ");
        String hourPart = parts[0].split(":")[0];

        int hour = Integer.parseInt(hourPart);

        if (upperTime.contains("PM") && hour != 12) {
            hour += 12;
        }

        if (upperTime.contains("AM") && hour == 12) {
            hour = 0;
        }

        return hour;
    }

    public void printInfo() {

        System.out.println("Technician: " + name);
        System.out.println("Specialty: " + specialty);
        System.out.println("Shift: " + shiftStart + ":00 - " + shiftEnd + ":00");
        System.out.println("Available: " + available);
    }
}