package dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private long userId;
    private String userName;
    private String email;
    private String password;
    private String userType;
}
