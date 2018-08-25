package com.zda.client;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class BusController {

	public static void main(String[] args) throws Exception {
		//刷新rabbitmq
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost post = new HttpPost("http://localhost:10000/bus/refresh");
		//通过调用bus的某些端口，就会调用特定的方法，这边发送请求到localhost：10000端口后
		//总线会将这个请求发送到各个有配置接收rabbitmq的的项目
		//例如本例中  refresh短端点，会将这个请求转发到ALL-clientandprovider和ALL-zuul两个项目中，然后这两个项目就会刷新配置文件
		HttpResponse response = client.execute(post);
		System.out.println(EntityUtils.toString(response.getEntity()));
	}

}
