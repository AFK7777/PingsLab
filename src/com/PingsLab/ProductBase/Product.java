package com.PingsLab.ProductBase;

import java.util.List;

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
	
	public String type = null;  //drop/restock
	
}