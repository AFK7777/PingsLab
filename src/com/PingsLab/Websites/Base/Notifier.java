package com.PingsLab.Websites.Base;

import com.PingsLab.ProductBase.Product;

public abstract class Notifier {
	
	public abstract String getWebhook();
	public void notify(Stage stage, Product prod) {
		
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
