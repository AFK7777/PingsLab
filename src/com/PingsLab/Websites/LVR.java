package com.PingsLab.Websites;

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
			
//			
//			  "Pagination":{
//			      "TotalePages":4,
//			      "TotaleRecords":380,
//			      "CurrentPage":3,
			
			//&Seasons=76I
			
			
			
			//https://www.luisaviaroma.com/it-it/shop/uomo/scarpe/sneakers?lvrid=_gm_i4&FilterDes=210,247,140,4R8,P3B,JRD,KAN,DNV&SortType=NewIn
			//https://www.luisaviaroma.com/it-it/shop/uomo/scarpe/sneakers?lvrid=_gm_i4_c97&FilterDes=210,247,140,4R8&PriceFrom=&PriceTo=&SortType=NewIn&ajax=true
			//							   it-it/shop/uomo/scarpe/sneakers?lvrid=_gm_i4_c97&FilterDes=210,247,140,4R8&Page=1&SortType=NewIn&ajax=true
			
			//https://www.luisaviaroma.com/it-it/shop/d?lvrid=_gm_i4&Available=true&FilterDes=210,247,140,4R8,P3B,JRD,KAN,DNV&SortType=NewIn&aka_re=1&Seasons=75I
			
			
			//_abck
			
			
			
			///en-it/shop/uomo/stocazz?lvrid=_gm_i4&FilterDes=210,247,140,4R8,P3B,JRD,KAN,DNV&Seasons=73I,74I&Page=1&SortType=NewIn&ajax=true
			Pair<Integer, String> content = WebUtils.readPage(getWebsite() + "/it-it/shop/uomo/scarpe?lvrid=_gm_i4&FilterDes=210,247,140,4R8,P3B,JRD,KAN,DNV&Page=3&SortType=NewIn&ajax=true", Method.GET, null, Maps.asMap(
					"referer","https://www.luisaviaroma.com/",
					"user-agent" , "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.5005.62 Safari/537.36",
					"cookie","ak_bmsc=59EC0024973DB80E41E220DCD7BF072A~000000000000000000000000000000~YAAQNLVlXxgy60+BAQAAyUHYXRASSHeBAMNvk50FRXmJzdMSl8zN5GginZhgDEp4Q48MV3RTqvROAn3DxTa+TMPwp89/7QjjzdsR2uvA3S40OYC9K0xzru5Py2jhL5ArzVa8jZAQ3dxks4azIrfuSg45prxfme1ueuqSfrVNJjF1BUx2IjzTtj5q+RBSmm54WyuLFrpiGMc1JmX3aTp4fbheqbmaFhcSXekGhFCv6P9G93I5px40AvTdr8/ca6tbE1T8QFplGt1tqDJ+WgnzYumc4oqMrlTspYL7kS5S/svacOC9Hf8brciAMiJY8Xg8Xk6GD+SnlE5y39jfYMxujYbrBmE8O1mZAWwuwGP0AhTWKyZqR1P2ZAeWO1agyUqPvRJDvaNG/Y0AFcNaveEyOn+Kz3wtkDZzUmWUt9oEAi/JQtDXD4e/u2FGpulynWha22rDkmlKGU46s530Ze5NMvqyjDO+KsvmXJRU1VMaTKxG4KfXItiChOLAxk+Ls1D1Fw==;") );
			
			System.out.println(content.getKey());
			System.out.println(content.getValue());
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
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
