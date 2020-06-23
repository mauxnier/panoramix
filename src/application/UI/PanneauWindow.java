package application.UI;

import java.util.Optional;

import application.ListePanneaux;
import application.Panneau;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class PanneauWindow {

	private GridPane cadre = new GridPane();
	private Label tarif;
	private Label visibilite;
	private Label taille;
	private Label id;
	private Label details;
	private Label tarifl;
	private Label visibilitel;
	private Label taillel;
	private Label idl;
	private Label detailsl;
	private Button supprimer = new Button("Supprimer");
	private Button modifier = new Button("Modifier");
	private String vis;

	public PanneauWindow(Panneau panneau, Double x, Double y) {
		creerContenu(panneau, x, y);
	}

	Parent creerContenu(Panneau panneau, Double x, Double y) {
		// Créer les labels des informations sur le panneau.
		this.tarifl = new Label("Tarif :");
		this.tarif = new Label(String.valueOf(panneau.getTarif()) + " €/J");
		this.visibilitel = new Label("Visibilité :");
		this.vis = panneau.getVisibilite();
		this.visibilite = new Label(this.vis);
		this.taillel = new Label("Taille :");
		this.taille = new Label(String.valueOf(panneau.getLargeur() + " x " + panneau.getHauteur() + "m"));
		this.idl = new Label("Identifiant :");
		this.id = new Label(panneau.getId());
		this.detailsl = new Label("Détails :");
		this.details = new Label(String.valueOf(panneau.getDetails()));
		
		// Créer le style des boutons.
		modifier.setStyle("-fx-font: 12 arial; -fx-base: #05a32f;");
		supprimer.setStyle("-fx-font: 12 arial; -fx-base: #ba1616;");
		modifier.setOnAction(event -> {
			System.out.println("Bouton Modifier");
			Stage window = new AfficherPanneaux();
        	window.show();
		});
		supprimer.setOnAction(event -> {
			System.out.println("Bouton Supprimer");
			
			// Afficher fenêtre de confirmation.
			Alert alertConfirmation = new Alert(AlertType.CONFIRMATION);
			alertConfirmation.setTitle("Panoramix - Confirmation");
			alertConfirmation.setHeaderText("Suppression d'un panneau");
			alertConfirmation.setContentText("Voulez-vous vraiment supprimer le panneau");
			Stage stageConfirmation = (Stage) alertConfirmation.getDialogPane().getScene().getWindow();
			stageConfirmation.getIcons().add(new Image(MainWindow.class.getResourceAsStream("ressources/img/logo.png")));

			Optional<ButtonType> result = alertConfirmation.showAndWait();
			// On regarde sa réponse.
			if (result.get() == ButtonType.OK) {
				ListePanneaux.removePanneau(Integer.parseInt(panneau.getId()));
				MainWindow.getpMap().getChildren().remove(panneau.getBouton());
				MainWindow.actualiserCarte();
				
				System.out.println("Le panneau a bien été supprimé");
				Alert alert = new Alert(AlertType.INFORMATION, "Le panneau a bien été supprimé");
				alert.setTitle("Panoramix - Information");
				Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
				stage.getIcons().add(new Image(MainWindow.class.getResourceAsStream("ressources/img/logo.png")));
				alert.show();
			}
		});
		
		// Ajouter les labels & boutons dans le GridPane.
		cadre.add(tarifl, 0, 0);
		cadre.add(tarif, 1, 0);
		cadre.add(visibilitel, 0, 1);
		cadre.add(visibilite, 1, 1);
		cadre.add(taillel, 0, 2);
		cadre.add(taille, 1, 2);
		cadre.add(idl, 0, 3);
		cadre.add(id, 1, 3);
		cadre.add(detailsl, 0, 4);
		cadre.add(details, 1, 4);
		cadre.add(modifier, 0, 5);
		cadre.add(supprimer, 1, 5);
		
		// Créer le style général de la page.
		cadre.setPadding(new Insets(25, 25, 25, 25));
		cadre.setHgap(30);
		cadre.setVgap(20);
		
		// Placer horizontalement à gauche des labels.
		GridPane.setHalignment(this.tarif, HPos.CENTER);
		GridPane.setHalignment(this.visibilite, HPos.CENTER);
		GridPane.setHalignment(this.taille, HPos.CENTER);
		GridPane.setHalignment(this.id, HPos.CENTER);
		GridPane.setHalignment(this.details, HPos.CENTER);
		GridPane.setHalignment(this.tarifl, HPos.LEFT);
		GridPane.setHalignment(this.visibilitel, HPos.LEFT);
		GridPane.setHalignment(this.taillel, HPos.LEFT);
		GridPane.setHalignment(this.idl, HPos.LEFT);
		GridPane.setHalignment(this.detailsl, HPos.LEFT);
		
		// Centrer verticalement des labels.
		GridPane.setValignment(tarif, VPos.CENTER);
		GridPane.setValignment(visibilite, VPos.CENTER);
		GridPane.setValignment(taille, VPos.CENTER);
		GridPane.setValignment(id, VPos.CENTER);
		GridPane.setValignment(details, VPos.CENTER);
		GridPane.setValignment(tarifl, VPos.CENTER);
		GridPane.setValignment(visibilitel, VPos.CENTER);
		GridPane.setValignment(taillel, VPos.CENTER);
		GridPane.setValignment(idl, VPos.CENTER);;
		GridPane.setValignment(detailsl, VPos.CENTER);
		
		// Placer les boutons.
		GridPane.setHalignment(modifier, HPos.LEFT);
		GridPane.setValignment(modifier, VPos.CENTER);
		GridPane.setHalignment(supprimer, HPos.LEFT);
		GridPane.setValignment(supprimer, VPos.CENTER);
		
		cadre.setPrefSize(240, 280);
		cadre.setLayoutX(x);
		cadre.setLayoutY(y);
		cadre.setStyle("-fx-background-color: #567ebf; -fx-fill: white; -fx-opacity: 1;");
		return cadre;
	}
	
	/**
	 * @return the groot
	 */
	public GridPane getCadre() {
		return cadre;
	}
	
	/**
	 * @param groot
	 *            the groot to set
	 */
	public void setCadre(GridPane cadre) {
		this.cadre = cadre;
	}
	
}
