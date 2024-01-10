package dto;

import entity.Item;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {
    private long id;
    private String name;
    private String category;
    private int qty;
    private String status;

    public ItemDto(String name, String category, int qty, String status) {
        this.name = name;
        this.category = category;
        this.qty = qty;
        this.status = status;
    }
}
