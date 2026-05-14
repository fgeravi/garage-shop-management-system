import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        GarageShop shop = new GarageShop();

        // Add technicians
        shop.addTechnician(
                new Technician(
                        "Jim",
                        Specialty.FORD,
                        8,
                        17,
                        true
                )
        );

        shop.addTechnician(
                new Technician(
                        "Carlos",
                        Specialty.CHEVY,
                        8,
                        17,
                        true
                )
        );

        shop.addTechnician(
                new Technician(
                        "Dave",
                        Specialty.DODGE,
                        9,
                        18,
                        true
                )
        );

        shop.addTechnician(
                new Technician(
                        "Mike",
                        Specialty.ALL,
                        8,
                        18,
                        true
                )
        );

        boolean running = true;

        while (running) {

            System.out.println(
                    "\n===== GARAGE SHOP SYSTEM ====="
            );

            System.out.println(
                    "1. Create Appointment"
            );

            System.out.println(
                    "2. View Appointments"
            );

            System.out.println(
                    "3. Search Customer"
            );

            System.out.println(
                    "4. Update Appointment Status"
            );

            System.out.println(
                    "5. Show Available Times"
            );

            System.out.println(
                    "6. Show Revenue Stats"
            );

            System.out.println(
                    "7. Exit"
            );

            System.out.print(
                    "\nChoose an option: "
            );

            int choice = input.nextInt();
            input.nextLine();

            // CREATE APPOINTMENT
            if (choice == 1) {

                System.out.print(
                        "\nEnter customer name: "
                );

                String customerName =
                        input.nextLine();

                System.out.print(
                        "Enter vehicle year: "
                );

                int year = input.nextInt();
                input.nextLine();

                System.out.print(
                        "Enter vehicle make: "
                );

                String make =
                        input.nextLine();

                System.out.print(
                        "Enter vehicle model: "
                );

                String model =
                        input.nextLine();

                System.out.print(
                        "Enter engine type (FORD/CHEVY/DODGE): "
                );

                String engineType =
                        input.nextLine()
                                .toUpperCase();

                // Create vehicle
                Vehicle vehicle =
                        new Vehicle(
                                year,
                                make,
                                model,
                                engineType
                        );

                // Service selection
                System.out.println(
                        "\nChoose Service:"
                );

                System.out.println(
                        "1. Maintenance"
                );

                System.out.println(
                        "2. Alignment"
                );

                System.out.println(
                        "3. Headers"
                );

                System.out.println(
                        "4. Supercharger"
                );

                System.out.println(
                        "5. Tune"
                );

                System.out.print(
                        "\nEnter service number: "
                );

                int serviceChoice =
                        input.nextInt();

                input.nextLine();

                ServiceType service =
                        ServiceType.MAINTENANCE;

                if (serviceChoice == 1) {

                    service =
                            ServiceType.MAINTENANCE;
                }
                else if (serviceChoice == 2) {

                    service =
                            ServiceType.ALIGNMENT;
                }
                else if (serviceChoice == 3) {

                    service =
                            ServiceType.HEADERS;
                }
                else if (serviceChoice == 4) {

                    service =
                            ServiceType.SUPERCHARGER;
                }
                else if (serviceChoice == 5) {

                    service =
                            ServiceType.TUNE;
                }

                // Appointment date
                System.out.print(
                        "\nEnter appointment date (MM/DD/YYYY): "
                );

                String date =
                        input.nextLine();

                Appointment appointment = null;

                // Keep asking until valid appointment
                while (appointment == null) {

                    String appointmentTime = null;

                    // Keep asking until valid time slot
                    while (appointmentTime == null) {

                        // Show open times
                        shop.showAvailableTimes(date);

                        System.out.print(
                                "\nChoose time slot number: "
                        );

                        int timeChoice =
                                input.nextInt();

                        input.nextLine();

                        // Convert menu number to time
                        appointmentTime =
                                shop.getTimeSlot(timeChoice);

                        // Invalid slot
                        if (appointmentTime == null) {

                            System.out.println(
                                    "\nInvalid time slot. Try again."
                            );
                        }
                    }

                    // Try creating appointment
                    appointment =
                            shop.createAppointment(
                                    customerName,
                                    vehicle,
                                    service,
                                    appointmentTime,
                                    date
                            );

                    // Failed appointment
                    if (appointment == null) {

                        System.out.println(
                                "\nThat time slot is unavailable."
                        );

                        System.out.println(
                                "Please choose another time."
                        );
                    }
                }

                // Success
                System.out.println(
                        "\n===== APPOINTMENT CREATED ====="
                );

                appointment.printAppointmentDetails();
            }

            // VIEW APPOINTMENTS
            else if (choice == 2) {

                shop.showAppointments();
            }

            // SEARCH CUSTOMER
            else if (choice == 3) {

                System.out.print(
                        "\nEnter customer name: "
                );

                String searchName =
                        input.nextLine();

                shop.searchCustomer(searchName);
            }

            // UPDATE STATUS
            else if (choice == 4) {

                System.out.print(
                        "\nEnter appointment ID: "
                );

                String appointmentId =
                        input.nextLine();

                System.out.println(
                        "\nChoose New Status:"
                );

                System.out.println(
                        "1. WAITING"
                );

                System.out.println(
                        "2. IN_PROGRESS"
                );

                System.out.println(
                        "3. COMPLETED"
                );

                System.out.println(
                        "4. CANCELLED"
                );

                System.out.print(
                        "\nEnter choice: "
                );

                int statusChoice =
                        input.nextInt();

                input.nextLine();

                AppointmentStatus newStatus =
                        AppointmentStatus.WAITING;

                if (statusChoice == 1) {

                    newStatus =
                            AppointmentStatus.WAITING;
                }
                else if (statusChoice == 2) {

                    newStatus =
                            AppointmentStatus.IN_PROGRESS;
                }
                else if (statusChoice == 3) {

                    newStatus =
                            AppointmentStatus.COMPLETED;
                }
                else if (statusChoice == 4) {

                    newStatus =
                            AppointmentStatus.CANCELLED;
                }

                shop.updateAppointmentStatus(
                        appointmentId,
                        newStatus
                );
            }

            // SHOW AVAILABLE TIMES
            else if (choice == 5) {

                System.out.print(
                        "\nEnter date (MM/DD/YYYY): "
                );

                String date =
                        input.nextLine();

                shop.showAvailableTimes(date);
            }

            // SHOW REVENUE
            else if (choice == 6) {

                shop.showRevenueStats();
            }

            // EXIT
            else if (choice == 7) {

                running = false;

                System.out.println(
                        "\nClosing Garage Shop System..."
                );
            }

            // INVALID OPTION
            else {

                System.out.println(
                        "\nInvalid option."
                );
            }
        }

        input.close();
    }
}