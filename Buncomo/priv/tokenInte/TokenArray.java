package Buncomo.priv.tokenInte;

import Buncomo.priv.TokenExtent;

import java.util.ArrayList;

import Buncomo.lib.vm.BuncomoException;
import Buncomo.priv.Token;
public class TokenArray extends TokenExtent{
	public boolean close = false;
	private ArrayList<TokenExtent> tokens = new ArrayList<TokenExtent>();
	public int getType() {
		return TokenExtent.ArrayType;
	}
	public void sendOperation(TokenExtent e) {
		if(close) {
			throw new BuncomoException("YA se cerro el array");
		}
		tokens.add(e);
	}
}
