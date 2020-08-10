package paquete;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class Ventana {

	private JFrame frame;

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventana window = new Ventana();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Ventana() {
		initialize();
	}

	private void initialize() {
		usuariosPermitidos = new Archivo("Usuarios Permitidos.txt");
		libros = new Archivo("Libros.txt");
		// guardamos los datos del archivo libros.txt en un ArrayList (uno de los
		// atributos de la clase Archivo)
		libros.guardarDatosDeLibros();

		prestamos = new Archivo("Prestamos.txt");
		devoluciones = new Archivo("Devoluciones.txt");
		alumnosPermitidos = new Archivo("Alumnos Permitidos.txt");

		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(200, 100, 800, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 784, 460);
		frame.getContentPane().add(tabbedPane);

		panelIngreso = new JPanel();
		panelIngreso.setToolTipText("INGRESAR");
		tabbedPane.addTab("INGRESAR", null, panelIngreso, null);
		panelIngreso.setLayout(new CardLayout(0, 0));

		panel_Ingresar = new JPanel();
		panel_Ingresar.setToolTipText("INGRESAR");
		panelIngreso.add(panel_Ingresar, "name_1210557668120393");
		panel_Ingresar.setLayout(null);

		cajaTextoUsuario = new JTextField();
		cajaTextoUsuario.setBounds(655, 11, 114, 20);
		panel_Ingresar.add(cajaTextoUsuario);
		cajaTextoUsuario.setColumns(10);

		passwordUsuario = new JPasswordField();
		passwordUsuario.setBounds(655, 48, 114, 20);
		panel_Ingresar.add(passwordUsuario);

		JLabel labelUsuario = new JLabel("USUARIO");
		labelUsuario.setFont(new Font("Tahoma", Font.BOLD, 20));
		labelUsuario.setForeground(Color.GREEN);
		labelUsuario.setBounds(539, 14, 106, 14);
		panel_Ingresar.add(labelUsuario);

		JLabel labelContrasena = new JLabel("CONTRASE\u00D1A");
		labelContrasena.setForeground(Color.ORANGE);
		labelContrasena.setFont(new Font("Tahoma", Font.BOLD, 20));
		labelContrasena.setBounds(510, 49, 148, 23);
		panel_Ingresar.add(labelContrasena);

		botonIngresar = new JButton("INGRESAR");
		botonIngresar.setForeground(Color.YELLOW);
		botonIngresar.setBackground(Color.RED);
		botonIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				usuarioIngresado = cajaTextoUsuario.getText();
				contrasenaIngresada = passwordUsuario.getText();
				cajaTextoUsuario.setText("");
				passwordUsuario.setText("");
				cajaTextoUsuario.requestFocus();

				/*
				 * revisamos en el archivo de texto: Usuarios Permitidos.txt si el usuario y
				 * contraseña que ingresamos coincide con los datos de los usuarios del archivo
				 * de texto(que son los autorizados para gestionar la biblioteca)
				 */

				if (usuariosPermitidos.esUsuarioPermitido(usuarioIngresado, contrasenaIngresada) == true) {
					panel_Ingresar.setVisible(false);
					panel_Bienvenida.setVisible(true);
					debeIniciarSesion.setVisible(false);
					panelGestionLibros.setVisible(true);
					// Permitimos el ingreso del usuario habilitando las vistas de los paneles
					// correspondientes
				} else {
					JOptionPane.showMessageDialog(null, "USUARIO O CONTRASEÑA INCORRECTO");
				}
			}
		});
		botonIngresar.setBounds(655, 91, 114, 34);
		panel_Ingresar.add(botonIngresar);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(Ventana.class.getResource("/paquete/biblio.jpg")));
		lblNewLabel.setBounds(0, -27, 789, 460);
		panel_Ingresar.add(lblNewLabel);

		panel_Bienvenida = new JPanel();
		panel_Bienvenida.setBackground(new Color(0, 255, 0));
		panelIngreso.add(panel_Bienvenida, "name_1210561213410605");
		panel_Bienvenida.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("BIENVENIDO");
		lblNewLabel_1.setForeground(Color.ORANGE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 60));
		lblNewLabel_1.setBounds(128, 0, 405, 125);
		panel_Bienvenida.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setToolTipText("");
		lblNewLabel_2.setBackground(new Color(0, 255, 0));
		lblNewLabel_2.setIcon(new ImageIcon(Ventana.class.getResource("/paquete/einstein.jpg")));
		lblNewLabel_2.setBounds(0, 0, 590, 433);
		panel_Bienvenida.add(lblNewLabel_2);

		botonCerrarSesion = new JButton("CERRAR SESI\u00D3N");
		botonCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelGestionLibros.setVisible(false);
				panelPrestamos.setVisible(false);
				panel_Bienvenida.setVisible(false);
				debeIniciarSesion.setVisible(true);
				panel_Ingresar.setVisible(true);
			}
		});
		botonCerrarSesion.setForeground(new Color(255, 255, 0));
		botonCerrarSesion.setBackground(new Color(255, 0, 0));
		botonCerrarSesion.setBounds(630, 390, 139, 32);
		panel_Bienvenida.add(botonCerrarSesion);

		lblNewLabel_5 = new JLabel("AHORA PUEDE");
		lblNewLabel_5.setFont(new Font("Sitka Small", Font.BOLD, 18));
		lblNewLabel_5.setForeground(new Color(0, 0, 255));
		lblNewLabel_5.setBounds(600, 57, 175, 53);
		panel_Bienvenida.add(lblNewLabel_5);

		lblNewLabel_6 = new JLabel("VISUALIZAR EL");
		lblNewLabel_6.setForeground(new Color(0, 0, 255));
		lblNewLabel_6.setFont(new Font("Sitka Small", Font.BOLD, 18));
		lblNewLabel_6.setBounds(600, 121, 169, 26);
		panel_Bienvenida.add(lblNewLabel_6);

		lblNewLabel_7 = new JLabel("CONTENIDO DE");
		lblNewLabel_7.setForeground(new Color(0, 0, 255));
		lblNewLabel_7.setFont(new Font("Sitka Small", Font.BOLD, 18));
		lblNewLabel_7.setBounds(600, 192, 157, 26);
		panel_Bienvenida.add(lblNewLabel_7);

		lblNewLabel_8 = new JLabel("LAS PESTA\u00D1AS");
		lblNewLabel_8.setForeground(new Color(0, 0, 205));
		lblNewLabel_8.setFont(new Font("Sitka Small", Font.BOLD, 18));
		lblNewLabel_8.setBounds(600, 242, 157, 23);
		panel_Bienvenida.add(lblNewLabel_8);

		panelGestionarLibros = new JPanel();
		tabbedPane.addTab("GESTIONAR LIBROS", null, panelGestionarLibros, null);
		panelGestionarLibros.setLayout(new CardLayout(0, 0));

		debeIniciarSesion = new JPanel();
		debeIniciarSesion.setBackground(new Color(255, 215, 0));
		panelGestionarLibros.add(debeIniciarSesion, "name_1218808854568826");
		debeIniciarSesion.setLayout(null);

		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(Ventana.class.getResource("/paquete/Tortuga Veloz.jpg")));
		lblNewLabel_3.setBounds(125, 99, 549, 311);
		debeIniciarSesion.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("DEBE INICIAR SESI\u00D3N");
		lblNewLabel_4.setForeground(new Color(0, 0, 205));
		lblNewLabel_4.setFont(new Font("Tempus Sans ITC", Font.BOLD, 40));
		lblNewLabel_4.setBounds(125, 35, 470, 53);
		debeIniciarSesion.add(lblNewLabel_4);

		panelGestionLibros = new JPanel();
		panelGestionarLibros.add(panelGestionLibros, "name_1218813451784380");
		panelGestionLibros.setLayout(null);

		btnNewButton = new JButton("NUEVO PRESTAMO DE LIBRO");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setBackground(new Color(255, 255, 0));
		btnNewButton.setForeground(new Color(0, 0, 255));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelGestionLibros.setVisible(false);
				panelPrestamos.setVisible(true);
			}
		});
		btnNewButton.setBounds(56, 38, 205, 131);
		panelGestionLibros.add(btnNewButton);

		btnNewButton_1 = new JButton("DEVOLUCI\u00D3N DE LIBRO");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelGestionLibros.setVisible(false);
				panelDevoluciones.setVisible(true);
			}
		});
		btnNewButton_1.setForeground(Color.ORANGE);
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_1.setBackground(new Color(255, 0, 255));
		btnNewButton_1.setBounds(450, 38, 183, 131);
		panelGestionLibros.add(btnNewButton_1);

		btnNewButton_2 = new JButton("INGRESAR NUEVO LIBRO");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelGestionLibros.setVisible(false);
				panelIngresarNuevosLibros.setVisible(true);
			}
		});
		btnNewButton_2.setForeground(Color.GREEN);
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_2.setBackground(new Color(0, 128, 0));
		btnNewButton_2.setBounds(56, 270, 205, 131);
		panelGestionLibros.add(btnNewButton_2);

		btnNewButton_3 = new JButton("BORRAR DATOS DE LIBRO");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelGestionLibros.setVisible(false);
				panelBorrarDatosLibro.setVisible(true);
			}
		});
		btnNewButton_3.setForeground(Color.YELLOW);
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_3.setBackground(new Color(255, 0, 0));
		btnNewButton_3.setBounds(450, 270, 183, 131);
		panelGestionLibros.add(btnNewButton_3);

		lblNewLabel_9 = new JLabel("");
		lblNewLabel_9.setIcon(new ImageIcon(Ventana.class.getResource("/paquete/samurai.jpg")));
		lblNewLabel_9.setBounds(0, 0, 779, 433);
		panelGestionLibros.add(lblNewLabel_9);
		panelGestionLibros.setVisible(false);

		panelPrestamos = new JPanel();
		panelGestionarLibros.add(panelPrestamos, "name_1220272844216280");
		panelPrestamos.setLayout(null);

		lblNewLabel_10 = new JLabel("PRESTAMOS");
		lblNewLabel_10.setForeground(Color.BLUE);
		lblNewLabel_10.setFont(new Font("Trebuchet MS", Font.BOLD, 40));
		lblNewLabel_10.setBounds(280, 11, 341, 59);
		panelPrestamos.add(lblNewLabel_10);

		botonVolverDePrestamo = new JButton("VOLVER");
		botonVolverDePrestamo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelPrestamos.setVisible(false);
				panelGestionLibros.setVisible(true);
			}
		});
		botonVolverDePrestamo.setBounds(659, 381, 89, 23);
		panelPrestamos.add(botonVolverDePrestamo);

		JLabel lblISBN = new JLabel("ISBN");
		lblISBN.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblISBN.setBounds(52, 110, 64, 23);
		panelPrestamos.add(lblISBN);

		textISBN = new JTextField();
		textISBN.setBounds(133, 114, 86, 20);
		panelPrestamos.add(textISBN);
		textISBN.setColumns(10);

		JLabel lblNewLabel_14 = new JLabel("Datos del alumno:");
		lblNewLabel_14.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_14.setBounds(52, 144, 167, 48);
		panelPrestamos.add(lblNewLabel_14);

		JLabel lblNombreAlumno = new JLabel("Nombre");
		lblNombreAlumno.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNombreAlumno.setBounds(52, 203, 64, 30);
		panelPrestamos.add(lblNombreAlumno);

		textNombreAlumno = new JTextField();
		textNombreAlumno.setBounds(133, 210, 86, 20);
		panelPrestamos.add(textNombreAlumno);
		textNombreAlumno.setColumns(10);

		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblApellido.setBounds(52, 244, 64, 30);
		panelPrestamos.add(lblApellido);

		textApellidoAlumno = new JTextField();
		textApellidoAlumno.setBounds(133, 251, 86, 20);
		panelPrestamos.add(textApellidoAlumno);
		textApellidoAlumno.setColumns(10);

		textDniAlumno = new JTextField();
		textDniAlumno.setBounds(133, 305, 86, 20);
		panelPrestamos.add(textDniAlumno);
		textDniAlumno.setColumns(10);
		textDniAlumno.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				if ((int) arg0.getKeyChar() < 48 || (int) arg0.getKeyChar() > 57) {
					arg0.consume();
				}
			}
		});

		btnConfirmarPrestamo = new JButton("Confirmar Prestamo");
		btnConfirmarPrestamo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnConfirmarPrestamo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String isbn = textISBN.getText();
				String nombreAlumno = textNombreAlumno.getText();
				String apellidoAlumno = textApellidoAlumno.getText();
				String dniAlumno = textDniAlumno.getText();
				String fechaRetiro = textFechaRetiro.getText();
				textISBN.setText("");
				textNombreAlumno.setText("");
				textApellidoAlumno.setText("");
				textDniAlumno.setText("");
				textFechaRetiro.setText("");
				textISBN.requestFocus();
				if ((isbn.equalsIgnoreCase("") == false) && (nombreAlumno.equalsIgnoreCase("") == false)
						&& (apellidoAlumno.equalsIgnoreCase("") == false) && (dniAlumno.equalsIgnoreCase("") == false)
						&& (fechaRetiro.equalsIgnoreCase("") == false)) {
					if (libros.estaLibro(isbn) == true) {
						String datosLibros[] = libros.devolverDatosDeLibro(isbn);
						int cantLibros = Integer.parseInt(datosLibros[2]);

						if (cantLibros > 0) {

							String datosPrestamos = "";
							datosPrestamos += nombreAlumno + " " + apellidoAlumno + " " + dniAlumno + " " + fechaRetiro
									+ " " + isbn + " " + datosLibros[0] + " " + datosLibros[1];

							prestamos.escribeArchivo(datosPrestamos);
							libros.disminuirStockLibro(isbn);

							JOptionPane.showMessageDialog(null, "El prestamo ha sido registrado");
						} else {
							JOptionPane.showMessageDialog(null, "NO hay Stock disponible para el libro solicitado");
						}

					} else {
						JOptionPane.showMessageDialog(null, "El libro solicitado NO se encuentra registrado");
					}
				} else {
					JOptionPane.showMessageDialog(null,
							"Recuerde rellenar todos los campos requeridos para acceder al prestamo");
				}
			}
		});
		btnConfirmarPrestamo.setBounds(52, 365, 149, 23);
		panelPrestamos.add(btnConfirmarPrestamo);

		lblFechaRetiro = new JLabel("Fecha de retiro");
		lblFechaRetiro.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblFechaRetiro.setBounds(391, 102, 136, 40);
		panelPrestamos.add(lblFechaRetiro);

		textFechaRetiro = new JTextField();
		textFechaRetiro.setBounds(555, 114, 86, 20);
		panelPrestamos.add(textFechaRetiro);
		textFechaRetiro.setColumns(10);

		JLabel lblDniAlumno = new JLabel("DNI");
		lblDniAlumno.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblDniAlumno.setBounds(52, 301, 64, 23);
		panelPrestamos.add(lblDniAlumno);

		panelDevoluciones = new JPanel();
		panelGestionarLibros.add(panelDevoluciones, "name_1226451858893587");
		panelDevoluciones.setLayout(null);

		lblNewLabel_11 = new JLabel("DEVOLUCIONES");
		lblNewLabel_11.setForeground(Color.ORANGE);
		lblNewLabel_11.setFont(new Font("Trebuchet MS", Font.BOLD, 40));
		lblNewLabel_11.setBounds(247, 11, 332, 61);
		panelDevoluciones.add(lblNewLabel_11);

		botonVolverDeDevoluciones = new JButton("VOLVER");
		botonVolverDeDevoluciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelDevoluciones.setVisible(false);
				panelGestionLibros.setVisible(true);
			}
		});
		botonVolverDeDevoluciones.setBounds(680, 399, 89, 23);
		panelDevoluciones.add(botonVolverDeDevoluciones);

		lblIsbnDevuelto = new JLabel("ISBN");
		lblIsbnDevuelto.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblIsbnDevuelto.setBounds(55, 113, 75, 23);
		panelDevoluciones.add(lblIsbnDevuelto);

		textIsbn = new JTextField();
		textIsbn.setBounds(132, 116, 86, 20);
		panelDevoluciones.add(textIsbn);
		textIsbn.setColumns(10);

		lblDni = new JLabel("Dni de Alumno");
		lblDni.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDni.setBounds(46, 187, 113, 34);
		panelDevoluciones.add(lblDni);

		textDni = new JTextField();
		textDni.setBounds(168, 196, 86, 20);
		panelDevoluciones.add(textDni);
		textDni.setColumns(10);
		textDni.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				if ((int) arg0.getKeyChar() < 48 || (int) arg0.getKeyChar() > 57) {
					arg0.consume();
				}
			}
		});

		lblFechaDevolucion = new JLabel("Fecha de devolucion");
		lblFechaDevolucion.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblFechaDevolucion.setBounds(46, 249, 151, 34);
		panelDevoluciones.add(lblFechaDevolucion);

		textFechaDevolucion = new JTextField();
		textFechaDevolucion.setBounds(223, 258, 86, 20);
		panelDevoluciones.add(textFechaDevolucion);
		textFechaDevolucion.setColumns(10);

		JButton btnConfirmarDevolucion = new JButton("Confirmar Devolucion");
		btnConfirmarDevolucion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String isbn = textIsbn.getText();
				String dniAlumno = textDni.getText();
				String fechaDevolucion = textFechaDevolucion.getText();
				textIsbn.setText("");
				textDni.setText("");
				textFechaDevolucion.setText("");
				textIsbn.requestFocus();
				if ((isbn.equalsIgnoreCase("") == false) && (dniAlumno.equalsIgnoreCase("") == false)
						&& (fechaDevolucion.equalsIgnoreCase("") == false)) {
					if (libros.estaLibro(isbn) == true) {
						// String datosLibros[] = libros.devolverDatosDeLibro(isbn);
						// int cantLibros = Integer.parseInt(datosLibros[2]);
						/*
						 * String datosPrestamos = ""; datosPrestamos += nombreAlumno + " " +
						 * apellidoAlumno + " " + dniAlumno + " " + fechaRetiro + " " + isbn + " " +
						 * datosLibros[0] + " " + datosLibros[1];
						 * 
						 * prestamos.escribeArchivo(datosPrestamos);
						 */
						libros.aumentarStockLibro(isbn);

						JOptionPane.showMessageDialog(null, "La Devolucion ha sido registrada");

					} else {
						JOptionPane.showMessageDialog(null, "El libro solicitado NO se encuentra registrado");
					}
				} else {
					JOptionPane.showMessageDialog(null,
							"Recuerde rellenar todos los campos para poder realizar la devolucion un libro");
				}
			}
		});
		btnConfirmarDevolucion.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnConfirmarDevolucion.setBounds(41, 350, 156, 34);
		panelDevoluciones.add(btnConfirmarDevolucion);

		panelIngresarNuevosLibros = new JPanel();
		panelGestionarLibros.add(panelIngresarNuevosLibros, "name_1226543394292916");
		panelIngresarNuevosLibros.setLayout(null);

		lblNewLabel_12 = new JLabel("NUEVO LIBRO");
		lblNewLabel_12.setForeground(Color.GREEN);
		lblNewLabel_12.setFont(new Font("Trebuchet MS", Font.BOLD, 40));
		lblNewLabel_12.setBounds(263, 11, 276, 38);
		panelIngresarNuevosLibros.add(lblNewLabel_12);

		btnNewButton_4 = new JButton("VOLVER");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelIngresarNuevosLibros.setVisible(false);
				panelGestionLibros.setVisible(true);
			}
		});
		btnNewButton_4.setBounds(680, 399, 89, 23);
		panelIngresarNuevosLibros.add(btnNewButton_4);

		JLabel lblIsbnNuevo = new JLabel("ISBN");
		lblIsbnNuevo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblIsbnNuevo.setBounds(57, 108, 64, 17);
		panelIngresarNuevosLibros.add(lblIsbnNuevo);

		textIsbnNuevo = new JTextField();
		textIsbnNuevo.setBounds(119, 108, 86, 20);
		panelIngresarNuevosLibros.add(textIsbnNuevo);
		textIsbnNuevo.setColumns(10);

		JLabel lblTituloNuevo = new JLabel("Titulo");
		lblTituloNuevo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTituloNuevo.setBounds(57, 163, 64, 23);
		panelIngresarNuevosLibros.add(lblTituloNuevo);

		textTituloNuevo = new JTextField();
		textTituloNuevo.setBounds(119, 166, 86, 20);
		panelIngresarNuevosLibros.add(textTituloNuevo);
		textTituloNuevo.setColumns(10);

		JLabel lblAutorNuevo = new JLabel("Autor");
		lblAutorNuevo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAutorNuevo.setBounds(57, 233, 64, 23);
		panelIngresarNuevosLibros.add(lblAutorNuevo);

		textAutorNuevo = new JTextField();
		textAutorNuevo.setBounds(119, 236, 86, 20);
		panelIngresarNuevosLibros.add(textAutorNuevo);
		textAutorNuevo.setColumns(10);

		JLabel lblCantidadDeNuevoLibro = new JLabel("Cantidad");
		lblCantidadDeNuevoLibro.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCantidadDeNuevoLibro.setBounds(57, 300, 75, 23);
		panelIngresarNuevosLibros.add(lblCantidadDeNuevoLibro);

		textCantidadDeNuevoLibro = new JTextField();
		textCantidadDeNuevoLibro.setBounds(134, 303, 86, 20);
		panelIngresarNuevosLibros.add(textCantidadDeNuevoLibro);
		textCantidadDeNuevoLibro.setColumns(10);
		textCantidadDeNuevoLibro.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				if ((int) arg0.getKeyChar() < 48 || (int) arg0.getKeyChar() > 57) {
					arg0.consume();
				}
			}
		});

		btnGuardarLibro = new JButton("Guardar libro");
		btnGuardarLibro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String isbn = textIsbnNuevo.getText();
				String titulo = textTituloNuevo.getText();
				String autor = textAutorNuevo.getText();
				String cantidad = textCantidadDeNuevoLibro.getText();
				textIsbnNuevo.setText("");
				textTituloNuevo.setText("");
				textAutorNuevo.setText("");
				textCantidadDeNuevoLibro.setText("");
				textIsbnNuevo.requestFocus();
				if ((isbn.equalsIgnoreCase("") == false) && (titulo.equalsIgnoreCase("") == false)
						&& (autor.equalsIgnoreCase("") == false) && (cantidad.equalsIgnoreCase("") == false)) {
					if (libros.estaLibro(isbn) == false) {
						String datosNuevoLibro = "";
						datosNuevoLibro += isbn + "\r\n" + titulo + "\r\n" + autor + "\r\n" + cantidad;
						libros.escribeArchivo(datosNuevoLibro);
						Libro nuevoLibro = new Libro(isbn, titulo, autor, Integer.parseInt(cantidad));
						libros.agregarNuevoLibro(nuevoLibro);
						JOptionPane.showMessageDialog(null,
								"LOS DATOS DEL NUEVO LIBRO HAN INGRESADOS CON EXITO AL SISTEMA!");
					} else {
						JOptionPane.showMessageDialog(null,
								"El libro ingresado ya se encuentra registrado en el sistema");
					}
				} else {
					JOptionPane.showMessageDialog(null,
							"Recuerde rellenar todos los campos para registrar un nuevo libro");
				}
			}
		});
		btnGuardarLibro.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnGuardarLibro.setBounds(57, 358, 121, 32);
		panelIngresarNuevosLibros.add(btnGuardarLibro);

		panelBorrarDatosLibro = new JPanel();
		panelGestionarLibros.add(panelBorrarDatosLibro, "name_1226590364812626");
		panelBorrarDatosLibro.setLayout(null);

		lblNewLabel_13 = new JLabel("BORRAR DATOS DE LIBRO");
		lblNewLabel_13.setForeground(Color.RED);
		lblNewLabel_13.setFont(new Font("Trebuchet MS", Font.BOLD, 40));
		lblNewLabel_13.setBounds(171, 11, 495, 59);
		panelBorrarDatosLibro.add(lblNewLabel_13);

		btnNewButton_5 = new JButton("VOLVER");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelBorrarDatosLibro.setVisible(false);
				panelGestionLibros.setVisible(true);
			}
		});
		btnNewButton_5.setBounds(680, 399, 89, 23);
		panelBorrarDatosLibro.add(btnNewButton_5);

		lblIsbnEliminar = new JLabel("ISBN");
		lblIsbnEliminar.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblIsbnEliminar.setBounds(66, 131, 58, 23);
		panelBorrarDatosLibro.add(lblIsbnEliminar);

		textIsbnEliminar = new JTextField();
		textIsbnEliminar.setBounds(133, 134, 86, 20);
		panelBorrarDatosLibro.add(textIsbnEliminar);
		textIsbnEliminar.setColumns(10);

		btnEliminar = new JButton("Eliminar libro");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ISBNIngresado = "";
				ISBNIngresado += textIsbnEliminar.getText();
				libros.eliminarLibro(ISBNIngresado);
				textIsbnEliminar.setText("");
				textIsbnEliminar.requestFocus();
				ISBNIngresado = "";
			}
		});
		btnEliminar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnEliminar.setBounds(66, 214, 119, 35);
		panelBorrarDatosLibro.add(btnEliminar);
		panelPrestamos.setVisible(false);
	}

	JPanel panelIngreso;
	JPanel panel_Ingresar;
	JButton botonIngresar;
	JPanel panel_Bienvenida;
	JPanel debeIniciarSesion;
	JButton botonCerrarSesion;
	JPanel panelGestionarLibros;
	JPanel panelGestionLibros;
	JPanel panelPrestamos;

	private JButton btnGuardarLibro;
	private JButton btnEliminar;
	private String usuarioIngresado = "";
	private String contrasenaIngresada = "";
	private JTextField cajaTextoUsuario;
	private JPasswordField passwordUsuario;
	private JButton btnConfirmarPrestamo;
	private Archivo usuariosPermitidos;
	private Archivo libros;
	private Archivo prestamos;
	private Archivo devoluciones;
	private Archivo alumnosPermitidos;
	private JButton btnNewButton;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_7;
	private JLabel lblNewLabel_8;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JButton btnNewButton_3;
	private JLabel lblNewLabel_9;
	private JPanel panelDevoluciones;
	private JPanel panelIngresarNuevosLibros;
	private JPanel panelBorrarDatosLibro;
	private JLabel lblNewLabel_10;
	private JLabel lblNewLabel_11;
	private JLabel lblNewLabel_12;
	private JLabel lblNewLabel_13;
	private JButton botonVolverDePrestamo;
	private JButton botonVolverDeDevoluciones;
	private JButton btnNewButton_4;
	private JButton btnNewButton_5;
	private JTextField textISBN;
	private JTextField textNombreAlumno;
	private JTextField textApellidoAlumno;
	private JLabel lblFechaRetiro;
	private JTextField textFechaRetiro;
	private JTextField textDniAlumno;
	private JLabel lblIsbnEliminar;
	private JTextField textIsbnEliminar;
	private JLabel lblIsbnDevuelto;
	private JTextField textIsbn;
	private JLabel lblDni;
	private JTextField textDni;
	private JLabel lblFechaDevolucion;
	private JTextField textFechaDevolucion;
	private JTextField textIsbnNuevo;
	private JTextField textTituloNuevo;
	private JTextField textAutorNuevo;
	private JTextField textCantidadDeNuevoLibro;
	private String ISBNIngresado = "";
}
