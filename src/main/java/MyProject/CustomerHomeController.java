package MyProject;

import MyProject.Model.OrderLine;
import MyProject.Model.Product;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

public class CustomerHomeController {
    @FXML private MainController mainController;

    @FXML private Label welcome_lable;
    @FXML private ListView cart_list_view;
    @FXML private TableView<Product> productTable;
    @FXML private TableColumn<Product, String> category_column;
    @FXML private TableColumn<Product, String> name_column;
    @FXML private TableColumn<Product, String> description_column;
    @FXML private TableColumn<Product, String> size_column;
    @FXML private TableColumn<Product, String> price_column;

    @FXML private CheckBox add_extra_cmc_checkbox;
    @FXML private CheckBox add_extra_other_checkbox;
    @FXML private TextArea comment_text_area;

    private Product productSelected;
    private OrderLine orderLine;

    /**
     * Initialise layout.
     * Set welcome label to show Account name
     * Initialise columns in tableView
     * Calls method parseProductList() to get data for table.
     */
    @FXML
    private void initialize(){
        mainController = MainController.getMainControllerInstance();
        welcome_lable.setText("Welcome, " + mainController.getLoginAccount().getFirstName() + "!");
        category_column.setCellValueFactory(new PropertyValueFactory<>("Category"));
        name_column.setCellValueFactory(new PropertyValueFactory<>("Name"));
        description_column.setCellValueFactory(new PropertyValueFactory<>("Description"));
        size_column.setCellValueFactory(new PropertyValueFactory<>("Size"));
        price_column.setCellValueFactory(new PropertyValueFactory<>("Price"));
        mainController.getProductsFromDatabase();

        productTable.getItems().addAll(mainController.getProductList());
    }

    /**
     * Method is called when "add to cart" button is clicked.
     * Adds chosen product to cart.
     * @param event that triggered method call.
     */
    @FXML
    private void add(ActionEvent event)throws Exception{
        productSelected = productTable.getSelectionModel().getSelectedItem();
        if(productSelected.getCategory().equals("Pizza")){
            customizePizza();
        }else{
            //addNonPizzaToCart(productSelected);
        }
    }

    private void customizePizza()throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("CustomerHomeCustomPizzaView.fxml"));
        Stage smallStage = new Stage();
        smallStage.setScene(new Scene(root, 300, 500));
        root.requestFocus();
        smallStage.showAndWait();
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
    }

    private void addPizzaToCart( boolean extra_cmc, boolean extra_other, String comment){
        String details = " (" + productSelected.getSize() + productSelected.getUnits() + ") ";
        int price = productSelected.getPrice();
        if(extra_cmc){
            details = details + "(Extra Ost/Kött/Kyckling";
            for (Product product: mainController.getProductsExtraCMC()) {
                if(product.getSize() == productSelected.getSize()){
                    price = price + product.getPrice();
                    details = details + product.getPrice() + "kr) ";
                }
            }
        }
        if(extra_other){
            details = details + "(Extra Other";
            for (Product product: mainController.getProductsExtraOther()) {
                if(product.getSize() == productSelected.getSize()){
                    price = price + product.getPrice();
                    details = details + product.getPrice() + "kr) ";
                }
            }
        }

        orderLine.setProductId(productSelected.getProductId());
        orderLine.setProductName(productSelected.getName());
        orderLine.setDetails(details);
        orderLine.setComment(comment);
        orderLine.setTotalPrice(price);

        String cartLine = "" + orderLine.getProductName() + orderLine.getDetails() + "price: " + orderLine.getTotalPrice();
        addLineToCart(cartLine, orderLine.getTotalPrice());
    }

    /**
     * Adds a line with product details along with the total price to cart listView.
     * @param cartLine is a string that holds information about chosen product
     * @param price total price for a product
     */
    @SuppressWarnings("unchecked")
    private void addLineToCart(String cartLine, double price){
        mainController.setTotalPrice(mainController.getTotalPrice() + price);
        String totalPriceLine = "Total price: " + mainController.getTotalPrice() + " kr";
        int size;
        if(!(cart_list_view.getItems().isEmpty())){
            mainController.setCartLineList(cart_list_view.getItems());
            size = mainController.getCartLineList().size();
            mainController.getCartLineList().remove(size-1);
            mainController.getCartLineList().add(size-1, cartLine);
        }else{
            mainController.getCartLineList().add(cartLine);
        }
        mainController.getCartLineList().add(totalPriceLine);
        cart_list_view.setItems(mainController.getCartLineList());
    }

    /**
     * Method is called when "log out" button is clicked.
     * Changes scene to loginView.
     * @param event that triggered method call.
     * @throws Exception if the resource is null.
     */
    @FXML
    private void homeLogOut(ActionEvent event)throws Exception{
        productTable.getItems().clear();
        //cart_list_view.getItems().clear();
        mainController.clearAllData();
        //open login window
        Parent root = FXMLLoader.load(getClass().getResource("LoginView.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, mainController.MAIN_STAGE_WIDTH, mainController.MAIN_STAGE_HEIGHT));
        root.requestFocus();
        stage.show();
    }

    /**
     * Method is called when "buy" button is clicked.
     * Changes scene to confirmOrderView.
     * @param event that triggered method call.
     * @throws Exception if the resource is null.
     */
    @FXML
    private void placeOrder(ActionEvent event)throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("UserHomeOrderView.fxml"));
        Stage smallStage = new Stage();
        smallStage.setScene(new Scene(root, 350, 200));
        root.requestFocus();
        smallStage.showAndWait();
    }

    /**
     * Method is called when "cancel" button is clicked.
     * Empties cart and set total price to 0.
     * @param event that triggered method call.
     */
    @SuppressWarnings("unchecked")
    @FXML
    private void cancelOrder(ActionEvent event){ //cancel button
        cart_list_view.getItems().clear();
        mainController.clearCart();
    }

}
