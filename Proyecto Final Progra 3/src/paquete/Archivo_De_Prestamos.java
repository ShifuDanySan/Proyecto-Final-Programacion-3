package paquete;

import java.io.FileWriter;
import java.io.IOException;


public class Archivo_De_Prestamos {
	String direccion;
	
	
	public Archivo_De_Prestamos(String direccion) {
		this.direccion = direccion;
	}
	
	
	public void escribeArchivo(String datos) {
		try (FileWriter archivo = new FileWriter(this.direccion, true)) {
			String texto = datos;
			texto += "\r\n";
			archivo.write(texto);
			archivo.close();// cierro el archivo
		} catch (IOException e) {// controlo excepciones
			System.out.println("No se encuentra el archivo");
		}
	
	}

}
