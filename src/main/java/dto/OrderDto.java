package dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private long orderId;
    private String customerName;
    private String note;
    private String date;
    private String status;
    private double additionFee;
    private double totFee;
}
