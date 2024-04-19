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
    private String reviwerName;
    private int reviewerId;
    private int numberOfStars;
    private String review;
    private Date date;

    public Review(String bikeId, String title, int reviewerId, String reviwerName, int numberOfStars, String review) {
        this.bikeId = bikeId;
        this.title = title;
        this.reviwerName = reviwerName;
        this.reviewerId = reviewerId;
        this.numberOfStars = numberOfStars;
        this.review = review;
        this.date = new Date();
    }

    public Review(String id, String bikeId, String title, String reviwerName, int reviewerId, int numberOfStars, String review) {
        this.id = id;
        this.bikeId = bikeId;
        this.title = title;
        this.reviwerName = reviwerName;
        this.reviewerId = reviewerId;
        this.numberOfStars = numberOfStars;
        this.review = review;
    }
}
