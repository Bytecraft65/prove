package Buncomo.priv;

public class Token {
	private String n;
	private String v;
	public Token(String name,String value) {
		n = name;
		v = value;
	}
	public String getTokenName() {
		return n;
	}
	public String getValue() {
		return v;
	}
	/* MATEMATICAS */
	public static Token sum = new Token("sum","+");
	public static Token res = new Token("res","-");
	public static Token mul = new Token("mul","*");
	public static Token div = new Token("div","/");
	public static Token restan = new Token("restan","%");
	public static Token power = new Token("power","$");
	public static Token squareRoot = new Token("squareRoot","#");
	/* PARENTESIS */
	public static Token ap = new Token("ap","(");
	public static Token cp = new Token("cp",")");
	public static Token ac = new Token("ac","{");
	public static Token cc = new Token("cc","}");
	public static Token aa = new Token("aa","[");
	public static Token ca = new Token("ca","]");
	/*.,; newline */
	public static Token next = new Token("next",",");
	public static Token punto = new Token("punto",".");
	public static Token end = new Token("end",";");
	public static Token newline = new Token("newline","\n");
	/* EQUAL */
	public static Token asig = new Token("asig","=");
	public static Token comparator = new Token("comparator","==");
	public static Token comparatorEqual = new Token("comparatorEqual","===");
	public static Token add = new Token("add","+=");
	public static Token remove = new Token("remove","-=");
	public static Token mulasig = new Token("mulasig","*=");
	public static Token divasig = new Token("divasig","/=");
	public static Token powerasig = new Token("powerasig","$=");
	public static Token squareRootAsig = new Token("sqasig","#=");
	public static Token restanasig = new Token("restanasig","%=");
	/* VARIABLES */
	public static Token var = new Token("var","var");
	public static Token consta = new Token("const","const");
	public static Token let = new Token("let","let");
	public static Token global = new Token("global","global");
	/* CONDICIONES */
	public static Token condition = new Token("condition","if");
	public static Token orcondition = new Token("else","else");
	/* BUCLES */
	public static Token whileBucle = new Token("buclewhile","while");
	public static Token forBucle = new Token("bucleFor","for");
	/* FUNCION */
	public static Token function = new Token("function","fn");
	/* continue,break */
	public static Token cont = new Token("cont","continue");
	public static Token brek = new Token("brek","break");
	/* PALABRAS DE CONDICIONES */
	public static Token and = new Token("and","and");
	public static Token or = new Token("or","or");
	public static Token not = new Token("not","not");
	/* valores default */
	public static Token False = new Token("false","false");
	public static Token True = new Token("true","true");
	public static Token Null = new Token("null","null");
}
