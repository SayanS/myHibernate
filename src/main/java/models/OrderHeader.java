package models;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Transactional
public class OrderHeader implements Serializable {
    private Integer orderID=null;
    private String customerName;
    private String customerAddress;
    private List<OrderDetails> orderDetails;

    public OrderHeader() {
    }

    @Id
//    @GenericGenerator(name = "generator", strategy = "increment")
//    @GeneratedValue(generator = "generator")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="orderID",updatable = true)
    public Integer getOrderID() {
        return orderID;
    }
    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }

    @OneToMany(mappedBy = "orderHeader", cascade = {CascadeType.ALL})
    public List<OrderDetails> getOrderDetails() {
        return orderDetails;
    }
    public void setOrderDetails(List<OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public void addOrderDetails(OrderDetails orderDetails) {
        orderDetails.setOrderHeader(this);
        if(this.orderDetails==null){
            this.orderDetails=new ArrayList<OrderDetails>();
        }
        this.orderDetails.add(orderDetails);
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderHeader that = (OrderHeader) o;
        if (orderID != that.orderID) return false;
        if (customerName != null ? !customerName.equals(that.customerName) : that.customerName != null) return false;
        if (customerAddress != null ? !customerAddress.equals(that.customerAddress) : that.customerAddress != null)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = orderID;
        result = 31 * result + (customerName != null ? customerName.hashCode() : 0);
        result = 31 * result + (customerAddress != null ? customerAddress.hashCode() : 0);
        return result;
    }
}
