package run;

import models.OrderDetails;
import models.OrderHeader;
import utils.DAOutils;

import java.util.ArrayList;
import java.util.List;

public class MainTestHibernate {
    public static DAOutils dao = new DAOutils();

    public static void main(String[] args) {
        OrderHeader orderHeader = new OrderHeader();
        orderHeader.setCustomerName("NEW");
        orderHeader.setCustomerAddress("Fesenko str.");

        List<OrderDetails> orderDetailsList=new ArrayList<>();
        OrderDetails orderDetails=new OrderDetails();
        orderDetails.setProductName("GOODS");
        orderDetails.setQty(10);
        orderDetails.setPrice(20000.00);
        orderDetailsList.add(orderDetails);

        orderDetailsList.forEach(details->orderHeader.addOrderDetails(details));

        dao.addEntity(orderHeader);

//        READ
        List<OrderHeader> orderHeaders = dao.getListEntity(OrderHeader.class);
        List<OrderDetails> tasksList = orderHeaders.get(0).getOrderDetails();
        System.out.println(orderHeader);
        orderHeaders = dao.geListEntity(OrderHeader.class, "customerName", "Gepard4444444");

//        UPDATE
//      Update via HQL
//        dao.updateEntityHQL(OrderHeader.class, "orderID", 83, "customerName", "HibernateTest");

        orderHeader.setOrderID(1);
        orderHeader.setCustomerName("Gepard9999");
        orderHeader.setCustomerAddress("9999999");
//        Update via update
        dao.updateEntity(orderHeader);



//        DELETE
//        orderHeader.setOrderID(152);
//        orderHeader.setCustomerName("Gepard4444444");
//        orderHeader.setCustomerAddress("Kharkov222222 str.");
//
//        dao.deleteEntity(orderHeader);
//
//        dao.deleteEntity(OrderHeader.class, "customerName", "Gepard4444444");
//
        dao.deleteEntity(OrderHeader.class, 18);
//
//        orderHeaders = dao.getListEntity(OrderHeader.class);

//        orderHeaders.forEach(orderHeaderEntity -> {
//            if (orderHeaderEntity.getCustomerName() != null) {
//                if (orderHeaderEntity.getCustomerName().contains("8888888888")) {
//                    dao.deleteEntity(orderHeaderEntity);
//                }
//            }
//        });

//        orderHeaders.stream()
//                .filter(orderHeaderEntity -> orderHeaderEntity.getCustomerName() != null
//                        && orderHeaderEntity.getCustomerName().contains("11111"))
//                .forEach(dao::deleteEntity);
    }


}
