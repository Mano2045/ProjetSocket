package affichage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.GridLayout;
import java.lang.reflect.InvocationTargetException;
import java.util.Vector;
import javax.swing.JPanel;
import function.Function;

import joueur.Joueur;
import client.Client;

@SuppressWarnings("serial")
public class Table extends JPanel {
	static Vector<Joueur> listJ = new Vector<Joueur>();
	Joueur joueur;
	Client client;
	public Table(Joueur j,Client cli) {
		joueur = j;
		client = cli;
		addPlayer(joueur);
		addPlayer(getClient().getJoueur());

		setLayout(null);		
		setBounds(0,0,500,300);
		setBackground(Color.GRAY);
		setLayout(new GridLayout(1,4));
	}
		
	public void paint(Graphics g) {
		try {
			Graphics2D g2d =  (Graphics2D) g;
			Image fond = Toolkit.getDefaultToolkit().getImage("img\\Tapis.jfif");
			g2d.drawImage(fond, 0, 0, 500, 300, this);
			int pos = 50;
			for (int i=0;i<listJ.size() ;i++ ) {
				listJ.get(i).drawPlayer(g2d,pos);
				pos = pos + 200;
			}
			super.repaint();	
		} catch(Exception e) {}
	}
	
	public void addPlayer(Joueur j) {
		listJ.addElement(j);
	}

	public void addPlayers(Vector<Joueur> lj){
		listJ = lj;
	}

	public Joueur getWinner() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		return (Joueur) Function.max(listJ.toArray(), "getSommeCarte");
	}

	public Client getClient() {
		return client;
	}
	public void setJoueur(Joueur j) {
		joueur = j;
	}

	public Joueur getJoueur() {
		return joueur;
	}
	
	public Vector<Joueur> getListJoueur() {
		return listJ;
	}

}