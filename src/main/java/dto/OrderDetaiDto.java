package dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetaiDto {
    private String orerId;
    private String itemCode;
    private double cost;
    private double additionFee;

}
