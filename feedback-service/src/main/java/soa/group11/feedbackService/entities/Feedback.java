package soa.group11.feedbackService.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
    private String bikeId;
    private String reviewerId;
    private int numberOfStars;
    private String review;

    public Feedback(String bikeId, String reviewerId, int numberOfStars, String review) {
        this.bikeId = bikeId;
        this.reviewerId = reviewerId;
        this.numberOfStars = numberOfStars;
        this.review = review;
    }

    public Feedback(String id, String bikeId, String reviewerId, int numberOfStars, String review) {
        this.id = id;
        this.bikeId = bikeId;
        this.reviewerId = reviewerId;
        this.numberOfStars = numberOfStars;
        this.review = review;
    }
}
