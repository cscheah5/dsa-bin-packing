package app.strategy;

import app.model.Parcel;
import app.model.Truck;
import app.model.TruckLoadingProblem;
import java.util.List;

public interface TruckLoadingStrategy {

    /**
     * Return List of Trucks for visualization
     */
    List<Truck> getTrucks();

    /**
     * Pack all the parcels, by invoking the packParcel method
     */
    void solve(TruckLoadingProblem problem);

    /**
     * Pack one of the parcel, and add it to Bin List
     */
    void packParcel(Parcel parcel);
}
