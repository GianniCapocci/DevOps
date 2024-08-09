package gr.hua.dev_ops.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="listings")
public class Listing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private String area;

    @Column(nullable = false)
    private int areaCode;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private int squareMeters;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @Column(nullable = false)
    private LocalDate builtDate;

    @Column(nullable = false)
    private int floor;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "listing", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "listing-images")
    private List<Image> images;

    @OneToMany(mappedBy = "listing", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "listing-reviews")
    private List<Review> reviews;

    @Column(name = "broker_id")
    private Long brokerId;

    @ManyToOne
    @JoinColumn(name = "broker_id", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonIgnore
    private Broker broker;


    @Column(name = "owner_id")
    private Long ownerId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id", referencedColumnName = "id", insertable = false, updatable = false, nullable = true)
    @JsonIgnore
    private Owner owner;

    @OneToMany(mappedBy = "listing", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "listing-bookmarks")
    private Set<Bookmark> bookmarks = new HashSet<>();

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
