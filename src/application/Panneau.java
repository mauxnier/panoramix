package application;

import java.io.Serializable;

import javafx.scene.control.Button;

public class Panneau implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    /* Compteur du nombre de panneau */
	public static int nbpan = 0;
	
	/* Tarif du panneau */
	private double tarifD;
	
	private String tarif;
	
	/* Visibilite du panneau */
	private String visibilite;
	
	/* Largeur du panneau */
	private String largeur;
	
	/* Hauteur du panneau */
	private String hauteur;
	
	/* ID du panneau attribué automatiquement et unique */
	private int idI;
	
	private String id;
	
	// Location du panneau
	private Location location = null;
	
	/* Détails du panneau */
	private String details;
	
	/* Bouton correspond à la représentation graphique du panneau sur la carte */
	private transient Button bouton;
	
	/* coordonnées X du panneau */
	private double x;
	
	/* coordonnées Y du panneau */
	private double y;
	
	public transient Button edit;
	public transient Button delete;

    public Panneau(double tarif, String visibilite, String largeur, String hauteur, String details, double x, double y) {
    	this.idI = nbpan;
    	this.id = Integer.toString(idI);
    	nbpan++;
        this.tarifD = tarif;
        this.tarif = Double.toString(tarifD);
        this.visibilite = visibilite;
        this.largeur = largeur;
        this.hauteur = hauteur;
        this.details = details;
        this.x = x;
        this.y = y;
    }

	public Button getBouton() {
		return bouton;
	}

	public void setBouton(Button bouton) {
		this.bouton = bouton;
	}

	public void ajouterLocation(Location location) {
        this.location = location;
    }

    public void supprimerLocation() {
        this.location = null;
    }

    @Override
    public String toString() {
    	return "ID : " + getId();
    }

	public double getTarifD() {
		return tarifD;
	}

	public void setTarifD(double tarif) {
		this.tarifD = tarif;
	}
	
	public String getTarif() {
		return tarif;
	}
	
	public void setTarif(String newValue) {
		this.tarif = newValue;
	}

	public String getVisibilite() {
		return visibilite;
	}

	public void setVisibilite(String visibilite) {
		this.visibilite = visibilite;
	}

	public String getLargeur() {
		return largeur;
	}

	public void setLargeur(String largeur) {
		this.largeur = largeur;
	}

	public String getHauteur() {
		return hauteur;
	}

	public void setHauteur(String  hauteur) {
		this.hauteur = hauteur;
	}

	public int getIdI() {
		return idI;
	}

	public void setIdI(int id) {
		this.idI = id;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void setIdI(String newValue) {
		this.idI = Integer.parseInt(newValue);
	}

	public void setId(String newValue) {
		this.id = newValue;
	}

	public String getId() {
		return this.id;
	}
	
	public Button getEdit() {
		return edit;
	}
	
	public Button getDelete() {
		return delete;
	}
}
