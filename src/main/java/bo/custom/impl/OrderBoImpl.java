package bo.custom.impl;

import bo.custom.OrderBo;
import dao.DaoFactory;
import dao.custom.ItemDao;
import dao.custom.OrderDao;
import dao.util.DaoType;
import dto.CustomerDto;
import dto.OrderDto;

import javax.persistence.criteria.Order;
import java.sql.SQLException;
import java.util.List;

public class OrderBoImpl implements OrderBo {
    OrderDao orderDao = DaoFactory.getInstance().getDao(DaoType.ORDER);


    @Override
    public boolean saveOrder(OrderDto dto) throws SQLException, ClassNotFoundException {
        return orderDao.save((Order) dto);
    }

    @Override
    public String generateId() throws SQLException, ClassNotFoundException {

                return "D001";


    }
}
