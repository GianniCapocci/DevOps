package gr.hua.dev_ops.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="brokers")
public class Broker extends User {

    @Column(unique = true, nullable = false)
    private String phoneNumber;

    @OneToMany(mappedBy = "broker", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Listing> listings;
}
