package Buncomo.lib.vm;

import java.util.ArrayList;
import java.util.regex.Pattern;

import Buncomo.priv.BcCompleje;
import Buncomo.priv.Token;
import Buncomo.priv.TokenExtent;

public class Bc {
	public static Pattern floater = Pattern.compile("^-?\\d+(_\\d+)*(\\.\\d+)?$");
	public static Pattern size = Pattern.compile("^-?\\d+(_\\d+)*x-?\\d+(_\\d+)*$");
	public static Pattern isVlVar = Pattern.compile("^\\w+$");
	public Bc(TokenExtent[] f) {
		
	}
	public static TokenExtent[] getTokens(String script){
		ArrayList<Token> g = new ArrayList<Token>();
		String[] r = script.split("");
		String compress = "";
		Token ultimate = null;
		boolean str = false;
		for(int i = 0; i<r.length; i++) {
			String y = r[i];
			if(str == true && y.equals("\n")) {
				str = false;
				continue;
			}else if(str ) {
				continue;
			}
			/* CONDICIONALES */
			if(
					y.equals("+") || y.equals("-") || y.equals("*") || y.equals("/") ||
					y.equals("#") || y.equals("$") || y.equals("%")
			) {
				if(!compress.equals("")) {g.add(getToken(compress)); compress = "";}
				switch(y){
					case "+":
						g.add(Token.sum);
						ultimate = Token.sum;
						break;
					case "-":
						g.add(Token.res);
						ultimate = Token.res;
						break;
					case "*":
						g.add(Token.mul);
						ultimate = Token.mul;
						break;
					case "/":
						if(ultimate == Token.div) {
							ultimate = null;
							g.remove(g.size()-1);
							str = true;
						}else {
							g.add(Token.div);
							ultimate = Token.div;
						}
						break;
					case "%":
						g.add(Token.restan);
						ultimate = Token.restan;
						break;
					case "$":
						g.add(Token.power);
						ultimate = Token.power;
						break;
					case "#":
						g.add(Token.squareRoot);
						ultimate = Token.squareRoot;
						break;
				}
			}else if(y.equals("=")) {
				if(!compress.equals("")) {ultimate =getToken(compress);g.add(ultimate);  compress = "";}
				if(ultimate == null) {
					ultimate = Token.asig;
					g.add(ultimate);
				}else if(ultimate == Token.asig) {
					
					ultimate = Token.comparator;
					g.set(g.size()-1, ultimate);
					
				}else if(ultimate == Token.comparator) {
					
					ultimate = Token.comparatorEqual;
					g.set(g.size()-1, ultimate);
					
				}else if(ultimate == Token.sum) {
					
					ultimate = Token.add;
					g.set(g.size()-1, ultimate);
					
				}else if(ultimate == Token.res) {
					
					ultimate = Token.remove;
					g.set(g.size()-1, ultimate);
				}else if(ultimate == Token.div) {
					
					ultimate = Token.divasig;
					g.set(g.size()-1, ultimate);
					
				}else if(ultimate == Token.mul) {
					
					ultimate = Token.mulasig;
					g.set(g.size()-1, ultimate);
					
				}else if(ultimate == Token.restan) {
					
					ultimate = Token.restanasig;
					g.set(g.size()-1, ultimate);
					
				}else if(ultimate == Token.power) {
					
					ultimate = Token.powerasig;
					g.set(g.size()-1, ultimate);
				}else if(ultimate == Token.squareRoot) {
					
					ultimate = Token.squareRootAsig;
					g.set(g.size()-1, ultimate);
					
				}else{
					ultimate = Token.asig;
					g.add(ultimate);
				}
			}else if(
					y.equals("(") || y.equals(")") ||
					y.equals("{") || y.equals("}") ||
					y.equals("[") || y.equals("]") ||
					/* newline,next,and,etc */
					y.equals("\n") || y.equals(",") ||
					y.equals(";") || y.equals(".")
			) {
				if(!compress.equals("")) {g.add(getToken(compress)); compress = "";}
				switch(y) {
					case "{":
						ultimate = Token.ac;
						g.add(ultimate);
						break;
					case "}":
						ultimate = Token.cc;
						g.add(ultimate);
						break;
					case "(":
						ultimate = Token.ap;
						g.add(ultimate);
						break;
					case ")":
						ultimate = Token.cp;
						g.add(ultimate);
						break;
					case "[":
						ultimate = Token.aa;
						g.add(ultimate);
						break;
					case "]":
						ultimate = Token.ca;
						g.add(ultimate);
						break;
					case "\n":
						ultimate = Token.newline;
						g.add(ultimate);
						break;
					case ";":
						ultimate = Token.end;
						g.add(ultimate);
						break;
					case ",":
						ultimate = Token.next;
						g.add(ultimate);
						break;
					case ".":
						ultimate = Token.punto;
						g.add(ultimate);
						break;
				}
			}else if(
					y.equals("\"") ||y.equals("'")
			) {
				String ints = y;
				String w = "";
				String ant = "";
				boolean ter = false;
				for(int d = i+1; d < r.length; d++) {
					String j = r[d];
					if(j.equals(ints) && !ant.equals("\\")) {
						ter = true;
						i=d;
						break;
					}else if(j.equals("\\") && ant.equals("\\")) {
						ant = "";
					}else if(j.equals("\n")) {
						break;
					}else if(ant.equals("\\")) {
						w = w.substring(0,w.length()-1);
					}
					ant = j;
					w+=j;
				}
				if(!ter) {
					throw new BuncomoException("String not closed");
				}
				g.add(new Token("string",w));
			} else if(y.equals(" ")) {
				if(!compress.equals("")) {g.add(getToken(compress)); compress = "";}
			}else {
				compress+=y;
			}
			
		}
		if(!compress.equals("")) {g.add(getToken(compress)); compress = "";}
		for(Token h : g) {
			System.out.print(h.getTokenName()+"='"+h.getValue()+"'\n");
		}
		return BcCompleje.getTokenOperation(g);
	}
	private static Token getToken(String g) {
		Token t;
		if(floater.matcher(g).find()) {
			t = new Token("number",g);
		}else {
			switch(g) {
				case "var":
					t = Token.var;
					break;
				case "global":
					t = Token.global;
					break;
				case "if":
					t = Token.condition;
					break;
				case "else":
					t = Token.orcondition;
					break;
				case "while":
					t = Token.whileBucle;
					break;
				case "for":
					t = Token.forBucle;
					break;
				case "break":
					t = Token.brek;
					break;
				case "continue":
					t = Token.cont;
					break;
				case "false":
					t = Token.False;
					break;
				case "true":
					t = Token.True;
					break;
				case "null":
					t = Token.Null;
					break;
				case "and":
					t = Token.and;
					break;
				case "or":
					t = Token.or;
					break;
				case "not":
					t = Token.not;
					break;
				case "fn":
					t = Token.function;
					break;
				default:
					if(!isVlVar.matcher(g).find()) {
						throw new BuncomoException("'"+g+"' no se comprendio que se quiere hacer");
					}
					t = new Token("ni",g);
					break;
			}
		}
		return t;
	}

}
