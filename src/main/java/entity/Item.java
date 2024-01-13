package entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "item")
    private List<OrderDetail> orderDetails = new ArrayList<>();
    public Item(String id,String name, String category, int qty, String status) {
        this.itemId=id;
        this.name=name;
        this.category=category;
        this.qty=qty;
        this.status=status;
    }
}
