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
public class OrderDetail {
    @Id
    private Long orderIDetaild;
    private long orerDetailId;
    private long itemCode;
    private double cost;

    public OrderDetail(Long orderIDetaild, long orerDetailId, long itemCode, double cost) {
        this.orderIDetaild = orderIDetaild;
        this.orerDetailId = orerDetailId;
        this.itemCode = itemCode;
        this.cost = cost;
    }
}
