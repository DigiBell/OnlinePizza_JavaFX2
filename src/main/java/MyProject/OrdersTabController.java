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
import java.util.ArrayList;
import java.util.List;

public class OrdersTabController {
    @FXML private MainController mainController;
    @FXML private TableView<Order> ordersTable;
    @FXML private TableColumn<Order, String> orders_order_id_column;
    @FXML private TableColumn<Order, String> orders_user_id_column;
    @FXML private TableColumn<Order, String> orders_date_column;
    @FXML private TableColumn<Order, String[]> orders_products_column;
    @FXML private TableColumn<Order, String> orders_total_price_column;
    @FXML private TableColumn<Order, String> orders_comment_column;
    @FXML private DatePicker orders_from_date_picker;
    @FXML private DatePicker orders_to_date_picker;

    private Alert alert;


    @FXML
    private void initialize(){
        mainController = MainController.getMainControllerInstance();
        orders_order_id_column.setCellValueFactory(new PropertyValueFactory<>("OrderId"));
        orders_user_id_column.setCellValueFactory(new PropertyValueFactory<>("UserId"));
        orders_date_column.setCellValueFactory(new PropertyValueFactory<>("Date"));
        orders_products_column.setCellValueFactory(new PropertyValueFactory<>("OrderProductLines"));
        orders_total_price_column.setCellValueFactory(new PropertyValueFactory<>("TotalPrice"));
        orders_comment_column.setCellValueFactory(new PropertyValueFactory<>("Comment"));
    }

    @FXML
    private void showAll(ActionEvent event){//1
        //show orders
        LocalDate dateFrom = orders_from_date_picker.getValue();
        LocalDate dateTo = orders_to_date_picker.getValue();
        //getOrderList(dateFrom, dateTo);
//        ordersTable.getItems().clear();
//        ordersTable.getItems().addAll(sortOrders(dateFrom, dateTo));
    }

    @FXML
    private void showDetails(ActionEvent event) throws  Exception{
        //show choosen item as text in a popup window
        //or add a listView on the side
        Order order = ordersTable.getSelectionModel().getSelectedItem();
        if(order == null){
            alert = new Alert(Alert.AlertType.WARNING, "Choose an order from table.", ButtonType.OK);
            alert.showAndWait();
        }else{
            mainController.setOrder(order);
            Parent root = FXMLLoader.load(getClass().getResource("../../resources/MyProject/OrderDetailsView.fxml"));
            Stage smallStage = new Stage();
            smallStage.setTitle("Order details");
            smallStage.setScene(new Scene(root, 500, 500));
            root.requestFocus();
            smallStage.showAndWait();
        }
    }

//    private List<Order> sortOrders(LocalDate from, LocalDate to){
//
//        List<Order> sortedOrders = new ArrayList<>();
//        if(from != null && to != null){
//            for (Order order: orders) {
//                if(LocalDate.parse(order.getDate()).isBefore(to) && LocalDate.parse(order.getDate()).isAfter(from)){
//                    sortedOrders.add(order);
//                }
//            }
//            return sortedOrders;
//        }else if(orders == null){
//            return sortedOrders;
//        }else{
//            alert = new Alert(Alert.AlertType.WARNING, "Date can't be empty.", ButtonType.OK);
//            alert.showAndWait();
//        }
//        return sortedOrders;
//    }
}
