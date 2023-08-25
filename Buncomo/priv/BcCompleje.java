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
				if(nocompleted.size() > 0) {
					TokenExtent l = nocompleted.get(nocompleted.size()-1);
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
				if((y = nocompleted.size()) > 0) {
					TokenExtent l = nocompleted.get(y-1);
					if(l.getType() == TokenExtent.varType) {
						l.isEqual = true;
						continue;
					}
					nocompleted.add(new TokenOperation());
				}else {
					if(after == null) {
						throw new BuncomoException("falto un nombre XD");
					}else if(after.getTokenName().equals("number")) {
						throw new BuncomoException("el nombre no puede ser numero XD");
					}
					TokenAsig h = new TokenAsig(after.getValue());
					h.isEqual = true;
					nocompleted.add(new TokenOperation());
					
				}
			}else if(r == Token.end || r==Token.newline) {
				if(esperanuevaline == true && r == Token.newline) {continue;}
				int y;
				if((y = nocompleted.size()) > 0) {
					TokenExtent e = nocompleted.get(y-1);
					if(e.getType() == TokenExtent.varType) {
						
						if(after != null) {
							if(!e.isMinRequired()) {
								throw new BuncomoException("el nombre, que paso con el? XD");
							}
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
							save.add(e);
							nocompleted.remove(y-1);
						}
					}else if(e.getType() == TokenExtent.operatorType) {
						if(y > 1) {
							TokenExtent q = nocompleted.get(y-2);
							if(q.getType() == TokenExtent.varType) {
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
