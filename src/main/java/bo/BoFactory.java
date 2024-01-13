package bo;

import bo.custom.impl.CustomerBoImpl;
import bo.custom.impl.ItemBoImpl;
import bo.custom.impl.OrderBoImpl;
import bo.custom.impl.UserBoImpl;
import dao.util.BoType;

public class BoFactory {
    private static BoFactory boFactory;
    private BoFactory(){

    }
    public static BoFactory getInstance(){
        return boFactory!=null? boFactory:(boFactory=new BoFactory());
    }

    public <T extends SuperBo>T getBo(BoType type){
        switch (type){
            case ITEM: return (T) new ItemBoImpl();
            case CUSTOMER: return (T) new CustomerBoImpl();
            case ORDER: return (T) new OrderBoImpl();
            case USER: return (T) new UserBoImpl();

        }
        return null;
    }
}
