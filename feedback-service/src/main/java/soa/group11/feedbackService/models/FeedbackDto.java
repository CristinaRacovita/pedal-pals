package soa.group11.feedbackService.models;



import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Validated
@NoArgsConstructor
public class FeedbackDto {
    private String id;
    @NotNull
    private String bikeId;
    @NotNull
    private String reviewerId;
    @Min(1)
    @Max(5)
    private int numberOfStars;
    private String review = "";
    
    public FeedbackDto(String id, String bikeId, String reviewerId, int numberOfStars, String review) {
        this.id = id;
        this.bikeId = bikeId;
        this.reviewerId = reviewerId;
        this.numberOfStars = numberOfStars;
        this.review = review;
    }
}
