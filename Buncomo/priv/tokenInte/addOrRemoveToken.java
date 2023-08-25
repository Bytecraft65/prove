package Buncomo.priv.tokenInte;

import Buncomo.priv.TokenExtent;

public class addOrRemoveToken extends TokenExtent{
	private int type = 0;
	public addOrRemoveToken(int y) {
		type = y;
	}
	public int getType() {
		return type;
	}

}
