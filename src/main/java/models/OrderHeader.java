package models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.transaction.Transactional;
import java.io.Serializable;

@Entity
@Transactional
public class OrderHeader implements Serializable {
    @Id
    @GenericGenerator(name="generator", strategy="increment")
    @GeneratedValue(generator="generator")
    @Column(updatable = true)
    private Integer orderID=null;
    private String customerName;
    private String customerAddress;

    public OrderHeader(){}

    public Integer getOrderID() {
        return orderID;
    }
    public void setOrderID(Integer orderID) {
        this.orderID=orderID;
    }
//    @Basic
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

//    @Basic
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
