package com.PingsLab.Utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.URL;
import java.util.Map;

public class WebUtils {
	
	public static Pair<Integer, String> readPage(String url, Method method, String body, Map<String, String> headers) throws Exception {
		Pair<Integer, String> kv = new Pair<Integer, String>();
		DefaultConsumer dc = new DefaultConsumer();
		kv.setKey(readPage(url, new Proxy(Type.HTTP, new InetSocketAddress("127.0.0.1", 8080)), method, body, headers, dc));
		kv.setValue(dc.getOutput());
		return kv;
	}
	
	public static <T> int readPage(String url, Proxy proxy, Method method, String body, Map<String, String> headers, Consumer<T> consumer) throws Exception {
		URL _url = new URL(url);
		HttpURLConnection connection =  (HttpURLConnection) (proxy == null ? _url.openConnection() : _url.openConnection(proxy)); //Apro la connessione in base alla presenza o meno del proxy
		connection.setRequestMethod(method.name()); //Imposto il metodo
		if(headers != null)
			headers.forEach((k,v) -> connection.addRequestProperty(k, v)); //Imposto gli headers passati per parametro
		if(body != null) { //Mando i dati
			connection.setDoOutput(true);
			new DataOutputStream(connection.getOutputStream()).writeBytes(body); //Mando i dati
		}

		connection.connect();
		if(consumer != null) {
			T initData = consumer.init();
			boolean hasErrors = connection.getErrorStream() != null;
			BufferedReader br = new BufferedReader(new InputStreamReader(hasErrors ? connection.getErrorStream() : connection.getInputStream()));
			String line = null;
			/** Per questioni di velocità faccio un if esterno dal ciclo **/
			if(hasErrors) {
				while((line = br.readLine()) != null) {
					consumer.onError(initData, line);
				}
			}else {
				while((line = br.readLine()) != null) { 
					consumer.onProcess(initData, line);
				}
			}
		}
		int code = connection.getResponseCode();
		connection.disconnect();
		return code;
	}
	
	
	public interface Consumer<T>{
		T init();
		//Riga per riga
		void onError(T data, String line);
		void onProcess(T data, String line);
	}
	
	public enum Method {
		GET,
		POST,
		HEAD,
		PUT
		//E così via tutti quelli che potrebbero essere utili
	}
	
	public static class DefaultConsumer implements Consumer<StringBuilder>{
		StringBuilder output;
		@Override
		public StringBuilder init() {
			output = new StringBuilder();
			return output;
		}
		@Override
		public void onError(StringBuilder data, String line) {
			data.append(line);
		}
		@Override
		public void onProcess(StringBuilder data, String line) {
			data.append(line);
		}
		
		public String getOutput() {
			return output.toString();
		}
	}
}
