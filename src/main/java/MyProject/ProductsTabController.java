package MyProject;

import MyProject.Model.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ProductsTabController {
    @FXML private MainController mainController;

    @FXML private TableView<Product> productsTable;
    @FXML private TableColumn<Product, String> products_product_id_column;
    @FXML private TableColumn<Product, String> products_category_column;
    @FXML private TableColumn<Product, String> products_name_column;
    @FXML private TableColumn<Product, String> products_size_column;
    @FXML private TableColumn<Product, String> products_quantity_column;
    @FXML private TableColumn<Product, String> products_unit_price_column;

    @FXML
    private void initialize(){
        mainController = MainController.getMainControllerInstance();
        products_product_id_column.setCellValueFactory(new PropertyValueFactory<>("Id"));
        products_category_column.setCellValueFactory(new PropertyValueFactory<>("Category"));
        products_name_column.setCellValueFactory(new PropertyValueFactory<>("Name"));
        products_size_column.setCellValueFactory(new PropertyValueFactory<>("Size"));
        products_quantity_column.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
        products_unit_price_column.setCellValueFactory(new PropertyValueFactory<>("Price"));
    }

    @FXML
    private void showAll(ActionEvent event){//1
        //show all products in the table
        productsTable.getItems().clear();
        productsTable.getItems().addAll(mainController.generateTestData());
    }

    @FXML
    private void showDetails(ActionEvent event){
        //show choosen item as text in a popup window
        //or add a listView on the side
    }
}
