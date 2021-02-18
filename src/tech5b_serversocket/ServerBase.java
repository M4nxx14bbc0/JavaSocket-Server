/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tech5b_serversocket;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author parrarodriguez.manue
 */
public class ServerBase {
    private int port;
    private ServerSocket SS;

    public ServerBase(int port) {
        this.port = port;
        if (!startServer()) {
            System.err.println("Errore in fase di creazione");
        }
    }
    
    private boolean startServer() {
        try {
            SS = new ServerSocket(port);
        } catch(IOException io) {
            Logger.getLogger(ServerBase.class.getName()).log(Level.SEVERE, null, io);
        }
        System.out.println("Server creato con successo");
        return false;
    }
    
    public void runServer(){
        try {
            while (true) {
                System.out.println("Server in attesa di richieste...");
                
                Socket client = SS.accept();
                System.out.println("Un client si è connesso");
                
                OutputStream versoIlClient = client.getOutputStream();
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(versoIlClient));
                
                bw.write("Ciao bello ! Sono il server !");
                
                bw.close();
                client.close();
                
                System.out.println("Chiusura connessione !");
            }
        } catch (IOException io) {
            Logger.getLogger(ServerBase.class.getName()).log(Level.SEVERE, null, io);
        }
    }
    
    /**
     * @param args gygfyug
     */
    public static void main(String[] args){
        ServerBase server = new ServerBase(6666);
        server.runServer();
    }
}

