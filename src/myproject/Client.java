/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package myproject;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 *
 * @author Rajesh Karmaker
 */
public class Client {
    private String name,Ip;
    private int port;
    private DatagramSocket socket;
    private InetAddress address;
    private Thread send;
    private int ID;

    public Client(String name, String Ip, int port) {
        this.name = name;
        this.Ip = Ip;
        this.port = port;
    }
    public boolean openConnection(String Ip){
        try {
            socket = new DatagramSocket();
            address = InetAddress.getByName(Ip);
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
            return false;
        } catch (SocketException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }
    public void send(byte [] Byte){
        send = new Thread("Send"){
            @Override
            public void run(){
                DatagramPacket packet = new DatagramPacket(Byte, Byte.length,address, port);
                try {
                    socket.send(packet);
                } catch (IOException ex) {
                   ex.printStackTrace();
                }
            }
        };
        send.start();
    }
    public String receive(){
        byte [] buf = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        try {
            socket.receive(packet);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        //System.out.println("Receiving:"+new String(packet.getData()));
        return new String(packet.getData());
    }
    public void close(){
        synchronized(socket){
            socket.close();
        }
    }
    public String getName(){return name;}
    public String getIp(){return Ip;}
    public int getPort(){return port;}  
    public void setID(int ID){this.ID = ID;}
    public int getID(){return ID;}
}
