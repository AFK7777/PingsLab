package com.PingsLab.ProductBase;

public class Size{
	public String size = null;
	public String quantity = null; //String perché spesso non è un numero => "MANY"
	public String PID = null;
	public String ATC = null;  //AddToCart link for this size number
	
	public String toString() {
		String r = size;
		
		if(ATC != null)
			r = "["+size+"]("+ATC + "#" + size+")";
		
		if(quantity != null)
			r += " [" + quantity + "]";
		
//		if(PID != null)
//			r += " [PID]("+PID+")";
//		
		return r;
	}
}
