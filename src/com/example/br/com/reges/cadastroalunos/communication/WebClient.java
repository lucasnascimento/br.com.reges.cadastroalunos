package com.example.br.com.reges.cadastroalunos.communication;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class WebClient {
	
	private String url;
	
	public WebClient(String url){
		this.url = url;
	}
	
	public String recuperaNovosAlunos(){
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost post = new HttpPost(url);
			HttpResponse response = httpClient.execute(post);
			String jsonDeResposta = EntityUtils.toString(response.getEntity());
			return jsonDeResposta;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
	

}
