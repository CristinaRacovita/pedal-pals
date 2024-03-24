package soa.group11.feedbackService.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FeedbackDto {
    private int bikeId;
    private int reviewerId;
    private int numberOfStars;
    private String review = "";

    public FeedbackDto(int bikeId, int reviewerId, int numberOfStars) {
        this.bikeId = bikeId;
        this.reviewerId = reviewerId;
        this.numberOfStars = numberOfStars;
    }
}
