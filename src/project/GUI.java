package project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GUI extends Application {

	private static Pipe _dataINPipe;
	private static Pipe _dataOUTPipe;

	public void setP1(Pipe p) {
		_dataINPipe = p;
	}

	public void setP2(Pipe p) {
		_dataOUTPipe = p;
	}

	@Override
	public void start(Stage stage) throws Exception {

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Home.fxml"));

		Parent root = (Parent) fxmlLoader.load();
		HomeController cntl = fxmlLoader.<HomeController>getController();
		cntl.setP1(_dataINPipe);
		cntl.setP2(_dataOUTPipe);

		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("dark_theme.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}

}
