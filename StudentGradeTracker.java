//home/claude/SimpleJavaTasks/StudentGradeTracker.java << 'JAVAEOF'
// ============================================================
//  TASK 1 — Student Grade Tracker
//  CodeAlpha Java Internship
//  HOW TO RUN:
//    Step 1: javac StudentGradeTracker.java
//    Step 2: java StudentGradeTracker
// ============================================================

import java.util.Scanner;
import java.util.ArrayList;

public class StudentGradeTracker {

    // Store student names and their grades
    static ArrayList<String>          names      = new ArrayList<>();
    static ArrayList<ArrayList<Double>> gradesList = new ArrayList<>();

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("============================================");
        System.out.println("     STUDENT GRADE TRACKER - CodeAlpha      ");
        System.out.println("============================================");

        boolean running = true;

        while (running) {
            System.out.println("\n------------ MAIN MENU ------------");
            System.out.println("  1. Add a Student");
            System.out.println("  2. View All Students");
            System.out.println("  3. View Class Summary");
            System.out.println("  4. Search Student");
            System.out.println("  5. Exit");
            System.out.println("-----------------------------------");
            System.out.print("Enter your choice (1-5): ");

            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1": addStudent();      break;
                case "2": viewAllStudents(); break;
                case "3": viewSummary();     break;
                case "4": searchStudent();   break;
                case "5":
                    System.out.println("\nThank you for using Student Grade Tracker!");
                    System.out.println("Goodbye! - CodeAlpha Internship");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice! Please enter 1 to 5.");
            }
        }
    }

    // ── ADD STUDENT ──────────────────────────────────────────
    static void addStudent() {
        System.out.println("\n--- Add New Student ---");

        System.out.print("Enter student name: ");
        String name = sc.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("Name cannot be empty!");
            return;
        }

        System.out.print("How many subjects/grades to enter? ");
        int count;
        try {
            count = Integer.parseInt(sc.nextLine().trim());
            if (count <= 0) {
                System.out.println("Number of grades must be at least 1.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
            return;
        }

        ArrayList<Double> grades = new ArrayList<>();

        for (int i = 1; i <= count; i++) {
            System.out.print("Enter grade " + i + " (0 to 100): ");
            try {
                double grade = Double.parseDouble(sc.nextLine().trim());
                if (grade < 0 || grade > 100) {
                    System.out.println("Grade must be between 0 and 100. Skipping this grade.");
                } else {
                    grades.add(grade);
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid grade. Skipping.");
            }
        }

        if (grades.isEmpty()) {
            System.out.println("No valid grades entered. Student not added.");
            return;
        }

        // Save student data
        names.add(name);
        gradesList.add(grades);

        // Show result immediately
        double avg = calculateAverage(grades);
        System.out.println("\n✔ Student added successfully!");
        System.out.println("  Name    : " + name);
        System.out.println("  Average : " + String.format("%.2f", avg));
        System.out.println("  Grade   : " + getLetterGrade(avg));
        System.out.println("  Remark  : " + getRemark(avg));
    }

    // ── VIEW ALL STUDENTS ─────────────────────────────────────
    static void viewAllStudents() {
        System.out.println("\n--- All Students ---");

        if (names.isEmpty()) {
            System.out.println("No students added yet. Please add a student first.");
            return;
        }

        // Print table header
        System.out.println("------------------------------------------------------------");
        System.out.printf("  %-4s %-18s %-10s %-8s %-8s %-6s %-14s%n",
            "No.", "Name", "Average", "Highest", "Lowest", "Grade", "Remark");
        System.out.println("------------------------------------------------------------");

        for (int i = 0; i < names.size(); i++) {
            ArrayList<Double> grades = gradesList.get(i);
            double avg  = calculateAverage(grades);
            double high = calculateHighest(grades);
            double low  = calculateLowest(grades);

            System.out.printf("  %-4d %-18s %-10s %-8s %-8s %-6s %-14s%n",
                (i + 1),
                names.get(i),
                String.format("%.2f", avg),
                String.format("%.1f", high),
                String.format("%.1f", low),
                getLetterGrade(avg),
                getRemark(avg)
            );
        }
        System.out.println("------------------------------------------------------------");
        System.out.println("Total Students: " + names.size());
    }

    // ── VIEW SUMMARY ──────────────────────────────────────────
    static void viewSummary() {
        System.out.println("\n--- Class Summary Report ---");

        if (names.isEmpty()) {
            System.out.println("No students added yet.");
            return;
        }

        double totalAvg   = 0;
        double highestAvg = -1;
        double lowestAvg  = 101;
        String topStudent = "";
        String lowStudent = "";

        for (int i = 0; i < names.size(); i++) {
            double avg = calculateAverage(gradesList.get(i));
            totalAvg += avg;

            if (avg > highestAvg) {
                highestAvg = avg;
                topStudent = names.get(i);
            }
            if (avg < lowestAvg) {
                lowestAvg = avg;
                lowStudent = names.get(i);
            }
        }

        double classAvg = totalAvg / names.size();

        // Count grade distribution
        int countA=0, countB=0, countC=0, countD=0, countF=0;
        for (int i=0; i<names.size(); i++) {
            String grade = getLetterGrade(calculateAverage(gradesList.get(i)));
            switch (grade) {
                case "A": countA++; break;
                case "B": countB++; break;
                case "C": countC++; break;
                case "D": countD++; break;
                default:  countF++; break;
            }
        }

        System.out.println("============================================");
        System.out.println("   CLASS SUMMARY — CodeAlpha Grade Tracker  ");
        System.out.println("============================================");
        System.out.println("  Total Students  : " + names.size());
        System.out.printf( "  Class Average   : %.2f%n", classAvg);
        System.out.printf( "  Top Student     : %s (%.2f)%n", topStudent, highestAvg);
        System.out.printf( "  Needs Help      : %s (%.2f)%n", lowStudent, lowestAvg);
        System.out.println("--------------------------------------------");
        System.out.println("  Grade Distribution:");
        System.out.println("    A (90-100) : " + countA + " student(s)");
        System.out.println("    B (80-89)  : " + countB + " student(s)");
        System.out.println("    C (70-79)  : " + countC + " student(s)");
        System.out.println("    D (60-69)  : " + countD + " student(s)");
        System.out.println("    F (0-59)   : " + countF + " student(s)");
        System.out.println("============================================");
    }

    // ── SEARCH STUDENT ────────────────────────────────────────
    static void searchStudent() {
        System.out.println("\n--- Search Student ---");
        System.out.print("Enter student name to search: ");
        String query = sc.nextLine().trim().toLowerCase();

        boolean found = false;
        for (int i = 0; i < names.size(); i++) {
            if (names.get(i).toLowerCase().contains(query)) {
                ArrayList<Double> grades = gradesList.get(i);
                double avg  = calculateAverage(grades);
                double high = calculateHighest(grades);
                double low  = calculateLowest(grades);

                System.out.println("\n  Student Found!");
                System.out.println("  --------------------------------");
                System.out.println("  Name    : " + names.get(i));
                System.out.print(  "  Grades  : ");
                for (int j=0; j<grades.size(); j++) {
                    System.out.print(grades.get(j));
                    if (j < grades.size()-1) System.out.print(", ");
                }
                System.out.println();
                System.out.printf( "  Average : %.2f%n", avg);
                System.out.printf( "  Highest : %.1f%n", high);
                System.out.printf( "  Lowest  : %.1f%n", low);
                System.out.println("  Grade   : " + getLetterGrade(avg));
                System.out.println("  Remark  : " + getRemark(avg));
                System.out.println("  --------------------------------");
                found = true;
            }
        }
        if (!found) {
            System.out.println("No student found with name: " + query);
        }
    }

    // ── CALCULATION METHODS ───────────────────────────────────
    static double calculateAverage(ArrayList<Double> grades) {
        double sum = 0;
        for (double g : grades) sum += g;
        return sum / grades.size();
    }

    static double calculateHighest(ArrayList<Double> grades) {
        double max = grades.get(0);
        for (double g : grades) if (g > max) max = g;
        return max;
    }

    static double calculateLowest(ArrayList<Double> grades) {
        double min = grades.get(0);
        for (double g : grades) if (g < min) min = g;
        return min;
    }

    static String getLetterGrade(double avg) {
        if (avg >= 90) return "A";
        if (avg >= 80) return "B";
        if (avg >= 70) return "C";
        if (avg >= 60) return "D";
        return "F";
    }

    static String getRemark(double avg) {
        if (avg >= 90) return "Outstanding";
        if (avg >= 80) return "Very Good";
        if (avg >= 70) return "Good";
        if (avg >= 60) return "Needs Improvement";
        return "Fail";
    }
}