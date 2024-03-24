package soa.group11.feedbackService.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "feedbacks")
public class Feedback {
    @Id
    private String id;

    private int bikeId;
    private int reviewerId;
    private int numberOfStars;
    private String review;

    public Feedback(int bikeId, int reviewerId, int numberOfStars) {
        this.bikeId = bikeId;
        this.reviewerId = reviewerId;
        this.numberOfStars = numberOfStars;
    }

    public Feedback(int bikeId, int reviewerId, int numberOfStars, String review) {
        this.bikeId = bikeId;
        this.reviewerId = reviewerId;
        this.numberOfStars = numberOfStars;
        this.review = review;
    }

    public Feedback(String id, int bikeId, int reviewerId, int numberOfStars, String review) {
        this.id = id;
        this.bikeId = bikeId;
        this.reviewerId = reviewerId;
        this.numberOfStars = numberOfStars;
        this.review = review;
    }
}
