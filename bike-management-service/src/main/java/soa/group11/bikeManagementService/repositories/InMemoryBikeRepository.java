package soa.group11.bikeManagementService.repositories;

import java.util.ArrayList;
import java.util.List;

import soa.group11.bikeManagementService.entities.Bike;
import soa.group11.bikeManagementService.entities.BikeType;
import soa.group11.bikeManagementService.enums.SuitableRoad;

public class InMemoryBikeRepository implements BikeRepository {
    private List<Bike> bikes = new ArrayList<Bike>() {
        {
            add(new Bike(1, "Gazelle", new BikeType(SuitableRoad.TOWN)));
            add(new Bike(2, "Batavus", new BikeType(SuitableRoad.TOWN)));
            add(new Bike(3, "Van Hulsteijn", new BikeType(SuitableRoad.MOUNTAIN)));
            add(new Bike(1, "Elops", new BikeType(SuitableRoad.TOWN)));
            add(new Bike(1, "Gazelle", new BikeType(SuitableRoad.MOUNTAIN)));
            add(new Bike(2, "Union", new BikeType(SuitableRoad.TOWN)));
        }
    };

    @Override
    public List<Bike> getBikesByUserId(int userId) {
        return bikes.stream().filter(bike -> bike.getUserId() == userId).toList();
    }

    @Override
    public void save(Bike bike) {
        this.bikes.add(bike);
    }
}
