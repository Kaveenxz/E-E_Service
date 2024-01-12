package dto.tm;

import javafx.scene.control.Button;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrderTm {
    private long orderId;
    private String customerName;
    private String note;
    private String date;
    private String status;
    private double additionFee;
    private double totFee;
    private Button btn;
}
