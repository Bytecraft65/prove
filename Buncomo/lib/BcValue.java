package Buncomo.lib;

public class BcValue {
	private String toeString = null;
	private String typeof = "object";
	private boolean isReferal = false;
	private Object r = null;
	private BcValue(Object y) {
		if(y instanceof Integer || y instanceof Double || y instanceof Float) {
			typeof = "number";
		}else if(y instanceof String) {
			typeof = "string";
		}else if(y instanceof Boolean) {
			typeof = "boolean";
		}else {
			toeString = "[ Object ]";
			isReferal = true;
		}
		r = y;
	}
	private BcValue(String i1,String i2,Boolean i3,Object value) {
		toeString = i1;
		typeof = i2;
		isReferal = true;
		r = value;
	}
	public String getToString() {
		return toeString;
	}
	public String getTypeof() {
		return typeof;
	}
	public boolean isReferif() {
		return isReferal;
	}
	public static BcValue CoerceJavaToBuncomo(Object y) {
		return new BcValue(y);
	}
	public static BcValue nullBcValue = new BcValue(null,null,false,null);
	public static BcValue falseBcValue = new BcValue(null,"boolean",false,false);
	public static BcValue trueBcValue = new BcValue(null,"boolean",false,true);
}
