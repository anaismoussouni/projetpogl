package main;

import Controleur.Controleur;
import Modele.CModele;
import Vue.CVue;

/**
 * Nous allons commencer à construire notre application, en voici la classe
 * principale.
 */
public class Conway {
    /**
     * L'amorçage est fait en créant le modèle et la vue, par un simple appel
     * à chaque constructeur.
     * Ici, le modèle est créé indépendamment (il s'agit d'une partie autonome
     * de l'application), et la vue prend le modèle comme paramètre (son
     * objectif est de faire le lien entre modèle et utilisateur).
     */
    public static void main(String[] args) {
        CModele modele = new CModele();
        CVue vue = new CVue(modele);
    }
}
/** Fin de la classe principale. */

