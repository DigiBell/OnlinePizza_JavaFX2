package MyProject;

import MyProject.Model.Order;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ManagerOrdersTabController {
    @FXML private MainController mainController;
    @FXML private TableView<Order> ordersTable;
    @FXML private TableColumn<Order, String> orders_order_id_column;
    @FXML private TableColumn<Order, String> orders_user_id_column;
    @FXML private TableColumn<Order, String> orders_date_column;
    @FXML private TableColumn<Order, String[]> orders_products_column;
    @FXML private TableColumn<Order, String> orders_total_price_column;
    @FXML private DatePicker orders_from_date_picker;
    @FXML private DatePicker orders_to_date_picker;

    private Alert alert;

    @FXML
    private void initialize(){
        mainController = MainController.getMainControllerInstance();
        orders_order_id_column.setCellValueFactory(new PropertyValueFactory<>("OrderId"));
        orders_user_id_column.setCellValueFactory(new PropertyValueFactory<>("UserId"));
        orders_date_column.setCellValueFactory(new PropertyValueFactory<>("Date"));
        orders_products_column.setCellValueFactory(new PropertyValueFactory<>("OrderLines"));
        orders_total_price_column.setCellValueFactory(new PropertyValueFactory<>("TotalPrice"));
    }

    @FXML
    private void showAll(ActionEvent event){//show orders
        LocalDate dateFrom = orders_from_date_picker.getValue();
        LocalDate dateTo = orders_to_date_picker.getValue();
        ordersTable.getItems().clear();
        if(dateFrom != null && dateTo != null){
            mainController.getOrdersFromDatabase(Date.from(dateFrom.atStartOfDay(ZoneId.systemDefault()).toInstant()),
                    Date.from(dateTo.atStartOfDay(ZoneId.systemDefault()).toInstant()));

        }else{
            mainController.getOrdersFromDatabase();
        }
        ordersTable.getItems().addAll(mainController.getOrderList());
    }
}
