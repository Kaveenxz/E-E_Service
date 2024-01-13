package dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String userId;
    private String userName;
    private String email;
    private String password;
    private String userType;

    public UserDto(String userId, String userName, String email, String userType) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.userType = userType;
    }
}
