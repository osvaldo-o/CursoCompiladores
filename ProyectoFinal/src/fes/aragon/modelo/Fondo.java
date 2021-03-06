package fes.aragon.modelo;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import fes.aragon.compilador.parser;
import fes.aragon.extras.EfectosMusica;
import fes.aragon.inicio.Main;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Fondo extends ComponentesJuego {
	private int yy = 0;
	private int xx = 0;
	private int randomX = (int) (Math.random() * 10);
	private int randomY = (int) (Math.random() * 10);
	private Image arribaImg;
	private Image abajoImg;
	private Image derechaImg;
	private Image izquierdaImg;
	private Image imagen;
	private Image manzana;
	private Stage ventana;
	private ArrayList<String> comandos = new ArrayList<>();
	private int ancho = 40;
	private int alto = 40;
	private boolean iniciar = false;
	private GraphicsContext graficos;
	private int indice = 0;
	private int moverCuadros = 0;
	private String comando = "";
	private boolean arriba = false;
	private boolean abajo = false;
	private boolean derecha = false;
	private boolean izquierda = false;
	private boolean clear = false;
	private EfectosMusica musica;
	private parser inicio;

	public Fondo(int x, int y, String imagen, int velocidad, Stage ventana) {
		super(x, y, imagen, velocidad);
		this.derechaImg = new Image(imagen);
		this.izquierdaImg = new Image("/fes/aragon/recursos/izquierda.png");
		this.arribaImg = new Image("/fes/aragon/recursos/arriba.png");
		this.abajoImg = new Image("/fes/aragon/recursos/abajo.png");
		this.manzana = new Image("/fes/aragon/recursos/manzana.png");
		this.imagen = derechaImg;
		this.ventana = ventana;
		this.accionBotonRun();
		this.actionBotonClear();
	}

	@Override
	public void pintar(GraphicsContext graficos) {
		this.graficos = graficos;
		int xx = 50;
		int yy = 50;
		for (int j = 1; j <= 10; j++) {
			for (int i = 1; i <= 10; i++) {
				graficos.strokeRect(xx, yy, 50, 50);
				xx += 50;
			}
			xx = 50;
			yy += 50;
		}
		graficos.drawImage(imagen, x, y, ancho, alto);
		graficos.strokeRect(x, y, ancho, alto);
		if (!comandos.isEmpty() && !clear) {
			graficos.strokeText(comandos.get(indice), 100, 40);
		}else {
			graficos.strokeText("", 100, 40);
		}
		graficos.drawImage(manzana, (55 + (ancho + 10) * randomX), (55 + (ancho + 10) * randomY), 40, 40);
	}

	@Override
	public void teclado(KeyEvent evento, boolean presiona) {}

	@Override
	public void raton(KeyEvent evento) {}

	@Override
	public void logicaCalculos() {
		if (iniciar) {
			switch (this.comando) {
			case "arriba":
			case "abajo":
			case "izquierda":
			case "derecha":
				indice++;
				this.ejecutar();
				break;
			case "coloca":
				if (x < xx) {
					x += velocidad;
					this.imagen = this.derechaImg;
					graficos.clearRect(0, 0, 600, 600);
				} else {
					if (y < yy) {
						this.imagen = this.abajoImg;
						y += velocidad;
						graficos.clearRect(0, 0, 600, 600);
					}
				}
				if ((x >= xx) && (y >= yy)) {
					indice++;
					this.ejecutar();
				}

				break;

			case "mover":
				if (arriba) {
					if (y < 55) {
						JOptionPane.showMessageDialog(null, "Error, limite maximo arriba");
						iniciar();
						graficos.clearRect(0, 0, 600, 600);
					} else if (y > yy) {
						y -= velocidad;
						graficos.clearRect(0, 0, 600, 600);
					} else {
						indice++;
						this.ejecutar();
					}
				}
				if (abajo) {
					if (y > 505) {
						JOptionPane.showMessageDialog(null, "Error, limite maximo abajo");
						iniciar();
						graficos.clearRect(0, 0, 600, 600);
					} else if (y < yy) {
						y += velocidad;
						graficos.clearRect(0, 0, 600, 600);
					} else {
						indice++;
						this.ejecutar();
					}
				}
				if (izquierda) {
					if (x < 55) {
						JOptionPane.showMessageDialog(null, "Error, limite maximo izquierda");
						iniciar();
						graficos.clearRect(0, 0, 600, 600);
					} else if (x > xx) {
						x -= velocidad;
						graficos.clearRect(0, 0, 600, 600);
					} else {
						indice++;
						this.ejecutar();
					}
				}
				if (derecha) {
					if (x > 505) {
						JOptionPane.showMessageDialog(null, "Error, limite maximo derecha");
						iniciar();
						graficos.clearRect(0, 0, 600, 600);
					} else if (x < xx) {
						x += velocidad;
						graficos.clearRect(0, 0, 600, 600);
					} else {
						indice++;
						this.ejecutar();
					}
				}
			}
		}
	}

	private void ejecutar() {
		if (indice < comandos.size()) {
			String string = comandos.get(indice);
			String[] datos = string.split(" ");
			switch (datos[0]) {
			case "arriba":
				this.arriba = true;
				this.abajo = false;
				this.izquierda = false;
				this.derecha = false;
				this.imagen = this.arribaImg;
				this.comando = "arriba";
				break;
			case "abajo":
				this.arriba = false;
				this.abajo = true;
				this.izquierda = false;
				this.derecha = false;
				this.imagen = this.abajoImg;
				this.comando = "abajo";
				break;
			case "izquierda":
				this.arriba = false;
				this.abajo = false;
				this.izquierda = true;
				this.derecha = false;
				this.imagen = this.izquierdaImg;
				this.comando = "izquierda";
				break;
			case "derecha":
				this.arriba = false;
				this.abajo = false;
				this.izquierda = false;
				this.derecha = true;
				this.imagen = this.derechaImg;
				this.comando = "derecha";
				break;
			case "coloca":
				x = 55;
				y = 55;
				xx = Integer.parseInt(datos[1]);
				yy = Integer.parseInt(datos[2]);
				if (xx < 11 && yy < 11) {
					xx = (x + (ancho + 10) * (xx - 1));
					yy = (y + (alto + 10) * (yy - 1));
					this.comando = "coloca";
				} else {
					JOptionPane.showMessageDialog(null, "Limite maximo, intentalo de nuevo :)");
					indice++;
					ejecutar();
				}
				break;
			case "mover":
				moverCuadros = Integer.parseInt(datos[1]);
				if (arriba) {
					yy = (y - (alto + 10) * moverCuadros);
				}
				if (abajo) {
					yy = (y + (alto + 10) * moverCuadros);
				}
				if (izquierda) {
					xx = (x - (ancho + 10) * moverCuadros);
				}
				if (derecha) {
					xx = (x + (ancho + 10) * moverCuadros);
				}
				// mover derecha si no se indica ninguna direccion
				if (!arriba && !abajo && !izquierda && !derecha) {
					xx = (x + (ancho + 10) * moverCuadros);
					this.arriba = false;
					this.abajo = false;
					this.izquierda = false;
					this.derecha = true;
					this.imagen = this.derechaImg;
					this.comando = "derecha";
				}
				this.comando = "mover";
				break;
			case "ver":
				if (xx == (55 + (ancho + 10) * randomX) && yy == (55 + (ancho + 10) * randomY)) {
					musica = new EfectosMusica("victoria");
					musica.run();
				} else {
					while (!comandos.get(indice).equals("}")) {
						indice++;
					}
				}
			default:
				indice++;
				this.ejecutar();
				break;
			}

		} else {
			this.iniciar = false;
			indice = 1;
		}

	}

	private void abrirArchivo() throws IOException, ClassNotFoundException {
		FileChooser archivos = new FileChooser();
		archivos.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Archivos compilador", "salida.txt"));
		archivos.setTitle("Abrir archivo de Compilador");
		archivos.setInitialDirectory(new File(System.getProperty("user.dir")));
		File ruta = archivos.showOpenDialog(this.ventana);
		if (ruta != null) {
			FileReader fr = new FileReader(ruta);
			BufferedReader buff = new BufferedReader(fr);
			String cad;
			this.comandos.clear();
			this.iniciar();
			while ((cad = buff.readLine()) != null) {
				comandos.add(cad);
			}
			buff.close();
			fr.close();
		}

	}
	
	private void accionBotonRun() {
		Main.getButonRun().setOnAction(a -> {
			inicio = new parser();
			try {
				inicio.cargar(new ByteArrayInputStream(Main.textArea.getText().getBytes(StandardCharsets.UTF_8)));
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			if (inicio.valido) {
				try {
					this.abrirArchivo();
					graficos.clearRect(0, 0, 600, 600);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				iniciar();
				this.ejecutar();
				this.iniciar = true;
				graficos.clearRect(0, 0, 600, 600);
			}else {
				JOptionPane.showMessageDialog(null,inicio.textError);
				inicio.valido = true;
				inicio.textError = "";
			}
		});
		
	}
	
	private void actionBotonClear() {
		Main.getButonClear().setOnAction(a ->{
			Main.textArea.clear();
			iniciar();
			graficos.clearRect(0, 0, 600, 600);
			clear = true;
		});
	}

	private void iniciar() {
		x = 55;
		y = 55;
		xx = 0;
		yy = 0;
		indice = 0;
		this.imagen = this.derechaImg;
		moverCuadros = 0;
		comando = "";
		arriba = false;
		abajo = false;
		derecha = false;
		izquierda = false;
		clear = false;
	}
}
