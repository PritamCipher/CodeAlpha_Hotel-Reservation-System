import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Room {
    private int roomNumber;
    private String category;
    private double price;
    private boolean isAvailable;

    public Room(int roomNumber, String category, double price) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.price = price;
        this.isAvailable = true;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void bookRoom() {
        isAvailable = false;
    }

    public void releaseRoom() {
        isAvailable = true;
    }
}

class Hotel {
    private List<Room> rooms;

    public Hotel() {
        rooms = new ArrayList<>();
        initializeRooms();
    }

    private void initializeRooms() {
        rooms.add(new Room(101, "Standard", 100.0));
        rooms.add(new Room(102, "Deluxe", 150.0));
        rooms.add(new Room(103, "Suite", 200.0));
    }

    public List<Room> getAvailableRooms() {
        List<Room> availableRooms = new ArrayList<>();
        for (Room room : rooms) {
            if (room.isAvailable()) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    public Room getRoomByNumber(int roomNumber) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber) {
                return room;
            }
        }
        return null;
    }
}

class Reservation {
    private Room room;
    private String guestName;

    public Reservation(Room room, String guestName) {
        this.room = room;
        this.guestName = guestName;
    }

    public Room getRoom() {
        return room;
    }

    public String getGuestName() {
        return guestName;
    }
}

class Payment {
    public static boolean processPayment(double amount, String paymentMethod) {
        System.out.println("Processing payment of $" + amount + " via " + paymentMethod);
        return true;
    }
}

public class HotelReservationSystem {
    public static void main(String[] args) {
        Hotel hotel = new Hotel();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Hotel Reservation System!");

        while (true) {
            System.out.println("\n1. Search for available rooms");
            System.out.println("2. Make a reservation");
            System.out.println("3. View booking details");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    List<Room> availableRooms = hotel.getAvailableRooms();
                    System.out.println("Available Rooms:");
                    for (Room room : availableRooms) {
                        System.out.println("Room " + room.getRoomNumber() +
                                " - " + room.getCategory() +
                                " - $" + room.getPrice() +
                                " per night");
                    }
                    break;
                case 2:
                    System.out.print("Enter the room number you want to reserve: ");
                    int roomNumber = scanner.nextInt();
                    Room selectedRoom = hotel.getRoomByNumber(roomNumber);

                    if (selectedRoom != null && selectedRoom.isAvailable()) {
                        System.out.print("Enter your name: ");
                        String guestName = scanner.nextLine();

                        System.out.println("Room selected: " +
                                selectedRoom.getCategory() +
                                " - $" + selectedRoom.getPrice() +
                                " per night");

                        System.out.println("Select a payment method:");
                        System.out.println("1. UPI");
                        System.out.println("2. Credit Card");
                        System.out.println("3. Debit Card");
                        System.out.print("Enter the number corresponding to your payment method choice: ");
                        int paymentMethodChoice = scanner.nextInt();
                        scanner.nextLine();

                        String paymentMethod;
                        switch (paymentMethodChoice) {
                            case 1:
                                paymentMethod = "UPI";
                                break;
                            case 2:
                                paymentMethod = "Credit Card";
                                break;
                            case 3:
                                paymentMethod = "Debit Card";
                                break;
                            default:
                                System.out.println("Invalid payment method choice. Payment canceled.");
                                continue;
                        }

                        System.out.print("Enter the number of nights to stay: ");
                        int numNights = scanner.nextInt();
                        double totalAmount = numNights * selectedRoom.getPrice();

                        System.out.println("Total amount: $" + totalAmount);

                        System.out.println("Processing payment...");
                        if (Payment.processPayment(totalAmount, paymentMethod)) {
                            Reservation reservation = new Reservation(selectedRoom, guestName);
                            selectedRoom.bookRoom();
                            System.out.println("Reservation successful. Room booked for " + guestName);
                        } else {
                            System.out.println("Payment failed. Reservation not completed.");
                        }
                    } else {
                        System.out.println("Room not available or invalid room number.");
                    }
                    break;
                case 3:
                    // Implement viewing booking details based on reservation history;
                    break;
                case 4:
                    System.out.println("Thank you for using the Hotel Reservation System. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
