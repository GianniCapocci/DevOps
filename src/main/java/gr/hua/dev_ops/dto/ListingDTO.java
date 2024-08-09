package gr.hua.dev_ops.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ListingDTO {
    private Long id;
    private String street;
    private String area;
    private int areaCode;
    private Double price;
    private Integer squareMeters;
    private LocalDate builtDate;
    private Integer floor;
    private Long brokerId;
    private Long ownerId;
}
