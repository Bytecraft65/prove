package Buncomo;
import java.util.HashMap;
import java.util.Map;

import Buncomo.lib.BcValue;
import Buncomo.lib.vm.Bc;
import Buncomo.priv.TokenExtent;
public class Buncomo {
	private Map<String,BcValue> m = new HashMap<>();
	public static Buncomo getBuncomo() {
		return new Buncomo();
	}
	public void put(String a,BcValue b) {
		m.put(a, b);
	}
	public void clearVariable() {
		m.clear();
	}
	public Bc load(String h){
		TokenExtent[] tokens = Bc.getTokens(h);
		return new Bc(tokens);
	}
}
/* 
 Buncomo buncomo = Buncomo.getBuncomo();
 BcValue g= BcValue.CoerceJavaToBuncomo(true);
 buncomo.put("a",g);
 Bc load = buncomo.load("var t = 12; t;");
 BcValue result = load.call();
*/
