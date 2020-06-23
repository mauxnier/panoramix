package application;

import java.util.ArrayList;

import application.UI.MainWindow;

public class ListePanneaux {
	
	/** ArrayList qui contient tous les panneaux */
	private static ArrayList<Panneau> listePanneaux = new ArrayList<Panneau>();
	
	public static ArrayList<Panneau> getListePanneaux() {
		return listePanneaux;
	}
	
	public static void addPanneau(Panneau panneau) {
		listePanneaux.add(panneau);
    }

    public static void removePanneau(int id) {
    	//On supprime le panneau de la map
    	MainWindow.getpMap().getChildren().remove(ListePanneaux.getListePanneaux().get(id).getBouton());
    	listePanneaux.remove(id);
    	// Corrige les ids des autres objets
    	for (Panneau p : listePanneaux) {
    		if (Integer.parseInt(p.getId()) > id) {
    			p.setId(Integer.toString(Integer.parseInt(p.getId())-1));
    		}
    	}
    	Panneau.nbpan--;
    }

    public static void editPanneau(int id, Panneau panneau) {
    	listePanneaux.set(id, panneau);
    }
    
    public static void setListePanneaux(ArrayList<Panneau> lp) {
    	listePanneaux = lp;
    }
}