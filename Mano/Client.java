package client;

import java.util.*;
import java.net.*;
import java.io.*;
import joueur.Joueur;

public class Client{
	private  Object mess;
	private Socket socket;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private static Vector<Joueur> listJoueur = new Vector<Joueur>();
	Joueur joueur;

	public Client(){
		try{
			this.socket= new Socket("localhost",2222);
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
			joueur = new Joueur();
		}catch(IOException e){
 		
		}
	}

	public void setMess(Object m) {
		mess = m;
	}

	public Joueur getJoueur() {
		return joueur;
	}

	public void send(Object obj){	
		try{
			oos.writeObject(obj);
			oos.flush();
		}catch(IOException e){
			e.printStackTrace();
			// closeEverything(socket,ois,oos);
		}
	}
	public void listenForMessage(){
		new Thread(new Runnable(){
			public void run(){
				// System.out.println(listJoueur.size());
	
				while(socket.isConnected()){
					try{
						// System.out.println("ao lah");
					 	// listJoueur = (ArrayList<ClientHandler>) ois.readObject();
					 	// Joueur j = (Joueur) ois.readObject();
					 	int[][] vt = (int[][]) ois.readObject();
					 	String name = (String) ois.readObject();
						// joueur = new Joueur(name); 
					 	// listJoueur = (Vector<Joueur>) ois.readObject();
						// Joueur jj = (Joueur) ois.readObject();
						joueur.setNom(name);
 						joueur.setTirage(vt);
						joueur.calculeCarte();
						// for (int i = 0;i<3 ;i++ ) {
						// 	System.out.print(vt[i][0]);
						// 	System.out.println(vt[i][1]);
						// }
						// for(Joueur clientHandler : listJoueur){
						//  	System.out.println(clientHandler.getNom()+" : "+clientHandler.getSommeCarte());						
						// }
						// j.setTirage(vt);
						System.out.println(joueur.getNom()+" : "+joueur.getSommeCarte());						
						// System.out.println(jj.getNom()+" : "+jj.getSommeCarte());						
					 	// System.out.println(j);
					 	// System.out.println(listJoueur.size());
					}catch(Exception e){
						e.printStackTrace();
						// closeEverything(socket,ois,oos);
					}
				}
			}
		}).start();
	}
}