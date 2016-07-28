package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LoanCalculatorMenu implements Initializable
{
	@FXML
	MenuBar mBar;

	@FXML
	Menu fileMenu;

	@FXML
	MenuItem close;

	@FXML
	Menu helpMenu;

	@FXML
	MenuItem about;

	public void closeClick(ActionEvent event)
	{
		Platform.exit();
		System.exit(0);
	}

	public void aboutClick(ActionEvent event)
	{
		Stage stage = new Stage();
		stage.initStyle(StageStyle.UTILITY);
		try
		{
			AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("AboutPopup.fxml"));
			Scene scene = new Scene(new Group(pane));
			stage.setTitle("About This");
			stage.getIcons().add(new Image("/images/calcIcon.png"));
			stage.setScene(scene);
			stage.showAndWait();
		} catch (Exception e)
		{
			e.printStackTrace();
		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		assert mBar != null : "fx:id=\"mBar\" was not injected: check your FXML file 'LoanView.fxml'.";
		assert fileMenu != null : "fx:id=\"fileMenu\" was not injected: check your FXML file 'LoanView.fxml'.";
		assert helpMenu != null : "fx:id=\"helpMenu\" was not injected: check your FXML file 'LoanView.fxml'.";
		assert close != null : "fx:id=\"close\" was not injected: check your FXML file 'LoanView.fxml'.";
		assert about != null : "fx:id=\"about\" was not injected: check your FXML file 'LoanView.fxml'.";
	}

}
