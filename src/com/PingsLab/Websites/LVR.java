package com.PingsLab.Websites;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.concurrent.CompletableFuture;

import org.json.JSONObject;

import com.PingsLab.ProductBase.Product;
import com.PingsLab.ProductBase.Size;
import com.PingsLab.Utils.Maps;
import com.PingsLab.Utils.Pair;
import com.PingsLab.Utils.WebUtils;
import com.PingsLab.Utils.WebUtils.Method;
import com.PingsLab.Websites.Base.Notifier;
import com.PingsLab.Websites.Base.Website;

public class LVR extends Website implements Notifier { 

	@Override
	public void scanBase() {
		try { 
			//_abck 
			int actPage = 1;
			int maxPage = 2;
			
			/** TODO: Caso limite => Nel momento in cui sto aprendo la pagina successiva, un prodotto è stato rimosso quindi la pagina non esiste più **/
			while(actPage < maxPage) {
				Pair<Integer, String> content = WebUtils.readPage(getWebsite() + "/it-it/shop/uomo/stoc?lvrid=_gm_i4&FilterDes=210,247,140,4R8,P3B,JRD,KAN,DNV&Page=1&SortType=NewIn&ajax=true", Method.GET, null, Maps.asMap(
						"referer","https://www.luisaviaroma.com/",
						"user-agent" , "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.5005.62 Safari/537.36",
						"cookie","ak_bmsc=64DA9FBDEBC9A71BE245C21AC882839C~000000000000000000000000000000~YAAQePASAsyC5zmBAQAAnAvrXhBB+SECkLk7biBO8/CzQvfBerzkTTRE4F2IIC4emYYMenLE0hKorH+KUV0Uclwqyezmw3N3Zau9dblO/B0LLcYsoolwqGCyoC3Na9tMTJU9zXmW7R4TkPrcjVQpnwsa4/XT3XU9dQsmKhTJxEQ+GZ5LkGx1HhDXUjAZPURhMARAVoldR8YcPCHD5sYT2StZFXDoEleMsxnvBaTm4uhhXSIqy9Iawwfx80JLRRsL0n+hFXeGBhUbcZWqZ9wJOtwCpmqlE/WIDUc96CyxJ3RssQbDPX9M9g5RLiZDssi6XELul4hwZ3kU3lSEjiojZ2vOYAZTA1zvC5I0wid8R1yZ6cvEbrLvbKt+dPgzw2jv8K4eJ24HedlNePMJLDOTlLTbBM0X7SLZTFLBCuKdmy3I2xz8+ypdP06j+2kK0mA="));
				if(content.getKey() == 200) {
					actPage++;
					JSONObject job = new JSONObject(content.getValue());
					maxPage = job.getJSONObject("Pagination").getInt("TotalePages");
					job.getJSONArray("Items").forEach(item -> {
						
						
						CompletableFuture.supplyAsync(() -> {
							JSONObject jitem = (JSONObject)item;
							Product prod = new Product();
							prod.PID = jitem.getString("ItemCode");
							prod.sizes = new ArrayList<Size>();
							jitem.getJSONArray("SizeArray").forEach(obj -> {
								prod.sizes.add(new Size(""+obj));
							});
							prod.price = jitem.getString("ListPrice").split(" ")[1];
							prod.thumbnail =  "http://pxy.hawkaio.com/" + "https://images.lvrcdn.com/BigRetina"+ jitem.getString("Image");
							prod.url = "https://www.luisaviaroma.com/" + prod.PID;
							prod.name = jitem.getString("Description");
							notify(Stage.PRODUCT, prod, LVR.this.usefulLinks());
							return true;
						});

						
						
						
						//https://images.lvrcdn.com/BigRetina
						
					});
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	@Override
	public LinkedHashMap<String, String> usefulLinks() {
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		map.put("Login", "https://www.luisaviaroma.com/myarea/login#signin");
		map.put("Cart", "https://www.luisaviaroma.com/myarea/myCart.aspx");
		map.put("Checkout", "https://www.luisaviaroma.com/myarea/myCart.aspx?#checkout");
		return map;
	}
	
	
	@Override
	public String getWebsite() { 
		return "https://www.luisaviaroma.com";
	}

	@Override
	public String getWebhook() {
		return "https://discord.com/api/webhooks/984764138729398304/DsB2jTSrogcQ5VqF0FEpo_uK94ow_nymLeeYYK3H3dhgnhHY7C-1QM5C9HLbrGAsOX4Q?wait=true";
	}
}
