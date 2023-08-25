package Buncomo.priv.tokenInte;

import Buncomo.lib.vm.BuncomoException;
import Buncomo.priv.Token;
import Buncomo.priv.TokenExtent;

public class TokenAsig extends TokenExtent{
	public boolean isEqual = false;
	private String namevar = null;
	private int typevar = 0;
	private TokenExtent value = null;
	public TokenAsig(int typev){
		typevar = typev;
	}
	public boolean isValue() {
		return value == null;
	}
	public TokenAsig(String g) {
		namevar = g;
	}
	public void setName(String g) {
		namevar = g;
	}
	public boolean isMinRequired() {
		return namevar != null;
	}
	public TokenExtent getValue() {
		return value;
	}
	public String getName() {
		return namevar;
	}
	public int getTypeVar(){
		return typevar;
	}
	public int getType() {
		return TokenExtent.varType;
	}
	public Token[] getTokensValueVar() {
		Token[] e = null;
		if(value != null) {
			e = value.getTokensValueVar();
		}
		return e;
	}
	public void setValue(TokenExtent g) {
		if(value != null) {
			throw new BuncomoException("a");
		}
		value = g;
	}
	public void sendOperation(Token y) {
		if(value == null) {
			value = new TokenOperation();
		}
		value.sendOperation(y);
	}
	
}
