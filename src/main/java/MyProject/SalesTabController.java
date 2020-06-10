package MyProject;

import MyProject.Model.Sale;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SalesTabController {
    @FXML private MainController mainController;
    @FXML private TableView<Sale> salesTable;
    @FXML private TableColumn<Sale, String> sales_product_id_column;
    @FXML private TableColumn<Sale, String> sales_product_name_column;
    @FXML private TableColumn<Sale, String> sales_order_id_column;
    @FXML private TableColumn<Sale, String> sales_date_column;
    @FXML private TableColumn<Sale, String> sales_quantity_column;
    @FXML private TableColumn<Sale, String> sales_price_column;
    @FXML private DatePicker sales_from_date_picker;
    @FXML private DatePicker sales_to_date_picker;

    private Alert alert;

    @FXML
    private void initialize(){
        mainController = MainController.getMainControllerInstance();
        sales_product_id_column.setCellValueFactory(new PropertyValueFactory<>("ProductId"));
        sales_product_name_column.setCellValueFactory(new PropertyValueFactory<>("ProductName"));
        sales_order_id_column.setCellValueFactory(new PropertyValueFactory<>("OrderId"));
        sales_date_column.setCellValueFactory(new PropertyValueFactory<>("Date"));
        sales_quantity_column.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
        sales_price_column.setCellValueFactory(new PropertyValueFactory<>("Price"));
    }

    @FXML
    private void showAll(ActionEvent event){//1
        //show all
        LocalDate dateFrom = sales_from_date_picker.getValue();
        LocalDate dateTo = sales_to_date_picker.getValue();
        //getOrderList(dateFrom, dateTo);
//        salesTable.getItems().clear();
//        salesTable.getItems().addAll(sortSales(dateFrom, dateTo));
    }

    @FXML
    private void showDetails(ActionEvent event){
        //show choosen item as text in a popup window
        //or add a listView on the side
    }

//    private List<Sale> sortSales(LocalDate from, LocalDate to){
//        List<Sale> sales = mainController.generateTestDataSales();
//        List<Sale> sortedSales = new ArrayList<>();
//        if(from != null && to != null){
//            for (Sale sale: sales) {
//                if(LocalDate.parse(sale.getDate()).isBefore(to) && LocalDate.parse(sale.getDate()).isAfter(from)){
//                    sortedSales.add(sale);
//                }
//            }
//            return sortedSales;
//        }else if(sales == null){
//            return sortedSales;
//        }else{
//            alert = new Alert(Alert.AlertType.ERROR, "Date can't be empty.", ButtonType.OK);
//            alert.showAndWait();
//        }
//        return sortedSales;
//    }
}
