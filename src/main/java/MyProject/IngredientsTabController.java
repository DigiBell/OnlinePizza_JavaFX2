package MyProject;

import MyProject.Model.Ingredient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class IngredientsTabController {
    @FXML private MainController mainController;
    @FXML private TableView<Ingredient> ingredientsTable;
    @FXML private TableColumn<Ingredient, String> ingredients_ingredient_id_column;
    @FXML private TableColumn<Ingredient, String> ingredients_name_column;
    @FXML private TableColumn<Ingredient, String> ingredients_description_column;
    @FXML private TableColumn<Ingredient, String> ingredients_quantity_column;
    @FXML private TableColumn<Ingredient, String> ingredients_units_column;
    private Alert alert;

    @FXML
    private void initialize(){
        mainController = MainController.getMainControllerInstance();
        ingredients_ingredient_id_column.setCellValueFactory(new PropertyValueFactory<>("Id"));
        ingredients_name_column.setCellValueFactory(new PropertyValueFactory<>("Name"));
        ingredients_description_column.setCellValueFactory(new PropertyValueFactory<>("Description"));
        ingredients_quantity_column.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
        ingredients_units_column.setCellValueFactory(new PropertyValueFactory<>("Units"));
    }

    @FXML
    private void showAll(ActionEvent event){//1
        //show all products in the table
        ingredientsTable.getItems().clear();
        ingredientsTable.getItems().addAll(mainController.generateTestDataIngredients());
    }

    @FXML
    private void editDetails(ActionEvent event) throws Exception{
        //show choosen item as text in a popup window
        //or add a listView on the side
        Ingredient ingredient = ingredientsTable.getSelectionModel().getSelectedItem();
        if(ingredient == null){
            alert = new Alert(Alert.AlertType.WARNING, "Choose an ingredient from table.", ButtonType.OK);
            alert.showAndWait();
        }else{
            //mainController.setIngredientToEdit(ingredient);
            Parent root = FXMLLoader.load(getClass().getResource("../../resources/MyProject/IngredientEditView.fxml"));
            Stage smallStage = new Stage();
            smallStage.setTitle("Ingredient details");
            smallStage.setScene(new Scene(root, 400, 600));
            root.requestFocus();
            smallStage.showAndWait();
        }
    }

}
