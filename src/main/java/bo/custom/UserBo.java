package bo.custom;

import bo.SuperBo;
import dao.SuperDao;
import dto.UserDto;

import java.sql.SQLException;
import java.util.List;

public interface UserBo extends SuperBo {
    boolean saveUser(UserDto dto) throws SQLException, ClassNotFoundException;
    boolean updateUser(UserDto dto) throws SQLException, ClassNotFoundException;
    boolean deleteUser(String id) throws SQLException, ClassNotFoundException;
    List<UserDto> allUsers() throws SQLException, ClassNotFoundException;
    String hashPassword(String enteredPassword);
    boolean updateUserPassword(String userId, String newPassword) throws SQLException, ClassNotFoundException;
    UserDto getUserByEmail(String email) throws SQLException, ClassNotFoundException;

}
