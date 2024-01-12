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
public class Customer {
    @Id
    private String custId;
    private String custName;
    private String custEmail;
    private String custContact;

    public Customer(String custId, String custName, String custEmail, String custContact) {
        this.custId = custId;
        this.custName = custName;
        this.custEmail = custEmail;
        this.custContact = custContact;
    }
}
