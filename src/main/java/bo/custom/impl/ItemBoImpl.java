package bo.custom.impl;

import bo.custom.ItemBo;
import dao.DaoFactory;
import dao.custom.ItemDao;
import dao.util.DaoType;
import dto.ItemDto;
import entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemBoImpl implements ItemBo {

    ItemDao itemDao = DaoFactory.getInstance().getDao(DaoType.ITEM);
    @Override
    public boolean saveItem(ItemDto dto) throws SQLException, ClassNotFoundException {
        return itemDao.save(new Item(
                dto.getId(),
                dto.getName(),
                dto.getCategory(),
                dto.getQty(),
                dto.getStatus()
        ));
    }

    @Override
    public boolean updateItem(ItemDto dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean deleteItem(String id) throws SQLException, ClassNotFoundException {
        return itemDao.delete(id);
    }

    @Override
    public List<ItemDto> allItems() throws SQLException, ClassNotFoundException {
        List<Item> entityList = itemDao.getAll();
        List<ItemDto> dtoList = new ArrayList<>();
        for (Item item: entityList) {
            dtoList.add(new ItemDto(
                    item.getItemId(),
                    item.getName(),
                    item.getCategory(),
                    item.getQty(),
                    item.getStatus()
            ));
        }
        return dtoList;
    }

}