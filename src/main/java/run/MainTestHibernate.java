package run;

import models.OrderHeader;
import utils.DAOutils;

import java.util.List;

public class MainTestHibernate {
    public static DAOutils dao = new DAOutils();

    public static void main(String[] args) {
        OrderHeader orderHeader = new OrderHeader();
        orderHeader.setCustomerName("Gepard4444444");
        orderHeader.setCustomerAddress("Kharkov222222 str.");

        dao.addEntity(orderHeader);

//        READ
        List<OrderHeader> orderHeaders = dao.getListEntity(OrderHeader.class);
        System.out.println(orderHeader);
        orderHeaders = dao.geListEntity(OrderHeader.class, "customerName", "Gepard4444444");

//        UPDATE
//      Update via HQL
        dao.updateEntityHQL(OrderHeader.class, "orderID", 83, "customerName", "HibernateTest");

        orderHeader.setOrderID(153);
        orderHeader.setCustomerName("Gepard9999");
        orderHeader.setCustomerAddress("9999999");
//        Update via update
        dao.updateEntity(orderHeader);

//        DELETE
        orderHeader.setOrderID(152);
        orderHeader.setCustomerName("Gepard4444444");
        orderHeader.setCustomerAddress("Kharkov222222 str.");

        dao.deleteEntity(orderHeader);

        dao.deleteEntity(OrderHeader.class, "customerName", "Gepard4444444");

        dao.deleteEntity(OrderHeader.class, 31);

        orderHeaders = dao.getListEntity(OrderHeader.class);

//        orderHeaders.forEach(orderHeaderEntity -> {
//            if (orderHeaderEntity.getCustomerName() != null) {
//                if (orderHeaderEntity.getCustomerName().contains("8888888888")) {
//                    dao.deleteEntity(orderHeaderEntity);
//                }
//            }
//        });

        orderHeaders.stream()
                .filter(orderHeaderEntity -> orderHeaderEntity.getCustomerName() != null
                        && orderHeaderEntity.getCustomerName().contains("33333"))
                .forEach(dao::deleteEntity);
    }


}
