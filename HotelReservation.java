// ============================================================
//  TASK 4 — Hotel Reservation System
//  CodeAlpha Java Internship
//  HOW TO RUN:
//    Step 1: javac HotelReservation.java
//    Step 2: java HotelReservation
// ============================================================

import java.util.Scanner;
import java.util.ArrayList;

public class HotelReservation {

    // ── Room types available ───────────────────────────────────
    static final String[] ROOM_TYPES   = {"Standard Room", "Deluxe Room", "Luxury Suite"};
    static final double[] ROOM_PRICES  = {2000, 4000, 8000};
    static final int[]    ROOM_COUNT   = {10, 10, 10};  // total rooms per type
    static final String[] ROOM_AMENITIES = {
        "WiFi, AC, TV, Single Bed",
        "WiFi, AC, Smart TV, King Bed, Mini Bar",
        "WiFi, Jacuzzi, Sea View, Living Area, Butler Service"
    };

    // ── Booking storage ───────────────────────────────────────
    static ArrayList<String>  bookingIds   = new ArrayList<>();
    static ArrayList<String>  guestNames   = new ArrayList<>();
    static ArrayList<String>  roomTypes    = new ArrayList<>();
    static ArrayList<Integer> roomNumbers  = new ArrayList<>();
    static ArrayList<String>  checkIns     = new ArrayList<>();
    static ArrayList<String>  checkOuts    = new ArrayList<>();
    static ArrayList<Integer> nights       = new ArrayList<>();
    static ArrayList<Double>  amounts      = new ArrayList<>();
    static ArrayList<String>  statuses     = new ArrayList<>();

    static Scanner sc = new Scanner(System.in);
    static int bookingCounter = 0;

    public static void main(String[] args) {

        System.out.println("==============================================");
        System.out.println("   GRAND CODEALPHA HOTEL - Reservation System  ");
        System.out.println("==============================================");
        System.out.println("  Welcome! We offer luxury rooms at great prices.");
        System.out.println("==============================================");

        boolean running = true;

        while (running) {
            System.out.println("\n------------ MAIN MENU ------------");
            System.out.println("  1. View Available Rooms");
            System.out.println("  2. Book a Room");
            System.out.println("  3. View My Bookings");
            System.out.println("  4. Cancel a Booking");
            System.out.println("  5. View Booking Summary");
            System.out.println("  6. Exit");
            System.out.println("-----------------------------------");
            System.out.print("Enter your choice (1-6): ");

            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1": viewRooms();     break;
                case "2": bookRoom();      break;
                case "3": viewBookings();  break;
                case "4": cancelBooking(); break;
                case "5": viewSummary();   break;
                case "6":
                    System.out.println("\nThank you for choosing Grand CodeAlpha Hotel!");
                    System.out.println("Have a great day! - CodeAlpha Internship");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice! Please enter 1 to 6.");
            }
        }
    }

    // ── 1. VIEW ROOMS ─────────────────────────────────────────
    static void viewRooms() {
        System.out.println("\n--- Available Rooms ---");
        System.out.println("======================================================");
        System.out.printf("  %-4s %-16s %-18s %-30s%n",
            "No.", "Room Type", "Price/Night", "Amenities");
        System.out.println("------------------------------------------------------");

        for (int i = 0; i < ROOM_TYPES.length; i++) {
            System.out.printf("  %-4d %-16s Rs.%-15.0f %-30s%n",
                (i + 1),
                ROOM_TYPES[i],
                ROOM_PRICES[i],
                ROOM_AMENITIES[i]
            );
            System.out.println("      Rooms Available: " + getAvailableCount(i));
            System.out.println("------------------------------------------------------");
        }
    }

    // Count how many rooms of a type are still available
    static int getAvailableCount(int typeIndex) {
        int booked = 0;
        for (int i = 0; i < statuses.size(); i++) {
            if (roomTypes.get(i).equals(ROOM_TYPES[typeIndex])
                && statuses.get(i).equals("Confirmed")) {
                booked++;
            }
        }
        return ROOM_COUNT[typeIndex] - booked;
    }

    // ── 2. BOOK A ROOM ────────────────────────────────────────
    static void bookRoom() {
        System.out.println("\n--- Book a Room ---");

        // Show rooms first
        System.out.println("  Room Types:");
        for (int i = 0; i < ROOM_TYPES.length; i++) {
            System.out.println("  " + (i+1) + ". " + ROOM_TYPES[i]
                + "  (Rs." + (int)ROOM_PRICES[i] + "/night)"
                + "  | Available: " + getAvailableCount(i));
        }

        // Choose room type
        System.out.print("\nChoose room type (1, 2, or 3): ");
        int typeChoice;
        try {
            typeChoice = Integer.parseInt(sc.nextLine().trim()) - 1;
            if (typeChoice < 0 || typeChoice >= ROOM_TYPES.length) {
                System.out.println("Invalid room type. Please choose 1, 2, or 3.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
            return;
        }

        // Check availability
        if (getAvailableCount(typeChoice) == 0) {
            System.out.println("Sorry! No rooms available for " + ROOM_TYPES[typeChoice]);
            return;
        }

        // Get room number
        int roomNo = getNextRoomNumber(typeChoice);

        // Guest details
        System.out.print("Enter your full name: ");
        String guestName = sc.nextLine().trim();
        if (guestName.isEmpty()) {
            System.out.println("Name cannot be empty!");
            return;
        }

        System.out.print("Enter check-in date (DD/MM/YYYY): ");
        String checkIn = sc.nextLine().trim();
        if (checkIn.isEmpty()) {
            System.out.println("Check-in date cannot be empty!");
            return;
        }

        System.out.print("Enter check-out date (DD/MM/YYYY): ");
        String checkOut = sc.nextLine().trim();
        if (checkOut.isEmpty()) {
            System.out.println("Check-out date cannot be empty!");
            return;
        }

        System.out.print("Enter number of nights: ");
        int numNights;
        try {
            numNights = Integer.parseInt(sc.nextLine().trim());
            if (numNights <= 0) {
                System.out.println("Nights must be at least 1.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
            return;
        }

        // Calculate total amount
        double totalAmount = ROOM_PRICES[typeChoice] * numNights;

        // Show booking summary before confirming
        System.out.println("\n--- Booking Summary ---");
        System.out.println("  Room Type  : " + ROOM_TYPES[typeChoice]);
        System.out.println("  Room No.   : " + roomNo);
        System.out.println("  Guest      : " + guestName);
        System.out.println("  Check-in   : " + checkIn);
        System.out.println("  Check-out  : " + checkOut);
        System.out.println("  Nights     : " + numNights);
        System.out.printf( "  Rate       : Rs.%.0f per night%n", ROOM_PRICES[typeChoice]);
        System.out.printf( "  Total      : Rs.%.0f%n", totalAmount);
        System.out.print("\nConfirm booking? (yes/no): ");

        String confirm = sc.nextLine().trim().toLowerCase();
        if (!confirm.equals("yes") && !confirm.equals("y")) {
            System.out.println("Booking cancelled by user.");
            return;
        }

        // Save booking
        bookingCounter++;
        String bookingId = "BK" + String.format("%03d", bookingCounter);

        bookingIds.add(bookingId);
        guestNames.add(guestName);
        roomTypes.add(ROOM_TYPES[typeChoice]);
        roomNumbers.add(roomNo);
        checkIns.add(checkIn);
        checkOuts.add(checkOut);
        nights.add(numNights);
        amounts.add(totalAmount);
        statuses.add("Confirmed");

        // Simulated payment
        System.out.println("\n  Processing payment...");
        System.out.println("  Payment Successful!");
        System.out.println("\n=== BOOKING CONFIRMED ===");
        System.out.println("  Booking ID : " + bookingId);
        System.out.println("  Guest      : " + guestName);
        System.out.println("  Room       : " + ROOM_TYPES[typeChoice] + " - Room " + roomNo);
        System.out.printf( "  Amount Paid: Rs.%.0f%n", totalAmount);
        System.out.println("  Thank you for choosing Grand CodeAlpha Hotel!");
        System.out.println("=========================");
    }

    // Get next available room number for a type
    static int getNextRoomNumber(int typeIndex) {
        int startNumber = (typeIndex + 1) * 100 + 1; // 101, 201, 301
        java.util.HashSet<Integer> used = new java.util.HashSet<>();
        for (int i = 0; i < roomTypes.size(); i++) {
            if (roomTypes.get(i).equals(ROOM_TYPES[typeIndex])
                && statuses.get(i).equals("Confirmed")) {
                used.add(roomNumbers.get(i));
            }
        }
        for (int n = startNumber; n < startNumber + ROOM_COUNT[typeIndex]; n++) {
            if (!used.contains(n)) return n;
        }
        return -1;
    }

    // ── 3. VIEW BOOKINGS ──────────────────────────────────────
    static void viewBookings() {
        System.out.println("\n--- All Bookings ---");

        if (bookingIds.isEmpty()) {
            System.out.println("No bookings yet. Please make a booking first.");
            return;
        }

        System.out.println("===========================================================================");
        System.out.printf("  %-8s %-16s %-14s %-6s %-10s %-10s %-10s%n",
            "ID", "Guest", "Room Type", "Room", "Check-in", "Nights", "Status");
        System.out.println("---------------------------------------------------------------------------");

        for (int i = 0; i < bookingIds.size(); i++) {
            System.out.printf("  %-8s %-16s %-14s %-6d %-10s %-10d %-10s%n",
                bookingIds.get(i),
                guestNames.get(i),
                roomTypes.get(i),
                roomNumbers.get(i),
                checkIns.get(i),
                nights.get(i),
                statuses.get(i)
            );
            System.out.printf("           Amount: Rs.%.0f%n", amounts.get(i));
            System.out.println("---------------------------------------------------------------------------");
        }
    }

    // ── 4. CANCEL BOOKING ─────────────────────────────────────
    static void cancelBooking() {
        System.out.println("\n--- Cancel a Booking ---");

        if (bookingIds.isEmpty()) {
            System.out.println("No bookings found.");
            return;
        }

        // Show all confirmed bookings
        System.out.println("Confirmed Bookings:");
        boolean anyConfirmed = false;
        for (int i = 0; i < bookingIds.size(); i++) {
            if (statuses.get(i).equals("Confirmed")) {
                System.out.println("  " + bookingIds.get(i)
                    + " | " + guestNames.get(i)
                    + " | " + roomTypes.get(i)
                    + " | Room " + roomNumbers.get(i));
                anyConfirmed = true;
            }
        }

        if (!anyConfirmed) {
            System.out.println("No confirmed bookings to cancel.");
            return;
        }

        System.out.print("\nEnter Booking ID to cancel (e.g. BK001): ");
        String id = sc.nextLine().trim().toUpperCase();

        int index = -1;
        for (int i = 0; i < bookingIds.size(); i++) {
            if (bookingIds.get(i).equals(id)) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            System.out.println("Booking ID not found: " + id);
            return;
        }

        if (statuses.get(index).equals("Cancelled")) {
            System.out.println("This booking is already cancelled.");
            return;
        }

        // Confirm cancellation
        System.out.println("  Booking: " + bookingIds.get(index)
            + " | " + guestNames.get(index)
            + " | " + roomTypes.get(index));
        System.out.print("Are you sure you want to cancel? (yes/no): ");
        String confirm = sc.nextLine().trim().toLowerCase();

        if (confirm.equals("yes") || confirm.equals("y")) {
            statuses.set(index, "Cancelled");
            System.out.println("Booking " + id + " has been successfully cancelled.");
            System.out.printf("Refund of Rs.%.0f will be processed.%n", amounts.get(index));
        } else {
            System.out.println("Cancellation aborted.");
        }
    }

    // ── 5. SUMMARY ────────────────────────────────────────────
    static void viewSummary() {
        System.out.println("\n--- Hotel Summary Report ---");

        int totalBookings    = bookingIds.size();
        int confirmedCount   = 0;
        int cancelledCount   = 0;
        double totalRevenue  = 0;

        for (int i = 0; i < statuses.size(); i++) {
            if (statuses.get(i).equals("Confirmed")) {
                confirmedCount++;
                totalRevenue += amounts.get(i);
            } else {
                cancelledCount++;
            }
        }

        System.out.println("============================================");
        System.out.println("   GRAND CODEALPHA HOTEL — Summary Report   ");
        System.out.println("============================================");
        System.out.println("  Total Bookings    : " + totalBookings);
        System.out.println("  Confirmed         : " + confirmedCount);
        System.out.println("  Cancelled         : " + cancelledCount);
        System.out.printf( "  Total Revenue     : Rs.%.0f%n", totalRevenue);
        System.out.println("--------------------------------------------");
        System.out.println("  Room Availability:");
        for (int i = 0; i < ROOM_TYPES.length; i++) {
            System.out.println("    " + ROOM_TYPES[i]
                + ": " + getAvailableCount(i) + " / "
                + ROOM_COUNT[i] + " rooms available");
        }
        System.out.println("============================================");
    }
}