package server;

import java.util.*;
import java.net.*;
import java.io.*;

import joueur.Joueur;
import client.ClientHandler;

public class Server{
	private ServerSocket serverSocket;
	private Vector<Joueur> listClient = new Vector<Joueur>();

	public Server(){
		try{
			serverSocket = new ServerSocket(2222);
		}catch(Exception e){

		}
	}

	public void startServer(){
		try{
			while(!serverSocket.isClosed()){
				Socket socket = serverSocket.accept();
				System.out.println("A new Client has Connected");
				ClientHandler clientHandler = new ClientHandler(socket);
				Thread thread = new Thread(clientHandler);
				thread.start();
			}
		}catch(Exception e){
		
		}
	}
	public void closeServerSocket(){
		try{
			if(serverSocket != null){
				serverSocket.close();
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	public static void main(String[] args) throws IOException{
		Server server = new Server();
		server.startServer();
	}

}