package MyProject;

import MyProject.Model.Order;
import MyProject.Model.OrderLine;
import MyProject.Model.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class CustomerCustomPizzaController {
    @FXML private MainController mainController;

    @FXML private CheckBox add_extra_cmc_checkbox;
    @FXML private CheckBox add_extra_other_checkbox;
    @FXML private TextArea comment_text_area;

    @FXML
    private void initialize(){
        mainController = MainController.getMainControllerInstance();
    }

    @FXML
    private void confirm(ActionEvent event){
        boolean extra_cmc = false;
        if(add_extra_cmc_checkbox.isSelected()){
            extra_cmc = true;
        }

        boolean extra_other= false;
        if(add_extra_other_checkbox.isSelected()){
            extra_other = true;
        }

        addPizzaToCart(extra_cmc, extra_other, comment_text_area.getText());

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }

    private void addPizzaToCart( boolean extra_cmc, boolean extra_other, String comment){
        Product productSelected = mainController.getProductSelected();
        String details = " (" + productSelected.getSize() + productSelected.getUnits() + ") ";
        int price = productSelected.getPrice();
        if(extra_cmc){
            details = details + "(Extra Ost/Kött/Kyckling";
            for (Product product: mainController.getProductsExtraCMC()) {
                System.out.println(product.toString());

                if(product.getSize().equals(productSelected.getSize())){
                    System.out.println("Size:" + productSelected.getSize());

                    price = price + product.getPrice();
                    details = details + product.getPrice() + "kr) ";
                }
            }
        }
        if(extra_other){
            details = details + "(Extra Other";
            for (Product product: mainController.getProductsExtraOther()) {
                System.out.println(product.toString());

                if(product.getSize().equals(productSelected.getSize())){
                    System.out.println("Size:" + productSelected.getSize());

                    price = price + product.getPrice();
                    details = details + product.getPrice() + "kr) ";
                }
            }
        }
        OrderLine orderLine = new OrderLine();
        orderLine.setProductId(productSelected.getProductId());
        orderLine.setProductName(productSelected.getName());
        orderLine.setProductCategory(productSelected.getCategory());
        orderLine.setDetails(details);
        orderLine.setComment(comment);
        orderLine.setTotalPrice(price);

        String cartLine = "" + orderLine.getProductName() + orderLine.getDetails() + "price: " + orderLine.getTotalPrice();

        mainController.setOrderLine(orderLine);
        mainController.getOrderLineList().add(orderLine);
        mainController.setCartLine(cartLine);
    }
}
