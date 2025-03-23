package motorph.phase.pkg1;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

class Employee {
    int employeeNumber;
    String lastName;
    String firstName;
    LocalDate birthday;
    String address;
    String phoneNumber;
    String sss;
    String philHealth;
    String tinNumber;
    String pagibig;
    String status;
    String position;
    String immediateSupervisor;
    double basicSalary;
    double riceSubsidy;
    double phoneAllowance;
    double clothingAllowance;
    double grossSemiMonthly;
    double hourlyRate;

    public Employee(int employeeNumber, String lastName, String firstName, LocalDate birthday,
                    String address, String phoneNumber, String sss, String philHealth,
                    String tinNumber, String pagibig, String status, String position,
                    String immediateSupervisor, double basicSalary, double riceSubsidy,
                    double phoneAllowance, double clothingAllowance) {
        this.employeeNumber = employeeNumber;
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthday = birthday;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.sss = sss;
        this.philHealth = philHealth;
        this.tinNumber = tinNumber;
        this.pagibig = pagibig;
        this.status = status;
        this.position = position;
        this.immediateSupervisor = immediateSupervisor;
        this.basicSalary = basicSalary;
        this.riceSubsidy = riceSubsidy;
        this.phoneAllowance = phoneAllowance;
        this.clothingAllowance = clothingAllowance;
        this.grossSemiMonthly = basicSalary / 2;
        this.hourlyRate = (basicSalary / 2) / 80;
    }
}

public class EmployeeSalaryManager {

    static final String EMPLOYEES_FILE = "Employees.csv";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nEmployee Management System");
            System.out.println("1. View Employee by ID");
            System.out.println("2. Add Employee");
            System.out.println("3. Edit Employee");
            System.out.println("4. Delete Employee");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> viewEmployeeById(scanner);
                case 2 -> addEmployee(scanner);
                case 3 -> editEmployee(scanner);
                case 4 -> deleteEmployee(scanner);
                case 5 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public static void viewEmployeeById(Scanner scanner) {
        System.out.print("Enter Employee # to view: ");
        String empId = scanner.nextLine();
        List<String> lines;
        try {
            lines = Files.readAllLines(Paths.get(EMPLOYEES_FILE));
        } catch (IOException e) {
            System.out.println("Error reading file.");
            return;
        }

        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts[0].equals(empId)) {
                System.out.println("Employee Details:");
                System.out.println("Employee Number: " + parts[0]);
                System.out.println("Name: " + parts[2] + " " + parts[1]);
                System.out.println("Birthday: " + parts[3]);
                System.out.println("Address: " + parts[4]);
                System.out.println("Phone Number: " + parts[5]);
                System.out.println("SSS: " + parts[6]);
                System.out.println("PhilHealth: " + parts[7]);
                System.out.println("TIN: " + parts[8]);
                System.out.println("Pagibig: " + parts[9]);
                System.out.println("Status: " + parts[10]);
                System.out.println("Position: " + parts[11]);
                System.out.println("Immediate Supervisor: " + parts[12]);
                System.out.println("Basic Salary: " + parts[13]);
                System.out.println("Rice Subsidy: " + parts[14]);
                System.out.println("Phone Allowance: " + parts[15]);
                System.out.println("Clothing Allowance: " + parts[16]);
                return;
            }
        }
        System.out.println("Employee not found.");
    }

    public static void deleteEmployee(Scanner scanner) {
        System.out.print("Enter Employee # to delete: ");
        String empId = scanner.nextLine();
        List<String> lines;
        try {
            lines = Files.readAllLines(Paths.get(EMPLOYEES_FILE));
        } catch (IOException e) {
            System.out.println("Error reading file.");
            return;
        }

        List<String> updatedLines = new ArrayList<>();
        boolean found = false;

        for (String line : lines) {
            if (!line.startsWith(empId + ",")) {
                updatedLines.add(line);
            } else {
                found = true;
            }
        }

        if (!found) {
            System.out.println("Employee not found.");
            return;
        }

        try {
            Files.write(Paths.get(EMPLOYEES_FILE), updatedLines);
            System.out.println("Employee deleted successfully.");
        } catch (IOException e) {
            System.out.println("Error writing file.");
        }
    }

    public static void addEmployee(Scanner scanner) {
        try {
            int newEmployeeId = 1;
            List<String> lines = Files.readAllLines(Paths.get(EMPLOYEES_FILE));
            if (!lines.isEmpty()) {
                String lastLine = lines.get(lines.size() - 1);
                String[] lastEmployeeData = lastLine.split(",");
                newEmployeeId = Integer.parseInt(lastEmployeeData[0]) + 1;
            }

            System.out.println("Adding Employee #" + newEmployeeId);
            System.out.print("Enter Last Name: ");
            String lastName = cleanInput(scanner.nextLine());
            System.out.print("Enter First Name: ");
            String firstName = cleanInput(scanner.nextLine());
            System.out.print("Enter Birthday (yyyy-MM-dd): ");
            String birthday = cleanInput(scanner.nextLine());
            System.out.print("Enter Address: ");
            String address = cleanInput(scanner.nextLine());
            System.out.print("Enter Phone Number: ");
            String phoneNumber = cleanInput(scanner.nextLine());
            System.out.print("Enter SSS Number: ");
            String sss = cleanInput(scanner.nextLine());
            System.out.print("Enter PhilHealth Number: ");
            String philHealth = cleanInput(scanner.nextLine());
            System.out.print("Enter TIN Number: ");
            String tinNumber = cleanInput(scanner.nextLine());
            System.out.print("Enter Pagibig Number: ");
            String pagibig = cleanInput(scanner.nextLine());
            System.out.print("Enter Status: ");
            String status = cleanInput(scanner.nextLine());
            System.out.print("Enter Position: ");
            String position = cleanInput(scanner.nextLine());
            System.out.print("Enter Immediate Supervisor: ");
            String immediateSupervisor = cleanInput(scanner.nextLine());
            double basicSalary = getValidDoubleInput(scanner, "Enter Basic Salary: ");
            double riceSubsidy = getValidDoubleInput(scanner, "Enter Rice Subsidy: ");
            double phoneAllowance = getValidDoubleInput(scanner, "Enter Phone Allowance: ");
            double clothingAllowance = getValidDoubleInput(scanner, "Enter Clothing Allowance: ");

            String newEmployeeData = String.join(",", String.valueOf(newEmployeeId), lastName, firstName, birthday, address, phoneNumber, sss,
                    philHealth, tinNumber, pagibig, status, position, immediateSupervisor,
                    String.valueOf(basicSalary), String.valueOf(riceSubsidy),
                    String.valueOf(phoneAllowance), String.valueOf(clothingAllowance));

            Files.write(Paths.get(EMPLOYEES_FILE), (newEmployeeData + "\n").getBytes(), StandardOpenOption.APPEND, StandardOpenOption.CREATE);
            System.out.println("Employee added successfully.");
        } catch (IOException e) {
            System.out.println("Error writing file.");
        }
    }

    public static void editEmployee(Scanner scanner) {
        System.out.print("Enter Employee # to edit: ");
        String empId = scanner.nextLine();
        List<String> lines;

        try {
            lines = Files.readAllLines(Paths.get(EMPLOYEES_FILE));
        } catch (IOException e) {
            System.out.println("Error reading file.");
            return;
        }

        List<String> updatedLines = new ArrayList<>();
        boolean found = false;
        int maxFields = 17; // Expected number of fields in Employee records

        for (String line : lines) {
            String[] parts = line.split(",", -1); // Keep empty fields
            if (parts[0].equals(empId)) {
                found = true;
                System.out.println("Editing Employee #" + empId);

                // Ensure parts array has exactly maxFields elements
                if (parts.length < maxFields) {
                    parts = Arrays.copyOf(parts, maxFields);
                }

                for (int i = 1; i < maxFields; i++) { // Limit to known fields
                    String fieldName = getFieldName(i);
                    System.out.print("Enter new value for " + fieldName + " (leave blank to keep current): ");
                    String newValue = scanner.nextLine().trim();

                    if (!newValue.isEmpty()) {
                        if (i >= 13) { // Double values
                            try {
                                parts[i] = String.valueOf(Double.parseDouble(newValue));
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid input. Please enter a valid number for " + fieldName + ".");
                                parts[i] = String.valueOf(getValidDoubleInput(scanner, fieldName));
                            }
                        } else {
                            parts[i] = cleanInput(newValue);
                        }
                    }
                }
                updatedLines.add(String.join(",", parts));
            } else {
                updatedLines.add(line);
            }
        }

        if (!found) {
            System.out.println("Employee not found.");
            return;
        }

        try {
            Files.write(Paths.get(EMPLOYEES_FILE), updatedLines);
            System.out.println("Employee details updated successfully.");
        } catch (IOException e) {
            System.out.println("Error writing file.");
        }
    }

    private static String cleanInput(String input) {
        return input.replace(",", "").trim();
    }

    private static double getValidDoubleInput(Scanner scanner, String fieldName) {
        while (true) {
            System.out.print("Enter a valid number for " + fieldName + ": ");
            String input = scanner.nextLine().trim();
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a numeric value.");
            }
        }
    }

    private static String getFieldName(int index) {
        String[] fields = {"Employee Number", "Last Name", "First Name", "Birthday", "Address", "Phone Number", "SSS",
                "PhilHealth", "TIN", "Pagibig", "Status", "Position", "Immediate Supervisor",
                "Basic Salary", "Rice Subsidy", "Phone Allowance", "Clothing Allowance"};

        return (index >= 0 && index < fields.length) ? fields[index] : "Unknown Field (" + index + ")";
    }

    public static double calculateSSSContribution(double basicSalary) {
        double[][] sssTable = {
                {0, 3249.99, 135.00}, {3250, 3749.99, 157.50}, {3750, 4249.99, 180.00},
                {4250, 4749.99, 202.50}, {4750, 5249.99, 225.00}, {5250, 5749.99, 247.50},
                {5750, 6249.99, 270.00}, {6250, 6749.99, 292.50}, {6750, 7249.99, 315.00},
                {7250, 7749.99, 337.50}, {7750, 8249.99, 360.00}, {8250, 8749.99, 382.50},
                {8750, 9249.99, 405.00}, {9250, 9749.99, 427.50}, {9750, 10249.99, 450.00},
                {10250, 10749.99, 472.50}, {10750, 11249.99, 495.00}, {11250, 11749.99, 517.50},
                {11750, 12249.99, 540.00}, {12250, 12749.99, 562.50}, {12750, 13249.99, 585.00},
                {13250, 13749.99, 607.50}, {13750, 14249.99, 630.00}, {14250, 14749.99, 652.50},
                {14750, 15249.99, 675.00}, {15250, 15749.99, 697.50}, {15750, 16249.99, 720.00},
                {16250, 16749.99, 742.50}, {16750, 17249.99, 765.00}, {17250, 17749.99, 787.50},
                {17750, 18249.99, 810.00}, {18250, 18749.99, 832.50}, {18750, 19249.99, 855.00},
                {19250, 19749.99, 877.50}, {19750, 20249.99, 900.00}, {20250, 20749.99, 922.50},
                {20750, 21249.99, 945.00}, {21250, 21749.99, 967.50}, {21750, 22249.99, 990.00},
                {22250, 22749.99, 1012.50}, {22750, 23249.99, 1035.00}, {23250, 23749.99, 1057.50},
                {23750, 24249.99, 1080.00}, {24250, 24749.99, 1102.50}, {24750, Double.MAX_VALUE, 1125.00}
        };

        for (double[] range : sssTable) {
            if (basicSalary >= range[0] && basicSalary <= range[1]) {
                return range[2] / 2; // Employee's share (semi-monthly)
            }
        }
        return 0.0; // Default case (should never happen)
    }

    public static double calculatePhilHealthContribution(double basicSalary) {
        double premiumRate = 0.03; // 3% of the basic salary
        double monthlyPremium = basicSalary * premiumRate;

        // PhilHealth contribution is capped at 1,800
        if (monthlyPremium > 1800) {
            monthlyPremium = 1800;
        } else if (monthlyPremium < 300) {
            monthlyPremium = 300;
        }

        return monthlyPremium / 2; // Employee's share (semi-monthly)
    }

    public static double calculatePagIbigContribution(double basicSalary) {
        double employeeRate = (basicSalary <= 1500) ? 0.01 : 0.02; // 1% if ≤1500, otherwise 2%
        double contribution = basicSalary * employeeRate;

        // Pag-IBIG employee contribution is capped at 100
        return Math.min(contribution, 100);
    }

    public static double calculateWithholdingTax(double basicSalary) {
        double taxableIncome = basicSalary - (calculateSSSContribution(basicSalary)
                + calculatePhilHealthContribution(basicSalary)
                + calculatePagIbigContribution(basicSalary));

        if (taxableIncome <= 20832) {
            return 0.0;
        } else if (taxableIncome <= 33333) {
            return (taxableIncome - 20833) * 0.20;
        } else if (taxableIncome <= 66667) {
            return 2500 + (taxableIncome - 33333) * 0.25;
        } else if (taxableIncome <= 166667) {
            return 10833 + (taxableIncome - 66667) * 0.30;
        } else if (taxableIncome <= 666667) {
            return 40833.33 + (taxableIncome - 166667) * 0.32;
        } else {
            return 200833.33 + (taxableIncome - 666667) * 0.35;
        }
    }
    
}