package application;

import java.util.ArrayList;

public class ListeClients {
	
	/** ArrayList qui contient tous les clients */
	private static ArrayList<Client> listeClients = new ArrayList<Client>();
	
	public static ArrayList<Client> getListeClients() {
		return listeClients;
	}
	
	public static void addClient(Client client) {
		listeClients.add(client);
    }

    public static void removeClient(int id) {
    	listeClients.remove(id);
    	// Corrige les ids des autres objets
    	for (Client c : listeClients) {
    		if (Integer.parseInt(c.getId()) > id) {
    			c.setId(Integer.toString(Integer.parseInt(c.getId())-1));
    		}
    	}
    	Client.nbrcli--;
    }

    public static void editClient(int id, Client client) {
    	listeClients.set(id, client);
    }
    
    public static void setListeClients(ArrayList<Client> liste) {
    	listeClients = liste;
    }
}