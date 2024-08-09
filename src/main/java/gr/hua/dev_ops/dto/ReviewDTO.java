package gr.hua.dev_ops.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReviewDTO {
    private String content;
    private int rating;
    private LocalDateTime createdAt;
    private Long listingId;
    private Long clientId;
}
