/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package myproject;

import java.util.Scanner;

/**
 *
 * @author Rajesh Karmaker
 */
public class A {
    public static void main(String[] args) {
        int n;
        int arr[];
        int max[]=new int[2];
        max[0]=-1;
        max[1]=-1;
        System.out.print("n:");
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        arr = new int[n];
        for(int i=0; i<n;i++){
            arr[i]=in.nextInt();
        }
        for(int j=0;j<n;j++){
            if(arr[j]>max[0]){
                max[0] = arr[j];
            }
            else if(arr[j]>max[1]){
                max[1] = arr[j];
            }
        }
        
        System.out.println(max[0]+" "+max[1]);
    }
    
}
