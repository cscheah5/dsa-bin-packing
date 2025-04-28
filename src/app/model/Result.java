package app.model;

import java.util.List;

public class Result {

    public String name;
    public double timeMs;
    public double memoryUsageMb;
    public List<Truck> trucks;

    public Result(String name, double timeMs, double memoryUsageMb, List<Truck> trucks) {
        this.name = name;
        this.timeMs = timeMs;
        this.memoryUsageMb = memoryUsageMb;
        this.trucks = trucks;
    }
}
