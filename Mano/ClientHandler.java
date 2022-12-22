package client;

import java.util.*;
import java.net.*;
import java.io.*;

import joueur.Joueur;
public class ClientHandler implements Serializable,Runnable{
	public static ArrayList<ClientHandler> clientHandlers = new ArrayList<ClientHandler>();
	private Socket socket;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private	Joueur joueur;
	private static Vector<Joueur> listClient = new Vector<Joueur>();

	public ClientHandler(Socket soc){
		try{
			// System.out.println("1");
			this.socket=soc;
			ois = new ObjectInputStream(socket.getInputStream());
			oos = new ObjectOutputStream(socket.getOutputStream());
			clientHandlers.add(this);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public Joueur getJoueur() {
		return joueur;
	}
	public void setJoueur(Joueur jj) {
		joueur = jj;
	}

	public void run(){
		// System.out.println("2");
		
		while(socket.isConnected()){
			try{	
			    Object j = ois.readObject();
				broadcastMessage(j);
			}catch(Exception e){
				e.printStackTrace();
				// closeEverything(socket,ois,oos);
				break;
			}
		}
	}

	public void setAllClient(ArrayList<ClientHandler> chs) {
		for(ClientHandler clientHandler : chs){
			try{
				if ( !clientHandler.getJoueur().getNom().equals(getJoueur().getNom()) ) {
					listClient.addElement(clientHandler.getJoueur());									
				}
			}catch(Exception e){
				e.printStackTrace();
				// closeEverything(socket,ois,oos);
			}
		}	
	}

	public void setListJoueur(Vector<Joueur> lc){
		listClient = lc;
	}
	public Vector<Joueur> getListJoueur(){
		return listClient;
	}
	public void broadcastMessage(Object obj){
		for(ClientHandler clientHandler : clientHandlers){
			try{
				// if ( !clientHandler.getJoueur().equals(getJoueur()) ) {
					clientHandler.oos.writeObject(obj);
					clientHandler.oos.flush();
				// }
			}catch(Exception e){
				e.printStackTrace();
				// closeEverything(socket,ois,oos);
			}
		}
	}

	// public Vector<Joueur> getAllClient(){
	// 	for(ClientHandler clientHandler : clientHandlers){
	// 		try{
	// 			// if ( !clientHandler.getJoueur().equals(getJoueur()) ) {
	// 				listClient.addElement(clientHandler.getJoueur());				
	// 			// }
	// 		}catch(Exception e){
	// 			e.printStackTrace();
	// 			// closeEverything(socket,ois,oos);
	// 		}
	// 	}
	// 	return listClient;
	// }
}