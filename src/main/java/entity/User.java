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
public class User {
    @Id
    private String userId;
    private String userName;
    private String email;
    private String password;
    private String userType;

    public User(String userId, String userName, String email, String password, String userType) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.userType = userType;
    }
    public User(String userId, String userName, String email,String userType) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.userType = userType;
    }
}
