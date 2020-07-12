package paquete;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ArchivoAlumnos {
	String direccion;
	
	
	
	public String leeArchivo() {
		String texto = "";
		try {
			BufferedReader bf = new BufferedReader(new FileReader(this.direccion));
			String temp = "";
			String bfRead;
			while ((bfRead = bf.readLine()) != null) {// Este while avanza linea por linea
				temp += bfRead;// guardo el contenido de la linea del archivo en la variable temporal
				temp += "\n";/*
								 * guardo en la variable String temporal un salto de linea antes de guardar la
								 * siguente linea en esta variable
								 */
			}
			texto = temp;// aqui asigno el contenido de temp a la variable String que retorno
			bf.close();// cierro el archivo
		} catch (IOException e) {// controlo excepciones
			System.out.println("No se encuentra el archivo");
		}
		return texto;
	}
	
	public boolean estaAlumno(String nombre, String apellido) {
		String nombreCompleto="";
		nombreCompleto+=nombre;
		nombreCompleto+=" ";
		nombreCompleto+=apellido;
		boolean estaAlumno=false;
		
		try {
			BufferedReader bf = new BufferedReader(new FileReader(this.direccion));
			String bfRead;
			
			while((bfRead = bf.readLine())!=null) {
				
				if(bfRead==nombreCompleto) {
					estaAlumno=true;	
				}
			}
		}catch(IOException e) {
			System.out.println("No se encuentra el archivo");
			
		}
		System.out.println(estaAlumno);
		return estaAlumno;
	}
	

}
