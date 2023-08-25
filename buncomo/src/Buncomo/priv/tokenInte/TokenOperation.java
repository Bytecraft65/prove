package Buncomo.priv.tokenInte;

import java.util.ArrayList;

import Buncomo.priv.*;

public class TokenOperation extends TokenExtent{
	private ArrayList<Token> g1 = new ArrayList<Token>();
	public void sendOperation(Token h1) {
		g1.add(h1);
	}
	public Token[] getTokensValueVar() {
		Token[] g = new Token[g1.size()];
		for(int i = 0; i < g1.size(); i++) {g[i] = g1.get(i);}
		return g;
	}
}
