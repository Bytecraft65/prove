package Buncomo.priv;

public class TokenExtent {
	public boolean isEqual = false;
	public int getTypevar() {
		return 0;
	}
	public String getName() {
		return "";
	}
	public TokenExtent[] getTokenExtentsValueVar() {
		return null;
	}
	public Token[] getTokensValueVar() {
		return null;
	}
	public Object getValue() {
		return null;
	}
	public void setName(String h) {}
	public int getType() {
		return 0;
	}
	public boolean isTotalRequired() {
		return false;
	}
	public boolean isMinRequired() {
		return false;
	}
	public void sendOperation(Token n1) {
		
	}
	/* typo de variables */
	public static int notDeclarated = 0;
	public static int localVar = 1;
	public static int global = 2;
	/* TYPES */
	public static int typenotDeclarated = 0;
	public static int asingType = 1;
	public static int conditionType = 2;
	public static int whileType = 3;
	public static int forType = 4;
	public static int functionType = 5;
	public static int varType = 6;
	public static int operatorType = 7;
	public static int addType = 8;
	public static int removeType = 9;
	public static int ArrayType = 10;
	/*  */
}
