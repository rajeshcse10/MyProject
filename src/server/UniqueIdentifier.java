/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package server;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Rajesh Karmaker
 */
public class UniqueIdentifier {
    private static ArrayList<Integer> ids = new ArrayList<Integer>();    
    private static final int RANGE = 100;
    private static int index = 0;
    static{
        for(int i=0;i<RANGE;i++){
            ids.add(i);
        }
        Collections.shuffle(ids);
    }
    private UniqueIdentifier(){
    }
    public static int getIndex(){
        if(index>ids.size()-1) index = 0;
        return ids.get(index++);
    }
    /*public static void main(String[] args) {
        for(int j=0;j<10;j++){
            System.out.println(UniqueIdentifier.getIndex());
        }
    }*/
}
