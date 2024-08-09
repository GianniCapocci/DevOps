package gr.hua.dev_ops.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)
    private String content;

    @Column(nullable = false)
    private int rating;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "listing_id", referencedColumnName = "id")
    @JsonBackReference(value = "listing-reviews")
    private Listing listing;

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    @JsonBackReference(value = "client-reviews")
    private Client client;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
