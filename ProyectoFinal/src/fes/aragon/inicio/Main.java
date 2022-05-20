package fes.aragon.inicio;

import fes.aragon.compilador.parser;
import fes.aragon.extras.MusicaCiclica;
import fes.aragon.modelo.Fondo;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Main extends Application {
	private GraphicsContext graficos;
	private Group root;
	private Scene escena;
	private Canvas hoja;
	private Thread hiloFondo;
	private Fondo fondo;
	private Stage ventana;
	public static TextArea textArea;
	private static Button btnRun;
	private static Button btnClear;
	private parser inicio;
	private String ruta;

	@Override
	public void start(Stage ventana) {
		this.ventana=ventana;
		componentesIniciar();
		pintar();
		eventosTeclado();		
		this.ventana.setResizable(false);
		this.ventana.setScene(escena);
		this.ventana.setTitle("Interprete");
		this.ventana.show();
		ciclo();
	}
	
	
	@SuppressWarnings("deprecation")
	@Override
	public void stop() throws Exception {
		hiloFondo.stop();		
	}


	public static void main(String[] args) {
		launch(args);
	}

	private void componentesIniciar() {
		BorderPane contenedorDerecha = new BorderPane();
		HBox root = new HBox();
		HBox contenedorBotones = new HBox();
		textArea = new TextArea();
		textArea.setPrefColumnCount(14);
		BorderPane.setMargin(textArea, new Insets(5, 5, 5, 5));
		btnRun = new Button("RUN");
		btnClear = new Button("CLEAR");
		hoja = new Canvas(600, 600);	
		contenedorBotones.setSpacing(10);
		contenedorBotones.getChildren().addAll(btnRun,btnClear);
		contenedorDerecha.setTop(contenedorBotones);
		contenedorDerecha.setCenter(textArea);
		root.getChildren().addAll(hoja,contenedorDerecha);
		escena = new Scene(root, 880, 600);		
		BorderPane.setMargin(contenedorBotones, new Insets(10, 10, 10, 10));
		root.getStylesheets().add("fes/aragon/inicio/estilos.css");
		MusicaCiclica entrada = new MusicaCiclica("melodia");
		hiloFondo = new Thread(entrada);
		//hiloFondo.start();	  
		fondo=new Fondo(55, 55,"/fes/aragon/recursos/derecha.png" ,2,ventana);
		graficos = hoja.getGraphicsContext2D();
	}
	
	public void ciclo() {
		long tiempoInicio=System.nanoTime();
		AnimationTimer tiempo=new AnimationTimer() {			
			@Override
			public void handle(long tiempoActual) {
				double t=(tiempoActual-tiempoInicio)/1000000000.0;
				calculosLogica();
				pintar();	
			}
		};
		tiempo.start();
	}
	
	private void eventosTeclado() {
		escena.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent arg0) {				
				fondo.teclado(arg0,true);
			}			
		});	
		
	}
	
	private void pintar() { this.fondo.pintar(graficos); }
	
	private void calculosLogica() { this.fondo.logicaCalculos(); }
	
	public static Button getButonRun() { return btnRun; }	
	
	public static Button getButonClear() { return btnClear; }
}
