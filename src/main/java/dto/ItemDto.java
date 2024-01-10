package dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {
    private String id;
    private String name;
    private String category;
    private int qty;
    private String status;


}