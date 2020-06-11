package MyProject.Model;

import org.bson.codecs.pojo.annotations.BsonProperty;

public class OrderLine {
    @BsonProperty(value="product_id")
    private int productId;
    private String productName;
    private String productCategory;
    private String details;
    private String comment;
    private int totalPrice;

    public OrderLine() { }

    public OrderLine(int productId, String productName, String productCategory, String details, String comment, int totalPrice) {
        this.productId = productId;
        this.productName = productName;
        this.productCategory = productCategory;
        this.details = details;
        this.comment = comment;
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "OrderLine{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productCategory='" + productCategory + '\'' +
                ", details='" + details + '\'' +
                ", comment='" + comment + '\'' +
                ", totalPrice=" + totalPrice +
                '}';
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
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

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
