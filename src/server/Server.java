/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package server;

import java.awt.List;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rajesh Karmaker
 */
public class Server implements Runnable{
    private int port;
    private DatagramSocket socket;
    private Thread run,manage,send,receive;
    private boolean running = false;
    private ArrayList<ServerClient> clients = new ArrayList<ServerClient>();
    public Server(int port) {
        this.port = port;
        try {
            socket = new DatagramSocket(port);
        } catch (SocketException ex) {
            ex.printStackTrace();
            return;
        }
        run = new Thread(this, "Server");
        run.start();
    }

    @Override
    public void run() {
        running = true; 
        System.out.println("Server strated on port "+ port);
        //manageClient();
        receive();
    }
    public void manageClient(){
        manage = new Thread("Manage"){
            public void run(){
                while(running){
                    
                }
            }           
        };
        manage.start();
    }

    public void receive() {
        receive = new Thread("Receive"){
            public void run(){
                while(running){
                    
                    byte [] data = new byte[1024];
                    DatagramPacket packet = new DatagramPacket(data, data.length);
                    try {
                        socket.receive(packet);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    process(packet);    
                }
            }           
        };
        receive.start();
        
    }
    public void process(DatagramPacket packet){
        String string = new String(packet.getData());
        //System.out.println("in process: "+string);
        if(string.startsWith("/c/")){
            int id = UniqueIdentifier.getIndex();
            System.out.println("ID: "+id);
            clients.add(new ServerClient(string.substring(3, string.length()), packet.getAddress(), packet.getPort(), id));
            System.out.println(string.substring(3, string.length()));
            //System.out.println(clients.get(0).address.toString()+" : "+clients.get(0).port);
            String ID = "/c/"+id;
            send(ID,packet.getAddress(),packet.getPort());
        }
        else if(string.startsWith("/m/")){
            String message = new String(string.substring(3, string.length()));
            System.out.println("Message received :"+message);
            sendToAll(string);
        }
        else if(string.startsWith("/d/")){
            String id = string.split("/d/|/e/")[1];
        }
        else{
            System.out.println(string);
        }
    }
    private void sendToAll(String message){
        //System.out.println("No of clients "+clients.size());
        for(int i=0;i<clients.size();i++){
            ServerClient Client = clients.get(i);
            
            send(message,Client.address,Client.port);
        }
    }
    private void send(final byte[] data,final InetAddress address,final int port){
        send = new Thread("Send"){
            @Override
            public void run(){
                DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
                try {
                    System.out.println("Sending from server: "+new String(data));
                    socket.send(packet);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        };
        send.start();
    }
    private void send(String message,InetAddress address,int port){
        message+="/e/";
        //System.out.println("Sending message :"+message);
        send(message.getBytes(),address,port);
    }
    
}
