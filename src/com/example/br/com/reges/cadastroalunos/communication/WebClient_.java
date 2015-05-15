package com.example.br.com.reges.cadastroalunos.communication;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class WebClient_ {
	
	private String url;
	
	public WebClient_ (String url){
		this.url = url;
	}
	
	public String recuperarNovosAlunos(){
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost post = new HttpPost(url);
			HttpResponse response = httpClient.execute(post);
			String jsonDeReposta = EntityUtils.toString(response.getEntity());
			return jsonDeReposta;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

}
