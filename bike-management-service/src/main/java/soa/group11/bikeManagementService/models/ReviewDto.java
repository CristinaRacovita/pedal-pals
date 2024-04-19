package soa.group11.bikeManagementService.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReviewDto {
    private String id;
    private String bikeId;
    private String reviewerId;
    private int numberOfStars;
    private String review = "";

    public ReviewDto(String reviewerId, int numberOfStars, String review) {
        this.reviewerId = reviewerId;
        this.numberOfStars = numberOfStars;
        this.review = review;
    }
}
