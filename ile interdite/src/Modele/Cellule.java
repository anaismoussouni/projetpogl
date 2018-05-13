package Modele;


/**
 * Définition d'une classe pour les cellules.
 * Cette classe fait encore partie du modèle.
 */
public class Cellule {
    /** On conserve un pointeur vers la classe principale du mod�le. */
    private CModele modele;
    
    protected Etat etat;
    private int x;
    private int y;
    boolean estSpeciale = false;
    private Objet obj;
   
   
    /** L'état d'une cellule est donné par un booléen. */
    
    public enum Etat {
    	EMERGE,
    	INONDE,
    	SUBMERGE,
    };
   
	
    public Cellule (CModele modele, int x, int y) {
        this.modele = modele;
        this.etat = Etat.EMERGE;
        this.x = x; 
        this.y = y;
    }
    
    
    public Etat getEtat() {
        return this.etat;
    }
    
    public void setObjet(Objet obj){
    	this.obj=obj;
    }
    
    public Objet getObjet(){
    	return this.obj;
    }
    
  

}
    
/** Fin de la classe Cellule, et du modèle en général. */
