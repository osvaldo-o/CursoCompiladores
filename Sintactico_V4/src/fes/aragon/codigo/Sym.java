/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fes.aragon.codigo;

/**
 *
 * @author MASH
 */
public class Sym {
  public static final int PUNTOCOMA =0;
  public static final int RESTA = 1;
  public static final int SUMA = 2; 
  public static final int DIV = 3;
  public static final int POR = 4;  
  public static final int NUM = 5;
  public static final int PARC = 6;
  public static final int PARA = 7;
  public static final int SALTOLINEA = 8;  
  public static final int EOF = 9;
  
  public static final String[] terminales = new String[] {
  "PUNTOCOMA",
  "SUMA",  
  "RESTA",
  "DIV",
  "POR",
  "PARC",
  "PARA",
  "SALTOLINEA",
  "EOF"
  };
}
