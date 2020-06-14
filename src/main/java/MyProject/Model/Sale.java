package MyProject.Model;

import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;
import java.util.Date;

public class Sale {
    private ObjectId id;
    //@BsonProperty(value="produc_id")
    private int productId;
   //@BsonProperty(value="order_id")
    private int orderId;
    private String productName;
    private String productCategory;
    private Date date;
    private int quantity;
    private int price;

    public Sale(){}

    public Sale(int productId, int orderId, String productName, String productCategory, Date date, int quantity, int price) {
        this.productId = productId;
        this.orderId = orderId;
        this.productName = productName;
        this.productCategory = productCategory;
        this.date = date;
        this.quantity = quantity;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "id=" + id +
                ", productId=" + productId +
                ", orderId=" + orderId +
                ", productName='" + productName + '\'' +
                ", productCategory='" + productCategory + '\'' +
                ", date=" + date +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
