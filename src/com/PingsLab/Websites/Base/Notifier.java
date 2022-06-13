package com.PingsLab.Websites.Base;

import com.PingsLab.ProductBase.Product;

public interface Notifier {
	
	String getWebhook();
	
	default void notify(Stage stage, Product prod) {
		
	}
	
	public static enum Stage{
		PING(15158332), //Il prodotto � stato appena rilevato => Rosso
		PRODUCT(16776960), //Il prodotto � stato scansionato => Giallo
		EXTRA(5763719); //Il prodotto � stato elaborato (Ex Ricerca prezzo su StockX) => Verde
		public final int color;
		Stage(int color) {
			this.color = color;
		}
	}
}
