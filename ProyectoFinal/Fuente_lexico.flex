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
"repetir" {return new Symbol(sym.REPETIR);}
"ver" {return new Symbol(sym.VER);}
"fruta" {return new Symbol(sym.FRUTA);}
"coloca" {return new Symbol(sym.COLOCA);}
";" {return new Symbol(sym.PUNTOYCOMA);}
"mover" {return new Symbol(sym.MOVER);}
"arriba" {return new Symbol(sym.ARRIBA);}
"abajo" {return new Symbol(sym.ABAJO);}
"izquierda" {return new Symbol(sym.IZQ);}
"derecha" {return new Symbol(sym.DER);}
"{" {return new Symbol(sym.PARA);}
"}" {return new Symbol(sym.PARC);}
{INT}+ {
		return new Symbol(sym.NUMERO, new Integer(yytext())); }
[\t\r\f]  {}
[\n] {}
" " {}
. { System.out.println("Caracter no valido. "+yytext()+"-"); }


