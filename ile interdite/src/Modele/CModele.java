package Modele;

import Modele.Objet.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

/**
 * Le modèle : le coeur de l'application.
 *
 * Le modèle étend la classe [Observable] : il va posséder un certain nombre
 * d'observateurs (ici, un : la partie de la vue responsable de l'affichage)
 * et devra les prévenir avec [notifyObservers] lors des modifications.
 * Voir la méthode [avance()] pour cela.
 */
public class CModele extends Observable {
	
    /** On fixe la taille de la grille. */
    
	public static final int HAUTEUR=6, LARGEUR=6;

	/** On stocke un tableau de cellules. */
    
	static Cellule[][] cellules;
    
	public int tourJoueur = 0;
    
	private ArrayList<Joueur> joueurs = new ArrayList<Joueur>();
    

    /** Construction : on initialise un tableau de cellules. */
    public CModele() {
	/**
	 * Pour éviter les problèmes aux bords, on ajoute une ligne et une
	 * colonne de chaque côté, dont les cellules n'évolueront pas.
	 */ 
		cellules = new Cellule[LARGEUR+2][HAUTEUR+2];
		
		for(int i=0; i<LARGEUR+2; i++) {
			
		    for(int j=0; j<HAUTEUR+2; j++) {
			
		    	cellules[i][j] = new Cellule(this,i, j);
		    
		    }
		}
		
		for(int i= 0; i < 4; i++) {
			
			joueurs.add(new Joueur(1,1,"joueur"+i));
		}
    }

    /**
     * Calcul de la génération suivante.
     */
    
    public void inondation() {
    	
    	for (int n=1; n<=3; n++) {
    		
    		/* test si toutes les cellules sont submergees*/
	    	boolean disponibilite = false;
			for(int i=1; i<=LARGEUR; i++) {
			    for(int j=1; j<=HAUTEUR; j++) {
		    		if (cellules[i][j].etat == Cellule.Etat.INONDE | cellules[i][j].etat == Cellule.Etat.EMERGE) {
		    			disponibilite = true;
		    		}
			    }
			}
			if (! disponibilite) {
				System.out.print("Toutes les cellules sont submergees ");
				break;
			}
			
	    	int xAleatoire = 1+ (int)(Math.random() * (LARGEUR));
	    	int yAleatoire = 1+ (int)(Math.random() * (HAUTEUR));
	    	
	    	while (cellules[xAleatoire][yAleatoire].etat == Cellule.Etat.SUBMERGE){
		    
	    		xAleatoire = 1+ (int)(Math.random() * (LARGEUR));
		    	yAleatoire = 1+ (int)(Math.random() * (HAUTEUR));

	    	}
	    	if (cellules[xAleatoire][yAleatoire].etat == Cellule.Etat.INONDE) {
	    		
	    		cellules[xAleatoire][yAleatoire].etat = Cellule.Etat.SUBMERGE;
	    	
	    	}
	    	else {
	    		
	    		cellules[xAleatoire][yAleatoire].etat = Cellule.Etat.INONDE;
	    	
	    	}
	    }
    }
    
    
    public ArrayList<Integer> choisiPlacement(CModele modele){
    	
    	
    	int xAleatoire = 1+(int)(Math.random()*(LARGEUR));
    	int yAleatoire = 1+(int)(Math.random()*(HAUTEUR));
    	
   	    	
		while(cellules[xAleatoire][yAleatoire].estSpeciale = true){
			
			xAleatoire = 1+(int)(Math.random()*(LARGEUR));
		    yAleatoire = 1+(int)(Math.random()*(HAUTEUR));
		    
		}
		ArrayList<Integer> coords = new ArrayList();
		coords.add(xAleatoire);
		coords.add(yAleatoire);
		return coords;
    }
    
	public void placerElement(CModele modele){
		for (Type type : Type.values()){
			for (Element element : Element.values()) {
				ArrayList<Integer> coords = choisiPlacement(modele);
				Objet obj = new Objet(type, element);
				this.cellules[coords.get(0)][coords.get(1)].setObjet(obj);
			}
		}
	}
		
    
    
		

   
    /*
    public void deplacement(String direction, int x, int y) {
    	
    }*/
    
    
    //public void avance() {


    /**
     * Une méthode pour renvoyer la cellule aux coordonnées choisies (sera
     * utilisée par la vue).
     */
    public Cellule getCellule(int x, int y) {
    
    	return cellules[x][y];
   
    }
    
    public Joueur getJoueur(int i) {
    	
    	return joueurs.get(i);
    }
    
    /**
     * Notez qu'à l'intérieur de la classe [CModele], la classe interne est
     * connue sous le nom abrégé [Cellule].
     * Son nom complet est [CModele.Cellule], et cette version complète est
     * la seule à pouvoir être utilisée depuis l'extérieur de [CModele].
     * Dans [CModele], les deux fonctionnent.
     */
}

/** Fin de la classe CModele. */