package application;

import java.io.Serializable;

import javafx.scene.control.Button;

public class Location implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/* Compteur de location pour numéro de location unique */
	public static int cpt = 0;
	
	/* ID de la location attribué automatiquement et unique */
	private int id;
	
	/* Client qui à la location */
	private Client client;
	
	/* Panneau qui est loué */
	private Panneau panneau;
	
	/* Durée de la location */
	private int duree;
	
	/* Date de début de la location */
	private Date dateDebut;
	
	public transient Button delete;
	

    public Location(Client client, Panneau panneau, int duree, Date dateDebut) {
        this.client = client;
        this.panneau = panneau;
        this.duree = duree;
        this.dateDebut = dateDebut;
        this.id = cpt;
        cpt++;
    }

    public static int getCpt() {
		return cpt;
	}

	public static void setCpt(int cpt) {
		Location.cpt = cpt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Panneau getPanneau() {
		return panneau;
	}

	public void setPanneau(Panneau panneau) {
		this.panneau = panneau;
	}

	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}
	
	@Override
	public String toString(){
        return "Client : " + client + "Panneau : " + panneau + "Durée : " + duree + "Date de début : " + dateDebut + "ID : " + id + "Compteur : " + cpt;
    }
	
	public Button getDelete() {
		return delete;
	}
}