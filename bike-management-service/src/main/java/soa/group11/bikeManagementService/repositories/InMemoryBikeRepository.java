package soa.group11.bikeManagementService.repositories;

import java.util.ArrayList;
import java.util.List;

import soa.group11.bikeManagementService.entities.Bike;
import soa.group11.bikeManagementService.entities.BikeType;

public class InMemoryBikeRepository {
    private List<Bike> bikes = new ArrayList<Bike>() {
        {
            add(new Bike(1, "Gazelle", new BikeType("CITY")));
            add(new Bike(2, "Batavus", new BikeType("CITY")));
            add(new Bike(3, "Van Hulsteijn", new BikeType("MOUNTAIN")));
            add(new Bike(1, "Elops", new BikeType("CITY")));
            add(new Bike(1, "Gazelle", new BikeType("MOUNTAIN")));
            add(new Bike(2, "Union", new BikeType("CITY")));
        }
    };

    public List<Bike> getBikesByUserId(int userId) {
        return bikes.stream().filter(bike -> bike.getUserId() == userId).toList();
    }

    public Bike save(Bike bike) {
        this.bikes.add(bike);
        return bike;
    }
}
