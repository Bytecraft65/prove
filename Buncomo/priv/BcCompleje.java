package Buncomo.priv;

import java.util.ArrayList;

import Buncomo.lib.vm.BuncomoException;
import Buncomo.priv.tokenInte.*;

public class BcCompleje {
	private static boolean success(TokenExtent r) {
		return true;
	}
	/* *TRANSFORMAR TOKEN EN TOKENEXTENT MAS COMPLEJOS* */
	public static TokenExtent[] getTokenOperation(ArrayList<Token> g){
		ArrayList<TokenExtent> save = new ArrayList<TokenExtent>();
		Token after = null;
		boolean esperanuevaline = false;
		ArrayList<TokenExtent> nocompleted = new ArrayList<TokenExtent>();
		for(int i = 0; i < g.size(); i++) {
			Token r = g.get(i);
			if(r == Token.global || r == Token.var) {
				/* Se revisa si hay una accion no completada abierta */
				if(nocompleted.size() > 0) {
					TokenExtent l = nocompleted.get(nocompleted.size()-1); // acceder a la ultima accion abiert
					/* Revisando si es variable o operacion esto es para que no lanze error si es una funcion para que puedas crear variables en funciones */
					if(l.getType() == TokenExtent.varType || l.getType() == TokenExtent.operatorType || l.getType() == TokenExtent.ArrayType) {
						throw new BuncomoException("a");
					}
				}
				int y = TokenExtent.localVar;
				if(r == Token.global) {
					y = TokenExtent.global;
				}
				nocompleted.add(new TokenAsig(y));
			}else if(r == Token.asig) {
				int y;
				/* Se Revisa si hay una tarea no completada */
				if((y = nocompleted.size()) > 0) {
					TokenExtent l = nocompleted.get(y-1);
					/* revisar si es una variable */
					if(l.getType() == TokenExtent.varType) {
						l.isEqual = true;
						nocompleted.add(new TokenOperation());
						continue;
					}else {
						throw new BuncomoException("a2");
					}
					
				}else {
					/* si no hay tarea de tipo variable se busca si hay valor en after para crear 
					 * una variable con el valor de after */
					if(after == null) {
						throw new BuncomoException("falto un nombre XD");
					}else if(after.getTokenName().equals("number")) {
						/* Lanza error ya que una varible no puede tener nombre numerico */
						throw new BuncomoException("el nombre no puede ser numero XD");
					}
					/* Se crea la varible con el valor de after */
					TokenAsig h = new TokenAsig(after.getValue());
					h.isEqual = true; // para decir que ya se detecto el signo "="
					nocompleted.add(new TokenOperation()); // se añade operator para el valor de la variable
					
				}
			}else if(r == Token.end || r==Token.newline) { 
				/* Si se abre un array,un objeto o un parentesis esperanuevaline sera true, para que los espacios no cuentes como operacion cerrada*/
				if(esperanuevaline == true && r == Token.newline) {continue;}
				int y;
				if((y = nocompleted.size()) > 0) {
					TokenExtent e = nocompleted.get(y-1);
					if(e.getType() == TokenExtent.varType) {
						
						if(after != null) {
							if(!e.isMinRequired()) {
								throw new BuncomoException("el nombre, que paso con el? XD");
							}
							/* Se completa la operacion de crear variable con el valor de after si tiene valor */
							TokenOperation po = new TokenOperation();
							po.sendOperation(after);
							TokenAsig s = (TokenAsig)e;
							s.setValue(po);
							save.add(s);
							nocompleted.remove(y-1);
						}else {
							if(!e.isMinRequired()) {
								throw new BuncomoException("el nombre, que paso con el? XD");
							}
							if(e.isMinRequired()&&e.isEqual) {
								throw new BuncomoException("asignar sin valor?, quie te paso mi pana?");
							}
							/* En el caso en que la variable no se le asigna valor se crea pero siendo null */
							save.add(e);
							nocompleted.remove(y-1);
						}
					}else if(e.getType() == TokenExtent.operatorType) {
						/* En el caso que si tiene valor */
						if(y > 1) {
							TokenExtent q = nocompleted.get(y-2);
							if(q.getType() == TokenExtent.varType) {
								/* Si la accion antes de la operacion es la de crear una variable, se ejecutara esto y se cerrara la accion de variable y operacion */
								TokenAsig h = (TokenAsig)q;
								h.setValue(e);
								save.add(h);
								nocompleted.remove(y-2);
								nocompleted.remove(y-2);
							}
						}else {
							
							save.add(e);
							nocompleted.remove(y-1);
						}
					}
				}
			}else if(false){
				
			}else{
				if(after != null) {
					throw new BuncomoException("ERROR XD");
				}
				if(nocompleted.size() > 0) {
					TokenExtent l = nocompleted.get(nocompleted.size()-1);
					if(l.getType() == TokenExtent.varType) {
						if(l.isEqual && l.isMinRequired()) {
							TokenOperation w = new TokenOperation();
							w.sendOperation(r);
							nocompleted.add(w);
							continue;
						}else if(l.isMinRequired()) {
							throw new BuncomoException("ERRRO");
						}
						l.setName(r.getValue());

						continue;
					}else if(l.getType() == TokenExtent.operatorType) {
						l.sendOperation(r);
						continue;
					}
				}
				after = r;
			}
		}
		for(TokenExtent s : save) {
			if(s == null) {
				System.out.print("ah");
			}
			System.out.print(s+"\n");
			if(s.getType() == TokenExtent.varType) {
				Token[] e = s.getTokensValueVar();
				System.out.print(s.getName() + "="+s.getValue());
				/*if(e == null) {continue;}
				for(Token q : e) {
					System.out.print(q.getValue()+",");
				}
				System.out.print("\n");*/
			}
		}
		return null;
	}
}
