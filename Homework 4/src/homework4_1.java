/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.*;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;

public class homework4_1 {
    static Random r = new Random();
    static ArrayList<String> studentName = new ArrayList<>();
    
    public static void readFile(){
        File f = new File("advisee.txt");
        try {
            Scanner scan = new Scanner(f);
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                if (line.isEmpty()) continue;
                
                if (line.startsWith("1024")) {
                    String tmps[] = line.split("\\s+");
                    studentName.add(tmps[1]);
                }
            }
            scan.close();
        } catch (FileNotFoundException ex) {
            System.out.printf("reading file error: %s\n", ex.getMessage());
        }
    }
    private static void print(ArrayList a){
        for (int i=0 ; i<a.size(); i++){
            System.out.printf("%s ",a.get(i));
        }
        System.out.println(a.size());
    }
    
    private static void shuffel(ArrayList<String> studentName){
        for (int i=0; i < studentName.size() ; i++){
            Collections.swap(studentName, r.nextInt(studentName.size()), r.nextInt(studentName.size()));
        }
    }
    
    public static void main(String []args){
        HashMap result = new HashMap();
        String[] role = new String[]{"將","士","士","象","象","車","車","馬","馬","包","包","卒","卒","卒","卒","卒","帥","仕","仕","相","相","?","?","傌","傌","炮","炮","兵","兵","兵","兵","兵"};
        readFile();
        print(studentName);
        shuffel(studentName);
        print(studentName);
        for (int i=0; i < role.length; i++){
            result.put(studentName.get(i), role[i]);
        }
        for (Object key : result.keySet()) {
            System.out.println(key + " : " + result.get(key));
        }
    }
}
