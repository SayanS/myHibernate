package models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.io.Serializable;

@Entity
@Transactional
@Table(name = "OrderDetails", schema = "Orders")
public class OrderDetails implements Serializable {
    @Id
    @GenericGenerator(name = "generator", strategy = "increment")
    @GeneratedValue(generator = "generator")
    @Column(updatable = true)
    private Integer orderDetailsID=null;

    @ManyToOne
    @JoinColumn(name = "orderID", updatable = true)
    private OrderHeader orderHeader;

    private String productName;
    private Integer qty;
    private Double price;

    public OrderDetails() {    }

    @Column(name = "orderDetailsID")
    public Integer getOrderDetailsID() {
        return orderDetailsID;
    }

    public void setOrderDetailsID(OrderHeader orderHeader) {
        this.orderHeader = orderHeader;
    }

    public OrderHeader getOrderHeader() {
        return orderHeader;
    }

    public void setOrderHeader(OrderHeader orderHeader) {
        this.orderHeader = orderHeader;
    }

    @Column(name = "productName")
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Column(name = "qty")
    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    @Column(name = "price")
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDetails that = (OrderDetails) o;
        if (orderDetailsID != that.orderDetailsID) return false;
        if (orderHeader != null ? !orderHeader.equals(that.orderHeader) : that.orderHeader != null) return false;
        if (productName != null ? !productName.equals(that.productName) : that.productName != null) return false;
        if (qty != null ? !qty.equals(that.qty) : that.qty != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = orderDetailsID;
        result = 31 * result + (orderHeader != null ? orderHeader.hashCode() : 0);
        result = 31 * result + (productName != null ? productName.hashCode() : 0);
        result = 31 * result + (qty != null ? qty.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }
}
