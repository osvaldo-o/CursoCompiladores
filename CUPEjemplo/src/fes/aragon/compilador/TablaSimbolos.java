/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fes.aragon.compilador;

import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author Administrador
 */
public class TablaSimbolos {

    private HashMap t;

    public TablaSimbolos() {
        t = new HashMap();
    }
    public Datos buscar(String nombre) {
        return (Datos) (t.get(nombre));
    }

    public void imprimir() {
        System.out.println("Valores que contiene Tabla Simbolos");
        Iterator it = t.values().iterator();
        while (it.hasNext()) {            
            Datos s = (Datos) it.next();
            System.out.println(s.getNombreVariable() + ": " + s.getValor());
        }
    }

}
