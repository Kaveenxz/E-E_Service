package entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
public class Item {
    @Id
    private String itemId;
    private String name;
    private String category;
    private int qty;
    private String status;


    public Item(String id,String name, String category, int qty, String status) {
        this.itemId=id;
        this.name=name;
        this.category=category;
        this.qty=qty;
        this.status=status;
    }
}
