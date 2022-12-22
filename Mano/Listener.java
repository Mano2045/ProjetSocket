package listener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import joueur.Joueur;
import affichage.Table;

public class Listener implements KeyListener {
	Table table;
	public Listener( Table t ) {
		table = t;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_SPACE ) {
			Joueur j = new Joueur();
			j.tirage();
			j.calculeCarte();
			table.getJoueur().setTirage(j.getValTypeTirage());
			table.getJoueur().calculeCarte();
			int[][] vt = j.getValTypeTirage();
			table.getClient().send(vt);
			table.getClient().send(table.getJoueur().getNom());
			// System.out.println(table.getJoueur().getNom()+" : "+table.getJoueur().getSommeCarte());
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
