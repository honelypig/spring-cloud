package com.zda.consumer.controller;


import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.context.support.StaticApplicationContext;

public class ExcuteFallBack {
	public static void main(String[] args) throws Exception {
		final CloseableHttpClient httpclient = HttpClients.createDefault();
		final String url = "http://localhost:8083/TOhystrix";
		
		for(int i = 0; i < 6; i++) {
			Thread t = new Thread() {
				@Override
				public void run() {
					try {
						HttpGet httpget = new HttpGet(url);
						HttpResponse response = httpclient.execute(httpget);
						System.out.println(this.getName()+"==>"+EntityUtils.toString(response.getEntity()));
					} catch (Exception e ) {
						e.printStackTrace();
					}
				}				
			};
			t.start();
		}
		Thread.sleep(15000);
	}
}
