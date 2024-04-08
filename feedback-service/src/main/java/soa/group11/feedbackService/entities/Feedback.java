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
@Document(collection = "feedbacks")
public class Feedback {
    @Id
    private String id;
    private String title;
    private String bikeId;
    private String reviewerId;
    private int numberOfStars;
    private String review;
    private Date date;

    public Feedback(String bikeId, String title, String reviewerId, int numberOfStars, String review) {
        this.bikeId = bikeId;
        this.title = title;
        this.reviewerId = reviewerId;
        this.numberOfStars = numberOfStars;
        this.review = review;
        this.date = new Date();
    }

    public Feedback(String id, String bikeId, String title, String reviewerId, int numberOfStars, String review) {
        this.id = id;
        this.bikeId = bikeId;
        this.title = title;
        this.reviewerId = reviewerId;
        this.numberOfStars = numberOfStars;
        this.review = review;
    }
}
