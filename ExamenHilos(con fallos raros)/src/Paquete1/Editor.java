/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Paquete1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author Alumno
 */
public class Editor {
    	public synchronized void guardarArchivo(String Usuarios, String texto){
		
		

		File f = new File(Usuarios);
		try{
			FileWriter fw = new FileWriter(f,true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			
			pw.append(texto);
		
			pw.close();
			bw.close();	
		}catch(IOException e){
			
			System.err.println("error"+e);
			
				
		}
		

	}
}
