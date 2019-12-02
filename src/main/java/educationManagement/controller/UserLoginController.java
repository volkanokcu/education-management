package educationManagement.controller;

import java.sql.SQLException;

import educationManagement.model.DBContext;
import educationManagement.model.entity.Admin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UserLoginController extends AbstractController {

    @FXML
    private Button joinBtn;
    @FXML
    private Button exitBtn;
    @FXML
    private Label statusLabel;
    @FXML
    private TextField userNo;
    @FXML
    private PasswordField password;
    
    
    @FXML
    void buttonEvent(ActionEvent event) throws NumberFormatException, SQLException {
    	
    	if (joinBtn == event.getSource()) {
    		String userType = userNo.getText().substring(0, 2);
    		switch (userType) {
    		case "10":
    			Admin admin = DBContext.getInstance().admins().isUserExist(Integer.parseInt(userNo.getText()), password.getText());
    			if(admin != null) {
    				final String fxml = "/educationManagement/view/AdminPanel.fxml";
    				try {
    					FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
    					Parent root = (Parent) loader.load();
    					AdminController adminController = loader.getController();
//    					adminController.setAdmin(admin);
//    					adminController.load();
    					Scene scene = new Scene(root);
    					Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
    					stage.setTitle(admin.getFirstName() + " " + admin.getLastName());
    					stage.setResizable(false);
    					stage.setScene(scene);
    					stage.show();
    				} catch (Exception e) {
    					e.printStackTrace();
    					System.out.println("fuck");
    				}
    				
    			} else {
    				statusLabel.setText("Kullanıcı Adı veya Şifre Yanlış");
    				userNo.setText("");
    				password.setText("");
    			}
    			break;
    			
    		case "40":
    			
    			break;
    			
    		case "50":
    			
    			break;

    		default:
    			statusLabel.setText("Kullanıcı Adı Bulunamadı");
    			break;
    		}

		}
    }
}