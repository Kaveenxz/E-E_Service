package bo.custom;

import bo.SuperBo;
import dto.CustomerDto;
import dto.OrderDto;
import net.bytebuddy.implementation.bind.annotation.Super;

import java.sql.SQLException;
import java.util.List;

public interface OrderBo extends SuperBo {
    boolean saveOrder(OrderDto dto) throws SQLException, ClassNotFoundException;
    String generateId() throws SQLException, ClassNotFoundException;

}
