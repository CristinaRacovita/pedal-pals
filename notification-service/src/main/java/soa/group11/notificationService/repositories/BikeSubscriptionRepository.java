package soa.group11.notificationService.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import soa.group11.notificationService.entities.BikeSubscription;

@Repository
public class BikeSubscriptionRepository {
        private final MongoOperations mongoOperations;

        @Autowired
        public BikeSubscriptionRepository(MongoOperations mongoOperations) {
                this.mongoOperations = mongoOperations;
        }

        public List<BikeSubscription> findByUserId(int userId) {
                Query query = new Query();
                query.addCriteria(Criteria.where("userId").is(userId));

                return mongoOperations.find(query, BikeSubscription.class);
        }

        public List<BikeSubscription> getSubscriptionsLike(int wheelSize, int numberOfGears, String startDate,
                        String endDate, String brand, String type, String usage, String color) {
                Query query = new Query();

                if (wheelSize != 0){
                        query.addCriteria(Criteria.where("wheelSize").is(wheelSize));
                }
                if (numberOfGears != 0){
                        query.addCriteria(Criteria.where("numberOfGears").is(numberOfGears));
                }
                if (color != ""){
                        query.addCriteria(Criteria.where("color").is(color));
                }
                query.addCriteria(Criteria.where("brand").is(brand));
                query.addCriteria(Criteria.where("type").is(type));
                query.addCriteria(Criteria.where("usage").is(usage));

                query.addCriteria(Criteria.where("startDate").gte(startDate));
                query.addCriteria(Criteria.where("endDate").lte(endDate));

                return mongoOperations.find(query, BikeSubscription.class);
        }

        public void save(BikeSubscription bikeSubscription){
                mongoOperations.save(bikeSubscription);
        }
}
