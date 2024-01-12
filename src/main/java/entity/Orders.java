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
public class Orders {
    @Id
    private long orderId;
    private String customerName;
    private String note;
    private String date;
    private String status;
    private double additionFee;
    private double totFee;

    public Orders(long orderId, String customerName, String note, String date, String status, double additionFee, double totFee) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.note = note;
        this.date = date;
        this.status = status;
        this.additionFee = additionFee;
        this.totFee = totFee;
    }
}
