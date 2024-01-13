package bo.custom.impl;

import bo.custom.UserBo;
import dao.DaoFactory;
import dao.custom.UserDao;
import dao.util.DaoType;
import dto.UserDto;
import entity.User;

import javax.persistence.NonUniqueResultException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserBoImpl implements UserBo {
    UserDao userDao = DaoFactory.getInstance().getDao(DaoType.USER);

    @Override
    public boolean saveUser(UserDto dto) throws SQLException, ClassNotFoundException {

        String password = hashPassword(dto.getPassword());
        return userDao.save(new User(
                dto.getUserId(),
                dto.getUserName(),
                dto.getEmail(),
                password,
                dto.getUserType()
        ));
    }

    @Override
    public boolean updateUser(UserDto dto) throws SQLException, ClassNotFoundException {
        String password = hashPassword(dto.getPassword());
        return userDao.update(new User(
                dto.getUserId(),
                dto.getUserName(),
                dto.getEmail(),
                password,
                dto.getUserType()

        ));
    }

    @Override
    public boolean deleteUser(String id) throws SQLException, ClassNotFoundException {
        return userDao.delete(id);
    }

    @Override
    public List<UserDto> allUsers() throws SQLException, ClassNotFoundException {
        List<User> entityList = userDao.getAll();
        List<UserDto> dtoList = new ArrayList<>();
        for (User user : entityList) {
            // Instead of passing the hashed password, pass the original password
            dtoList.add(new UserDto(
                    user.getUserId(),
                    user.getUserName(),
                    user.getEmail(),
                    user.getPassword(),  // Original password
                    user.getUserType()
            ));
        }
        return dtoList;
    }


    public String hashPassword(String plainTextPassword) {
        String encryptedPassword = null;
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(plainTextPassword.getBytes());
            byte[] bytes = m.digest();
            StringBuilder s = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            encryptedPassword = s.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return encryptedPassword;
    }

    @Override
    public UserDto getUserById(String userId) throws SQLException, ClassNotFoundException {
        User user = userDao.getById(userId);
        if (user != null) {
            return new UserDto(
                    user.getUserId(),
                    user.getUserName(),
                    user.getEmail(),
                    user.getPassword(),  // Original password
                    user.getUserType()
            );
        }
        return null;
    }

    @Override
    public UserDto getUserByEmail(String email) throws SQLException, ClassNotFoundException {
        try {
            User user = userDao.getUserByEmail(email);

            if (user != null) {
                return new UserDto(
                        user.getUserId(),
                        user.getUserName(),
                        user.getEmail(),
                        user.getPassword(),
                        user.getUserType()
                );
            } else {
                return null;
            }
        } catch (NonUniqueResultException e) {
            e.printStackTrace();
            // Handle the exception, log it, or throw a custom exception
            return null;
        }
    }

}
