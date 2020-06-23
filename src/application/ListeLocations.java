package application;

import java.util.ArrayList;

public class ListeLocations {
	
	/** ArrayList qui contient toutes les locations */
	private static ArrayList<Location> listeLocations = new ArrayList<Location>();
	
	public static ArrayList<Location> getListeLocations() {
		return listeLocations;
	}
	
	public static void addLocation(Location location) {
		listeLocations.add(location);
    }

    public static void removeLocation(int id) {
    	listeLocations.remove(id);
    	// Corrige les ids des autres objets
    	for (Location l : listeLocations) {
    		if (l.getId() > id) {
    			l.setId(l.getId()-1);
    		}
    	}
    	Location.cpt--;
    }

    public static void editLocation(int id, Location location) {
    	listeLocations.set(id, location);
    }
    
    public static void setListeLocations(ArrayList<Location> liste) {
    	listeLocations = liste;
    }
}
