package com.PingsLab.Discord;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.json.JSONArray;
import org.json.JSONObject;

public class Messanger {
	
	public void sendMessage() {
		JSONObject msg = new JSONObject();
		JSONObject embeds = new JSONObject();
		embeds.put("title", "StoCazz");
		embeds.put("color", 1711673);
		
		JSONObject footer = new JSONObject();
		footer.put("text", "PingsLab");
		embeds.put("footer", footer);
		
		
		TimeZone tz = TimeZone.getTimeZone("UTC");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		df.setTimeZone(tz);
		String nowAsISO = df.format(new Date());
		embeds.put("timestamp", nowAsISO);
 
		 
		String str = null;
		msg.put("content", str);
		msg.put("embeds", new JSONArray().put(embeds));
		
		
		//embeds.put(new JSON);
		try {
			send("https://discord.com/api/webhooks/984764138729398304/DsB2jTSrogcQ5VqF0FEpo_uK94ow_nymLeeYYK3H3dhgnhHY7C-1QM5C9HLbrGAsOX4Q", "POST", 
					msg);
		} catch (Exception e) { 
			e.printStackTrace();
		}
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