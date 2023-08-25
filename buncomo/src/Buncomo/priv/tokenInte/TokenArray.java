package Buncomo.priv.tokenInte;

import Buncomo.priv.TokenExtent;

import java.util.ArrayList;

import Buncomo.priv.Token;
public class TokenArray extends TokenExtent{
	private ArrayList<TokenExtent> tokens = new ArrayList<TokenExtent>();
	public void add(TokenExtent e) {
		tokens.add(e);
	}
	public int getType() {
		return TokenExtent.ArrayType;
	}
	public void sendOperation(TokenExtent e) {
		tokens.add(e);
	}
}
