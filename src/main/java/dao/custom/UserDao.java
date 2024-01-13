package dao.custom;

import dao.CrudDao;
import dto.UserDto;
import entity.User;

import java.sql.SQLException;

public interface UserDao extends CrudDao<User> {
    User getById(String userId) throws SQLException, ClassNotFoundException;
    User getUserByEmail(String email) throws SQLException, ClassNotFoundException;

}
