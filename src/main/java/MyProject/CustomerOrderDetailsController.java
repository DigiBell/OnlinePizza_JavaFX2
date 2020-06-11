package MyProject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class CustomerOrderDetailsController {
    @FXML private MainController mainController;

    @FXML private ListView cart_list_view;

    @FXML
    private void initialize(){
        mainController = MainController.getMainControllerInstance();
        cart_list_view.getItems();
    }

    @FXML
    private void confirm(ActionEvent event){

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }
}
