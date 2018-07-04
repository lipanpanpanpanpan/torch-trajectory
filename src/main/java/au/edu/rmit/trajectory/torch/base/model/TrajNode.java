package au.edu.rmit.trajectory.torch.base.model;

import java.util.ArrayList;
import java.util.List;

/**
 * A trajNode models a node on a trajectory.
 * Besides GPS coordinate, each node could also carries other information such as timestep, current speed etc.
 * However, algorithm from raw trajectory nodes to graph vertices for other kind of information requires further research and experiment.
 * This function is expected to be developed in future.
 */
public class TrajNode implements TrajEntry{

    public int id;
    public double lat = -1;
    public double lng = -1;

    private long _time = -1;
    List<String> _bundle = new ArrayList<>(3);

    public TrajNode(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public TrajNode(double lat, double lng, long time) {
        this(lat, lng);
        _time = time;
    }

    public void addExtraInfo(String extra){
        _bundle.add(extra);
    }

    public void setTime(long time){
        _time = time;
    }

    public long getTime() {
        if (_time == -1) throw new IllegalStateException("try to fetch time in node not containing time information");
        return _time;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public double getLat(){
        return lat;
    }

    @Override
    public double getLng(){
        return lng;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        return hashCode() == o.hashCode();
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(this.lat);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(lng);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "{" + this.lat + ", " + lng + '}';
    }
}