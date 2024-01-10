package entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long itemId;
    private String name;
    private String category;
    private int qty;
    private String status;


    public Item(String name, String category, int qty, String status) {
        this.name=name;
        this.category=category;
        this.qty=qty;
        this.status=status;
    }
}
