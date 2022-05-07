package fes.aragon.compilador;
import java_cup.runtime.Symbol;
import java.io.Reader;
%%
%{	
	public int getYyline() {
                return yyline;
        }

        public int getYy_currentPos() {
            return yy_currentPos-1;
        }
%}
%class Lexico
%public
%char
%line
%cup
%ignorecase
%type java_cup.runtime.Symbol
//%implements java_cup.runtime.Scanner
%eofval{
 return new Symbol(sym.EOF,new String("Fin del archivo"));
//return null;
%eofval}
DIGITO=[0-9]
INT={DIGITO}+
%%
"repetir" {System.out.println("repetir"); return new Symbol(sym.REPETIR); }
"ver" {System.out.println("ver"); return new Symbol(sym.VER); }
"fruta" {System.out.println("fruta"); return new Symbol(sym.FRUTA); }
"coloca" {System.out.println("coloca"); return new Symbol(sym.COLOCA); }
";" {System.out.println("puntocoma"); return new Symbol(sym.PUNTOYCOMA); }
"mover" {System.out.println("mover"); return new Symbol(sym.MOVER); }
"arriba" {System.out.println("arriba"); return new Symbol(sym.ARRIBA); }
"abajo" {System.out.println("abajo"); return new Symbol(sym.ABAJO); }
"izquierda" {System.out.println("izquierda"); return new Symbol(sym.IZQ); }
"derecha" {System.out.println("derecha"); return new Symbol(sym.DER); }
"{" {System.out.println("para"); return new Symbol(sym.PARA); }
"}" { System.out.println("parc");return new Symbol(sym.PARC); }
{INT}+ {
		System.out.println(yytext()+"");
		return new Symbol(sym.NUMERO, new Integer(yytext())); }
[\t\r\f]  {}
[\n] {}
" " {}
. { System.out.println("Caracter no valido. "+yytext()+"-"); }


