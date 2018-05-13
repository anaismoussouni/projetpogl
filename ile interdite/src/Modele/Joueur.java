package Modele;



import java.util.ArrayList;
import java.util.Random;

import Modele.Cellule.Etat;
import Modele.Objet.Type;



public class Joueur {
	private String nom;
	private int x;
	private int y;
	
	public Joueur(int x, int y,String nom) {
		this.nom=nom;
		this.x=x;
		this.y=y;
	}
	
	public int getX() {
		return this.x;
	}
	public int getY() {
		return this.y;
	}
	
	public void setX( int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	public boolean estAdjacente(int x, int y){
		int difX=Math.abs(this.x-x);
		int difY=Math.abs(this.y-y);
		if ((difX+difY==1) && (difX+difY==0)) {
			return true;
		}
		return false;
	}
	
	
	public void seDeplacer(int x, int y) {
		if (estAdjacente(x,y) ) {
			this.x=x;
			this.y=y;
		}
		else {
			System.out.print("Case non adjacente");
		}
	}
	
	
	public void assecher (int x, int y) {
		
		this.x = x;
		this.y = y;
		
		Etat etat = CModele.cellules[x][y].getEtat();
		
		if(estAdjacente(x,y) && CModele.cellules[x][y].etat == etat.INONDE){
			
			CModele.cellules[x][y].etat = etat.EMERGE;
		
		}
	}
	
	public void chercher (int x, int y){
		
		this.x = x;
		this.y = y;
		
		
		Etat etat = CModele.cellules[x][y].getEtat();
		Objet obj = CModele.cellules[x][y].getObjet();
		
		if (! (etat == etat.SUBMERGE)) {
		
		}
	
	} 
	
}	
		