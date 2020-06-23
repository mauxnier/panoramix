package application;
	
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import application.UI.MainWindow;
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
	
	@Override
    public void start(Stage primaryStage) {
        primaryStage = new MainWindow();
        primaryStage.show();
        //System.out.println(Date.dateAujourdhui().toString());
    }
	
    public static void enregistrer() {
    	String nomFichier = new String();
    	saveListes(nomFichier + ".pano");
    }
    
    public static void saveListes(String nomFichier) {
    	FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(nomFichier);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
    	ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(fos);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
    	try {
			oos.writeObject(ListePanneaux.getListePanneaux());
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	try {
			oos.writeObject(ListeClients.getListeClients());
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	try {
			oos.writeObject(ListeLocations.getListeLocations());
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	try {
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    
    
    public static void ouvrirListes(String nomFichier) {
    	try {
            FileInputStream fileIn = new FileInputStream(nomFichier);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            
            try {
				@SuppressWarnings("unchecked")
				ArrayList<Panneau> listePanneaux = (ArrayList<Panneau>) in.readObject();
				ListePanneaux.setListePanneaux(listePanneaux);
				Panneau.nbpan = ListePanneaux.getListePanneaux().size();
			} catch (ClassNotFoundException e2) {
				e2.printStackTrace();
			}
            try {
				@SuppressWarnings("unchecked")
				ArrayList<Client> listeClients = (ArrayList<Client>) in.readObject();
				ListeClients.setListeClients(listeClients);
				Client.nbrcli = ListeClients.getListeClients().size();
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			}
            try {
				@SuppressWarnings("unchecked")
				ArrayList<Location> listeLocations = (ArrayList<Location>) in.readObject();
				ListeLocations.setListeLocations(listeLocations);
				Location.cpt = ListeLocations.getListeLocations().size();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
              
            in.close();
            fileIn.close();
         } catch(IOException i) {
            i.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Fichier introuvable");
			alert.setContentText("Ce fichier n'existe pas.");
			Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
			stage.getIcons().add(new Image(MainWindow.class.getResourceAsStream("ressources/img/logo.png")));
			alert.show();
            return;
         }
    }
    
    public static void main(String[] args) {
        Application.launch(args);
    }
}
