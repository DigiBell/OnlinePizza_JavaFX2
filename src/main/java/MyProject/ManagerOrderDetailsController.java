package MyProject;

import MyProject.Model.Order;
import MyProject.Model.OrderLine;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class ManagerOrderDetailsController {
    @FXML private MainController mainController;
    @FXML private ListView order_details_list_view;
    private Stage stage;

    @FXML
    private void initialize(){
        mainController = MainController.getMainControllerInstance();
        Order order = mainController.getOrder();
        ObservableList<String> orderLines = FXCollections.observableArrayList();
        orderLines.add("orderId= " + order.getOrderId());
        orderLines.add("userId= " + order.getUserId());
        orderLines.add("date= " + order.getDate());
        orderLines.add("orderProductLines= ");//change
        for (OrderLine opl: order.getOrderLines()) {
            orderLines.add(opl.toString());
        }
        orderLines.add("totalPrice= " + order.getTotalPrice());
        //orderLines.add("comment= " + order.getComment());

        order_details_list_view.setItems(orderLines);
    }

    @FXML
    private void closeView(ActionEvent event){
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }
}
