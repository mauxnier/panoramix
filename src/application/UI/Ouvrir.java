package application.UI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Ouvrir extends Stage implements Initializable{
	
	private final int windowWidth = 300;
	private final int windowHeight = 100;
	
	@FXML
	private Button bnSave;
	@FXML
	private TextField textNom;
	
	public Ouvrir() {
		try {
		    // Localisation du fichier FXML.
			final URL fxmlURL = getClass().getResource("ouvrir.fxml"); 
			final FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
			fxmlLoader.setController(this);
			
		    // Chargement du FXML.
		    final BorderPane root = (BorderPane) fxmlLoader.load();
		    
		    // Création de la scène.
		      final Scene scene = new Scene(root, windowWidth, windowHeight);
		      this.setScene(scene);
		      this.setResizable(false);
		    } 
		catch (IOException ex) {
		    	System.err.println("Erreur au chargement: " + ex);
		    }
		    this.setTitle("Panoramix - Ouvrir");
		    this.getIcons().add(new Image(MainWindow.class.getResourceAsStream("ressources/img/logo.png")));
	}
	
	@Override
    public void initialize(URL location, ResourceBundle resources) {
		
		bnSave.disableProperty().bind(
				Bindings.isEmpty(textNom.textProperty())
		);
		
		bnSave.setOnAction(e-> {
			application.Main.ouvrirListes(textNom.getText() + ".pano");;
			MainWindow.actualiserCarte();
			close();
		});
	}
}
