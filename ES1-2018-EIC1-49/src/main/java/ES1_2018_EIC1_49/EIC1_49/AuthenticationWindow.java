package ES1_2018_EIC1_49.EIC1_49;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class AuthenticationWindow extends Application{
	

	public static void main(String[] args) {
	Application.launch(args);

	}

	@Override
	public void start(Stage primaryStage)  {
		Parent mainPane;
		try {
			mainPane = FXMLLoader.load(getClass().getResource("Auth.fxml"));
			Scene scene = new Scene(mainPane);
			
			primaryStage.setTitle("Authentication");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
