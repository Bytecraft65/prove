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
		TokenExtent l = null;
		TokenExtent y = null;
		for(int i = 0; i < g.size(); i++) {
			Token r = g.get(i);
			if(r == Token.var || r == Token.global) {
				if(y != null || l != null) {
					throw new BuncomoException("Var dentro de operacion... mal");
				}
				int t = TokenExtent.localVar;
				if(r == Token.global) {
					t = TokenExtent.global;
				}
				y = new TokenAsig(t);
			}else if(r == Token.asig) {
				if(l != null) {
					throw new BuncomoException("verga");
				}
				if(y == null) {
					if(after == null) {
						throw new BuncomoException("Asig sin nombre a la variable que es esto un pan?");
					}else if(!after.getTokenName().equals("ni")) {
						throw new BuncomoException("Hay nombre mal escrio?");
					}
					y = new TokenAsig(after.getValue());
					y.isEqual = true;
					after = null;
				}else if(y.getType() == TokenExtent.varType) {
					y.isEqual = true;
				}
			}else if(r == Token.newline || r == Token.end){
				save.add(y);
				y = null;
				after = null;
			}else if(
					r == Token.sum || r == Token.res || r == Token.div ||r == Token.mul
					|| r == Token.restan || r == Token.power || r == Token.squareRoot
			){
				if(after == null && y == null) {
					if(r != Token.res) {
						throw new BuncomoException("SintaxisError");
					}
					
				}
				if(y == null){
					if(after == null && r != Token.res) {
						throw new BuncomoException("SintaxisError");
					}
					y = new TokenOperation();
					if(after != null) {
						y.sendOperation(after);
					}
					y.sendOperation(r);
					after = null;
				}else if(y.getType() == TokenExtent.varType || y.getType() == TokenExtent.operatorType) {
					y.sendOperation(r);
				}
			}else if(r == Token.aa){
				if(y == null) {
					y = new TokenArray();
					continue;
				}
				if(y.getType() == TokenExtent.varType) {
					l = y;
					y = new TokenArray();
				}else {
					throw new BuncomoException("no entendi");
				}
			}else if(r == Token.ca){
				if(y == null) {
					if(l == null) {
						throw new BuncomoException("no se abrio array");
					}
					save.add(l);
					l = null;
				}else if(y.getType() == TokenExtent.varType) {
					TokenAsig s = (TokenAsig)y;
					s.setValue(l);
					l = null;
				}
			}else {
				if(after != null) {
					throw new BuncomoException("SintaxisError");
				}
				if(y != null) {
					if(y.getType() == TokenExtent.varType) {
						if(!y.isEqual && !y.isMinRequired()) {
							y.setName(r.getValue());
							continue;
						}
						if(y.isEqual) {
							//System.out.print("VALOR: "+r.getValue());
							y.sendOperation(r);
						}else {
							throw new BuncomoException("y el equal?");
						}
					}
				}else {
					after = r;
				}
			}
		}
		for(TokenExtent s : save) {
			if(s.getType() == TokenExtent.varType) {
				Token[] e = s.getTokensValueVar();
				System.out.print(s.getName() + "=");
				if(e == null) {continue;}
				for(Token q : e) {
					System.out.print(q.getValue()+",");
				}
				System.out.print("\n");
			}
		}
		return null;
	}
}
