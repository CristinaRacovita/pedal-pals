package soa.group11.feedbackService.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FeedbackDto {
    private String id;
    private int bikeId;
    private int reviewerId;
    private int numberOfStars;
    private String review = "";

    public FeedbackDto(String id, int bikeId, int reviewerId, int numberOfStars) {
        this.id = id;
        this.bikeId = bikeId;
        this.reviewerId = reviewerId;
        this.numberOfStars = numberOfStars;
    }
    
    public FeedbackDto(String id, int bikeId, int reviewerId, int numberOfStars, String review) {
        this.id = id;
        this.bikeId = bikeId;
        this.reviewerId = reviewerId;
        this.numberOfStars = numberOfStars;
        this.review = review;
    }
}
