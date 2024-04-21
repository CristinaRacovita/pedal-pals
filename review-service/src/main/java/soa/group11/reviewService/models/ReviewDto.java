package soa.group11.reviewService.models;

import java.text.SimpleDateFormat;
import java.util.Date;

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
public class ReviewDto {
    private String id;
    private String bikeName;
    @NotNull
    private String bikeId;
    private String reviewerName;
    @NotNull
    private String title;
    @NotNull
    private int reviewerId;
    @Min(1)
    @Max(5)
    private int numberOfStars;
    private String review = "";
    private String reviewDate;

    public ReviewDto(String id, String bikeName, String bikeId, String reviewerName, String title, int reviewerId,
            int numberOfStars, String review,
            Date reviewDate) {
        this.id = id;
        this.bikeName = bikeName;
        this.bikeId = bikeId;
        this.reviewerName = reviewerName;
        this.title = title;
        this.reviewerId = reviewerId;
        this.numberOfStars = numberOfStars;
        this.review = review;
        this.reviewDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(reviewDate);
    }
}
