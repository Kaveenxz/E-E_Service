package dao.custom.impl;

import dao.custom.UserDao;
import dao.util.HibernateUtil;
import dto.UserDto;
import entity.Item;
import entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public boolean save(User entity) throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
        return true;    }

    @Override
    public boolean update(User entity) throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        User user = session.find(User.class, entity.getUserId());
        user.setUserName(entity.getUserName());
        user.setEmail(entity.getEmail());
        user.setUserType(entity.getUserType());
        user.setPassword(entity.getPassword());

        session.save(user);
        transaction.commit();
        session.close();
        return true;    }

    @Override
    public boolean delete(String value) throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(session.find(User.class,value));
        transaction.commit();
        session.close();
        return true;    }

    @Override
    public List<User> getAll() throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.getSession();
        Query query = session.createQuery("FROM User");
        List<User> list = query.list();
        session.close();
        return list;
    }

    @Override
    public User getById(String userId) throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.getSession();
        return session.find(User.class, userId);
    }

    @Override
    public User getUserByEmail(String email) throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.getSession();
        Query<User> query = session.createQuery("FROM User WHERE email = :email", User.class);
        query.setParameter("email", email);
        return query.uniqueResult();
    }

}




