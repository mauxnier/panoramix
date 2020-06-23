package application.UI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Client;
import application.ListeClients;
import javafx.beans.binding.Bindings;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class AjouterClient extends Stage implements Initializable {
	
	private final int windowWidth = 250;
	private final int windowHeight = 200;
	
	@FXML
	private Button bnClose;
	@FXML
	private Button bnSave;
	@FXML
	private TextField textNom;
	@FXML
	private TextField textAdresse;
	@FXML
	private TextField textTelephone;
	@FXML
	private TextField textEmail;
	
	public AjouterClient() {
		try {
		    // Localisation du fichier FXML.
			final URL fxmlURL = getClass().getResource("ajouterClient.fxml"); 
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
		    this.setTitle("Panoramix - Ajouter un client");
		    this.getIcons().add(new Image(MainWindow.class.getResourceAsStream("ressources/img/logo.png")));
    }

	@Override
    public void initialize(URL location, ResourceBundle resources) {
		// On désative le bouton valider si les champs sont vides.
				bnSave.disableProperty().bind(
						Bindings.isEmpty(textNom.textProperty()).or(Bindings.isEmpty(textEmail.textProperty()))
				);
				DropShadow shadow = new DropShadow();
				
				// Ajout de l'ombre quand le curseur de la souris est sur le bouton et changement du curseur
				bnSave.addEventHandler(MouseEvent.MOUSE_ENTERED, 
				    new EventHandler<MouseEvent>() {
				        @Override public void handle(MouseEvent e) {
				        	bnSave.setEffect(shadow);
				        	bnSave.setCursor(Cursor.HAND);
				        }
				});
				
				bnSave.addEventHandler(MouseEvent.MOUSE_EXITED, 
				    new EventHandler<MouseEvent>() {
				        @Override public void handle(MouseEvent e) {
				        	bnSave.setEffect(null);
				        }
				});
				
				// Ajout de l'ombre quand le curseur de la souris est sur le bouton et changement du curseur
				bnClose.addEventHandler(MouseEvent.MOUSE_ENTERED, 
				    new EventHandler<MouseEvent>() {
				        @Override public void handle(MouseEvent e) {
				        	bnClose.setEffect(shadow);
				        	bnClose.setCursor(Cursor.HAND);
				        }
				});
				
				bnClose.addEventHandler(MouseEvent.MOUSE_EXITED, 
				    new EventHandler<MouseEvent>() {
				        @Override public void handle(MouseEvent e) {
				        	bnClose.setEffect(null);
				        }
				});
				
				bnClose.setOnAction(event -> {
					System.out.println("Annulation de l'ajout d'un client");
		        	close();
		        });
				
				bnSave.setOnAction(event -> {
					String nom = textNom.getText();
					String adresse = textAdresse.getText();
					String tel = textTelephone.getText();
					String email = textEmail.getText();
					Client cli = new Client(nom, adresse, tel, email);
					ListeClients.addClient(cli);
					System.out.println("ajouté");
					Stage window = new AfficherClients();
					window.show();
					close();
				});
	}
}
