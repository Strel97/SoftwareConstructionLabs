import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

/**
 * Created by Сергей on 20.10.2015.
 */
public class SecondTask {

    /**
     * Maximum number of flights in collection
     */
    private static final int NUM_FLIGHTS = 20;

    /**
     * Minimum and maximum order number of each flight
     */
    private static final int MIN_FLIGHT_NUM = 0;
    private static final int MAX_FLIGHT_NUM = 500;

    /**
     * Minimum and maximum flight duration in hours
     */
    private static final int MIN_FLIGHT_DURATION = 2;
    private static final int MAX_FLIGHT_DURATION = 12;

    /**
     * Minimum and maximum flight distance in km
     */
    private static final int MIN_FLIGHT_DISTANCE = 1000;
    private static final int MAX_FLIGHT_DISTANCE = 50000;

    /**
     * Duration and distance bounds for deleting, given in the task
     */
    private static final int DURATION_BOUND = 6;
    private static final int DISTANCE_BOUND = 10000;

    /**
     * First param of map is the number of flight
     * Second param is info about flight (duration and distance)
     */
    private static final Map<Integer, Flight> flights = new TreeMap<>();


    private static void fillMap(Map<Integer, Flight> map) {
        Random rand = new Random();
        int i = NUM_FLIGHTS;

        while (i > 0) {
            int num = rand.nextInt(MAX_FLIGHT_NUM) + MIN_FLIGHT_NUM;
            Flight flight = new Flight(
                    rand.nextInt(MAX_FLIGHT_DURATION) + MIN_FLIGHT_DURATION,
                    rand.nextInt(MAX_FLIGHT_DISTANCE) + MIN_FLIGHT_DISTANCE
            );

            if (!map.containsKey(num)) {
                map.put(num, flight);
                i--;
            }
        }
    }

    /**
     * Deletes flights according to variant:
     *      duration > 6
     *      distance > 10 000
     *
     * @param map   Map of flights
     */
    private static void deleteSomething(Map<Integer, Flight> map) {
        ArrayList<Integer> keysToRemove = new ArrayList<>();
        map.forEach((num, flight) -> {
            if (flight.getDistance() > DISTANCE_BOUND ||
                    flight.getDuration() > DURATION_BOUND) {
                keysToRemove.add(num);
            }
        });

        keysToRemove.forEach(map::remove);
    }

    private static void printMap(Map<Integer, Flight> map) {
        System.out.println(" === FLIGHTS OUTPUT === ");
        map.forEach((num, flight) -> {
            System.out.println("\t Flight #" + num +
                    " -> ( duration = " + flight.getDuration() +
                    " ; distance = " + flight.getDistance() + " )");
        });
        System.out.println(" === ============== === ");
    }

    public static void main(String []args) {
        fillMap(flights);
        printMap(flights);

        System.out.println();

        deleteSomething(flights);
        printMap(flights);
       }
}
