package application;

import java.io.Serializable;

import javafx.scene.control.Button;

public class Client implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/* Compteur statique du nombre de clients */
	public static int nbrcli = 0;
	
	/* Nom du client */
	private String nom;
	
	/* Coordonnées du client */
	private String adresse;
    private String telephone;
    private String email;
    
	/* Nombre de locations en cours du client */
	private int nbaloc;
	
	/* Nombre de locations terminés du client */
	private int nbfloc;
	
	/* ID du client attribue automatiquement selon le compteur de clients */
	private String id;
	private int idI;
	
	public transient Button edit;
	public transient Button delete;

    public Client(String nom, String adresse, String num, String email) {
        this.idI = nbrcli;
        this.id = Integer.toString(idI);
        nbrcli++;
        this.nbaloc = 0;
        this.nbfloc = 0;
        this.nom = nom;
        this.adresse = adresse;
        this.telephone = num;
        this.email = email;
    }
    
    /* Getters */
    public String getNom() {
        return this.nom;
    }

    public String getAdresse() {
        return this.adresse;
    }

    public String getTelephone() {
        return this.telephone;
    }

    public String getEmail() {
        return this.email;
    }

    public String getId() {
        return this.id;
    }
    
    public int getIdI() {
    	return this.idI;
    }
    
    public int getNbaloc() {
    	return this.nbaloc;
    }
    
    public int getNbfloc() {
    	return this.nbfloc;
    }
    
    public Button getEdit() {
    	return this.edit;
    }
    
    public Button getDelete() {
    	return this.delete;
    }
    
    /* Setters */
    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setTelephone(String num) {
        this.telephone = num;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public int getNbaloc(int nbaloc) {
    	return this.nbaloc = nbaloc;
    }
    
    public int getNbfloc(int nbfloc) {
    	return this.nbfloc = nbfloc;
    }

    public String toString() {
        return "ID : " + getId() + ", Nom : " + getNom();
    }
}