package com.PingsLab.Websites;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LVR {
	public String baseurl= "https://www.luisaviaroma.com/";
	public List<String> productBase = new ArrayList<String>();  //season+collection
	public List<String> itemIds = new ArrayList<String>();
	
	public LVR() { 
		productBase.addAll(Arrays.asList("75I-0C9"));
		//itemIds.addAll(Arrays.asList("", ""));
	
	}
}
