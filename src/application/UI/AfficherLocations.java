package application.UI;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import application.ListeLocations;
import application.Location;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class AfficherLocations extends Stage implements Initializable {
	
	private final int windowWidth = 720;
	private final int windowHeight = 480;
	
	@FXML
	private TableView<application.Location> tableLocations;
	@FXML
	private TableColumn<application.Location, String> location_id;
	@FXML
	private TableColumn<application.Panneau, String> location_idPanneau;
	@FXML
	private TableColumn<application.Client, String> location_idClient;
	@FXML
	private TableColumn<application.Location, String> location_duree;
	@FXML
	private TableColumn<application.Location, String> location_dateDebut;
	@FXML
	private TableColumn<application.Location, Button> location_delete;
	@FXML
	private Button bnClose;
	@FXML
	private Button location_add;
	public static ObservableList<application.Location> locations = FXCollections.observableArrayList();
	
	public AfficherLocations() {
		try {
		    // Localisation du fichier FXML.
			final URL fxmlURL = getClass().getResource("listeLocations.fxml"); 
			final FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
			fxmlLoader.setController(this);
			
		    // Chargement du FXML.
		    final BorderPane root = (BorderPane) fxmlLoader.load();
		    
		    // Création de la scène.
		      final Scene scene = new Scene(root, windowWidth, windowHeight);
		      this.setScene(scene);
		    } catch (IOException ex) {
		    	System.err.println("Erreur au chargement: " + ex);
		    }
		    this.setTitle("Panoramix - Affichage des clients");
		    this.getIcons().add(new Image(MainWindow.class.getResourceAsStream("ressources/img/logo.png")));

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		refresh();
		// Init colonnes
		initTable();
		tableLocations.setItems(locations);
		tableLocations.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		setupDelete();
		// Bouton fermer.
		bnClose.setOnAction(e -> {

			this.close();
		});
		
		// Bouton ajouter.
		location_add.setOnAction(e -> {
			// Afficher fenêtre d'ajout d'une location.
			Stage window = new AjouterLocation();
        	window.show();
        	close();
		});
		
	}
	
	private void refresh() {
		// On ajout toutes les locations dans l'observable list
				locations.clear();
				for (Location l : ListeLocations.getListeLocations()) {
					l.delete = new Button("Supprimer");
					locations.add(l);
					//System.out.println("test");
				}
	}
	
	private void initTable() {
		initCols();
	}
	
	private void initCols() {
		location_id.setCellValueFactory(new PropertyValueFactory<>("id"));
		location_idPanneau.setCellValueFactory(new PropertyValueFactory<>("panneau"));
		location_idClient.setCellValueFactory(new PropertyValueFactory<>("client"));
		location_duree.setCellValueFactory(new PropertyValueFactory<>("duree"));
		location_dateDebut.setCellValueFactory(new PropertyValueFactory<>("dateDebut"));
		location_delete.setCellValueFactory(new PropertyValueFactory<>("delete"));
	}
	
	public static void setupDelete() {
		for (Location l : locations) {
			l.delete.setOnAction(e-> {
				gererDelete(l);
			});
		}
	}
	
	/**
	 * Methode pour gérer la suppresion d'une location
	 * 
	 * @param l la location à supprimer
	 */
	public static void gererDelete(Location l) {
		// On demande confirmation a l'utilisateur.
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation");
		alert.setHeaderText("");
		alert.setContentText("Etes vous sur de vouloir supprimer cette location : " + l.getId());

		Optional<ButtonType> result = alert.showAndWait();
		// On regarde sa réponse.
		if (result.get() == ButtonType.OK) {
			System.out.println("suppression de : " + l.toString());
			// On le supprime de l'observable liste.
			// on le supprime des fichiers.
			ListeLocations.removeLocation(l.getId());
			locations.remove(l);
		}
		setupDelete();

	}
}
