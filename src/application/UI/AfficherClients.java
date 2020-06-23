package application.UI;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import application.Client;
import application.ListeClients;
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
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class AfficherClients extends Stage implements Initializable {
	
	private final int windowWidth = 720;
	private final int windowHeight = 480;
	
	@FXML
	private TableView<application.Client> tableClients;
	@FXML
	private TableColumn<application.Client, String> clients_id;
	@FXML
	private TableColumn<application.Client, String> clients_nom;
	@FXML
	private TableColumn<application.Client, String> clients_adresse;
	@FXML
	private TableColumn<application.Client, String> clients_telephone;
	@FXML
	private TableColumn<application.Client, String> clients_email;
	@FXML
	private TableColumn<application.Client, Button> clients_edit;
	@FXML
	private TableColumn<application.Client, Button> clients_delete;
	@FXML
	private Button bnClose;
	@FXML
	private Button clients_add;
	private static ObservableList<application.Client> clients = FXCollections.observableArrayList();
	
	public AfficherClients() {
		try {
		    // Localisation du fichier FXML.
			final URL fxmlURL = getClass().getResource("listeClients.fxml"); 
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
				tableClients.setItems(clients);
				tableClients.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
				
				setupDelete();
				
				// Bouton fermer.
				bnClose.setOnAction(e -> {

					this.close();
				});
				
				// Bouton ajouter.
				clients_add.setOnAction(e -> {
					// Afficher fenêtre d'ajout d'un panneau.
					Stage window = new AjouterClient();
		        	window.show();
		        	close();
				});
    }
	
	private void initTable() {
		initCols();
	}
	
	private void initCols() {
		clients_id.setCellValueFactory(new PropertyValueFactory<>("id"));
		clients_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
		clients_adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
		clients_telephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));
		clients_email.setCellValueFactory(new PropertyValueFactory<>("email"));
		clients_edit.setCellValueFactory(new PropertyValueFactory<>("edit"));
		clients_delete.setCellValueFactory(new PropertyValueFactory<>("delete"));
		
		editableCols();
	}
	
	private void editableCols() {
		clients_id.setCellFactory(TextFieldTableCell.forTableColumn());
		clients_id.setOnEditCommit(e-> {
			e.getTableView().getItems().get(e.getTablePosition().getRow()).setId(e.getNewValue());
		});
		
		clients_nom.setCellFactory(TextFieldTableCell.forTableColumn());
		clients_nom.setOnEditCommit(e-> {
			e.getTableView().getItems().get(e.getTablePosition().getRow()).setNom(e.getNewValue());
		});
		
		clients_adresse.setCellFactory(TextFieldTableCell.forTableColumn());
		clients_adresse.setOnEditCommit(e-> {
			e.getTableView().getItems().get(e.getTablePosition().getRow()).setAdresse(e.getNewValue());
		});
		
		clients_telephone.setCellFactory(TextFieldTableCell.forTableColumn());
		clients_telephone.setOnEditCommit(e-> {
			e.getTableView().getItems().get(e.getTablePosition().getRow()).setTelephone(e.getNewValue());
		});
		
		clients_email.setCellFactory(TextFieldTableCell.forTableColumn());
		clients_email.setOnEditCommit(e-> {
			e.getTableView().getItems().get(e.getTablePosition().getRow()).setEmail(e.getNewValue());
		});
		
		tableClients.setEditable(true);
	}
	
	public void recharger_window() {
		this.close();
		Stage window = new AfficherPanneaux();
		window.show();
	}
	
	/**
	 * Méthode pour raffraichir la liste des clients
	 */
	public static void refresh() {
		// On ajout tous les clients dans l'observable list
		clients.clear();
		for (Client c : ListeClients.getListeClients()) {
	        c.edit = new Button("Mettre à jour");
	        c.delete = new Button("Supprimer");
			clients.add(c);
			//System.out.println("test");
		}
	}

	/**
	 * Methode pour gerer l'edition d'un client
	 *
	 * @param c le client à editer
	 */
	public static void gererEdit(Client c) {

		/* On ouvre la fenetre de modification avec en parametre le panneau */
		//ouvrirFenetreModifPanneau(p);

	}
	
	public static void setupDelete() {
		for (Client c : clients) {
			c.delete.setOnAction(e-> {
				gererDelete(c);
			});
		}
	}

	/**
	 * Methode pour gerer la suppresion d'un client
	 * 
	 * @param c le client à supprimer
	 */
	public static void gererDelete(Client c) {
		// On demande confirmation a l'utilisateur.
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation");
		alert.setHeaderText("");
		alert.setContentText("Etes vous sur de vouloir supprimer ce client : " + c.getId());

		Optional<ButtonType> result = alert.showAndWait();
		// On regarde sa réponse.
		if (result.get() == ButtonType.OK) {
			System.out.println("suppression de : " + c.toString());
			// Pour toutes locations du panneau P.
			for (Location l : ListeLocations.getListeLocations()) {
				// On les supprimes
				if (l.getClient().equals(c))
					ListeLocations.removeLocation(l.getId());
			}
			// On le supprime de l'observable liste.
			// on le supprime des fichiers.
			ListeClients.removeClient(Integer.parseInt(c.getId()));
			clients.remove(c);
			// On l'enlève de la carte.
			//a faire
		}
		setupDelete();

	}
}
