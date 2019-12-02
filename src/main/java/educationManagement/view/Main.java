package educationManagement.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("UserLogin.fxml")); //Fxml'in lokasyonunu belirtiyoruz.
		Parent root = (Parent) loader.load(); //Parent'a cast ediyoruz. Çünkü Scene parent alıyor.
		 Stage stage = new Stage();            //Pencerimizi oluşturuyoruz.
		 Scene scene = new Scene(root);		   //Penceremizde çalışacak arayüzü oluşturup içine parentimizi atıyoruz.
		 stage.setTitle("Kullanıcı Girişi");
		 stage.setScene(scene);				   //Penceremize arayüzümü ekliyoruz.
		 stage.show();						   //Penceremizi show methoduyla görünebilir yapıyoruz.
	}

}
