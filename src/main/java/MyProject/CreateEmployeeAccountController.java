package MyProject;

import MyProject.Model.Account;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreateEmployeeAccountController {
    @FXML private MainController mainController;
    @FXML private TextField create_email_field;
    @FXML private TextField create_password_field;
    @FXML private TextField create_first_name_field;
    @FXML private TextField create_last_name_field;
    @FXML private TextField create_country_field;
    @FXML private TextField create_city_field;
    @FXML private TextField create_street_field;
    @FXML private TextField create_postal_code_field;
    @FXML private TextField create_phone_number_field;

    private Alert alert;
    private Stage stage;
    private Account newAccount = new Account();

    @FXML
    private void initialize(){
        mainController = MainController.getMainControllerInstance();
    }

    @FXML
    private void createNewEmployeeAccount(ActionEvent event){
        if(getUserInput()){
            if(mainController.findAccountByEmail(newAccount.getEmail())){
                alert = new Alert(Alert.AlertType.ERROR, "Account with this email already exist", ButtonType.OK);
                alert.showAndWait();
                if (alert.getResult() == ButtonType.OK) {
                    create_email_field.clear();
                    create_email_field.requestFocus();
                }
            }else{
                newAccount.setAccess(MainController.EMPLOYEE_ACCESS_LEVEL);
                if(mainController.sendAccountToDatabase(newAccount)){
                    alert = new Alert(Alert.AlertType.CONFIRMATION, "Account has been successfully created", ButtonType.OK);
                    alert.showAndWait();
                    if (alert.getResult() == ButtonType.OK) {
                        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                        stage.close();
                    }
                }else{
                    alert = new Alert(Alert.AlertType.ERROR, "Account could not be created", ButtonType.OK);
                    alert.showAndWait();
                }
            }
        }else{
            alert = new Alert(Alert.AlertType.WARNING, "All fields must be filled.", ButtonType.OK);
            alert.showAndWait();
        }
    }

    @FXML
    private void cancelEmployeeAccountCreation(ActionEvent event){
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }

    /**
     * Read data from text fields and saves it to newUser instance in MainController.
     */
    private boolean getUserInput(){
        if (create_email_field.getText().isEmpty()){ return false;
        }else{ newAccount.setEmail(create_email_field.getText()); }
        if (create_password_field.getText().isEmpty()){ return false;
        }else{ newAccount.setPassword(create_password_field.getText()); }
        if (create_first_name_field.getText().isEmpty()){ return false;
        }else{ newAccount.setFirstName(create_first_name_field.getText()); }
        if (create_last_name_field.getText().isEmpty()){ return false;
        }else{ newAccount.setLastName(create_last_name_field.getText()); }
        if (create_country_field.getText().isEmpty()){ return false;
        }else{ newAccount.setCountry(create_country_field.getText()); }
        if (create_city_field.getText().isEmpty()){ return false;
        }else{ newAccount.setCity(create_city_field.getText()); }
        if (create_street_field.getText().isEmpty()){ return false;
        }else{ newAccount.setStreet(create_street_field.getText()); }
        if (create_postal_code_field.getText().isEmpty()){ return false;
        }else{ newAccount.setPostCode(create_postal_code_field.getText()); }
        if (create_phone_number_field.getText().isEmpty()){ return false;
        }else{ newAccount.setPhoneNumber(create_phone_number_field.getText()); }

        return true;
    }
}
