package affichage;

import javax.swing.JFrame;
import listener.Listener;
import joueur.Joueur;
import java.util.Vector;

import client.Client;

@SuppressWarnings("serial")
public class Fenetre extends JFrame {
	Table table;

	public Fenetre(Table t) {
		table = t;
		setTitle(table.getJoueur().getNom());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setLayout(null);
		setSize(515	,320);
		addKeyListener(new Listener(table));
		add(table);
		// table.addPlayer(table.getClient().getJoueur());
	}

	public void setTable(Table t){
		table = t;
	}
	public Table getTable() {
		return table;
	}

	public Client getClient() {
		return table.getClient();
	}

	public void addPlayers(Vector<Joueur> lj){
		table.addPlayers(lj);
	}
}