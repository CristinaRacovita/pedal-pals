package soa.group11.feedbackService.entities;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "feedbacks")
public class Feedback {
    @MongoId
    private int id;

    private int bikeId;
    private int reviewerId;
    private int numberOfStars;
    private String review;

    public Feedback(int bikeId, int reviewerId, int numberOfStars) {
        this.bikeId = bikeId;
        this.reviewerId = reviewerId;
        this.numberOfStars = numberOfStars;
    }
}
