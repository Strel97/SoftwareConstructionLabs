/**
 * Created by Сергей on 20.10.2015.
 */
public class Flight {

    /**
     * Duration of flight in hours
     */
    private int duration;

    /**
     * Distance of flight in km
     */
    private int distance;


    public Flight(int duration, int distance) {
        this.duration = duration;
        this.distance = distance;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
