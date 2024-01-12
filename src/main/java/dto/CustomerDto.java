package dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
    private String custId;
    private String custName;
    private String custEmail;
    private String custContact;

}
