
package Examen;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Logs {
    public synchronized void guardarCuenta(String print, String name){
         
         String nombreArchivo = "Cuenta De "+name;
         
        File f = new File(nombreArchivo);
        try{
            FileWriter fw = new FileWriter(f, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
             
            pw.append(print);
         
            pw.close();
            bw.close(); 
        }catch(IOException e){
             
            System.err.println("error"+e);
             
                 
        }
         
 
    }
    
    public synchronized void guardarTransferencia(String print, String nombre){
         
         String nombreArchivo = "Transferencia "+nombre;
         
        File f = new File(nombreArchivo);
        try{
            FileWriter fw = new FileWriter(f,true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
             
            pw.append(print);
         
            pw.close();
            bw.close(); 
        }catch(IOException e){
             
            System.err.println("error"+e);
             
                 
        }
         
 
    }
    
}
