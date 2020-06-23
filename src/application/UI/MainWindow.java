package application.UI;

import java.util.Optional;

import application.ListePanneaux;
import application.Panneau;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainWindow extends Stage {
	
	private final static int windowWidth = 1080;
	private final static int windowHeight = 720;
	private MenuBar mBar = new MenuBar();
	private static ImageView map = new ImageView();
	private static Pane pMap = new Pane();
	private static ScrollPane spMap = new ScrollPane();
	private static boolean supprimerPanneau = false;

	public MainWindow() {
        this.setTitle("Panoramix");

        // Position de la fenêtre sur l'écran.
        this.setX(400);
        this.setY(100);

        this.setResizable(true);
        this.getIcons().add(new Image(MainWindow.class.getResourceAsStream("ressources/img/logo.png")));
        Scene scene = new Scene(initialization(), windowWidth, windowHeight);
        this.setScene(scene);
        this.sizeToScene();
        this.centerOnScreen();
    }

    Parent initialization() {
        BorderPane root = new BorderPane();
        
        // Définition de l'ImageView contenant l'image de la map.
        Image image = new Image(MainWindow.class.getResourceAsStream("ressources/img/map.png"));
        map.setImage(image);
        map.setPreserveRatio(true);
        map.setSmooth(true);
        map.setCache(true);
        
        // Ajout de l'ImageView dans la pane.
        pMap.getChildren().add(map);
        
        // Ajout de la pane dans la scrollPane.
        spMap.setContent(pMap);
        spMap.setHvalue(0.5);
        spMap.setVvalue(0.5);
        spMap.setPannable(true);
        //sp.setPickOnBounds(true);
        root.setCenter(spMap);
        
        // Création des menus.
        Menu fichier = new Menu("Fichier");
    	Menu editer = new Menu("Éditer");
    	Menu affichage = new Menu("Affichage");
    	Menu aide = new Menu("Aide");
    	
    	// Création des items.
    	MenuItem ouvrir = new MenuItem("Ouvrir...");
    	MenuItem enregistrer = new MenuItem("Enregistrer...");
    	SeparatorMenuItem separator= new SeparatorMenuItem();
    	MenuItem quitter = new MenuItem("Quitter");
    	
    	Menu ajouter = new Menu("Ajouter");
    	MenuItem addPanneau = new MenuItem("Un panneau...");
    	MenuItem addClient = new MenuItem("Un client...");
    	MenuItem addLocation = new MenuItem("Une location...");
    	ajouter.getItems().addAll(addPanneau, addClient, addLocation);
    	
    	Menu supprimer = new Menu("Supprimer");
    	MenuItem delPanneau = new MenuItem("Un panneau...");
    	MenuItem delClient = new MenuItem("Un client...");
    	MenuItem delLocation = new MenuItem("Une location...");
    	supprimer.getItems().addAll(delPanneau, delClient, delLocation);
    	
    	MenuItem panneaux = new MenuItem("Panneaux...");
    	MenuItem clients = new MenuItem("Clients...");
    	MenuItem locations = new MenuItem("Locations...");
    	
    	MenuItem besoinAide = new MenuItem("Besoin d'aide...");
    	MenuItem aboutUs = new MenuItem("À propos...");
    	
    	// Ajout des items dans leur menu respectif.
    	fichier.getItems().addAll(ouvrir, enregistrer, separator, quitter);
    	editer.getItems().addAll(ajouter, supprimer);
    	affichage.getItems().addAll(panneaux, clients, locations);
    	aide.getItems().addAll(besoinAide, aboutUs);
    	
        mBar.getMenus().addAll(fichier, editer, affichage, aide);
        root.setTop(mBar);
        
        // --- Traitement de l'action des options de menu. ---
        
        // Fichier.
        ouvrir.setOnAction(event -> {
        	Stage window = new Ouvrir();
        	window.show();
        });
        enregistrer.setOnAction(event -> {
        	Stage window = new Enregistrer();
        	window.show();
        });
        quitter.setOnAction(event -> {
        	this.close();
        });
        
        // Éditer.
        addPanneau.setOnAction(event -> {
        	Stage window = new AjouterPanneau();
        	window.show();
        });
        addClient.setOnAction(event -> {
        	Stage window = new AjouterClient();
        	window.show();
        });
        addLocation.setOnAction(event -> {
        	Stage window = new AjouterLocation();
        	window.show();
        });
        
        delPanneau.setOnAction(event -> {
        	// Afficher fenêtre de confirmation.
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Panoramix - Confirmation");
			alert.setHeaderText("Suppression d'un panneau");
			alert.setContentText("Cliquez sur un panneau de la carte pour le supprimer");
			Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
			stage.getIcons().add(new Image(MainWindow.class.getResourceAsStream("ressources/img/logo.png")));

			Optional<ButtonType> result = alert.showAndWait();
			// On regarde sa réponse.
			if (result.get() == ButtonType.OK) {
				supprimerPanneau = true;
			}
        });
        delClient.setOnAction(event -> {
        	Stage window = new AfficherClients();
        	window.show();
        });
        delLocation.setOnAction(event -> {
        	Stage window = new AfficherLocations();
        	window.show();
        });
        
        // Affichage.
        panneaux.setOnAction(event -> {
        	Stage window = new AfficherPanneaux();
        	window.show();
        });
        clients.setOnAction(event -> {
        	Stage window = new AfficherClients();
        	window.show();
        });
        locations.setOnAction(event -> {
        	Stage window = new AfficherLocations();
        	window.show();
        });
        
        // Aide.
        besoinAide.setOnAction(event -> {
        	Stage window = new AideWindow();
        	window.show();
        });
        aboutUs.setOnAction(event -> {
        	Stage window = new AboutUs();
        	window.show();
        });
        
        return root;
    }
    
    /**
	 * @param panneau le panneau dont on souhaite associer l'évènement de suppression de panneau
	 * 
	 *			Permet de gérer la suppression du panneau.
	 */
	public static void gererSupprimerPanneau(Panneau panneau) {
		Button emplacement = panneau.getBouton();
		
		// On ajoute l'évènement du clique à ce bouton.
		emplacement.setOnMouseClicked(event_1 -> {
			if (supprimerPanneau) {
				supprimerPanneau = false;
				ListePanneaux.removePanneau(Integer.parseInt(panneau.getId()));
				MainWindow.getpMap().getChildren().remove(emplacement);
				MainWindow.actualiserCarte();
				
				System.out.println("Le panneau a bien été supprimé");
				Alert alert = new Alert(AlertType.INFORMATION, "Le panneau a bien été supprimé");
				alert.setTitle("Panoramix - Information");
				Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
				stage.getIcons().add(new Image(MainWindow.class.getResourceAsStream("ressources/img/logo.png")));
				alert.show();
			}
		});
		
		// Évènement au survol du bouton.
		emplacement.setOnMouseEntered(event_2 -> {
			if (!supprimerPanneau) {
				PanneauWindow fenetre = new PanneauWindow(panneau, panneau.getX(), panneau.getY());
				pMap.getChildren().add(fenetre.getCadre());
				
				// Évènement lorsque l'on quitte cette fenêtre il faut la supprimer.
				fenetre.getCadre().setOnMouseExited(event_3 -> {
					pMap.getChildren().remove(fenetre.getCadre());
				});
			}
		});
	}
	
	/*
	 *	Permet le placement des panneaux sur la carte
	 */
	public static void actualiserCarte() {
		// Pour chaque panneaux dans ListePanneaux.
		for (Panneau panneau : ListePanneaux.getListePanneaux()) {
			// Création d'un nouvel emplacement avec l'id du panneau.
			Button emplacement = new Button(panneau.getId());
			
			emplacement.setStyle("-fx-background-color: blue; -fx-text-fill: white;");
			emplacement.setLayoutX(panneau.getX());
			emplacement.setLayoutY(panneau.getY());
			
			// Fix bug ouff
			pMap.getChildren().remove(panneau.getBouton());
			
			// On ajoute le bouton au panneau.
			panneau.setBouton(emplacement);
			
			// On le place sur la map.
			pMap.getChildren().add(emplacement);
			
			// Gère la suppression du panneau.
			gererSupprimerPanneau(panneau);
		
			System.out.println("Le panneau n°" + panneau.getId() + " est placé sur la map");
		}
	}
    
    public MenuBar getmBar() {
		return mBar;
	}

	public void setmBar(MenuBar mBar) {
		this.mBar = mBar;
	}

	public static ImageView getMap() {
		return map;
	}
	
	public static ScrollPane getSpMap() {
		return spMap;
	}

	public static void setSpMap(ScrollPane spMap) {
		MainWindow.spMap = spMap;
	}

	public static void setMap(ImageView map) {
		MainWindow.map = map;
	}

	public int getWindowWidth() {
		return windowWidth;
	}

	public int getWindowHeight() {
		return windowHeight;
	}
	
	public static Pane getpMap() {
		return pMap;
	}

	public static void setpMap(Pane pMap) {
		MainWindow.pMap = pMap;
	}
	
}
