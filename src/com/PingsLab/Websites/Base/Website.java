package com.PingsLab.Websites.Base;

import java.util.LinkedHashMap;
import java.util.Map;

public abstract class Website {
	public abstract String getWebsite();
	public abstract void scanBase();
	
	
	/** **/
	public LinkedHashMap<String, String> usefulLinks(){return null;}
	
}
