package fes.aragon.utilerias;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Herramientas {
	private int longitudPalabra=0;
	private int indicePalabra=0;
	private String palabra;
	public Herramientas() {
		// TODO Auto-generated constructor stub
	}
	public ArrayList<String> lectura() throws IOException{
		JFileChooser archivo=new JFileChooser(System.getProperty("user.dir"));
		archivo.setMultiSelectionEnabled(false);
        FileNameExtensionFilter filtro=new FileNameExtensionFilter("Archivo de texto", "txt");
        archivo.setFileFilter(filtro);
        ArrayList<String> lineas=new ArrayList<>();
        int seleccion=archivo.showOpenDialog(null);
        if(seleccion==JFileChooser.APPROVE_OPTION){
            File f=archivo.getSelectedFile();
            FileReader fr=new FileReader(f);
            BufferedReader bf=new BufferedReader(fr);
            String cad="";
            while((cad=bf.readLine())!=null){
                lineas.add(cad);
            }
            bf.close();
            fr.close();
        }
        return lineas;
		
	}
	public void setPalabra(String palabra){
        this.palabra=palabra;
        this.longitudPalabra=palabra.length();
        this.indicePalabra=0;
    }
    public char siguienteCaracter(){
        char c=' ';
        if(indicePalabra<longitudPalabra){
            c=palabra.charAt(indicePalabra);
            indicePalabra++;
        }
        return c;
    }
	public int getIndicePalabra() {
		return indicePalabra;
	}
    

}