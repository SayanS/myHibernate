package run;

import models.OrderHeader;
import utils.DAOutils;

import java.util.List;

public class MainTestHibernate {
    public static DAOutils dao=new DAOutils();
    public static void main(String[] args){
        OrderHeader orderHeader = new OrderHeader();
        orderHeader.setCustomerName("Gepard4444444");
        orderHeader.setCustomerAddress("Kharkov222222 str.");

        dao.addEntity(orderHeader);

        List<OrderHeader> orderHeaders=dao.getListEntity(OrderHeader.class);
        System.out.println(orderHeader);


        dao.updateEntityHQL(OrderHeader.class, "orderID", 83, "customerName", "HibernateTest");

       // orderHeader.setOrderID(82);
        orderHeader.setCustomerName("XXXXXXXXX");
        orderHeader.setCustomerAddress("YYYYYYYYYYY");
        dao.updateEntity(OrderHeader.class, 86);

        int i=0;

    }
}
