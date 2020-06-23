package application.UI;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import application.ListeLocations;
import application.ListePanneaux;
import application.Location;
import application.Panneau;
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
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class AfficherPanneaux extends Stage implements Initializable {
	
	private final int windowWidth = 720;
	private final int windowHeight = 480;
	
	@FXML
	private TableView<application.Panneau> tablePanneaux;
	@FXML
	private TableColumn<application.Panneau, String> panneaux_id;
	@FXML
	private TableColumn<application.Panneau, String> panneaux_tarif;
	@FXML
	private TableColumn<application.Panneau, String> panneaux_visibilite;
	@FXML
	private TableColumn<application.Panneau, String> panneaux_detail;
	@FXML
	private TableColumn<application.Panneau, String> panneaux_largeur;
	@FXML
	private TableColumn<application.Panneau, String> panneaux_hauteur;
	@FXML
	private TableColumn<application.Panneau, Button> panneaux_edit;
	@FXML
	private TableColumn<application.Panneau, Button> panneaux_delete;
	@FXML
	private Button bnClose;
	@FXML
	private Button panneaux_add;
	private static ObservableList<application.Panneau> panneaux = FXCollections.observableArrayList();
	
	
	public AfficherPanneaux() {
		try {
		    // Localisation du fichier FXML.
			final URL fxmlURL = getClass().getResource("listePanneaux.fxml"); 
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
		    this.setTitle("Panoramix - Affichage des panneaux");
		    this.getIcons().add(new Image(MainWindow.class.getResourceAsStream("ressources/img/logo.png")));
    }
    
	@Override
    public void initialize(URL location, ResourceBundle resources) {
    	refresh();
    	
		// On initialise les colonnes.
		panneaux_id.setCellValueFactory(new PropertyValueFactory<>("id"));
		panneaux_tarif.setCellValueFactory(new PropertyValueFactory<>("tarif"));
		panneaux_visibilite.setCellValueFactory(new PropertyValueFactory<>("visibilite"));
		panneaux_hauteur.setCellValueFactory(new PropertyValueFactory<>("hauteur"));
		panneaux_largeur.setCellValueFactory(new PropertyValueFactory<>("largeur"));
		panneaux_detail.setCellValueFactory(new PropertyValueFactory<>("details"));
		panneaux_edit.setCellValueFactory(new PropertyValueFactory<>("edit"));
		panneaux_delete.setCellValueFactory(new PropertyValueFactory<>("delete"));
		editableCols();
		tablePanneaux.setItems(panneaux);
		tablePanneaux.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		setupDelete();
		
		// Bouton fermer.
		bnClose.setOnAction(e -> {

			this.close();
		});
		
		// Bouton ajouter.
		panneaux_add.setOnAction(e -> {
			// Afficher fenêtre d'ajout d'un panneau.
			Stage window = new AjouterPanneau();
        	window.show();
        	close();
		});
		
    }

	/**
	 * Méthode pour raffraichir la liste des panneaux
	 */
	public static void refresh() {
		// On ajout tous les panneaux dans l'observable list
		panneaux.clear();
		for (Panneau p : ListePanneaux.getListePanneaux()) {
	        p.edit = new Button("Mettre à jour");
	        p.delete = new Button("Supprimer");
			panneaux.add(p);
		}
	}
	
	public void recharger_window() {
		this.close();
		Stage window = new AfficherPanneaux();
		window.show();
	}
	
	private void editableCols() {
		panneaux_id.setCellFactory(TextFieldTableCell.forTableColumn());
		panneaux_id.setOnEditCommit(e-> {
			e.getTableView().getItems().get(e.getTablePosition().getRow()).setId(e.getNewValue());
			MainWindow.actualiserCarte();
		});
		
		panneaux_tarif.setCellFactory(TextFieldTableCell.forTableColumn());
		panneaux_tarif.setOnEditCommit(e-> {
			e.getTableView().getItems().get(e.getTablePosition().getRow()).setTarif(e.getNewValue());
			MainWindow.actualiserCarte();
		});
		
		panneaux_visibilite.setCellFactory(TextFieldTableCell.forTableColumn());
		panneaux_visibilite.setOnEditCommit(e-> {
			e.getTableView().getItems().get(e.getTablePosition().getRow()).setVisibilite(e.getNewValue());
			MainWindow.actualiserCarte();
		});
		
		panneaux_detail.setCellFactory(TextFieldTableCell.forTableColumn());
		panneaux_detail.setOnEditCommit(e-> {
			e.getTableView().getItems().get(e.getTablePosition().getRow()).setDetails(e.getNewValue());
			MainWindow.actualiserCarte();
		});
		
		panneaux_hauteur.setCellFactory(TextFieldTableCell.forTableColumn());
		panneaux_hauteur.setOnEditCommit(e-> {
			e.getTableView().getItems().get(e.getTablePosition().getRow()).setHauteur(e.getNewValue());
			MainWindow.actualiserCarte();
		});
		
		panneaux_largeur.setCellFactory(TextFieldTableCell.forTableColumn());
		panneaux_largeur.setOnEditCommit(e-> {
			e.getTableView().getItems().get(e.getTablePosition().getRow()).setLargeur(e.getNewValue());
			MainWindow.actualiserCarte();
		});
		
		tablePanneaux.setEditable(true);
	}
	
	public static void setupDelete() {
		for (Panneau p : panneaux) {
			p.delete.setOnAction(e-> {
				gererDelete(p);
			});
		}
	}

	/**
	 * Methode pour gerer la suppresion d'un panneau
	 * 
	 * @param p le panneau a supprimé
	 */
	public static void gererDelete(Panneau p) {
		// On demande confirmation a l'utilisateur.
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation");
		alert.setHeaderText("");
		alert.setContentText("Etes vous sur de vouloir supprimer ce panneau : " + p.getId());

		Optional<ButtonType> result = alert.showAndWait();
		// On regarde sa réponse.
		if (result.get() == ButtonType.OK) {
			System.out.println("suppression de : " + p.toString());
			// Pour toutes locations du panneau P.
			for (Location l : ListeLocations.getListeLocations()) {
				// On les supprimes
				if (l.getPanneau().equals(p)) {
					ListeLocations.removeLocation(l.getId());
					break;
				}
			}
			// On le supprime de l'observable liste.
			// on le supprime des fichiers.
			ListePanneaux.removePanneau(Integer.parseInt(p.getId()));
			panneaux.remove(p);
			// On l'enlève de la carte.
			//a faire
		}

	}
}
