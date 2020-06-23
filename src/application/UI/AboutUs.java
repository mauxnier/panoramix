package application.UI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class AboutUs extends Stage {
	
	private final int windowWidth = 500;
	private final int windowHeight = 300;
	private Label titre = new Label("Panoramix, gestionnaire de panneaux publicitaires\n"
			+ "Version: 2020-06 (1.0-SNAPSHOT)");
	private Label paragraphe = new Label("	Logiciel de gestion de panneaux publicitaires créé dans le cadre du \nprojet de première année de DUT Informatique à l'IUT de Lannion.\n\n"
			+ "Développeurs :\n"
			+ "	- Killian Monnier			"
			+ "	- Kilian Le Borgne\n"
			+ "	- Evan Scoarnec			"
			+ "	- Mathis Lardeux\n"
			+ "	- Guénaël Rivoal");
	
	public AboutUs() {
        this.setTitle("À propos de Panoramix");
        this.getIcons().add(new Image(MainWindow.class.getResourceAsStream("ressources/img/logo.png")));

        // Postion de la fenêtre sur l'écran.
        this.setX(700);
        this.setY(300);

        this.setResizable(false);

        Scene scene = new Scene(initialization(), windowWidth, windowHeight);
        this.setScene(scene);
        this.sizeToScene();
        this.centerOnScreen();
    }

    Parent initialization() {
        BorderPane root = new BorderPane();
        
        // Titre.
        titre.setPadding(new Insets(20, 10, 20, 10));
        titre.setFont(Font.font(20));
		BorderPane.setAlignment(titre, Pos.CENTER);
		root.setTop(titre);
		
		// Paragraphe.
		paragraphe.setPadding(new Insets(20, 10, 20, 10));
		root.setCenter(paragraphe);
		
        return root;
    }
}
