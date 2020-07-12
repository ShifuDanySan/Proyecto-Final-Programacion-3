package paquete;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

public class Archivo {
	public Archivo(String direccion) {
		this.direccion = direccion;
	}

	public void escribeArchivo() {
		try (FileWriter archivo = new FileWriter(this.direccion, true)) {
			String texto = "";
			texto = JOptionPane.showInputDialog("Ingrese el texto\n");
			texto += "\r\n";
			archivo.write(texto);
			archivo.close();// cierro el archivo
		} catch (IOException e) {// controlo excepciones
			System.out.println("No se encuentra el archivo");
		}
	}

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

	public boolean esUsuarioPermitido(String usuarioIngresado, String contrasenaIngresada) {
		String linea = "";
		String usuarioYContrasena = "";
		usuarioYContrasena += usuarioIngresado;
		usuarioYContrasena += " "; 
		usuarioYContrasena += contrasenaIngresada;
		try {
			BufferedReader bf = new BufferedReader(new FileReader(this.direccion));
			while (((linea = bf.readLine()) != null) && (usuarioYContrasena.equals(linea) == false)) {
				linea = "";
			}
			bf.close();
		} catch (IOException e) {
			System.out.println("Error de acceso al archivo");
		}
		if (usuarioYContrasena.equals(linea) == true) {
			return true;
		} else {
			return false;
		}
	}

	String direccion;
}