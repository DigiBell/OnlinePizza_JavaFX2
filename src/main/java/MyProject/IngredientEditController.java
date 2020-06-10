package MyProject;

import MyProject.Model.Ingredient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class IngredientEditController {
    @FXML private MainController mainController;
    @FXML private ListView ingredient_edit_list_view;
    @FXML private TextField ingredient_edit_description_field;
    @FXML private TextField ingredient_edit_quantity_field;
    @FXML private TextField ingredient_edit_units_field;

    private Stage stage;

    @FXML private void initialize(){
//        mainController = MainController.getMainControllerInstance();
//        Ingredient ingredient = mainController.getIngredientToEdit();
//        ObservableList<String> ingredientLines = FXCollections.observableArrayList();
//        ingredientLines.add("ingtredientId= " + ingredient.getId());
//        ingredientLines.add("name= " + ingredient.getName());
//        ingredientLines.add("description= " + ingredient.getDescription());
//        ingredientLines.add("quantity= " + ingredient.getQuantity());
//        ingredientLines.add("units= " + ingredient.getUnits());
//        ingredient_edit_list_view.setItems(ingredientLines);
    }

    @FXML
    private void confirmChange(ActionEvent event){
//        if(ingredient_edit_description_field.getText().trim().length() != 0){
//            String description = ingredient_edit_description_field.getText();
//            mainController.getIngredientToEdit().setDescription(description);
//        }
//        if(ingredient_edit_quantity_field.getText().trim().length() != 0){
//            String quantity = ingredient_edit_quantity_field.getText();
//            mainController.getIngredientToEdit().setQuantity(Integer.getInteger(quantity));
//        }
//        if(ingredient_edit_units_field.getText().trim().length() != 0){
//            String units = ingredient_edit_units_field.getText();
//            mainController.getIngredientToEdit().setUnits(units);
//        }
//        //send to database //editIngredient(ingredientId,
//        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//        stage.close();
    }

    @FXML
    private void cancelChange(ActionEvent event){
//        mainController.setIngredientToEdit(null);
//        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//        stage.close();
    }
}
