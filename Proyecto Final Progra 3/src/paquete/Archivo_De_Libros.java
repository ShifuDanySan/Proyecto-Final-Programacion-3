package paquete;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Archivo_De_Libros {
	String direccion;
	ArrayList <Libro> libros = new ArrayList <Libro>();
	
	
	public Archivo_De_Libros(String direccion) {
		this.direccion = direccion;
	}

	public int cantLineasArchivoLibros() {
		String bfRead;
		int cont=0;
		try {
			BufferedReader bf = new BufferedReader(new FileReader(this.direccion));
			while((bfRead = bf.readLine()) != null) {
				cont++;
			}
			bf.close();
		}catch(IOException e) {
			System.out.println("No se encuentra el archivo");
		}
		return cont;
	}
	
	
	public void guardarDatosDeLibros() {
		String bfRead="";
		int cont=0;
		String titulo="", autor="", isbn="";
		int cant=0, lineasARecorer;
		lineasARecorer=cantLineasArchivoLibros();
		lineasARecorer=lineasARecorer/4;
		try {
			
			BufferedReader bf = new BufferedReader(new FileReader(this.direccion));
						
			for(int i=0;i<lineasARecorer;i++) {
				
				if((bfRead = bf.readLine()) != null) {
					isbn+=bfRead;
					bfRead="";
				}
				
				if((bfRead = bf.readLine()) != null) {
					titulo+=bfRead;
					bfRead="";
				}
				
				if((bfRead = bf.readLine()) != null) {
					autor+=bfRead;
					bfRead="";
				}
				
				if((bfRead = bf.readLine()) != null) {
					cant=Integer.parseInt(bfRead);
					bfRead="";
				}
				
				libros.add(new Libro(isbn, titulo, autor, cant));
				bfRead="";
				autor="";
				titulo="";
				cant=0;
				isbn="";
				
								
			}
	
		}catch(IOException e) {
			System.out.println("No se encuentra el archivo");
		}
		
	}
	
	public boolean dimeSiEstaLibro(String isbn) {
		
		for(Libro e: libros) {
			
			if(isbn.equals(e.dameIsbn())) {
				return true;
			}
		}
		
		return false;
	}
	
	public String[] dameDatosDeLibro(String isbn) {
		
		String datosLibro[] = new String[3];
		for(Libro e: libros) {
			
			if(isbn.equals(e.dameIsbn())==true) {
				
				datosLibro[0]=e.dameTitulo();
				datosLibro[1]=e.dameAutor();
				datosLibro[2]=String.valueOf(e.dameCantidadDeLibros());
				
			}
		}
		return datosLibro;
		
	}
	
	
	public void escribirLibroEnArchivo(String libro) {
		try (FileWriter archivo = new FileWriter(this.direccion, true)) {
			String texto = libro;
			texto += "\r\n";
			archivo.write(texto);
			archivo.close();// cierro el archivo
		} catch (IOException e) {// controlo excepciones
			System.out.println("No se encuentra el archivo");
		}
	}
	
	

	
	
	
	public void eliminarLibro(String isbn) {
		//String archivoAUX = "librosAux.txt"; 
		
		File archivoAux;
		PrintWriter escribir;
		archivoAux = new File("archivoAux.txt");
		String datos="";
		if(!archivoAux.exists()) {
			
			try {
				//Codigo donde pueden ocurir errores(Excepciones)
				archivoAux.createNewFile();
			} catch (IOException e) {
				System.out.println("No se encuentra el archivo");
			}
		}
		
		if(archivoAux.exists()) {
			try {
				escribir = new PrintWriter(archivoAux);
				
				for(Libro e: libros) {
					
					if(e.dameIsbn().equals(isbn)!=true) {
						datos+=e.dameIsbn()+"\r\n"+e.dameTitulo()+"\r\n"+e.dameAutor()+"\r\n"+e.dameCantidadDeLibros()+"\r\n";
						escribir.println(datos);
			
					}
					datos="";
		
				}
				escribir.close();
				
				
				File archivo = new File(direccion);//eliminado archivo
				archivo.delete();
			
				archivoAux.renameTo(new File("Libros.txt"));//Cambiando nombre
				
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
		
}
