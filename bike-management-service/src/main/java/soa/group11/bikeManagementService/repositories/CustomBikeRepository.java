package soa.group11.bikeManagementService.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import soa.group11.bikeManagementService.entities.Bike;

@Repository
public class CustomBikeRepository {
    private final MongoOperations mongoOperations;

    @Autowired
    public CustomBikeRepository(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    public List<Bike> findByFilterCriteria(Integer wheelSize, Integer numberOfGears,
            Date startRentingDate, Date endRentingDate,
            String brand, String type, String suitability) {

        Query query = new Query();
        if (wheelSize != -1) {
            query.addCriteria(Criteria.where("wheelSize").is(wheelSize));
        }
        if (numberOfGears != -1) {
            query.addCriteria(Criteria.where("numberOfGears").is(numberOfGears));
        }
        if (startRentingDate != null) {
            query.addCriteria(Criteria.where("startRentingDate").lt(startRentingDate));
        }
        if (endRentingDate != null) {
            query.addCriteria(Criteria.where("endRentingDate").gt(endRentingDate));
        }
        if (brand != null) {
            query.addCriteria(Criteria.where("brand").is(brand));
        }
        if (type != null) {
            query.addCriteria(Criteria.where("type").is(type));
        }
        if (suitability != null) {
            query.addCriteria(Criteria.where("suitability").is(suitability));
        }
        return mongoOperations.find(query, Bike.class);
    }
}
