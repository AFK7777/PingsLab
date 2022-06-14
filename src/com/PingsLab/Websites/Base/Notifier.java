package com.PingsLab.Websites.Base;

import java.util.LinkedHashMap;

import com.PingsLab.Discord.Messanger;
import com.PingsLab.ProductBase.Product;

public interface Notifier {
	static Messanger messanger = new Messanger();
	
	String getWebhook();
	
	default void notify(Stage stage, Product prod, LinkedHashMap<String, String> map) {
		messanger.sendMessage(prod, getWebhook(), map, null, stage.color);
	}
	
	public static enum Stage{
		PING(15158332), //Il prodotto è stato appena rilevato => Rosso
		PRODUCT(16776960), //Il prodotto è stato scansionato => Giallo
		EXTRA(5763719); //Il prodotto è stato elaborato (Ex Ricerca prezzo su StockX) => Verde
		public final int color;
		Stage(int color) {
			this.color = color;
		}
	}
}
