import java.util.ArrayList;

public class GarageShop {

    private ArrayList<Appointment> appointments;
    private ArrayList<Technician> technicians;

    private int totalBays;
    private int nextAppointmentNumber;

    // Appointment time slots
    private String[] appointmentTimes = {
            "8:00 AM",
            "9:00 AM",
            "10:00 AM",
            "11:00 AM",
            "12:00 PM",
            "1:00 PM",
            "2:00 PM",
            "3:00 PM",
            "4:00 PM"
    };

    public GarageShop() {

        appointments = new ArrayList<>();
        technicians = new ArrayList<>();

        totalBays = 4;
        nextAppointmentNumber = 1001;
    }

    // Add technician
    public void addTechnician(Technician technician) {

        technicians.add(technician);
    }

    // Return time by menu number
    public String getTimeSlot(int choice) {

        if (choice >= 1 && choice <= appointmentTimes.length) {

            return appointmentTimes[choice - 1];
        }

        return null;
    }

    // Show available time slots
    public void showAvailableTimes(String date) {

        System.out.println("\n===== AVAILABLE TIMES =====");

        for (int i = 0; i < appointmentTimes.length; i++) {

            String time = appointmentTimes[i];

            int openBays = 0;

            for (int bay = 1; bay <= totalBays; bay++) {

                if (!isBayBooked(bay, time, date)) {

                    openBays++;
                }
            }

            // Only show available slots
            if (openBays > 0) {

                System.out.println(
                        (i + 1)
                                + ". "
                                + time
                                + " - "
                                + openBays
                                + " bay(s) open"
                );
            }
        }
    }

    // Create appointment
    public Appointment createAppointment(String customerName,
                                         Vehicle vehicle,
                                         ServiceType serviceType,
                                         String appointmentTime,
                                         String date) {

        int bayNumber = findAvailableBay(
                appointmentTime,
                date
        );

        if (bayNumber == -1) {

            System.out.println(
                    "\nNo bays available."
            );

            return null;
        }

        Technician technician =
                findAvailableTechnician(
                        vehicle,
                        appointmentTime,
                        date
                );

        if (technician == null) {

            System.out.println(
                    "\nNo technician available."
            );

            return null;
        }

        String appointmentId =
                "APT-" + nextAppointmentNumber;

        nextAppointmentNumber++;

        Appointment appointment =
                new Appointment(
                        appointmentId,
                        customerName,
                        vehicle,
                        technician,
                        serviceType,
                        bayNumber,
                        appointmentTime,
                        date,
                        AppointmentStatus.WAITING
                );

        appointments.add(appointment);

        return appointment;
    }

    // Find open bay
    private int findAvailableBay(String appointmentTime,
                                 String date) {

        for (int bay = 1; bay <= totalBays; bay++) {

            if (!isBayBooked(
                    bay,
                    appointmentTime,
                    date
            )) {

                return bay;
            }
        }

        return -1;
    }

    // Check if bay booked
    private boolean isBayBooked(int bayNumber,
                                String appointmentTime,
                                String date) {

        for (Appointment appointment : appointments) {

            if (appointment.getBayNumber()
                    == bayNumber
                    &&
                    appointment.getAppointmentTime()
                            .equalsIgnoreCase(appointmentTime)
                    &&
                    appointment.getDate()
                            .equalsIgnoreCase(date)
                    &&
                    appointment.getStatus()
                            != AppointmentStatus.CANCELLED) {

                return true;
            }
        }

        return false;
    }

    // Find technician
    private Technician findAvailableTechnician(
            Vehicle vehicle,
            String appointmentTime,
            String date) {

        // Exact specialty first
        for (Technician technician : technicians) {

            if (technician.canWorkOn(
                    vehicle.getEngineType())
                    &&
                    technician.getSpecialty()
                            != Specialty.ALL
                    &&
                    !isTechnicianBooked(
                            technician,
                            appointmentTime,
                            date)) {

                return technician;
            }
        }

        // ALL specialty fallback
        for (Technician technician : technicians) {

            if (technician.getSpecialty()
                    == Specialty.ALL
                    &&
                    !isTechnicianBooked(
                            technician,
                            appointmentTime,
                            date)) {

                return technician;
            }
        }

        return null;
    }

    // Check if technician booked
    private boolean isTechnicianBooked(
            Technician technician,
            String appointmentTime,
            String date) {

        for (Appointment appointment : appointments) {

            if (appointment.getTechnician()
                    == technician
                    &&
                    appointment.getAppointmentTime()
                            .equalsIgnoreCase(appointmentTime)
                    &&
                    appointment.getDate()
                            .equalsIgnoreCase(date)
                    &&
                    appointment.getStatus()
                            != AppointmentStatus.CANCELLED) {

                return true;
            }
        }

        return false;
    }

    // Show appointments
    public void showAppointments() {

        System.out.println(
                "\n===== ALL APPOINTMENTS ====="
        );

        if (appointments.isEmpty()) {

            System.out.println(
                    "No appointments found."
            );

            return;
        }

        for (Appointment appointment : appointments) {

            appointment.printAppointmentDetails();
        }
    }

    // Search customer
    public void searchCustomer(String customerName) {

        boolean found = false;

        System.out.println(
                "\n===== SEARCH RESULTS ====="
        );

        for (Appointment appointment : appointments) {

            if (appointment.getCustomerName()
                    .toLowerCase()
                    .contains(
                            customerName.toLowerCase())) {

                appointment.printAppointmentDetails();

                found = true;
            }
        }

        if (!found) {

            System.out.println(
                    "No customer found."
            );
        }
    }

    // Update status
    public void updateAppointmentStatus(
            String appointmentId,
            AppointmentStatus newStatus) {

        boolean found = false;

        for (Appointment appointment : appointments) {

            if (appointment.getAppointmentId()
                    .equalsIgnoreCase(
                            appointmentId)) {

                appointment.setStatus(newStatus);

                System.out.println(
                        "\nStatus updated successfully."
                );

                found = true;
            }
        }

        if (!found) {

            System.out.println(
                    "\nAppointment ID not found."
            );
        }
    }

    // Revenue stats
    public void showRevenueStats() {

        double totalRevenue = 0;

        int completedJobs = 0;

        for (Appointment appointment : appointments) {

            if (appointment.getStatus()
                    == AppointmentStatus.COMPLETED) {

                totalRevenue +=
                        appointment.getServiceType()
                                .getEstimatedCost();

                completedJobs++;
            }
        }

        System.out.println(
                "\n===== REVENUE STATS ====="
        );

        System.out.println(
                "Completed Jobs: "
                        + completedJobs
        );

        System.out.println(
                "Estimated Revenue: $"
                        + totalRevenue
        );
    }
}