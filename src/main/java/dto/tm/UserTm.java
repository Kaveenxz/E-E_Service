package dto.tm;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.scene.control.Button;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserTm extends RecursiveTreeObject<UserTm> {
    private String userId;
    private String userName;
    private String email;
    private String passWord;
    private String userType;
    private Button btn;
}
