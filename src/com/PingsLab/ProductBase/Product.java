package com.PingsLab.ProductBase;

import java.util.List;

public class Product {
	public String PID=null;  //product ID
	public String name=null;  //commercial name
	public String url=null;  //product url
	public List<String> store=null;  //country seller (by url)
	public String type=null;  //drop/restock
	public String price=null;  //eventually in sale
	public List<Size> sizes;  //all sizes available
}