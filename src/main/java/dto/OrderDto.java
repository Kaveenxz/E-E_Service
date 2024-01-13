package dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private String orderId;
    private String date;
    private String custId;

    private List<OrderDetaiDto> list;

}
