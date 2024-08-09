package gr.hua.dev_ops.filter;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ListingFilterCriteria {
    private String street;
    private String area;
    private Integer areaCode;
    private Double minPrice;
    private Double maxPrice;
    private Integer minSquareMeters;
    private Integer maxSquareMeters;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer minFloor;
    private Integer maxFloor;
}
