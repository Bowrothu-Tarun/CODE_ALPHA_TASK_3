import java.util.*;

class Room {
    int number;
    String category;
    double price;
    boolean available;

    public Room(int number, String category, double price) {
        this.number = number;
        this.category = category;
        this.price = price;
        this.available = true;
    }
}

class Reservation {
    Room room;
    String name;
    String email;
    double payment;

    public Reservation(Room room, String name, String email, double payment) {
        this.room = room;
        this.name = name;
        this.email = email;
        this.payment = payment;
    }
}

public class HotelReservationSystem {
    private static List<Room> rooms = new ArrayList<>();
    private static List<Reservation> reservations = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        initializeRooms();

        while (true) {
            System.out.println("1. Search Rooms");
            System.out.println("2. Make Reservation");
            System.out.println("3. View Reservations");
            System.out.println("4. Exit");
            System.out.print("Enter an option: ");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    searchRooms(scanner);
                    break;
                case 2:
                    makeReservation(scanner);
                    break;
                case 3:
                    viewReservations();
                    break;
                case 4:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option. Please choose a valid option.");
            }
        }
    }

    private static void initializeRooms() {
        rooms.add(new Room(101, "Standard", 500.0));
        rooms.add(new Room(102, "Standard", 500.0));
        rooms.add(new Room(103, "Standard", 500.0));
        rooms.add(new Room(104, "Standard", 500.0));
        rooms.add(new Room(105, "Standard", 500.0));
        rooms.add(new Room(201, "Deluxe", 1000.0));
        rooms.add(new Room(202, "Deluxe", 1000.0));
        rooms.add(new Room(203, "Deluxe", 1000.0));
        rooms.add(new Room(301, "Suite", 2000.0));
        rooms.add(new Room(302, "Suite", 2000.0));
    }

    private static void searchRooms(Scanner scanner) {
        System.out.print("Enter room category (Standard, Deluxe, Suite): ");
        String category = scanner.next();

        System.out.println("Available rooms:");
        for (Room room : rooms) {
            if (room.category.equalsIgnoreCase(category) && room.available) {
                System.out.println("Room " + room.number + " - " + room.category + " - $" + room.price);
            }
        }
    }

    private static void makeReservation(Scanner scanner) {
        System.out.print("Enter room number: ");
        int roomNumber = scanner.nextInt();
        System.out.print("Enter your name: ");
        String name = scanner.next();
        System.out.print("Enter your email: ");
        String email = scanner.next();
        System.out.print("Enter payment amount: ");
        double payment = scanner.nextDouble();

        Room room = findRoom(roomNumber);
        if (room != null && room.available) {
            Reservation reservation = new Reservation(room, name, email, payment);
            reservations.add(reservation);
            room.available = false;
            System.out.println("Reservation made successfully!");
        } else {
            System.out.println("Room not available or does not exist.");
        }
    }

    private static void viewReservations() {
        if (reservations.isEmpty()) {
            System.out.println("No reservations found.");
        } else {
            System.out.println("Reservations:");
            for (Reservation reservation : reservations) {
                System.out.println("Room " + reservation.room.number + " - " + reservation.room.category);
                System.out.println("Name: " + reservation.name);
                System.out.println("Email: " + reservation.email);
                System.out.println("Payment: $" + reservation.payment);
                System.out.println();
            }
        }
    }

    private static Room findRoom(int roomNumber) {
        for (Room room : rooms) {
            if (room.number == roomNumber) {
                return room;
            }
        }
        return null;
    }
}
