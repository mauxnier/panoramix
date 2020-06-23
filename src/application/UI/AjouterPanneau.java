package application.UI;

import java.text.DecimalFormat;
import java.util.Optional;

import application.ListePanneaux;
import application.Panneau;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AjouterPanneau extends Stage {
	
	private final int windowWidth = 430;
	private final int windowHeight = 500;
	private Label titre = new Label("Ajouter un panneau");
	private int tarif;
	private String largeur;
	private String hauteur;
	private String details;
	private String visibilite;
	private double x;
	private double y;
	boolean addPanneau = true;
	
	public AjouterPanneau() {
        this.setTitle("Panoramix - Ajouter un panneau");
        this.getIcons().add(new Image(MainWindow.class.getResourceAsStream("ressources/img/logo.png")));
        
        // Position de la fenêtre sur l'écran.
        this.setX(700);
        this.setY(200);

        this.setResizable(false);

        Scene scene = new Scene(initialization(), windowWidth, windowHeight);
        this.setScene(scene);
        this.sizeToScene();
        this.centerOnScreen();
    }

    Parent initialization() {
        BorderPane root = new BorderPane();
        VBox colonne = new VBox(20);
        
        // Titre.
        titre.setPadding(new Insets(40, 10, 40, 10));
        titre.setFont(Font.font(20));
		BorderPane.setAlignment(titre, Pos.CENTER);
		root.setTop(titre);
		
		// Hauteur.
		HBox ligneHauteur = new HBox();
		Text textHauteur = new Text("Hauteur :		");
		Slider slideHauteur = new Slider(1, 10, 1);
		Label valueHauteur = new Label();
		valueHauteur.setStyle("-fx-padding: 0 0 0 10;");
		slideHauteur.setShowTickLabels(true);
		slideHauteur.setShowTickMarks(true);
		slideHauteur.setBlockIncrement(1);
		
		valueHauteur.textProperty().bind(
	            Bindings.format(
	                "%.2f",
	                slideHauteur.valueProperty()
	            )
	        );
		
		ligneHauteur.getChildren().addAll(textHauteur, slideHauteur, valueHauteur);
		
		// Largeur.
		HBox ligneLargeur = new HBox();
		Text textLargeur = new Text("Largeur :		");
		Slider slideLargeur = new Slider(1, 10, 1);
		Label valueLargeur = new Label();
		valueLargeur.setStyle("-fx-padding: 0 0 0 10;");
		slideLargeur.setShowTickLabels(true);
		slideLargeur.setShowTickMarks(true);
		slideLargeur.setBlockIncrement(1);
		
		valueLargeur.textProperty().bind(
	            Bindings.format(
	                "%.2f",
	                slideLargeur.valueProperty()
	            )
	        );
		
		ligneLargeur.getChildren().addAll(textLargeur, slideLargeur, valueLargeur);
		
		// Tarif.
		HBox ligneTarif = new HBox();
		Text textTarif = new Text("Tarif :		");
		TextField fieldTarif = new TextField();
		fieldTarif.setPrefWidth(140);
		ligneTarif.getChildren().addAll(textTarif, fieldTarif);
		
		// Détails.
		HBox ligneDetails = new HBox();
		Text textDetails = new Text("Détails :		");
		TextField fieldDetails = new TextField();
		fieldDetails.setPrefWidth(140);
		ligneDetails.getChildren().addAll(textDetails, fieldDetails);
		
		// Visibilité.
		String mauvaise = "Mauvaise";
		String moyenne = "Moyenne";
		String bonne = "Bonne";
		String tresBonne = "Très bonne";
		String excellente = "Excellente";
		ObservableList<String> listeVisibilite = FXCollections.observableArrayList(mauvaise, moyenne, bonne, tresBonne, excellente);
		ChoiceBox<String> choiceBox = new ChoiceBox<String>(listeVisibilite);
		choiceBox.setPrefWidth(140);
		
		HBox ligneVisibilite = new HBox();
		ligneVisibilite.setPadding(new Insets(0, 0, 3, 0));
		Text textVisibilite = new Text("Visibilité :		");
		ligneVisibilite.getChildren().addAll(textVisibilite, choiceBox);
		
		// Message erreur de saisie.
		HBox ligneErreur = new HBox();
		Label textErreur = new Label("Erreur de saisie");
		textErreur.setStyle("-fx-text-fill: red");
		textErreur.setVisible(false);
		ligneErreur.setPadding(new Insets(0, 0, 3, 70));
		ligneErreur.getChildren().add(textErreur);
		
		// Boutons.
		HBox ligneBoutons = new HBox(35);
		Label paddingLeft = new Label();
		
		Button valider = new Button("Valider");
		Button annuler = new Button("Annuler");
		valider.setStyle("-fx-background-color: green; -fx-text-fill: white");
		annuler.setStyle("-fx-background-color: red; -fx-text-fill: white");
		
		// On désative le bouton valider si les champs sont vides.
		valider.disableProperty().bind(
				Bindings.isEmpty(fieldTarif.textProperty()).or(Bindings.isEmpty(fieldDetails.textProperty()))
		);
		
		ligneBoutons.getChildren().addAll(paddingLeft, valider, annuler);
		DropShadow shadow = new DropShadow();
		
		// Ajout de l'ombre quand le curseur de la souris est sur le bouton et changement du curseur
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
		
		// Ajout de l'ombre quand le curseur de la souris est sur le bouton et changement du curseur
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
		
		valider.setOnAction(event -> {
			
			try {
				visibilite = choiceBox.getValue().toString();
				
				try {
					tarif = Integer.parseInt(fieldTarif.getText());
					DecimalFormat df = new DecimalFormat("#.00");
					largeur = df.format(slideLargeur.getValue());
					hauteur =  df.format(slideHauteur.getValue());
					details = fieldDetails.getText();
					
					close();
					
					// Afficher fenêtre de confirmation.
					Alert alert = new Alert(AlertType.CONFIRMATION);
					alert.setTitle("Panoramix - Confirmation");
					alert.setHeaderText("Ajout d'un nouveau panneau");
					alert.setContentText("Cliquez sur la carte pour placer le panneau");
					Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
					stage.getIcons().add(new Image(MainWindow.class.getResourceAsStream("ressources/img/logo.png")));

					Optional<ButtonType> result = alert.showAndWait();
					// On regarde sa réponse.
					if (result.get() == ButtonType.OK) {
						MainWindow.getMap().setOnMouseClicked(e -> {
							if (addPanneau) {
								addPanneau = false;
								
								x = e.getX() - 15;
								y = e.getY() - 15;
								System.out.println("XY : " + x + " " + y);
								
								Panneau panneau = new Panneau(tarif, visibilite, largeur, hauteur, details, x, y);
								
								ListePanneaux.addPanneau(panneau);
								System.out.println("Le panneau est créé et ajouté à listePanneaux");
								
								// Création d'un nouvel emplacement avec l'id du panneau.
								Button emplacement = new Button(String.valueOf(panneau.getId()));
								
								emplacement.setStyle("-fx-background-color: blue; -fx-text-fill: white;");
								emplacement.setLayoutX(x);
								emplacement.setLayoutY(y);
								
								// On ajoute le bouton au panneau.
								panneau.setBouton(emplacement);
								
								// On le place sur la map.
								MainWindow.getpMap().getChildren().add(emplacement);
								
								System.out.println("Le panneau est placé sur la map");
								
								MainWindow.gererSupprimerPanneau(panneau);
								Stage window = new AfficherPanneaux();
								window.show();
							}
						});
					}
					
				} catch(NumberFormatException numberFormatException) {
					System.err.println("Input error on textFields or Sliders");
					textErreur.setVisible(true);
				}
				
			} catch(NullPointerException nullPointerException) {
				System.err.println("Input error on the choiceBox");
				textErreur.setVisible(true);
			}
			
        });
		
		annuler.setOnAction(event -> {
			System.out.println("Annulation de l'ajout d'un panneau");
        	close();
        });
		
		// Colonne.
		colonne.setPadding(new Insets(0, 0, 0, 100));
		colonne.getChildren().addAll(ligneHauteur, ligneLargeur, ligneTarif, ligneDetails, ligneVisibilite, ligneErreur, ligneBoutons);
		root.setCenter(colonne);
		
        return root;
    }
    
}
