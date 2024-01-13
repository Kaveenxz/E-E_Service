package dto.tm;

import javafx.scene.control.Button;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrderTm {
    private String itemId;
    private String customerName;
    private String status;
    private double totFee;
    private String date;
    private Button btn;
}
