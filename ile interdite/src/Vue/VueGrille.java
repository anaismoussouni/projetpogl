package Vue;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import Modele.CModele;
import Modele.Cellule;
import Modele.Joueur;

/**
 * Une classe pour représenter la zone d'affichage des cellules.
 *
 * JPanel est une classe d'éléments graphiques, pouvant comme JFrame contenir
 * d'autres éléments graphiques.
 *
 * Cette vue va être un observateur du modèle et sera mise à jour à chaque
 * nouvelle génération des cellules.
 */


class VueGrille extends JPanel implements Observer {
	
	
	/** On maintient une référence vers le modèle. */
    private CModele modele;
    /** Définition d'une taille (en pixels) pour l'affichage des cellules. */
    private final static int TAILLE = 100;
	public static final int ovalRayon = TAILLE/5;

 
    
    /** Constructeur. */
    public VueGrille(CModele modele) {
    	
    	this.modele = modele;
	/** On enregistre la vue [this] en tant qu'observateur de [modele]. */
    	modele.addObserver(this);
    
    	/**
	 * Définition et application d'une taille fixe pour cette zone de
	 * l'interface, calculée en fonction du nombre de cellules et de la
	 * taille d'affichage.
	 */
    	Dimension dim = new Dimension(TAILLE*CModele.LARGEUR,
				      TAILLE*CModele.HAUTEUR);
    	this.setPreferredSize(dim);

    }

    /**
     * L'interface [Observer] demande de fournir une méthode [update], qui
     * sera appelée lorsque la vue sera notifiée d'un changement dans le
     * modèle. Ici on se content de réafficher toute la grille avec la méthode
     * prédéfinie [repaint].
     */
    public void update(Observable obs, Object obj) { repaint(); } 
    

    /**
     * Les éléments graphiques comme [JPanel] possèdent une méthode
     * [paintComponent] qui définit l'action à accomplir pour afficher cet
     * élément. On la redéfinit ici pour lui confier l'affichage des cellules.
     *
     * La classe [Graphics] regroupe les éléments de style sur le dessin,
     * comme la couleur actuelle.
     */
    public void paintComponent(Graphics g) {
		super.repaint();
		/** Pour chaque cellule... */
		for(int i=1; i<=CModele.LARGEUR; i++) {
		    for(int j=1; j<=CModele.HAUTEUR; j++) {
			/**
			 * ... Appeler une fonction d'affichage auxiliaire.
			 * On lui fournit les informations de dessin [g] et les
			 * coordonnées du coin en haut à gauche.
			 */
			paint(g, modele.getCellule(i, j),
					(i-1)*TAILLE,
					(j-1)*TAILLE,
					modele.getJoueur(0),
					modele.getJoueur(1),
					modele.getJoueur(2),
					modele.getJoueur(3));
		    }
		}
    }
    
  
    
    /**
     * Fonction auxiliaire de dessin d'une cellule.
     * Ici, la classe [Cellule] ne peut être désignée que par l'intermédiaire
     * de la classe [CModele] à laquelle elle est interne, d'où le type
     * [CModele.Cellule].
     * Ceci serait impossible si [Cellule] était déclarée privée dans [CModele].
     */
    
    private void paint(Graphics g, Cellule c, int x, int y, Joueur joueur1, Joueur joueur2, Joueur joueur3, Joueur joueur4) {
        /** Sélection d'une couleur. */
    	
		if (Cellule.Etat.EMERGE.equals(c.getEtat())){
		    g.setColor(Color.GREEN);
		} 
		else if (Cellule.Etat.INONDE.equals(c.getEtat())){
	        g.setColor(Color.BLUE);
	        }
		else {
			g.setColor(Color.BLACK);
		}
        /** Coloration d'un rectangle. */
        g.fillRect(x, y, TAILLE, TAILLE);
        
        /** Placement des joueurs */
        
        int x1=joueur1.getX();
        int y1=joueur1.getY();
		g.setColor(Color.red);
		int xOval1 = x1 + TAILLE/2 - ovalRayon/2;
		int yOval1 = y1 + 20 - ovalRayon/2;
        g.fillOval(xOval1, yOval1, ovalRayon, ovalRayon);
		
        int x2=joueur2.getX();
        int y2=joueur2.getY();
		g.setColor(Color.yellow);
		int xOval2 = x2 + TAILLE/2 - ovalRayon/2;
		int yOval2 = y2 + TAILLE - 20 - ovalRayon/2;
        g.fillOval(xOval2, yOval2, ovalRayon, ovalRayon);			
		
        int x3=joueur3.getX();
        int y3=joueur3.getY();
		g.setColor(Color.white);
		int xOval3 = x3 + 20 - ovalRayon/2;
		int yOval3 = y3 + TAILLE/2 - ovalRayon/2;
        g.fillOval(xOval3, yOval3, ovalRayon, ovalRayon);
		
        int x4=joueur1.getX();
        int y4=joueur1.getY();
		g.setColor(Color.orange);
		int xOval4 = x4 + TAILLE - 20 - ovalRayon/2 ;
		int yOval4 = y4 + TAILLE/2 - ovalRayon/2;
        g.fillOval(xOval4, yOval4, ovalRayon, ovalRayon);
		
		/**Coloration du grillage */
		g.setColor(Color.white);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(4));
        for (int i=0; i<=CModele.LARGEUR*TAILLE; i+=TAILLE) {
            g2.draw(new Line2D.Float(i, 0, i, CModele.HAUTEUR*TAILLE));
        }
        for (int j=0; j<=CModele.HAUTEUR*TAILLE; j+=TAILLE) {
        	g2.draw(new Line2D.Float(0, j,CModele.LARGEUR*TAILLE,j));
        }
    }
   
}

