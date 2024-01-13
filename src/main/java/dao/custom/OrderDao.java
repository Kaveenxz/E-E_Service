package dao.custom;

import dao.CrudDao;
import dto.OrderDto;

import javax.persistence.criteria.Order;
import java.sql.SQLException;

public interface OrderDao extends CrudDao<Order> {
    OrderDto getLastOrder() throws SQLException, ClassNotFoundException;

}
