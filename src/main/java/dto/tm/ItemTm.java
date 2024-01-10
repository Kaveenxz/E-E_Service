package dto.tm;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import entity.Item;
import javafx.scene.control.Button;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ItemTm extends RecursiveTreeObject<ItemTm> {
    private long id;
    private String name;
    private String category;
    private int qty;
    private String status;
    private Button btn;


}
