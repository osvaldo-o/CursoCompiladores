package fes.aragon.compilador;

import java.net.URISyntaxException;

public class Principal {
	private static parser inicio;
    private String ruta;

    public Principal() {
    }

    public String getRuta() throws URISyntaxException {
        ruta=this.getClass().getResource("/fes/aragon/compilador/Fuente.txt")
                .toURI().getPath();
        return ruta;
    }
    
    public static void main(String[] args) {
    	Principal app=new Principal();
        inicio=new parser();
        try {
            inicio.cargar(app.getRuta());
        } catch (URISyntaxException ex) {
            //ex.printStackTrace();
        }catch (Exception ex) {
            //ex.printStackTrace();   
        }
    }
     public parser getInicio() {
    	 return inicio;
     }
}
