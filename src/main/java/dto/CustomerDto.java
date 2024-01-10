package dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {    private long custId;
    private long custName;
    private String custEmail;
    private String custContact;

}
