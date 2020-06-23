package application.UI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Client;
import application.Date;
import application.ListeClients;
import application.ListeLocations;
import application.ListePanneaux;
import application.Location;
import application.Panneau;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class AjouterLocation extends Stage implements Initializable {
	
	private final int windowWidth = 450;
	private final int windowHeight = 200;
	
	@FXML
	private ComboBox<Panneau> comboBoxP;
	@FXML
	private ComboBox<Client> comboBoxC;
	@FXML
	private Button buttonP;
	@FXML
	private Button buttonC;
	@FXML
	private TextField textDuree;
	@FXML
	private Button valider;
	@FXML
	private Button annuler;
	
	private static ObservableList<application.Panneau> panneaux = FXCollections.observableArrayList();
	private static ObservableList<application.Client> clients = FXCollections.observableArrayList();
	
	public AjouterLocation() {
		try {
		    // Localisation du fichier FXML.
			final URL fxmlURL = getClass().getResource("ajouterLocation.fxml"); 
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
		    this.setTitle("Panoramix - Ajouter une location");
		    this.getIcons().add(new Image(MainWindow.class.getResourceAsStream("ressources/img/logo.png")));
    }

	@Override
    public void initialize(URL location, ResourceBundle resources) {
		refresh();
		// On désative le bouton valider si les champs sont vides.
		
		/*
		valider.disableProperty().bind(
				Bindings.isEmpty(comboBoxP.getValue()).or(Bindings.isEmpty(comboBoxC.getProperties()))
		);
		*/
		
		DropShadow shadow = new DropShadow();
		
		// Ajout de l'ombre quand le curseur de la souris est sur le bouton et changement du curseur.
		valider.addEventHandler(MouseEvent.MOUSE_ENTERED, 
		    new EventHandler<MouseEvent>() {
		        @Override public void handle(MouseEvent e) {
		        	valider.setEffect(shadow);
		        	valider.setCursor(Cursor.HAND);
		        }
		});
		
		valider.addEventHandler(MouseEvent.MOUSE_EXITED, 
		    new EventHandler<MouseEvent>() {
		        @Override public void handle(MouseEvent e) {
		        	valider.setEffect(null);
		        }
		});
		
		// Ajout de l'ombre quand le curseur de la souris est sur le bouton et changement du curseur.
		annuler.addEventHandler(MouseEvent.MOUSE_ENTERED, 
		    new EventHandler<MouseEvent>() {
		        @Override public void handle(MouseEvent e) {
		        	annuler.setEffect(shadow);
		        	annuler.setCursor(Cursor.HAND);
		        }
		});
		
		annuler.addEventHandler(MouseEvent.MOUSE_EXITED, 
		    new EventHandler<MouseEvent>() {
		        @Override public void handle(MouseEvent e) {
		        	annuler.setEffect(null);
		        }
		});
		
		annuler.setOnAction(event -> {
			System.out.println("Annulation de l'ajout d'une location");
        	close();
        });
		
		buttonP.setOnAction(event -> {
			Stage window = new AjouterPanneau();
			window.show();
		});
		
		buttonC.setOnAction(event -> {
			Stage window = new AjouterClient();
			window.show();
		});
		
		valider.setOnAction(event -> {
			Client cli = (Client) comboBoxC.getValue();
			Panneau p = (Panneau) comboBoxP.getValue();
			String duree = textDuree.getText();
			Date dateAujd = Date.dateAujourdhui();
			
			Location loc = new Location(cli, p, Integer.parseInt(duree), dateAujd);
			ListeLocations.addLocation(loc);
			System.out.println("ajouté");
			Stage window = new AfficherLocations();
			window.show();
			close();
		});
	}
	
	public void recharger_window() {
		this.close();
		Stage window = new AfficherPanneaux();
		window.show();
	}
	
	public void refresh() {
		// On ajoute tous les panneaux dans l'observable list.
				panneaux.clear();
				for (Panneau p : ListePanneaux.getListePanneaux()) {
					panneaux.add(p);
				}
	   // On ajoute tous les clients.
				clients.clear();
				for (Client c : ListeClients.getListeClients()) {
					clients.add(c);
				}
				comboBoxC.setItems(clients);
				comboBoxP.setItems(panneaux);
	}
}
