package com.PingsLab.Discord;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;

import org.json.JSONArray;
import org.json.JSONObject;

import com.PingsLab.ProductBase.Product;
import com.PingsLab.ProductBase.Size; 

public class Messanger {
	
	public void sendMessage(String content, int color) {
		
		Product prod = new Product();
		prod.url = "https://www.snipes.it/p/karl_kani-89_prm_-white%2Fbeige-00013802052500.html";
		prod.name = "Luisa Via Roma 45";
		prod.PID = "76I-G14007";
		prod.thumbnail = "https://www.snipes.it/dw/image/v2/BDCB_PRD/on/demandware.static/-/Sites-snse-master-eu/default/dw61e86f49/2052500_P.jpg?sw=780&sh=780&sm=fit&sfrm=png";
		prod.price = "199,99 €";
		//prod.SKU = "AZ313WF";
		
		prod.sizes = new ArrayList<Size>();
		
		for(float i = 40f; i < 41.1; i+= 0.5f) {
			Size s = new Size();
			s.size = "" +i;
			s.quantity = "" +(5 + new Random().nextInt(10));
			s.ATC = "https://www.google.it";
			prod.sizes.add(s);
		}
		
		
		
		JSONObject msg = new JSONObject();
		JSONObject embeds = new JSONObject();
		msg.put("wait", true);
		embeds.put("color", color);
		embeds.put("footer", new JSONObject().put("text", "PingsLab LVR Monitor"));
		
		embeds.put("title", prod.name.toUpperCase());
		embeds.put("url", prod.url);
		
		
		if(prod.thumbnail != null)
			embeds.put("thumbnail", new JSONObject().put("url", prod.thumbnail));
		
		
		
		TimeZone tz = TimeZone.getTimeZone("UTC");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		df.setTimeZone(tz);
		String nowAsISO = df.format(new Date());
		
		
		
		JSONArray fields = new JSONArray();
		
		
		if(prod.sizes != null) {
			
			String[] values = new String[Math.min(3, prod.sizes.size())];
			//Init
			for(int i = 0; i < values.length; i++) values[i] = "";
			for(int i = 0; i < prod.sizes.size(); i++) {
				Size s = prod.sizes.get(i);
				values[i%values.length] +=  s.toString() + "\n";
			}
			for(int i = 0; i < values.length; i++) add(fields, i == 0 ? "Sizes" : "\u200b", values[i], true);
			for(int i = values.length; i < 3; i++)
				add(fields, "\u200b", "\u200b", true);
		}
		//Spacer
		//add(fields, "\u200b", "\u200b", false);
		
		//int maxLength = max(prod.price, prod.PID, prod.SKU);
		if(prod.price != null)
			add(fields, "Price", "```" + (prod.price) + "```", true);
		if(prod.PID != null)
			add(fields, "PID",  "```" + (prod.PID) + "```", true);
		if(prod.SKU != null)
			add(fields, "SKU",  "```" + (prod.SKU) + "```", true);
		
		//add(fields, "\u200b", "\u200b", false);
		add(fields, "Useful Links",  "[Login](https://www.stocazzo.it) | [Cart](https://www.stocazzo.it) | [Checkout](https://www.stocazzo.it)", false);
		
		
//		add(fields, "", "33", true);
//		add(fields, "", "34", true);
//		add(fields, "", "35", true);
//		add(fields, "", "36", true);
//		
		
		embeds.put("fields", fields);
		embeds.put("timestamp", nowAsISO);
		 
		msg.put("content", content);
		msg.put("embeds", new JSONArray().put(embeds));
		
		
		System.out.println(msg.toString());
		
		try {
			send("https://discord.com/api/webhooks/984764138729398304/DsB2jTSrogcQ5VqF0FEpo_uK94ow_nymLeeYYK3H3dhgnhHY7C-1QM5C9HLbrGAsOX4Q?wait=true", "POST", msg);
		} catch (Exception e) { 
			e.printStackTrace();
		}
	}
	
	public void add(JSONArray array, String name, String value, Boolean b) {
		JSONObject job = new JSONObject().put("name", name).put("value", value);
		if(b != null)
			job.put("inline", b.booleanValue());
		array.put(job);
	}
	 
	private JSONObject send(String link, String method, JSONObject job) throws Exception {
		URL url = new URL(link);	
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setRequestMethod(method);//Discord permette di utilizzare diversi Metodi per effettuare varie operazioni
		conn.addRequestProperty("content-type", "application/json"); /** Tipo dato **/
		conn.addRequestProperty("content-length", ""+job.toString().length());
		new DataOutputStream(conn.getOutputStream()).writeBytes(job.toString()); //Mando i dati al server
		conn.connect();
		StringBuilder sb = new StringBuilder();
		
		System.out.println("CODE:" + conn.getResponseCode());
		conn.getHeaderFields().forEach((k,v) -> {
			System.out.println(k + ": " + v);
		});
		
		if(conn.getResponseCode() == 204) return null;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getResponseCode() < 400 ? conn.getInputStream() : conn.getErrorStream()));
		String line = null;
		while((line = br.readLine()) != null) {
			sb.append(line);
		}
		System.out.println(sb.toString());
		
		return new JSONObject(sb.toString());
	}
}
//"content": null,
//"embeds": [{
//    "title": "**Console Opened**",
//    "color": 1711673,
//    "fields": [{
//            "name": "**User**",
//            "value": `<@${info.settings.id}>`,
//        },
//        {
//            "name": "**Discord ID**",
//            "value": '```' + `${info.settings.id}` + '```',
//        },
//        {
//            "name": "**Key**",
//            "value": '```' + `${info.settings.key}` + '```',
//        }
//    ],
//    "timestamp": new Date().toISOString(),
//    "footer": {
//        "text": "AstryScripts",
//        "icon_url": "https://cdn.discordapp.com/attachments/838836294200328262/848874536941125672/icon.png"
//    }
//}]