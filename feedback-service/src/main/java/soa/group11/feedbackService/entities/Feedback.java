package soa.group11.feedbackService.entities;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Feedback {
    private UUID reviewerId;
    private UUID reviewedUserId;
    private int numberOfStars;
    private String review;
}
