package dto.tm;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CustomerTm {
    private String custId;
    private long custName;
    private String custEmail;
    private String custContact;
}
