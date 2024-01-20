import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class AirTransportationApp {
    private Map<String, Map<String, Integer>> airNetwork;

    public AirTransportationApp() {
        airNetwork = new HashMap<>();
    }

    // Add a connection between departure and arrival airports with a given distance
    public void addConnection(String departure, String arrival, int distance) {
        // Add the departure airport to the air network if it doesn't exist
        airNetwork.computeIfAbsent(departure, k -> new HashMap<>()).put(arrival, distance);
        // Add the arrival airport to the air network if it doesn't exist
        airNetwork.computeIfAbsent(arrival, k -> new HashMap<>()).put(departure, distance);
    }

    // Find a travel route from the departAirport to arriveAirport
    public List<String> findTravel(String departAirport, String arriveAirport) {
        List<String> visited = new ArrayList<>();
        List<String> travel = new ArrayList<>();

        // Start depth-first search (DFS) to find the travel route
        dfs(departAirport, arriveAirport, visited, travel);

        return travel;
    }

    // Get the distance between departure and arrival airports
    public int getFlightDistance(String departure, String arrival) {
        return airNetwork.get(departure).get(arrival);
    }

    // Calculate the total distance of the given travel route
    public int calculateTotalDistance(List<String> travel) {
        int totalDistance = 0;
        for (int i = 0; i < travel.size() - 1; i++) {
            String currentAirport = travel.get(i);
            String nextAirport = travel.get(i + 1);
            totalDistance += airNetwork.get(currentAirport).get(nextAirport);
        }
        return totalDistance;
    }

    // Print the available locations in the air network
    public void printAvailableLocations() {
        System.out.println("                  Available Locations                     ");
        System.out.println("==========================================================");

        List<String> locations = new ArrayList<>(airNetwork.keySet());
        int numLocations = locations.size();

        int numColumns = 3;
        int numRows = (int) Math.ceil((double) numLocations / numColumns);

        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numColumns; col++) {
                int index = row + col * numRows;
                if (index < numLocations) {
                    String location = locations.get(index);
                    String country = getCountryName(location);
                    System.out.printf("%-1s (%-11s)\t", location, country);
                    if (col < numColumns - 1) {
                        System.out.print("   ");
                    }
                }
            }
            System.out.println();
        }
    }

    // Get the country name for a given location code
    private String getCountryName(String location) {
        // Reference on each code for Country name
        switch (location) {
            case "MY":
                return "MALAYSIA";
            case "SG":
                return "SINGAPORE";
            case "ID":
                return "INDONESIA";
            case "TL":
                return "TIMOR-LESTE";
            case "BN":
                return "BRUNEI";
            case "LA":
                return "LAOS";
            case "KH":
                return "CAMBODIA";
            case "MM":
                return "MYANMAR";
            case "VN":
                return "VIETNAM";
            case "PH":
                return "PHILIPPINES";
            case "TH":
                return "THAILAND";
            case "NK":
                return "NORTH-KOREA";
            default:
                return "";
        }
    }

    // Depth-first search (DFS) algorithm to find the travel route
    private boolean dfs(String vertexAirport, String arriveAirport, List<String> visited, List<String> travel) {
        visited.add(vertexAirport);
        travel.add(vertexAirport);

        if (vertexAirport.equals(arriveAirport)) {
            return true; // travel found
        }

        Map<String, Integer> neighbors = airNetwork.get(vertexAirport);
        if (neighbors != null) {
            for (Map.Entry<String, Integer> neighbor : neighbors.entrySet()) {
                String neighborVertex = neighbor.getKey();
                if (!visited.contains(neighborVertex)) {
                    if (dfs(neighborVertex, arriveAirport, visited, travel)) {
                        return true;
                    }
                }
            }
        }

        // Backtrack the path if the current vertex does not lead to the arrival airport
        travel.remove(travel.size() - 1);
        visited.remove(vertexAirport);

        return false;
    }
}
