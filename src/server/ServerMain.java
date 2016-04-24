/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package server;

import java.util.Scanner;

/**
 *
 * @author Rajesh Karmaker
 */
public class ServerMain {
    private int port;
    private Server server;

    public ServerMain(int port) {
        this.port = port;
        server = new Server(port);
    }
    
    public static void main(String [] args){
        Scanner in = new Scanner(System.in);
        System.out.print("Port:");
        int n = in.nextInt();
        new ServerMain(n);
        
    }
    
}
