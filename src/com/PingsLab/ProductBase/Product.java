package com.PingsLab.ProductBase;

import java.util.List;
import java.util.Map;

public class Product {
	public String PID = null;  //product ID
	public String SKU = null; //Identificatore della scarpa (produttore)
	
	public String name = null;  //commercial name
	public String url = null;  //product url
	
	public String thumbnail = null;
	public String price = null;  //eventually in sale
	
	public List<Size> sizes;  //all sizes available
	
	public List<String> store = null;  //country seller (by url)

	public List<String> discounts;  //Tutti gli sconti
	 
	
	
	public Map<String, String> emojiLinks = null;
	public String emojiLinksTitle = null;
	
	public Type notifyType = Type.NEW;
	
	enum Type{
		NEW("New Product"),
		RESTOCK("Restock");
		String name;
		Type(String enumName){
			name = enumName;
		}
		@Override
		public String toString() {
			return name;
		}
	}
}