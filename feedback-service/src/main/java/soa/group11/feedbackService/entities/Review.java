package soa.group11.feedbackService.entities;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "reviews")
public class Review {
    @Id
    private String id;
    private String title;
    private String bikeId;
    private String bikeName;
    private String reviewerName;
    private int reviewerId;
    private int numberOfStars;
    private String review;
    private Date date;

    public Review(String bikeId, String bikeName, String title, String reviewerName, int reviewerId, int numberOfStars,
            String review) {
        this.bikeId = bikeId;
        this.bikeName = bikeName;
        this.title = title;
        this.reviewerName = reviewerName;
        this.reviewerId = reviewerId;
        this.numberOfStars = numberOfStars;
        this.review = review;
        this.date = new Date();
    }

    public Review(String id, String bikeId, String bikeName, String title, String reviewerName, int reviewerId,
            int numberOfStars, String review) {
        this.id = id;
        this.bikeName = bikeName;
        this.bikeId = bikeId;
        this.title = title;
        this.reviewerName = reviewerName;
        this.reviewerId = reviewerId;
        this.numberOfStars = numberOfStars;
        this.review = review;
    }
}
