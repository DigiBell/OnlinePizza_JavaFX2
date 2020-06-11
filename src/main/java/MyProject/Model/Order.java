package MyProject.Model;

import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;
import java.util.Date;
import java.util.List;


public class Order {
    private ObjectId id;
    @BsonProperty(value="order_id")
    private int orderId;
    @BsonProperty(value="user_id")
    private int userId; //or Account user with all user information
    private Date date;
    private List<OrderLine> orderLines;
    private double totalPrice;

    public Order() { }

    public Order(int orderId, int userId, Date date, List<OrderLine> orderLines, double totalPrice) {
        this.orderId = orderId;
        this.userId = userId;
        this.date = date;
        this.orderLines = orderLines;
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", userId=" + userId +
                ", date=" + date +
                ", orderLines=" + orderLines +
                ", totalPrice=" + totalPrice +
                '}';
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
