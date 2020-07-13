package paquete;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Archivo {
	public Archivo(String direccion) {
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

	public int cantLineasEnArchivo() {
		String bfRead;
		int cont = 0;
		try {
			BufferedReader bf = new BufferedReader(new FileReader(this.direccion));
			while ((bfRead = bf.readLine()) != null) {
				cont++;
			}
			bf.close();
		} catch (IOException e) {
			System.out.println("No se encuentra el archivo");
		}
		return cont;
	}

	public void guardarDatosDeLibros() {
		this.libros = new ArrayList<Libro>();
		String bfRead = "";
		int cont = 0;
		String titulo = "", autor = "", isbn = "";
		int cant = 0, lineasARecorer;
		lineasARecorer = cantLineasEnArchivo();
		lineasARecorer = lineasARecorer / 4;
		try {

			BufferedReader bf = new BufferedReader(new FileReader(this.direccion));

			for (int i = 0; i < lineasARecorer; i++) {

				if ((bfRead = bf.readLine()) != null) {
					isbn += bfRead;
					bfRead = "";
				}

				if ((bfRead = bf.readLine()) != null) {
					titulo += bfRead;
					bfRead = "";
				}

				if ((bfRead = bf.readLine()) != null) {
					autor += bfRead;
					bfRead = "";
				}

				if ((bfRead = bf.readLine()) != null) {
					cant = Integer.parseInt(bfRead);
					bfRead = "";
				}

				libros.add(new Libro(isbn, titulo, autor, cant));
				bfRead = "";
				isbn = "";
				titulo = "";
				autor = "";
				cant = 0;

			}
		} catch (IOException e) {
			System.out.println("No se encuentra el archivo");
		}

	}

	public boolean estaLibro(String isbn) {

		for (Libro e : libros) {

			if (isbn.equals(e.devolverIsbn())) {
				return true;
			}
		}

		return false;
	}

	public String[] devolverDatosDeLibro(String isbn) {
		String datosLibro[] = new String[3];
		for (Libro e : libros) {

			if (isbn.equals(e.devolverIsbn()) == true) {

				datosLibro[0] = e.devolverTitulo();
				datosLibro[1] = e.devolverAutor();
				datosLibro[2] = String.valueOf(e.devolverCantidadDeLibros());

			}
		}
		return datosLibro;

	}

	public void eliminarLibro(String isbn) {
		// String archivoAUX = "librosAux.txt";
		if (estaLibro(isbn) == true) {
			File archivoAux;
			PrintWriter escribir;
			archivoAux = new File("archivoAux.txt");
			String datos = "";
			if (!archivoAux.exists()) {

				try {
					// Codigo donde pueden ocurir errores(Excepciones)
					archivoAux.createNewFile();
				} catch (IOException e) {
					System.out.println("No se encuentra el archivo");
				}
			}

			if (archivoAux.exists()) {
				try {
					escribir = new PrintWriter(archivoAux);

					for (Libro e : libros) {

						if (e.devolverIsbn().equals(isbn) != true) {
							datos += e.devolverIsbn() + "\r\n" + e.devolverTitulo() + "\r\n" + e.devolverAutor()
									+ "\r\n" + e.devolverCantidadDeLibros() + "\r\n";
							escribir.println(datos);
						}
						datos = "";
					}
					escribir.close();

					File archivo = new File(direccion);// eliminado archivo
					archivo.delete();

					archivoAux.renameTo(new File("Libros.txt"));// Cambiando nombre

				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		} else {
			JOptionPane.showMessageDialog(null,
					"El ISBN no corresponde a un Libro previamente registrado en el sistema");
		}
	}

	public boolean estaAlumno(String nombre, String apellido) {
		String nombreCompleto = "";
		nombreCompleto += nombre;
		nombreCompleto += " ";
		nombreCompleto += apellido;
		boolean estaAlumno = false;

		try {
			BufferedReader bf = new BufferedReader(new FileReader(this.direccion));
			String bfRead;

			while ((bfRead = bf.readLine()) != null) {

				if (bfRead == nombreCompleto) {
					estaAlumno = true;
				}
			}
		} catch (IOException e) {
			System.out.println("No se encuentra el archivo");

		}
		System.out.println(estaAlumno);
		return estaAlumno;
	}

	private String direccion;
	private ArrayList<Libro> libros;
}