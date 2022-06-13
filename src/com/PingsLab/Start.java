package com.PingsLab;

import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import com.PingsLab.Websites.LVR;

public class Start {
	//GET /it-it/shop/uomo?lvrid=_gm&Page=3&SortType=NewIn&ajax=true
	//https://www.luisaviaroma.com/it-it/shop/uomo/scarpe/sneakers?lvrid=_gm_i4_c97&FilterDes=210%2C247%2C140%2C4R8&PriceTo=399&SortType=NewIn
	public static void disableSSL() throws Exception {
		TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				return null;
			}
			public void checkClientTrusted(X509Certificate[] certs, String authType) {}
			public void checkServerTrusted(X509Certificate[] certs, String authType) {}
		} };
		SSLContext sc = SSLContext.getInstance("SSL");
		sc.init(null, trustAllCerts, new java.security.SecureRandom());
		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory()); 
		HostnameVerifier allHostsValid = new HostnameVerifier() {
			public boolean verify(String hostname, SSLSession session) {
				return true;
			}
		}; 
		HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
	}

	
	public static void main(String args[]) {
		try {
			disableSSL();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		LVR lvr = new LVR();
		lvr.scanBase();
		
		//Messanger msg = new Messanger();
		//msg.sendMessage(null, 3123445);
		//3123445
	}
	
}