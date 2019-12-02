package educationManagement;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Driver extends Application {
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/educationManagement/view/UserLogin.fxml")); //Fxml'in lokasyonunu belirtiyoruz.
		Parent root = (Parent) loader.load(); //Parent'a cast ediyoruz. Çünkü Scene parent alıyor.
		 Scene scene = new Scene(root);		   //Penceremizde çalışacak arayüzü oluşturup içine parentimizi atıyoruz.
		primaryStage.setTitle("Kullanıcı Girişi");
		primaryStage.setScene(scene); // Penceremize arayüzümü ekliyoruz.
		primaryStage.show();				   //Penceremizi show methoduyla görünebilir yapıyoruz.
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
