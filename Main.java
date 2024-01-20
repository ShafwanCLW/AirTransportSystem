import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AirTransportationApp app = new AirTransportationApp();

        // Add connections between airports
        app.addConnection("MY", "SG", 250); // MY -> SG
        app.addConnection("MY", "ID", 370); // MY -> ID
        app.addConnection("ID", "TL", 900); // ID -> TL
        app.addConnection("ID", "BN", 470); // ID -> BN
        app.addConnection("LA", "KH", 200); // LA -> KH
        app.addConnection("MM", "VN", 1000); // MM -> VH
        app.addConnection("PH", "SG", 850); // PH -> SG
        app.addConnection("PH", "MY", 700); // PH -> MY
        app.addConnection("SG", "TH", 200); // SG -> TH
        app.addConnection("SG", "MM", 850); // SG -> MM
        app.addConnection("SG", "ID", 450); // SG -> ID
        app.addConnection("TH", "LA", 150); // TH -> LA
        app.addConnection("TH", "VN", 700); // TH -> VN
        app.addConnection("TH", "KH", 400); // TH -> KH
        app.addConnection("VN", "LA", 1500); // VN -> LA
        app.addConnection("BN", "MY", 100); // BN -> MY
        app.addConnection("TL", "MM", 500); // TL -> MM
        app.addConnection("KH", "PH", 300); // KH -> PH
        app.addConnection("NK", "NK", 0); // NK -> NK
        Scanner scanner = new Scanner(System.in);

        System.out.println("===========================================================");
        System.out.println("        Welcome to Air Transportation Network Menu         ");
        System.out.println("-----------------------------------------------------------");
        System.out.println("              PLEASE TURN ON YOUR CAPS LOCK!              ");
        System.out.println("===========================================================");
        app.printAvailableLocations();
        System.out.println("===========================================================");
        System.out.println("1. Find Travel Path To Your Destination");
        System.out.println("2. Exit");
        System.out.print("Enter your choice: ");

        int choice;
        do {
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {

                case 1:
                    // Get user input for departure and arrival airports
                    System.out.print("Enter the Departure Airport: ");
                    String departureAirport = scanner.nextLine();
                    System.out.print("Enter the Arrival Airport: ");
                    String arrivalAirport = scanner.nextLine();

                    // Find the travel path
                    List<String> travel = app.findTravel(departureAirport, arrivalAirport);
                    // Print the travel path
                    System.out.println("\nTravel path:");
                    if (travel.isEmpty()) {
                        System.out.println("No path Available from " + departureAirport + " to " + arrivalAirport
                                + " in the Air Transportation Network.");
                    } else {
                        StringBuilder path = new StringBuilder();
                        for (int i = 0; i < travel.size() - 1; i++) {
                            String currentAirport = travel.get(i);
                            String nextAirport = travel.get(i + 1);
                            path.append(currentAirport).append(" -> ");
                        }
                        path.append(arrivalAirport);
                        int totalDistance = app.calculateTotalDistance(travel);
                        System.out.println("| " + path.toString() + " | ");
                        System.out.println("Total Distance Travelled = " + totalDistance + "KM");
                    }
                    break;
                case 2:
                    System.out.println("Exiting the program. Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

            if (choice != 2) {

                System.out.println("===========================================================");
                app.printAvailableLocations();
                System.out.println("===========================================================");
                System.out.println("1. Find Travel Path To Your Destination");
                System.out.println("2. Exit");
                System.out.print("Enter your choice: ");
            }
        } while (choice != 2);
    }
}
