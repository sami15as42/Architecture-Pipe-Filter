package project;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 */
public class CaseSoldeByClientController extends Filtre implements Initializable {

	public void setP1(Pipe p) {
		_dataINPipe = p;
	}

	public void setP2(Pipe p) {
		_dataOUTPipe = p;
	}

	@FXML
	private TextArea ta;
	@FXML
	private TextField client;

	@FXML
	public void changeScreenButtonPushed(ActionEvent event) throws IOException {
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.close();
	}

	@FXML
	public void getPushed(ActionEvent event) throws IOException {
		_dataOUTPipe.dataIN("4@" + client.getText());
		ta.setText(_dataINPipe.dataOUT());
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

	@Override
	void execute() {
		// TODO Auto-generated method stub

	}

}
