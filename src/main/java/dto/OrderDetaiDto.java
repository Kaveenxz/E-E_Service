package dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetaiDto {
    private long orerDetailId;
    private long itemCode;
    private double cost;
}
