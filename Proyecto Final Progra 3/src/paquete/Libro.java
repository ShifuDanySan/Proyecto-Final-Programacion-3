package paquete;

public class Libro {
	public Libro(String isbn, String titulo, String autor, int cant) {
		this.isbn = isbn;
		this.titulo = titulo;
		this.autor = autor;
		this.cant = cant;
	}

	public String devolverIsbn() {
		return this.isbn;
	}

	public String devolverTitulo() {
		return this.titulo;
	}

	public String devolverAutor() {
		return this.autor;
	}

	public int devolverCantidadDeLibros() {
		return this.cant;
	}

	private String isbn;
	private String titulo;
	private String autor;
	private int cant;
}
