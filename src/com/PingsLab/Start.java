package com.PingsLab;

import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import com.PingsLab.Discord.Messanger;

public class Start {
	
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
		Messanger msg = new Messanger();
		msg.sendMessage(null, 3123445);
		//3123445
	}
	
}