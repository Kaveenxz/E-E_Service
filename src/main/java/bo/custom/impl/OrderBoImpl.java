package bo.custom.impl;

import bo.custom.OrderBo;
import dao.DaoFactory;
import dao.custom.OrderDao;
import dao.util.DaoType;
import dto.OrderDto;
import entity.Orders;

import javax.persistence.criteria.Order;
import java.sql.SQLException;

public class OrderBoImpl implements OrderBo {
    OrderDao orderDao = DaoFactory.getInstance().getDao(DaoType.ORDER);

    @Override
    public boolean saveOrder(OrderDto dto) throws SQLException, ClassNotFoundException {
        Orders orderEntity = new Orders(dto.getOrderId(), dto.getDate());
        return orderDao.save((Order) orderEntity);
    }

    @Override
    public String generateId() throws SQLException, ClassNotFoundException {
        return "D001";
    }
}
