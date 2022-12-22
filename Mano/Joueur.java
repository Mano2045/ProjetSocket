package joueur;

import carte.Carte;
import javax.swing.JPanel;
import java.awt.Toolkit;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.awt.event.*;
import java.io.Serializable;

public class Joueur implements Serializable{
	String nom;
	Carte[] carte = new Carte[3];
	int sommeCarte;
	boolean tirage = false;
	int [][] valType = new int[3][2];

	boolean connect;
	
	public Joueur(){
	}

	public boolean getTirage(){
		return tirage;
	}
	
	public Joueur(String nom) {
		setNom(nom);
	}
	
	public void setNom(String nom2) {
		nom = nom2;
	}
	public String getNom() {
		return nom;
	}

	public void calculeCarte() {
		// System.out.println("---------------");
		int somme = 0;
		for(int i=0; i<carte.length; i++){
			// System.out.println("carte "+(i+1)+" = "+ carte[i].getValeur());
			if(!carte[i].isMamo()) {
				// System.out.println("carte "+getNom()+(i+1)+" = "+ carte[i].getValeur());
				somme = somme + carte[i].getValeur();
			}
		}
		if(somme > 9) {
			int t = (int) somme/10*10;
			somme = somme-t;	
			//System.out.println(t);
		}
		// setSommeCarte(somme);
		sommeCarte = somme;
		// System.out.println(getNom()+" : "+sommeCarte+"");
		// System.out.println("---------");
	}

	public void drawPlayer(Graphics2D g2d,int x) {
	 	int y = 50;
		// g2d.drawString(getNom(),x,20);	
	 	if ( tirage ) {
		 	for (int i = 0; i<carte.length ;i++ ) {
				carte[i].drawCarte(g2d,x,y);
				y = y + 50;
		 	}	
			g2d.drawString("Score : "+getSommeCarte(),x,60);	
	 	} else if (!tirage) {
			g2d.drawString("Pas de tirage",x,50);	
	 	}	
	}
	
	public int getSommeCarte() {
		return sommeCarte;
	}
	public void setSommeCarte(int s){
		sommeCarte = s;
	}

	public Carte[] getCarte(){
		return carte;
	}
	
	public void tirage() {
		for(int i=0; i<carte.length; i++){
			carte[i] = new Carte();
			int rand = (int) (Math.random()*14);
			int randType = (int) (Math.random()*4);
			carte[i].setType(randType);
			carte[i].setValeur(rand);
			valType[i][0] = rand;
			valType[i][1] = randType;
		} 			
		tirage = true;
	}

	public int[][] getValTypeTirage() {
		return valType;
	}

	public void setTirage(int[][] vt) {
		for(int i=0; i<carte.length; i++){
			carte[i] = new Carte();
			carte[i].setValeur(vt[i][0]);
			carte[i].setType(vt[i][1]);
		} 
		tirage = true;
	}


}
