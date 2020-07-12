package paquete;

public class Libro {
	private String isbn;
	private String titulo;
	private String autor;
	private int cant;
	
	public Libro(String isbn, String titulo, String autor, int cant) {
		this.isbn=isbn;
		this.titulo=titulo;
		this.autor= autor;
		this.cant=cant;
	}
	
	public String dameIsbn() {
		return this.isbn;
	}
	
	public String dameTitulo() {
		return this.titulo;
		
	}
	public String dameAutor() {
		return this.autor;
	}
	
	public int dameCantidadDeLibros() {
		return this.cant;
	}
	
}
