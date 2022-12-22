package affichage;

import java.util.*;
import java.net.*;
import java.io.*;

import joueur.Joueur;
import client.Client;
import affichage.*;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter you name");
		String username = scanner.nextLine();
		Joueur joueur = new Joueur(username);
		
		Client client=new Client();
	
		Table table = new Table(joueur,client);
		Fenetre f = new Fenetre(table);
		
		f.getClient().listenForMessage();
	}
}