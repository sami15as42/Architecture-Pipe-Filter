package project;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class HomeController extends Filtre implements Initializable {

	@FXML
	private TextArea ta;

	public void setP1(Pipe p) {
		_dataINPipe = p;
	}

	public void setP2(Pipe p) {
		_dataOUTPipe = p;
	}

	@FXML
	private ImageView image1;

	@FXML
	public void changeScreenButtonPushed(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/CaseFilmByActor.fxml"));
		Parent tableViewParent = (Parent) fxmlLoader.load();
		CaseFilmByActorController cntl = fxmlLoader.<CaseFilmByActorController>getController();
		cntl.setP1(_dataINPipe);
		cntl.setP2(_dataOUTPipe);
		Scene tableViewScene = new Scene(tableViewParent);
		tableViewScene.getStylesheets().add(getClass().getResource("dark_theme.css").toExternalForm());
		// This line gets the Stage information
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window = new Stage();
		window.setScene(tableViewScene);
		window.initModality(Modality.APPLICATION_MODAL);
		window.showAndWait();

	}

	@FXML
	public void FilmByActorPushed(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/CaseFilmByActor.fxml"));
		Parent tableViewParent = (Parent) fxmlLoader.load();
		CaseFilmByActorController cntl = fxmlLoader.<CaseFilmByActorController>getController();
		cntl.setP1(_dataINPipe);
		cntl.setP2(_dataOUTPipe);
		Scene tableViewScene = new Scene(tableViewParent);
		tableViewScene.getStylesheets().add(getClass().getResource("dark_theme.css").toExternalForm());
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window = new Stage();
		window.setScene(tableViewScene);
		window.initModality(Modality.APPLICATION_MODAL);
		window.showAndWait();

	}

	@FXML
	public void FilmByLocationPushed(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/CaseFilmByLocation.fxml"));
		Parent tableViewParent = (Parent) fxmlLoader.load();
		CaseFilmByLocationController cntl = fxmlLoader.<CaseFilmByLocationController>getController();
		cntl.setP1(_dataINPipe);
		cntl.setP2(_dataOUTPipe);

		Scene tableViewScene = new Scene(tableViewParent);
		tableViewScene.getStylesheets().add(getClass().getResource("dark_theme.css").toExternalForm());
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window = new Stage();
		window.setScene(tableViewScene);
		window.initModality(Modality.APPLICATION_MODAL);
		window.showAndWait();

	}

	@FXML
	public void FilmByClientPushed(ActionEvent event) throws IOException {

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/CaseFilmByClient.fxml"));
		Parent tableViewParent = (Parent) fxmlLoader.load();
		CaseFilmByClientController cntl = fxmlLoader.<CaseFilmByClientController>getController();
		cntl.setP1(_dataINPipe);
		cntl.setP2(_dataOUTPipe);

		Scene tableViewScene = new Scene(tableViewParent);
		tableViewScene.getStylesheets().add(getClass().getResource("dark_theme.css").toExternalForm());
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window = new Stage();
		window.setScene(tableViewScene);
		window.initModality(Modality.APPLICATION_MODAL);
		window.showAndWait();

	}

	@FXML
	public void SoldeByClientPushed(ActionEvent event) throws IOException {

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/CaseSoldeByClient.fxml"));
		Parent tableViewParent = (Parent) fxmlLoader.load();
		CaseSoldeByClientController cntl = fxmlLoader.<CaseSoldeByClientController>getController();
		cntl.setP1(_dataINPipe);
		cntl.setP2(_dataOUTPipe);

		Scene tableViewScene = new Scene(tableViewParent);
		tableViewScene.getStylesheets().add(getClass().getResource("dark_theme.css").toExternalForm());
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window = new Stage();
		window.setScene(tableViewScene);
		window.initModality(Modality.APPLICATION_MODAL);
		window.showAndWait();

	}

	public void OverdueArticlesPushed(ActionEvent event) throws IOException {

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/CaseOverDueArticle.fxml"));
		Parent tableViewParent = (Parent) fxmlLoader.load();
		CaseOverDueArticleController cntl = fxmlLoader.<CaseOverDueArticleController>getController();
		cntl.setP1(_dataINPipe);
		cntl.setP2(_dataOUTPipe);

		Scene tableViewScene = new Scene(tableViewParent);
		tableViewScene.getStylesheets().add(getClass().getResource("dark_theme.css").toExternalForm());
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window = new Stage();
		window.setScene(tableViewScene);
		window.initModality(Modality.APPLICATION_MODAL);
		window.showAndWait();
	}

	public void RentArticlePushed(ActionEvent event) throws IOException {

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/CaseRentArticle.fxml"));
		Parent tableViewParent = (Parent) fxmlLoader.load();
		CaseRentArticleController cntl = fxmlLoader.<CaseRentArticleController>getController();
		cntl.setP1(_dataINPipe);
		cntl.setP2(_dataOUTPipe);
		Scene tableViewScene = new Scene(tableViewParent);
		tableViewScene.getStylesheets().add(getClass().getResource("dark_theme.css").toExternalForm());
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window = new Stage();
		window.setScene(tableViewScene);
		window.initModality(Modality.APPLICATION_MODAL);
		window.showAndWait();

	}

	public void ReturnArticlePushed(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/CaseReturnArticle.fxml"));
		Parent tableViewParent = (Parent) fxmlLoader.load();
		CaseReturnArticleController cntl = fxmlLoader.<CaseReturnArticleController>getController();
		cntl.setP1(_dataINPipe);
		cntl.setP2(_dataOUTPipe);
		Scene tableViewScene = new Scene(tableViewParent);
		tableViewScene.getStylesheets().add(getClass().getResource("dark_theme.css").toExternalForm());
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window = new Stage();
		window.setScene(tableViewScene);
		window.initModality(Modality.APPLICATION_MODAL);
		window.showAndWait();

	}

	public void AddArticlePushed(ActionEvent event) throws IOException {

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/CaseAddArticle.fxml"));
		Parent tableViewParent = (Parent) fxmlLoader.load();
		CaseAddArticleController cntl = fxmlLoader.<CaseAddArticleController>getController();
		cntl.setP1(_dataINPipe);
		cntl.setP2(_dataOUTPipe);
		Scene tableViewScene = new Scene(tableViewParent);
		tableViewScene.getStylesheets().add(getClass().getResource("dark_theme.css").toExternalForm());
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window = new Stage();
		window.setScene(tableViewScene);
		window.initModality(Modality.APPLICATION_MODAL);
		window.showAndWait();

	}

	public void AddClientPushed(ActionEvent event) throws IOException {

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/CaseAddClient.fxml"));
		Parent tableViewParent = (Parent) fxmlLoader.load();
		CaseAddClientController cntl = fxmlLoader.<CaseAddClientController>getController();
		cntl.setP1(_dataINPipe);
		cntl.setP2(_dataOUTPipe);
		Scene tableViewScene = new Scene(tableViewParent);
		tableViewScene.getStylesheets().add(getClass().getResource("dark_theme.css").toExternalForm());
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window = new Stage();
		window.setScene(tableViewScene);
		window.initModality(Modality.APPLICATION_MODAL);
		window.showAndWait();

	}

	public void PenalizePushed(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/CasePenalizeClient.fxml"));
		Parent tableViewParent = (Parent) fxmlLoader.load();
		CasePenalizeClientController cntl = fxmlLoader.<CasePenalizeClientController>getController();
		cntl.setP1(_dataINPipe);
		cntl.setP2(_dataOUTPipe);
		Scene tableViewScene = new Scene(tableViewParent);
		tableViewScene.getStylesheets().add(getClass().getResource("dark_theme.css").toExternalForm());
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window = new Stage();
		window.setScene(tableViewScene);
		window.initModality(Modality.APPLICATION_MODAL);
		window.showAndWait();

	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {

		if (_dataINPipe != null) {
			System.out.print("init");
			ta.setText(_dataINPipe.dataOUT());
		}

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		execute();
	}

	@Override
	void execute() {
		// TODO Auto-generated method stub

	}

}
