public class Appointment {

    private String appointmentId;
    private String customerName;
    private Vehicle vehicle;
    private Technician technician;
    private ServiceType serviceType;
    private int bayNumber;
    private String appointmentTime;
    private String date;
    private AppointmentStatus status;

    public Appointment(String appointmentId,
                       String customerName,
                       Vehicle vehicle,
                       Technician technician,
                       ServiceType serviceType,
                       int bayNumber,
                       String appointmentTime,
                       String date,
                       AppointmentStatus status) {

        this.appointmentId = appointmentId;
        this.customerName = customerName;
        this.vehicle = vehicle;
        this.technician = technician;
        this.serviceType = serviceType;
        this.bayNumber = bayNumber;
        this.appointmentTime = appointmentTime;
        this.date = date;
        this.status = status;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public Technician getTechnician() {
        return technician;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public int getBayNumber() {
        return bayNumber;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public String getDate() {
        return date;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(AppointmentStatus status) {

        this.status = status;
    }

    public void printAppointmentDetails() {

        System.out.println(
                "\n===== APPOINTMENT DETAILS ====="
        );

        System.out.println(
                "Appointment ID: "
                        + appointmentId);

        System.out.println(
                "Customer: "
                        + customerName);

        System.out.println(
                "Date: "
                        + date);

        System.out.println(
                "Vehicle: "
                        + vehicle.getYear()
                        + " "
                        + vehicle.getMake()
                        + " "
                        + vehicle.getModel());

        System.out.println(
                "Engine Type: "
                        + vehicle.getEngineType());

        System.out.println(
                "Service: "
                        + serviceType);

        System.out.println(
                "Hourly Rate: $"
                        + serviceType.getHourlyRate());

        System.out.println(
                "Estimated Hours: "
                        + serviceType.getEstimatedHours());

        System.out.println(
                "Estimated Cost: $"
                        + serviceType.getEstimatedCost());

        System.out.println(
                "Technician: "
                        + technician.getName());

        System.out.println(
                "Technician Specialty: "
                        + technician.getSpecialty());

        System.out.println(
                "Bay Number: "
                        + bayNumber);

        System.out.println(
                "Appointment Time: "
                        + appointmentTime);

        System.out.println(
                "Status: "
                        + status);
    }
}